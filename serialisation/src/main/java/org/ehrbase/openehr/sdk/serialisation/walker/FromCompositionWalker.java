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
package org.ehrbase.openehr.sdk.serialisation.walker;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import java.util.List;
import java.util.function.BooleanSupplier;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

public abstract class FromCompositionWalker<T> extends Walker<T> {
    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    protected Object extractRMChild(
            RMObject currentRM,
            WebTemplateNode currentNode,
            WebTemplateNode childNode,
            BooleanSupplier isChoice,
            Integer count) {
        Object child =
                ItemExtractor.extractChild(currentRM, currentNode, childNode, count != null ? (() -> false) : isChoice);

        if (count != null && child instanceof List<?> childList) {

            Object selectedChild = childList.get(count);
            if (isChoice.getAsBoolean()
                    && !ARCHIE_RM_INFO_LOOKUP
                            .getTypeInfo(childNode.getRmType())
                            .getJavaClass()
                            .isAssignableFrom(selectedChild.getClass())) {
                return null;
            } else {
                child = selectedChild;
            }
        }
        return wrap(child);
    }

    @Override
    protected int calculateSize(Context<T> context, WebTemplateNode childNode) {

        Object child = extractRMChild(
                context.getRmObjectDeque().peek(), context.getNodeDeque().peek(), childNode, () -> false, null);
        if (child instanceof List<?> l) {
            return l.size();
        } else {
            return 0;
        }
    }

    protected ImmutablePair<T, RMObject> extractPair(
            Context<T> context,
            WebTemplateNode currentNode,
            BooleanSupplier isChoice,
            WebTemplateNode childNode,
            Integer i) {

        RMObject currentChild =
                (RMObject) extractRMChild(context.getRmObjectDeque().peek(), currentNode, childNode, isChoice, i);

        if (currentChild == null) {
            return null;
        }
        T childObject = extract(context, childNode, isChoice, i);

        return new ImmutablePair<>(childObject, currentChild);
    }
}
