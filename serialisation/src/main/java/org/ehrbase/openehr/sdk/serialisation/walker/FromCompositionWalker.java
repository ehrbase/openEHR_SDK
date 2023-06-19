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
package org.ehrbase.openehr.sdk.serialisation.walker;

import com.nedap.archie.query.RMPathQuery;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlPath;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

public abstract class FromCompositionWalker<T> extends Walker<T> {
    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    private final Map<AqlPath, RMPathQuery> rmPathableCache = new HashMap<>();

    private final BiFunction<AqlPath, Function<AqlPath, RMPathQuery>, RMPathQuery> rmPathableCacheFunc =
            (path, provider) -> rmPathableCache.computeIfAbsent(path, provider::apply);

    protected Object extractRMChild(
            RMObject currentRM,
            WebTemplateNode currentNode,
            WebTemplateNode childNode,
            boolean isChoice,
            Integer count) {

        ItemExtractor itemExtractor = new ItemExtractor(currentRM, currentNode, childNode, isChoice && count == null)
                .withRmPathQueryCache(rmPathableCacheFunc)
                .invoke();

        Object child = itemExtractor.getChild();

        if (count != null && child instanceof List) {

            child = ((List<RMObject>) child).get(count);
            if (isChoice
                    && !ARCHIE_RM_INFO_LOOKUP
                            .getTypeInfo(childNode.getRmType())
                            .getJavaClass()
                            .isAssignableFrom(child.getClass())) {
                child = null;
            }
        }
        child = wrap(child);
        return child;
    }

    @Override
    protected int calculateSize(Context<T> context, WebTemplateNode childNode) {

        Object child = extractRMChild(
                context.getRmObjectDeque().peek(), context.getNodeDeque().peek(), childNode, false, null);
        if (child instanceof List) {
            return ((List) child).size();
        } else {
            return 0;
        }
    }

    protected ImmutablePair<T, RMObject> extractPair(
            Context<T> context,
            WebTemplateNode currentNode,
            Map<String, List<WebTemplateNode>> choices,
            WebTemplateNode childNode,
            Integer i) {
        RMObject currentChild = null;
        T childObject = null;

        currentChild = (RMObject) extractRMChild(
                context.getRmObjectDeque().peek(),
                currentNode,
                childNode,
                choices.containsKey(childNode.getAqlPath()),
                i);

        if (currentChild != null) {
            childObject = extract(context, childNode, choices.containsKey(childNode.getAqlPath()), i);
        }

        ImmutablePair<T, RMObject> pair = new ImmutablePair<>(childObject, currentChild);
        return pair;
    }
}
