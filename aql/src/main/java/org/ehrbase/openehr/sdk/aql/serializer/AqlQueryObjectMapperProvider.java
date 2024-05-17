/*
 * Copyright (c) 2023 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.aql.serializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPath;

/**
 * @author Stefan Spiska
 */
public class AqlQueryObjectMapperProvider {

    private AqlQueryObjectMapperProvider() {
        // NOP
    }

    private static ObjectMapper objectMapper = init();

    public static ObjectMapper getObjectMapper() {

        return objectMapper;
    }

    private static ObjectMapper init() {

        ObjectMapper aqlobjectMapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(AqlObjectPath.class, new ObjectPathSerializer());
        simpleModule.addDeserializer(AqlObjectPath.class, new ObjectPathDeserializer());

        aqlobjectMapper.registerModule(simpleModule);
        aqlobjectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return aqlobjectMapper;
    }
}
