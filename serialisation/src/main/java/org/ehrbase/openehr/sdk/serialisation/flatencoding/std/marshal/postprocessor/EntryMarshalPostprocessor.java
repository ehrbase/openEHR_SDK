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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal.postprocessor;

import static org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;

public class EntryMarshalPostprocessor extends AbstractMarshalPostprocessor<Entry> {

    /** {@inheritDoc} Adds the encoding information */
    @Override
    public void process(String term, Entry rmObject, Map<String, Object> values, Context<Map<String, Object>> context) {
        values.put(term + PATH_DIVIDER + "encoding|code", "UTF-8");
        values.put(term + PATH_DIVIDER + "encoding|terminology", "IANA_character-sets");

        if (rmObject.getProvider() != null) {

            handleRmAttribute(term, rmObject.getProvider(), values, context, "provider");
        }

        if (rmObject.getOtherParticipations() != null) {
            IntStream.range(0, rmObject.getOtherParticipations().size()).forEach(i -> {
                callMarshal(
                        term,
                        "_other_participation:" + i,
                        rmObject.getOtherParticipations().get(i),
                        values,
                        context,
                        context.getNodeDeque()
                                .peek()
                                .findChildById("participation")
                                .orElse(null));
                callPostprocess(
                        term,
                        "_other_participation:" + i,
                        rmObject.getOtherParticipations().get(i),
                        values,
                        context,
                        context.getNodeDeque()
                                .peek()
                                .findChildById("participation")
                                .orElse(null));
            });
        }

        if (rmObject.getWorkflowId() != null) {

            addValue(
                    values,
                    term + "/_work_flow_id",
                    "id",
                    Optional.of(rmObject.getWorkflowId())
                            .map(ObjectRef::getId)
                            .map(ObjectId::getValue)
                            .orElse(null));
            addValue(
                    values,
                    term + "/_work_flow_id",
                    "id_scheme",
                    Optional.of(rmObject.getWorkflowId())
                            .map(ObjectRef::getId)
                            .filter(i -> GenericId.class.isAssignableFrom(i.getClass()))
                            .map(GenericId.class::cast)
                            .map(GenericId::getScheme)
                            .orElse(null));
            addValue(
                    values,
                    term + "/_work_flow_id",
                    "namespace",
                    rmObject.getWorkflowId().getNamespace());
            addValue(
                    values,
                    term + "/_work_flow_id",
                    "type",
                    rmObject.getWorkflowId().getType());
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<Entry> getAssociatedClass() {
        return Entry.class;
    }
}
