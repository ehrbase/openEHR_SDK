/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.dbencoding.wrappers.json.writer.translator_db2raw;

import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import org.ehrbase.serialisation.dbencoding.wrappers.json.I_DvTypeAdapter;
import org.ehrbase.util.rmconstants.RmConstants;

public class DvTextNameValue implements I_NameValueHandler {

    private final JsonWriter writer;
    private final String value;

    DvTextNameValue(JsonWriter writer, String value) {
        this.writer = writer;
        this.value = value;
    }

    DvTextNameValue(JsonWriter writer, LinkedTreeMap value) {
        this.writer = writer;
        this.value = value.get("value").toString();
    }

    /**
     * Encode a name value into the DB json structure
     * <code>
     *     "name": {
     *         "value":...
     *     }
     * </code>
     * @throws IOException
     */
    @Override
    public void write() throws IOException {
        if (value == null || value.isEmpty()) return;
        writer.name(I_DvTypeAdapter.NAME);
        writer.beginObject();
        writer.name(I_DvTypeAdapter.VALUE).value(value);
        writer.name(I_DvTypeAdapter.AT_TYPE).value(RmConstants.DV_TEXT);
        writer.endObject();
    }
}
