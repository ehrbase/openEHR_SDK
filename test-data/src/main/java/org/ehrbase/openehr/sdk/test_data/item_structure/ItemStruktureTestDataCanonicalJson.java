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
package org.ehrbase.openehr.sdk.test_data.item_structure;

import java.io.InputStream;

public enum ItemStruktureTestDataCanonicalJson {
    SIMPLE_EHR_OTHER_Details("Simple EHR other Details", "ehr_other_details.json");

    private final String filename;
    private final String description;

    ItemStruktureTestDataCanonicalJson(String description, String filename) {
        this.filename = filename;
        this.description = description;
    }

    public InputStream getStream() {
        return getClass().getResourceAsStream("/item_structure/canonical_json/" + filename);
    }
}
