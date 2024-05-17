/*
 * Copyright (c) 2020 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal.postprocessor;

import static org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper.consumeAllMatching;
import static org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper.extractMultiValued;
import static org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.support.identification.HierObjectId;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

public class LocatableUnmarshalPostprocessor extends AbstractUnmarshalPostprocessor<Locatable> {

    /** {@inheritDoc} Unmarshalls {@link Composition#setUid} */
    @Override
    public void process(
            String term,
            Locatable rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        if (RmConstants.ELEMENT.equals(context.getNodeDeque().peek().getRmType())
                || !context.getFlatHelper().skip(context)) {

            setValue(
                    term + PATH_DIVIDER + "_uid",
                    null,
                    values,
                    s -> rmObject.setUid(new HierObjectId(s)),
                    String.class,
                    consumedPaths);

            Map<Integer, Map<FlatPathDto, String>> links = extractMultiValued(term, "_link", values);

            if (rmObject.getLinks() == null) {
                rmObject.setLinks(new ArrayList<>());
            }

            rmObject.getLinks()
                    .addAll(links.entrySet().stream()
                            .map(e -> DefaultValues.createLink(e.getValue(), term + "/_link:" + e.getKey()))
                            .collect(Collectors.toList()));

            consumeAllMatching(term + PATH_DIVIDER + "_link", values, consumedPaths, false);

            Map<FlatPathDto, String> feederAuditValues = FlatHelper.filter(values, term + "/_feeder_audit", false);

            if (!feederAuditValues.isEmpty()) {

                rmObject.setFeederAudit(new FeederAudit());
                handleRmAttribute(
                        term, rmObject.getFeederAudit(), feederAuditValues, consumedPaths, context, "feeder_audit");
            }

            Map<FlatPathDto, String> nameValues = FlatHelper.filter(values, term + "/_name", false);
            if (!nameValues.isEmpty()) {
                final DvText name;
                boolean isDvCodedText = nameValues.keySet().stream()
                        .anyMatch(e -> "code".equals(e.getLast().getAttributeName())
                                && "_name".equals(e.getLast().getName()));

                if (isDvCodedText) {
                    name = new DvCodedText();
                } else {
                    name = new DvText();
                }
                rmObject.setName(name);
                handleRmAttribute(term, rmObject.getName(), nameValues, consumedPaths, context, "name");
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<Locatable> getAssociatedClass() {
        return Locatable.class;
    }
}
