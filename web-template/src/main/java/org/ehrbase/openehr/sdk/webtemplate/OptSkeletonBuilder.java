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
package org.ehrbase.openehr.sdk.webtemplate;

import com.nedap.archie.rm.RMObject;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser;
import org.ehrbase.openehr.sdk.webtemplate.webtemplateskeletonbuilder.WebTemplateSkeletonBuilder;
import org.openehr.schemas.v1.*;

/**
 *  @deprecated replaced by {@link WebTemplateSkeletonBuilder}
 */
@Deprecated
public class OptSkeletonBuilder {

    /**
     * Generate empty Rm from template
     *
     * @param opt
     * @return
     */
    public RMObject generate(OPERATIONALTEMPLATE opt) {
        WebTemplate webTemplate = new OPTParser(opt).parse();
        return WebTemplateSkeletonBuilder.build(webTemplate, true);
    }
}
