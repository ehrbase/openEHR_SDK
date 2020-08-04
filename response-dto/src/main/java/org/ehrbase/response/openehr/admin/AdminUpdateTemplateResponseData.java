/*
 * Copyright (c) 2019 Axel Siebert (Vitasystems GmbH) and Hannover Medical School.
 *
 * This file is part of project EHRbase
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
package org.ehrbase.response.openehr.admin;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.ehrbase.response.openehr.ResponseData;

@JacksonXmlRootElement(localName = "template")
public class AdminUpdateTemplateResponseData implements ResponseData<String> {

    private String template;

    public AdminUpdateTemplateResponseData() {

    }

    public AdminUpdateTemplateResponseData(String template) {
        this.template = template;
    }

    public String get() {
        return this.template;
    }

    public void set(String template) {
        this.template = template;
    }
}
