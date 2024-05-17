/*
 * Copyright (c) 2020 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.serialisation.flatencoding;

import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.util.exception.SdkException;
import org.ehrbase.openehr.sdk.webtemplate.templateprovider.TemplateProvider;

/**
 * Used to build the {@link RMDataFormat} for flat jason for a template.
 */
public class FlatJasonProvider {

    private final TemplateProvider templateProvider;

    public FlatJasonProvider(TemplateProvider templateProvider) {
        this.templateProvider = templateProvider;
    }

    /**
     * Builds a {@link FlatJson} for the template with {@code templateId} and {@link FlatFormat}
     * {@code format}
     *
     * @param format
     * @param templateId
     * @return
     */
    public RMDataFormat buildFlatJson(FlatFormat format, String templateId) {

        switch (format) {
            case SIM_SDT:
                return new FlatJson(this, templateId);
            case STRUCTURED:
                return new StructuredJson(this, templateId);
            default:
                throw new SdkException(String.format("Format %s not supported", format));
        }
    }

    TemplateProvider getTemplateProvider() {
        return templateProvider;
    }
}
