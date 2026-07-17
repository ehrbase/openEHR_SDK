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
package org.ehrbase.openehr.sdk.conformance_test.templateprovider;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.templateprovider.TemplateProvider;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

public class TestDataTemplateProvider implements TemplateProvider {

    private static final Map<String, Optional<OPERATIONALTEMPLATE>> OPT_CACHE = new HashMap<>();
    private static final Map<String, Optional<WebTemplate>> WEB_TEMPLATE_CACHE = new HashMap<>();

    @Override
    public Optional<OPERATIONALTEMPLATE> find(String templateId) {
        return OPT_CACHE.computeIfAbsent(
                templateId,
                tid -> Optional.of(tid)
                        .map(OperationalTemplateTestData::findByTemplateId)
                        .map(OperationalTemplateTestData::getStream)
                        .map(s -> {
                            try {
                                return TemplateDocument.Factory.parse(s);
                            } catch (XmlException | IOException e) {
                                throw new RuntimeException(e.getMessage(), e);
                            }
                        })
                        .map(TemplateDocument::getTemplate));
    }

    @Override
    public Optional<WebTemplate> buildIntrospect(String templateId) {
        return WEB_TEMPLATE_CACHE.computeIfAbsent(templateId, TemplateProvider.super::buildIntrospect);
    }

    public List<String> listTemplateIds() {
        return Arrays.stream(OperationalTemplateTestData.values())
                .map(OperationalTemplateTestData::getTemplateId)
                .toList();
    }
}
