/*
 * Copyright (c) 2023 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.aql.dto.path;

public final class AqlObjectPathUtil {

    public static final AqlObjectPath ARCHETYPE_NODE_ID =
            AqlObjectPath.builder().node("archetype_node_id").build().frozen();
    public static final AqlObjectPath NAME_VALUE =
            AqlObjectPath.builder().node("name").node("value").build().frozen();
    public static final AqlObjectPath NAME_CODE_STRING = AqlObjectPath.builder()
            .node("name")
            .node("defining_code")
            .node("code_string")
            .build()
            .frozen();
    public static final AqlObjectPath NAME_TERMINOLOGY = AqlObjectPath.builder()
            .node("name")
            .node("defining_code")
            .node("terminology_id")
            .node("value")
            .build()
            .frozen();

    private AqlObjectPathUtil() {
        // NOOP
    }
}
