/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.List;
import org.ehrbase.openehr.sdk.aql.dto.path.AndOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.parser.AqlQueryParser;

/**
 * @author Stefan Spiska
 */
public class PredicateDeserializer extends StdDeserializer<List<AndOperatorPredicate>> {

    protected PredicateDeserializer() {
        super((Class<List<AndOperatorPredicate>>) null);
    }

    @Override
    public List<AndOperatorPredicate> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        return AqlQueryParser.parsePredicate(p.getValueAsString());
    }
}
