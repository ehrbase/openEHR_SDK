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

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.client.classgenerator.EhrbaseBloodPressureSimpleDeV0;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FlattenerTest {


    @Test
    public void testFlatten() {
        Flattener cut = new Flattener();
        BloodpressureListDe bloodpressureListDe = buildExampleBloodpressureListDe();

        RMObject rmObject = new Unflattener(new TestDataTemplateProvider()).unflatten(bloodpressureListDe);

        BloodpressureListDe expected = cut.flatten((Locatable) rmObject, BloodpressureListDe.class);

        assertThat(expected).isNotNull();
        assertThat(expected.getStartTime()).isEqualTo(bloodpressureListDe.getStartTime());
        assertThat(expected.getBloodpressures()).extracting(BloodpressureListDe.Bloodpressure::getSystolischValue).containsExactlyInAnyOrder(12d, 22d);

    }


    @Test
    public void testFlattenEhrbaseBloodPressureSimpleDeV0() {
        Flattener cut = new Flattener();
        EhrbaseBloodPressureSimpleDeV0 bloodPressureSimpleDeV0 = buildEhrbaseBloodPressureSimpleDeV0();
        RMObject rmObject = new Unflattener(new TestDataTemplateProvider()).unflatten(bloodPressureSimpleDeV0);

        EhrbaseBloodPressureSimpleDeV0 expected = cut.flatten((Locatable) rmObject, EhrbaseBloodPressureSimpleDeV0.class);

        assertThat(expected).isNotNull();
        assertThat(expected.getBloodPressureTrainingSample()).size().isEqualTo(1);
        assertThat(expected.getBloodPressureTrainingSample().get(0).getSystolicMagnitude()).isEqualTo(22d);
        assertThat(expected.getBloodPressureTrainingSample().get(0).getSystolicUnits()).isEqualTo("mm[Hg]");
        assertThat(expected.getBloodPressureTrainingSample().get(0).getKorotkoffSounds()).isEqualTo(EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample.KorotkoffSounds.FIFTHSOUND);

    }


    public static BloodpressureListDe buildExampleBloodpressureListDe() {
        BloodpressureListDe dto = new BloodpressureListDe();
        OffsetDateTime startTime = OffsetDateTime.of(2019, 9, 10, 12, 0, 0, 0, ZoneOffset.ofHours(2));
        dto.setStartTime(startTime);
        List<BloodpressureListDe.Bloodpressure> bloodpressureList = new ArrayList<>();

        BloodpressureListDe.Bloodpressure bloodpressure1 = new BloodpressureListDe.Bloodpressure();
        bloodpressure1.setSystolischValue(12d);
        bloodpressureList.add(bloodpressure1);

        BloodpressureListDe.Bloodpressure bloodpressure2 = new BloodpressureListDe.Bloodpressure();
        bloodpressure2.setSystolischValue(22d);
        bloodpressureList.add(bloodpressure2);

        dto.setBloodpressures(bloodpressureList);
        return dto;
    }

    public static EhrbaseBloodPressureSimpleDeV0 buildEhrbaseBloodPressureSimpleDeV0() {
        EhrbaseBloodPressureSimpleDeV0 bloodPressureSimpleDeV0 = new EhrbaseBloodPressureSimpleDeV0();
        bloodPressureSimpleDeV0.setStartTimeValue(LocalDateTime.now());
        bloodPressureSimpleDeV0.setEndTimeValue(LocalDateTime.now());
        bloodPressureSimpleDeV0.setBloodPressureTrainingSample(new ArrayList<>());
        bloodPressureSimpleDeV0.setLanguage(new CodePhrase(new TerminologyId("ISO_639-1"), "de"));
        bloodPressureSimpleDeV0.setTerritory(new CodePhrase(new TerminologyId("ISO_3166-1"), "UY"));
        bloodPressureSimpleDeV0.setSettingDefiningcode(new CodePhrase(new TerminologyId("openehr"), "229"));

        EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample bloodPressureTrainingSample = new EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample();
        bloodPressureTrainingSample.setSystolicMagnitude(22d);
        bloodPressureTrainingSample.setSystolicUnits("mm[Hg]");
        bloodPressureTrainingSample.setDiastolicMagnitude(22d);
        bloodPressureTrainingSample.setDiastolicUnits("mm[Hg]");
        bloodPressureTrainingSample.setMeanArterialPressureMagnitude(22d);
        bloodPressureTrainingSample.setMeanArterialPressureUnits("mm[Hg]");
        bloodPressureTrainingSample.setPulsePressureMagnitude(22d);
        bloodPressureTrainingSample.setPulsePressureUnits("mm[Hg]");
        bloodPressureTrainingSample.setKorotkoffSounds(EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample.KorotkoffSounds.FIFTHSOUND);
        bloodPressureTrainingSample.setCuffSize(EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample.CuffSize.ADULT);
        bloodPressureTrainingSample.setLocationOfMeasurement(EhrbaseBloodPressureSimpleDeV0.BloodPressureTrainingSample.LocationOfMeasurement.FINGER);
        bloodPressureSimpleDeV0.getBloodPressureTrainingSample().add(bloodPressureTrainingSample);
        return bloodPressureSimpleDeV0;
    }
}