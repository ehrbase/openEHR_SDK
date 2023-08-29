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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import java.time.DateTimeException;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Setting;
import org.ehrbase.openehr.sdk.serialisation.exception.UnmarshalException;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;
import org.ehrbase.openehr.sdk.webtemplate.webtemplateskeletonbuilder.WebTemplateSkeletonBuilder;

public class FlatJsonUnmarshaller {

    private static final ObjectMapper OBJECT_MAPPER = ArchieObjectMapperProvider.getObjectMapper();

    /**
     * Unmarshal flat Json to Composition
     *
     * @param flat the flat Json
     * @param introspect the introspect belonging to the template
     * @return
     */
    public Composition unmarshal(String flat, WebTemplate introspect) {

        Set<String> consumedPath;

        Map<String, String> currentValues;
        consumedPath = new HashSet<>();

        try {

            currentValues = new HashMap<>();

            for (Iterator<Map.Entry<String, JsonNode>> it =
                            OBJECT_MAPPER.readTree(flat).fields();
                    it.hasNext(); ) {
                Map.Entry<String, JsonNode> e = it.next();
                currentValues.put(e.getKey(), e.getValue().toString());
            }

            Composition generate = WebTemplateSkeletonBuilder.build(introspect, false);

            StdToCompositionWalker walker = new StdToCompositionWalker();
            DefaultValues defaultValues = new DefaultValues(currentValues);
            // put default for the defaults
            if (!defaultValues.containsDefaultValue(DefaultValuePath.LANGUAGE)) {
                defaultValues.addDefaultValue(
                        DefaultValuePath.LANGUAGE,
                        FlatHelper.findEnumValueOrThrow(introspect.getDefaultLanguage(), Language.class));
            }
            if (!defaultValues.containsDefaultValue(DefaultValuePath.TIME)) {
                defaultValues.addDefaultValue(DefaultValuePath.TIME, OffsetDateTime.now());
            }
            if (!defaultValues.containsDefaultValue(DefaultValuePath.SETTING)) {
                defaultValues.addDefaultValue(DefaultValuePath.SETTING, Setting.OTHER_CARE);
            }

            String templateId = generate.getArchetypeDetails().getTemplateId().getValue();
            walker.walk(
                    generate,
                    currentValues.entrySet().stream()
                            .collect(Collectors.toMap(e1 -> new FlatPathDto(e1.getKey()), Map.Entry::getValue)),
                    introspect,
                    defaultValues,
                    templateId);
            consumedPath = walker.getConsumedPaths();
            if (!CollectionUtils.isEmpty(getUnconsumed(consumedPath, currentValues))) {

                throw new UnmarshalException(
                        String.format("Could not consume Parts %s", getUnconsumed(consumedPath, currentValues)));
            }

            return generate;
        } catch (JsonProcessingException e) {
            throw new UnmarshalException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            if (e.getCause() instanceof DateTimeException) {
                throw new UnmarshalException(e.getMessage(), e);
            } else {
                throw e;
            }
        }
    }

    private static Set<String> getUnconsumed(Set<String> consumedPath, Map<String, String> currentValues) {
        if (currentValues != null && consumedPath != null) {
            HashSet<String> set = new HashSet<>(currentValues.keySet());
            set.removeAll(consumedPath);
            return set.stream().filter(p -> !p.startsWith("ctx")).collect(Collectors.toSet());
        } else {
            return Collections.emptySet();
        }
    }
}
