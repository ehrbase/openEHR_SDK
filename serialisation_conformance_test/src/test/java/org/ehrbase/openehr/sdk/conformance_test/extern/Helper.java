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
package org.ehrbase.openehr.sdk.conformance_test.extern;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatJasonProvider;
import org.openehr.schemas.v1.TemplateDocument;

public class Helper {

    private Helper() {}

    public static RMDataFormat getFlatJson(String template, FlatFormat flatFormat) throws XmlException, IOException {
        TemplateDocument templateDocument =
                TemplateDocument.Factory.parse(IOUtils.toInputStream(template, StandardCharsets.UTF_8));

        RMDataFormat flatJson = new FlatJasonProvider(t -> Optional.ofNullable(templateDocument.getTemplate()))
                .buildFlatJson(
                        flatFormat,
                        templateDocument.getTemplate().getTemplateId().getValue());
        return flatJson;
    }
}
