/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.NullFlavour;

@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-06-10T14:23:37.658905+07:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0")
public interface LaborergebnisJedesEreignisChoice {
    List<Cluster> getStrukturierteTestdiagnostik();

    void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik);

    GesamtUntersuchungsstatusDefiningCode getGesamtUntersuchungsstatusDefiningCode();

    void setGesamtUntersuchungsstatusDefiningCode(
            GesamtUntersuchungsstatusDefiningCode gesamtUntersuchungsstatusDefiningCode);

    NullFlavour getInterpretationNullFlavourDefiningCode();

    void setInterpretationNullFlavourDefiningCode(NullFlavour interpretationNullFlavourDefiningCode);

    NullFlavour getGesamtUntersuchungsstatusNullFlavourDefiningCode();

    void setGesamtUntersuchungsstatusNullFlavourDefiningCode(
            NullFlavour gesamtUntersuchungsstatusNullFlavourDefiningCode);

    List<LaboranalytResultatCluster> getLaboranalytResultat();

    void setLaboranalytResultat(List<LaboranalytResultatCluster> laboranalytResultat);

    String getInterpretationValue();

    void setInterpretationValue(String interpretationValue);

    NullFlavour getAnforderungNullFlavourDefiningCode();

    void setAnforderungNullFlavourDefiningCode(NullFlavour anforderungNullFlavourDefiningCode);

    NullFlavour getKommentarNullFlavourDefiningCode();

    void setKommentarNullFlavourDefiningCode(NullFlavour kommentarNullFlavourDefiningCode);

    FeederAudit getFeederAudit();

    void setFeederAudit(FeederAudit feederAudit);

    String getAnforderungValue();

    void setAnforderungValue(String anforderungValue);

    TemporalAccessor getTimeValue();

    void setTimeValue(TemporalAccessor timeValue);

    List<ProbeCluster> getProbe();

    void setProbe(List<ProbeCluster> probe);

    List<Cluster> getMultimediaDarstellung();

    void setMultimediaDarstellung(List<Cluster> multimediaDarstellung);

    List<Cluster> getStrukturierteErfassungDerStoerfaktoren();

    void setStrukturierteErfassungDerStoerfaktoren(List<Cluster> strukturierteErfassungDerStoerfaktoren);

    String getKommentarValue();

    void setKommentarValue(String kommentarValue);
}
