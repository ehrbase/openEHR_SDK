/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation.terminology;

import com.nedap.archie.rm.datatypes.CodePhrase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record TerminologyParam(
        String serviceApi, ResouceType resouceType, CodePhrase codePhrase, String operation, String parameter) {

    public enum ResouceType {
        CODE_SYSTEM,
        VALUE_SET
    }

    // FIXME CDR-2273 based on URL parser
    static final Pattern PATTERN = Pattern.compile(
            "(?<api>//[^/]*)/(?<type>CodeSystem|ValueSet)/?(?<op>\\$expand|\\$validate-code)?(?:\\?(?<param>.*))?");

    /**
     * @param url          fhir url of format:
     *                     </br>
     *                     {@code //<some char>/<"CodeSystem" or "ValueSet">?<req-parameter>}
     *                     </br>
     *                     {@code //<some char>/<"CodeSystem" or "ValueSet">/<"$expand" or "$validate-code">?<req-parameter>}
     * @param definingCode
     */
    public static TerminologyParam ofFhir(String url, final CodePhrase definingCode) {
        if (url == null) {
            return null;
        }

        Matcher matcher = PATTERN.matcher(url);
        if (!matcher.matches()) {
            return null;
        }

        ResouceType resouceType =
                "ValueSet".equals(matcher.group("type")) ? ResouceType.VALUE_SET : ResouceType.CODE_SYSTEM;
        return new TerminologyParam(
                matcher.group("api"), resouceType, definingCode, matcher.group("op"), matcher.group("param"));
    }
}
