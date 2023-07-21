/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.parser.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import org.ehrbase.openehr.sdk.aql.dto.containment.AbstractContainmentExpression;

/**
 * @author Stefan Spiska
 */
public class AbstractContainmentExpressionSerializer extends StdSerializer<AbstractContainmentExpression> {
    protected AbstractContainmentExpressionSerializer() {
        super(AbstractContainmentExpression.class);
    }

    @Override
    public void serialize(AbstractContainmentExpression value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {

        gen.writeString(value.getIdentifier());
    }

    @Override
    public void serializeWithType(
            AbstractContainmentExpression value,
            JsonGenerator gen,
            SerializerProvider serializers,
            TypeSerializer typeSer)
            throws IOException {
        serialize(value, gen, serializers);
    }
}
