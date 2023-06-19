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
package org.ehrbase.openehr.sdk.serialisation.dto;

import java.lang.reflect.Field;
import java.util.Map;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlPath;

class DtoWithMatchingFields {

    private final Object dto;
    private final Map<AqlPath, Field> fieldByPath;

    DtoWithMatchingFields(Object dto, Map<AqlPath, Field> fieldByPath) {
        this.dto = dto;
        this.fieldByPath = fieldByPath;
    }

    Object getDto() {
        return dto;
    }

    Map<AqlPath, Field> getFieldByPath() {
        return fieldByPath;
    }
}
