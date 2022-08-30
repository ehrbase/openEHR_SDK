/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor;

import static org.ehrbase.serialisation.walker.FlatHelper.*;
import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

public class EntryPostprocessor extends AbstractUnmarshalPostprocessor<Entry> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term,
            Entry rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {
        consumedPaths.add(term + PATH_DIVIDER + "encoding|code");
        consumedPaths.add(term + PATH_DIVIDER + "encoding|terminology");

        Map<FlatPathDto, String> subjectValues = FlatHelper.filter(values, term + "/subject", false);

        if (FlatHelper.isExactlyPartySelf(
                subjectValues, term + "/subject", FlatHelper.findOrBuildSubNode(context, "subject"))) {
            rmObject.setSubject(new PartySelf());
        }

        setParty(term, rmObject::setProvider, values, consumedPaths, context, "_provider", true);

        Map<Integer, Map<FlatPathDto, String>> other = extractMultiValued(term, "_other_participation", values);

        other.replaceAll(
                (k, v) -> convertAttributeToFlat(v, term + "/_other_participation:" + k, "identifiers", "_identifier"));

        rmObject.getOtherParticipations()
                .addAll(other.entrySet().stream()
                        .map(e -> DefaultValues.buildParticipation(
                                e.getValue(), term + "/_other_participation:" + e.getKey()))
                        .collect(Collectors.toList()));

        consumeAllMatching(term + PATH_DIVIDER + "_other_participation", values, consumedPaths, false);

        Map<FlatPathDto, String> workflowIdValues = filter(values, term + "/_work_flow_id", false);
        if (!workflowIdValues.isEmpty()) {
            ObjectRef<GenericId> ref = new ObjectRef<>();
            ref.setId(new GenericId());
            rmObject.setWorkflowId(ref);
            setValue(
                    term + "/_work_flow_id",
                    "id",
                    workflowIdValues,
                    s -> ref.getId().setValue(s),
                    String.class,
                    consumedPaths);
            setValue(
                    term + "/_work_flow_id",
                    "id_scheme",
                    workflowIdValues,
                    s -> ref.getId().setScheme(s),
                    String.class,
                    consumedPaths);
            setValue(
                    term + "/_work_flow_id",
                    "namespace",
                    workflowIdValues,
                    ref::setNamespace,
                    String.class,
                    consumedPaths);
            setValue(term + "/_work_flow_id", "type", workflowIdValues, ref::setType, String.class, consumedPaths);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<Entry> getAssociatedClass() {
        return Entry.class;
    }
}
