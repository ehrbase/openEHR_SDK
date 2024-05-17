/*
 * Copyright (c) 2022 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.geccoserologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-05-19T16:20:30.158762100+02:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public interface BefundJedesEreignisChoice {
    LabortestBezeichnungDefiningCode getLabortestBezeichnungDefiningCode();

    void setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode);

    ProAnalytTestmethodeChoice getTestmethode();

    void setTestmethode(ProAnalytTestmethodeChoice testmethode);

    String getErgebnisStatusValue();

    void setErgebnisStatusValue(String ergebnisStatusValue);

    List<Cluster> getProbendetail();

    void setProbendetail(List<Cluster> probendetail);

    List<Cluster> getAnalyseergebnisDetails();

    void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails);

    ProAnalytQuantitativesErgebnisChoice getQuantitativesErgebnis();

    void setQuantitativesErgebnis(ProAnalytQuantitativesErgebnisChoice quantitativesErgebnis);

    VirusnachweistestDefiningCode getVirusnachweistestDefiningCode();

    void setVirusnachweistestDefiningCode(VirusnachweistestDefiningCode virusnachweistestDefiningCode);

    List<Cluster> getMultimediaDarstellung();

    void setMultimediaDarstellung(List<Cluster> multimediaDarstellung);

    NachweisDefiningCode getNachweisDefiningCode();

    void setNachweisDefiningCode(NachweisDefiningCode nachweisDefiningCode);

    List<Cluster> getStrukturierteTestdiagnostik();

    void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik);

    TemporalAccessor getTimeValue();

    void setTimeValue(TemporalAccessor timeValue);

    List<Cluster> getStrukturierteErfassungDerStorfaktoren();

    void setStrukturierteErfassungDerStorfaktoren(List<Cluster> strukturierteErfassungDerStorfaktoren);

    FeederAudit getFeederAudit();

    void setFeederAudit(FeederAudit feederAudit);
}
