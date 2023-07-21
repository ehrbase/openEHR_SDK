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

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import org.ehrbase.openehr.sdk.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentClassExpression;

/**
 * @author Stefan Spiska
 */
public class AbstractContainmentExpressionDeserializer extends StdDeserializer<AbstractContainmentExpression> {
    protected AbstractContainmentExpressionDeserializer() {
        super(AbstractContainmentExpression.class);
    }

    @Override
    public AbstractContainmentExpression deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {

        ContainmentClassExpression containmentClassExpression = new ContainmentClassExpression();
        containmentClassExpression.setIdentifier(p.getValueAsString());

        return containmentClassExpression;
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer)
            throws IOException {
        return deserialize(p, ctxt);
    }
}
