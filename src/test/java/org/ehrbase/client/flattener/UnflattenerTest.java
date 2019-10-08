/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.flattener;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.client.classgenerator.EhrbaseBloodPressureSimpleDeV0;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.client.flattener.FlattenerTest.buildEhrbaseBloodPressureSimpleDeV0;
import static org.ehrbase.client.flattener.FlattenerTest.buildExampleBloodpressureListDe;

public class UnflattenerTest {

    @Test
    public void testUnflatten() {
        Unflattener cut = new Unflattener(new TestDataTemplateProvider());

        BloodpressureListDe dto = buildExampleBloodpressureListDe();

        Locatable rmObject = (Locatable) cut.unflatten(dto);

        assertThat(rmObject).isNotNull();
        assertThat(rmObject.itemAtPath("/context/start_time/value")).isEqualTo(dto.getStartTime());

        List<Object> observationList = rmObject.itemsAtPath("/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]");
        assertThat(observationList).size().isEqualTo(2);

        List<Double> systolischValues = observationList.stream()
                .map(o -> (Observation) o)
                .map(o -> (Element) o.itemAtPath("/data[at0001]/events[at0002]/data[at0003]/items[at0004]"))
                .map(e -> (DvQuantity) e.getValue())
                .map(DvQuantity::getMagnitude)
                .collect(Collectors.toList());

        assertThat(systolischValues).containsExactlyInAnyOrder(12d, 22d);
    }

    @Test
    public void testUnflattenEhrbaseBloodPressureSimpleDeV0() {
        Unflattener cut = new Unflattener(new TestDataTemplateProvider());

        EhrbaseBloodPressureSimpleDeV0 dto = buildEhrbaseBloodPressureSimpleDeV0();

        Composition rmObject = (Composition) cut.unflatten(dto);

        assertThat(rmObject).isNotNull();
        assertThat(rmObject.getArchetypeDetails().getTemplateId().getValue()).isEqualTo("ehrbase_blood_pressure_simple.de.v0");
        assertThat(rmObject.itemAtPath("/context/start_time/value")).isEqualTo(dto.getStartTimeValue());
        List<Object> observationList = rmObject.itemsAtPath("/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]");
        assertThat(observationList).size().isEqualTo(1);
        Observation observation = (Observation) observationList.get(0);
        DvCodedText expected = new DvCodedText("Fifth sound", new CodePhrase(new TerminologyId("local"), "at1012"));

        assertThat(observation.itemAtPath("/protocol[at0011]/items[at1010]/value")).isEqualTo(expected);

    }


}