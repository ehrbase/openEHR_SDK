/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.response.openehr;

public class ErrorBodyPayload {
    String errorType;
    String errorDescription;

    public ErrorBodyPayload(String errorType, String errorDescription) {
        this.errorType = errorType;
        this.errorDescription = errorDescription;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        stringBuilder.append("\"error_type\":");
        stringBuilder.append("\"");
        stringBuilder.append(errorType);
        stringBuilder.append("\"");
        stringBuilder.append(",\n");
        stringBuilder.append("\"error_description\":");
        stringBuilder.append("\"");
        stringBuilder.append(errorDescription.replace("\"", ""));
        stringBuilder.append("\"");
        stringBuilder.append("\n");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
