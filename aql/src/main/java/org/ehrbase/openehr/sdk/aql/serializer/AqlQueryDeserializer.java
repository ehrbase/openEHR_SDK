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
package org.ehrbase.openehr.sdk.aql.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.NumericNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.dto.condition.WhereCondition;
import org.ehrbase.openehr.sdk.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.Containment;
import org.ehrbase.openehr.sdk.aql.dto.orderby.OrderByExpression;
import org.ehrbase.openehr.sdk.aql.dto.select.SelectClause;
import org.ehrbase.openehr.sdk.aql.util.AqlUtil;

/**
 * @author Stefan Spiska
 */
public class AqlQueryDeserializer extends StdDeserializer<AqlQuery> {

    protected AqlQueryDeserializer() {
        super(AqlQuery.class);
    }

    @Override
    public AqlQuery deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JacksonException {

        AqlQuery aqlQuery = new AqlQuery();
        JsonNode node = p.getCodec().readTree(p);
        ObjectMapper objectMapper = AqlQueryObjectMapperProvider.getObjectMapper();

        JsonNode from = node.get("from");
        Map<String, AbstractContainmentExpression> listById;
        if (from != null) {
            Containment containment = objectMapper.readValue(from.traverse(), Containment.class);
            listById = AqlUtil.listById(containment);
            aqlQuery.setFrom(containment);
        } else {
            listById = Collections.emptyMap();
        }

        JsonNode select = node.get("select");
        if (select != null) {
            aqlQuery.setSelect(objectMapper
                    .reader()
                    .forType(SelectClause.class)
                    .withAttribute("listById", listById)
                    .readValue(select.traverse()));
        }
        JsonNode where = node.get("where");
        if (where != null) {
            aqlQuery.setWhere(objectMapper
                    .reader()
                    .forType(WhereCondition.class)
                    .withAttribute("listById", listById)
                    .readValue(where.traverse()));
        }

        JsonNode orderBy = node.get("orderBy");
        if (orderBy != null) {
            aqlQuery.setOrderBy(objectMapper
                    .reader()
                    .forType(objectMapper
                            .getTypeFactory()
                            .constructCollectionType(ArrayList.class, OrderByExpression.class))
                    .withAttribute("listById", listById)
                    .readValue(orderBy.traverse()));
        }

        JsonNode limit = node.get("limit");
        if (limit instanceof NumericNode numericNode) {
            aqlQuery.setLimit(numericNode.asLong());
        }

        JsonNode offset = node.get("offset");
        if (offset instanceof NumericNode numericNode) {
            aqlQuery.setOffset(numericNode.asLong());
        }

        return aqlQuery;
    }
}
