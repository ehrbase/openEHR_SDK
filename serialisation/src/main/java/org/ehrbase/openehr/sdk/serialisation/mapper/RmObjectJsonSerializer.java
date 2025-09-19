/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.serialisation.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.nedap.archie.rm.RMObject;
import java.io.IOException;
import java.util.Set;
import javax.xml.namespace.QName;
import org.ehrbase.openehr.sdk.serialisation.MarshalOption;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;

public class RmObjectJsonSerializer extends JsonSerializer<RMObject> {

    private final Set<MarshalOption> marshalOptions;

    public RmObjectJsonSerializer() {
        // use PRETTY_PRINT to have the same behavior same as versions <= 2.24.0
        this(Set.of(MarshalOption.PRETTY_PRINT));
    }

    public RmObjectJsonSerializer(MarshalOption... marshalOptions) {
        this(Set.of(marshalOptions));
    }

    public RmObjectJsonSerializer(Set<MarshalOption> marshalOptions) {
        this.marshalOptions = marshalOptions;
    }

    @Override
    public void serialize(RMObject value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (gen instanceof ToXmlGenerator) {
            final QName qName;
            if (gen.getOutputContext().getCurrentName() != null) {
                qName = new QName(null, gen.getOutputContext().getCurrentName());
            } else {
                qName = new QName(null, gen.getOutputContext().getParent().getCurrentName());
            }
            gen.writeRawValue(RMDataFormat.canonicalXML().marshalInline(value, qName));
        } else {
            gen.writeRawValue(RMDataFormat.canonicalJSON().marshalWithOptions(value, marshalOptions));
        }
    }
}
