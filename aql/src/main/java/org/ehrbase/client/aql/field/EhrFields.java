/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.client.aql.field;

import java.util.UUID;
import org.ehrbase.client.aql.containment.Containment;

public class EhrFields {

    public static final Containment EHR_CONTAINMENT = new Containment("EHR") {
        @Override
        public String getVariableName() {
            return "e";
        }
    };

    private EhrFields() {}

    public static SelectAqlField<UUID> EHR_ID() {
        AqlFieldImp<UUID> ehrId = new AqlFieldImp<>(null, "/ehr_id/value", "ehrId", UUID.class, EHR_CONTAINMENT);
        return ehrId;
    }
}
