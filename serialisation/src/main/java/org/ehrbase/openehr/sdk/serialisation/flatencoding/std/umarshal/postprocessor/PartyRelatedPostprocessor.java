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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal.postprocessor;

import static org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper.buildDummyChild;

import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyRelated;
import java.util.Map;
import java.util.Set;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

public class PartyRelatedPostprocessor extends AbstractUnmarshalPostprocessor<PartyRelated> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term,
            PartyRelated rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        Map<FlatPathDto, String> relationshipValues = FlatHelper.filter(values, term + "/relationship", false);

        if (!relationshipValues.isEmpty()) {
            rmObject.setRelationship(new DvCodedText());
            callUnmarshal(
                    term,
                    "relationship",
                    rmObject.getRelationship(),
                    relationshipValues,
                    consumedPaths,
                    context,
                    context.getNodeDeque()
                            .peek()
                            .findChildById("relationship")
                            .orElse(buildDummyChild(
                                    "relationship", context.getNodeDeque().peek())));
            callPostProcess(
                    term,
                    "relationship",
                    rmObject.getRelationship(),
                    relationshipValues,
                    consumedPaths,
                    context,
                    context.getNodeDeque()
                            .peek()
                            .findChildById("relationship")
                            .orElse(buildDummyChild(
                                    "relationship", context.getNodeDeque().peek())));
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<PartyRelated> getAssociatedClass() {
        return PartyRelated.class;
    }
}
