/*
 * Copyright (c) 2020 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.io.IOException;

public class VersionUidDeSerializer extends StdDeserializer<ObjectVersionId> {
    public VersionUidDeSerializer() {
        super(ObjectVersionId.class);
    }

    @Override
    public ObjectVersionId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        return new ObjectVersionId(jsonParser.getValueAsString());
    }
}
