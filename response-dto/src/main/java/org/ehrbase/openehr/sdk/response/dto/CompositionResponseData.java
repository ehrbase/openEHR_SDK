/*
 * Copyright (c) 2019 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.response.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.StructuredString;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.StructuredStringFormat;

/**
 * Response for composition endpoints. Most endpoints should use this class, while for some endpoints the `return=minimal` header option
 * indicates to build the response without this class.
 */
@JacksonXmlRootElement
public class CompositionResponseData extends StructuredString {

    public CompositionResponseData(String value, StructuredStringFormat format) {
        super(value, format);
    }
}
