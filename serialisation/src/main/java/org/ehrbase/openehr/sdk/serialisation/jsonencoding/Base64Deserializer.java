/*
 * Copyright (c) 2024 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.serialisation.jsonencoding;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import java.io.IOException;

/**
 * Custom deserialize that explicitly uses Base64 decoding without a linefeed. This ensures that the JSON, XML and FLat
 * formats are aligned.
 */
final class Base64Deserializer extends StdScalarDeserializer<byte[]> {

    private final StringDeserializer stringDeserializer = new StringDeserializer();

    Base64Deserializer() {
        super(byte[].class);
    }

    @Override
    public byte[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        String encoded = stringDeserializer.deserialize(p, ctxt);
        return Base64Variants.MIME_NO_LINEFEEDS.decode(encoded);
    }
}
