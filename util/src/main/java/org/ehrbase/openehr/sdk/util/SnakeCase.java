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
package org.ehrbase.openehr.sdk.util;

/** Created by christian on 8/1/2017. */
public class SnakeCase {

    protected String aString;

    public SnakeCase(String aString) {
        this.aString = aString;
    }

    public String camelToSnake() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < aString.length(); i++) {
            if (Character.isUpperCase(aString.charAt(i))) {
                if (i > 0 && aString.charAt(i - 1) != '<') {
                    buffer.append('_');
                }
                buffer.append(Character.toLowerCase(aString.charAt(i)));
            } else {
                buffer.append(aString.charAt(i));
            }
        }
        return buffer.toString();
    }

    public String camelToUpperSnake() {
        return camelToSnake()
                .toUpperCase()
                .replace("I_S_M", "ISM")
                .replace("_I_D", "_ID")
                .replace("_E_H_R", "_EHR")
                .replace("_U_R_I", "_URI");
    }
}
