/*
 * Copyright (c) 2025 vitasystems GmbH and Hannover Medical School.
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.DvText;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.ehrbase.openehr.sdk.serialisation.MarshalOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class RmObjectJsonSerializerTest {

    private final JsonGenerator mockGen = mock();
    private final SerializerProvider mockSerializers = mock();

    @BeforeEach
    void setUp() {
        Mockito.reset(mockGen, mockSerializers);
    }

    private String serialize(RmObjectJsonSerializer serializer, RMObject rmObject) throws IOException {

        ArgumentCaptor<String> writeRawValueCapture = ArgumentCaptor.forClass(String.class);

        serializer.serialize(rmObject, mockGen, mockSerializers);

        verify(mockGen, times(1)).writeRawValue(writeRawValueCapture.capture());
        return writeRawValueCapture.getValue();
    }

    @Test
    void serializeDefaultPrettyPrint() throws IOException {

        Composition composition = new Composition();
        composition.setName(new DvText("default pretty print test"));

        Assertions.assertThat(serialize(new RmObjectJsonSerializer(), composition))
                .isEqualTo(
                        """
                {
                  "_type" : "COMPOSITION",
                  "name" : {
                    "_type" : "DV_TEXT",
                    "value" : "default pretty print test"
                  }
                }""");
    }

    @Test
    void serializeExplicitPrettyPrint() throws IOException {

        Composition composition = new Composition();
        composition.setName(new DvText("explicit pretty print test"));

        Assertions.assertThat(serialize(new RmObjectJsonSerializer(MarshalOption.PRETTY_PRINT), composition))
                .isEqualTo(
                        """
                {
                  "_type" : "COMPOSITION",
                  "name" : {
                    "_type" : "DV_TEXT",
                    "value" : "explicit pretty print test"
                  }
                }""");
    }

    @Test
    void serializeWithOptionsNoPrettyPrint() throws IOException {

        Composition composition = new Composition();
        composition.setName(new DvText("no pretty print test"));

        Assertions.assertThat(serialize(new RmObjectJsonSerializer(MarshalOption.DEFAULT_OPTIONS), composition))
                .isEqualTo(
                        """
                {"_type":"COMPOSITION","name":{"_type":"DV_TEXT","value":"no pretty print test"}}""");
    }
}
