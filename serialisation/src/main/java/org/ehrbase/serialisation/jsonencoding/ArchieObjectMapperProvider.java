/*
 * Modifications copyright (C) 2019 Vitasystems GmbH and Hannover Medical School.

 * This file is part of Project EHRbase

 * Copyright (c) 2015 Nedap Healthcare
 * This file is part of Project Archie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.serialisation.jsonencoding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.json.JacksonUtil;

public final class ArchieObjectMapperProvider {

    private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

    private ArchieObjectMapperProvider() {
        // NOOP
    }

    /**
     * Get an object mapper that works with Archie RM and AOM objects. It will be cached in a static
     * variable for performance reasons
     *
     * @return the object mapper
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
