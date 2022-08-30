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
package org.ehrbase.serialisation.flatencoding.std.marshal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import java.util.LinkedHashMap;
import java.util.Map;
import org.ehrbase.serialisation.exception.MarshalException;
import org.ehrbase.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.webtemplate.model.WebTemplate;

public class FlatJsonMarshaller {

    private static final ObjectMapper OBJECT_MAPPER = ArchieObjectMapperProvider.getObjectMapper();

    public FlatJsonMarshaller() {}

    /**
     * Marshal the composition to flat json
     *
     * @param composition
     * @return
     */
    public String toFlatJson(Composition composition, WebTemplate webTemplate) {

        Map<String, Object> result = new LinkedHashMap<>();

        String templateId = webTemplate.getTemplateId();
        new StdFromCompositionWalker().walk(composition, result, webTemplate, null, templateId);

        try {
            return OBJECT_MAPPER.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new MarshalException(e.getMessage(), e);
        }
    }
}
