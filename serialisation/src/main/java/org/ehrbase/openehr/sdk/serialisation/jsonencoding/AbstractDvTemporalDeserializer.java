/*
 * Copyright (c) 2023 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.serialisation.jsonencoding;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.SingleValuedDataValue;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.ReferenceRange;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTemporal;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Base class for archie date-time parsing workarounds
 *
 * @param <V> type of /value attribute
 * @param <T> DV_TEMPORAL subtype that is parsed
 */
public abstract class AbstractDvTemporalDeserializer<V, T extends DvTemporal<T, ?> & SingleValuedDataValue<V>>
        extends JsonDeserializer<T> {

    abstract T createInstance();

    abstract V parseValue(String valueString);

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        final T result = createInstance();
        JsonNode rootNode = p.readValueAsTree();
        if (rootNode.isTextual()) {
            result.setValue(parseValue(rootNode.textValue()));
        } else if (rootNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
            while (fieldsIterator.hasNext()) {
                Map.Entry<String, JsonNode> nodeEntry = fieldsIterator.next();
                if (!nodeEntry.getValue().isNull()) {
                    try {
                        switch (nodeEntry.getKey()) {
                            case "normal_status":
                                result.setNormalStatus(ctxt.readTreeAsValue(nodeEntry.getValue(), CodePhrase.class));
                                break;
                            case "normal_range":
                                result.setNormalRange(ctxt.readTreeAsValue(
                                        nodeEntry.getValue(),
                                        ctxt.getTypeFactory().constructType(new TypeReference<DvInterval<T>>() {})));
                                break;
                            case "other_reference_ranges":
                                result.setOtherReferenceRanges(ctxt.readTreeAsValue(
                                        nodeEntry.getValue(),
                                        ctxt.getTypeFactory()
                                                .constructType(new TypeReference<List<ReferenceRange<T>>>() {})));
                                break;
                            case "magnitude_status":
                                result.setMagnitudeStatus(nodeEntry.getValue().textValue());
                                break;
                            case "accuracy":
                                result.setAccuracy(ctxt.readTreeAsValue(nodeEntry.getValue(), DvDuration.class));
                                break;
                            case "value":
                                result.setValue(parseValue(nodeEntry.getValue().textValue()));
                                break;
                            case "magnitude":
                                // is a calculated property
                            case "_type":
                                // not part of RM, specific to openEHR JSON
                                break;
                            default:
                                if (ctxt.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
                                    throw new JsonMappingException(
                                            "Property \"" + nodeEntry.getKey() + "\" is not part of "
                                                    + result.getClass().getSimpleName());
                                }
                                break;
                        }
                    } catch (Exception e) {
                        // we wrap the Exception to make sure the path to the property is provided accurately by jackson
                        throw JsonMappingException.wrapWithPath(e, result, nodeEntry.getKey());
                    }
                }
            }
        }
        return result;
    }
}
