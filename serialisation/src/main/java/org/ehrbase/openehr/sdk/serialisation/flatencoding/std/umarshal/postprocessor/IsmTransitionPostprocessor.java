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

import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.State;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

public class IsmTransitionPostprocessor extends AbstractUnmarshalPostprocessor<IsmTransition> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term,
            IsmTransition rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        if (rmObject.getCurrentState() == null && rmObject.getCareflowStep() != null) {
            // try to set from Template
            context.getNodeDeque().peek().findChildById("careflow_step").map(WebTemplateNode::getInputs).stream()
                    .flatMap(List::stream)
                    .filter(i -> i.getSuffix().equals("code"))
                    .map(WebTemplateInput::getList)
                    .flatMap(List::stream)
                    .filter(l -> rmObject.getCareflowStep()
                            .getDefiningCode()
                            .getCodeString()
                            .equals(l.getValue()))
                    .findAny()
                    .map(WebTemplateInputValue::getCurrentStates)
                    .filter(CollectionUtils::isNotEmpty)
                    .map(l -> l.get(0))
                    .map(v -> FlatHelper.findEnumValueOrThrow(v, State.class))
                    .map(EnumValueSet::toCodedText)
                    .ifPresent(rmObject::setCurrentState);
        }

        Map<Integer, Map<FlatPathDto, String>> reasonValues = FlatHelper.extractMultiValued(term, "_reason", values);

        reasonValues.forEach((key, value) -> {
            final DvText reasonText;
            if (FlatHelper.isExactlyDvCodedText(value, term + "/_reason:0")) {
                reasonText = new DvCodedText();
            } else {
                reasonText = new DvText();
            }
            rmObject.addReason(reasonText);
            callUnmarshal(
                    term,
                    "_reason:" + key,
                    reasonText,
                    value,
                    consumedPaths,
                    context,
                    FlatHelper.findOrBuildSubNode(context, "reason"));
            callPostProcess(
                    term,
                    "_reason:" + key,
                    reasonText,
                    value,
                    consumedPaths,
                    context,
                    FlatHelper.findOrBuildSubNode(context, "reason"));
        });
    }

    /** {@inheritDoc} */
    @Override
    public Class<IsmTransition> getAssociatedClass() {
        return IsmTransition.class;
    }
}
