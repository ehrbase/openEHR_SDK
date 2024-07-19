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

import static org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper.consumeAllMatching;
import static org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper.extractMultiValued;
import static org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
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

        String rmType = Optional.ofNullable(context.getNodeDeque().peek())
                .map(WebTemplateNode::getRmType)
                .orElse(null);

        if (RmConstants.ELEMENT.equals(rmType) || !context.getFlatHelper().skip(context)) {

            setUID(term, rmObject, values, consumedPaths);
            setName(term, rmObject, values, consumedPaths, context);
            setLinks(term, rmObject, values, consumedPaths);
            setFeederAudit(term, rmObject, values, consumedPaths, context);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<Locatable> getAssociatedClass() {
        return Locatable.class;
    }

    private void setUID(String term, Locatable rmObject, Map<FlatPathDto, String> values, Set<String> consumedPaths) {
        setValue(
                term + PATH_DIVIDER + "_uid",
                null,
                values,
                value -> rmObject.setUid(
                        StringUtils.countMatches(value, "::") == 2
                                ? new ObjectVersionId(value)
                                : new HierObjectId(value)),
                String.class,
                consumedPaths);
    }

    private void setName(
            String term,
            Locatable rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        Map<FlatPathDto, String> nameValues = FlatHelper.filter(values, term + "/_name", false);
        if (!nameValues.isEmpty()) {
            boolean isDvCodedText = nameValues.keySet().stream().anyMatch(e -> {
                FlatPathDto last = e.getLast();
                return "code".equals(last.getAttributeName()) && "_name".equals(last.getName());
            });

            rmObject.setName(isDvCodedText ? new DvCodedText() : new DvText());
            handleRmAttribute(term, rmObject.getName(), nameValues, consumedPaths, context, "name");
        }
    }

    private void setLinks(String term, Locatable rmObject, Map<FlatPathDto, String> values, Set<String> consumedPaths) {

        Map<Integer, Map<FlatPathDto, String>> links = extractMultiValued(term, "_link", values);

        if (rmObject.getLinks() == null) {
            rmObject.setLinks(new ArrayList<>());
        }

        rmObject.getLinks()
                .addAll(links.entrySet().stream()
                        .map(e -> DefaultValues.createLink(e.getValue(), term + "/_link:" + e.getKey()))
                        .toList());

        consumeAllMatching(term + PATH_DIVIDER + "_link", values, consumedPaths, false);
    }

    private void setFeederAudit(
            String term,
            Locatable rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        Map<FlatPathDto, String> feederAuditValues = FlatHelper.filter(values, term + "/_feeder_audit", false);

        if (!feederAuditValues.isEmpty()) {

            rmObject.setFeederAudit(new FeederAudit());
            handleRmAttribute(
                    term, rmObject.getFeederAudit(), feederAuditValues, consumedPaths, context, "feeder_audit");
        }
    }
}
