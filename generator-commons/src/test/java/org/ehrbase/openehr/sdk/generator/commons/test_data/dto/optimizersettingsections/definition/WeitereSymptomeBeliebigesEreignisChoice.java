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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingsections.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.417033200+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public interface WeitereSymptomeBeliebigesEreignisChoice {
    TemporalAccessor getTimeValue();

    void setTimeValue(TemporalAccessor timeValue);

    FeederAudit getFeederAudit();

    void setFeederAudit(FeederAudit feederAudit);

    List<WeitereSymptomeSpezifischesSymptomAnzeichenCluster> getSpezifischesSymptomAnzeichen();

    void setSpezifischesSymptomAnzeichen(
            List<WeitereSymptomeSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen);
}
