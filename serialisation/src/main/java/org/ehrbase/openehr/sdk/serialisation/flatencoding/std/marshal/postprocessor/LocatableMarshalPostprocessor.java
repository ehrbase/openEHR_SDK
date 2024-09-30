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

import com.nedap.archie.rm.archetyped.Link;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.datavalues.DvEHRURI;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.support.identification.ObjectId;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;

public class LocatableMarshalPostprocessor extends AbstractMarshalPostprocessor<Locatable> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term, Locatable rmObject, Map<String, Object> values, Context<Map<String, Object>> context) {

        if (RmConstants.ELEMENT.equals(context.getNodeDeque().peek().getRmType())
                || !context.getFlatHelper().skip(context)) {

            addValue(
                    values,
                    term + PATH_DIVIDER + "_uid",
                    null,
                    Optional.of(rmObject)
                            .map(Locatable::getUid)
                            .map(ObjectId::getValue)
                            .orElse(null));

            if (rmObject.getLinks() != null) {
                IntStream.range(0, rmObject.getLinks().size()).forEach(i -> {
                    Link link = rmObject.getLinks().get(i);
                    String termLoop = term + PATH_DIVIDER + "_link:" + i;

                    addValue(
                            values,
                            termLoop,
                            "meaning",
                            Optional.of(link)
                                    .map(Link::getMeaning)
                                    .map(DvText::getValue)
                                    .orElse(null));
                    addValue(
                            values,
                            termLoop,
                            "type",
                            Optional.of(link)
                                    .map(Link::getType)
                                    .map(DvText::getValue)
                                    .orElse(null));
                    addValue(
                            values,
                            termLoop,
                            "target",
                            Optional.of(link)
                                    .map(Link::getTarget)
                                    .map(DvEHRURI::getValue)
                                    .orElse(null));
                });
            }

            if (rmObject.getFeederAudit() != null) {
                callMarshal(
                        term,
                        "_feeder_audit",
                        rmObject.getFeederAudit(),
                        values,
                        context,
                        FlatHelper.findOrBuildSubNode(context, "feeder_audit"));

                callPostprocess(
                        term,
                        "_feeder_audit",
                        rmObject.getFeederAudit(),
                        values,
                        context,
                        FlatHelper.findOrBuildSubNode(context, "feeder_audit"));
            }

            if (Optional.ofNullable(rmObject.getName())
                    .map(DvText::getValue)
                    .filter(n -> !Objects.equals(context.getNodeDeque().peek().getName(), n))
                    .isPresent()) {

                handleRmAttribute(term, rmObject.getName(), values, context, "name");
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<Locatable> getAssociatedClass() {
        return Locatable.class;
    }
}
