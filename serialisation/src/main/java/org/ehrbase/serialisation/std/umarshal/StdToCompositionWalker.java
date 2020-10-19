/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.serialisation.std.umarshal;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.std.umarshal.postprocessor.UnmarshalPostprocessor;
import org.ehrbase.serialisation.std.umarshal.rmunmarshaller.DefaultRMUnmarshaller;
import org.ehrbase.serialisation.std.umarshal.rmunmarshaller.RMUnmarshaller;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class StdToCompositionWalker extends ToCompositionWalker<Map<String, String>> {

    private static final Map<Class<?>, RMUnmarshaller> UNMARSHALLER_MAP = ReflectionHelper.buildMap(RMUnmarshaller.class);
    private static final Map<Class<?>, UnmarshalPostprocessor> POSTPROCESSOR_MAP = ReflectionHelper.buildMap(UnmarshalPostprocessor.class);



    private Set<String> consumedPaths;

    @Override
    public void walk(Composition composition, Map<String, String> object, WebTemplate webTemplate) {
        consumedPaths = new HashSet<>();
        super.walk(composition, object, webTemplate);
    }

    @Override
    protected Map<String, String> extract(Context<Map<String, String>> context, WebTemplateNode child, boolean isChoice, Integer count) {

        context.getNodeDeque().push(child);
        String path = buildNamePath(context);
        context.getNodeDeque().remove();

        Map<String, String> subValues = context.getObjectDeque().peek().entrySet().stream().filter(e -> e.getKey().startsWith(path)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (isChoice && !isMatchingNode(subValues, context, child)) {
            subValues = Collections.emptyMap();
        }

        if (!subValues.isEmpty()) {
            return subValues;
        } else {
            return null;
        }
    }

    private boolean isMatchingNode(Map<String, String> subValues, Context<Map<String, String>> context, WebTemplateNode child) {

        if (visitChildren(child)) {
            for (WebTemplateNode n : child.getChildren()) {
                context.getNodeDeque().push(n);
                String path = buildNamePath(context);
                context.getNodeDeque().remove();
                subValues = subValues.entrySet().stream()
                        .filter(e -> !e.getKey().startsWith(path))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            }

            return subValues.isEmpty();
        } else {
            // End Nodes which are Choice always have unique flat paths
            return true;
        }


    }

    @Override
    protected void preHandle(Context<Map<String, String>> context) {

        //Handle if at a End-Node
        if (!visitChildren(context.getNodeDeque().peek())) {
            RMUnmarshaller rmUnmarshaller = UNMARSHALLER_MAP.getOrDefault(context.getRmObjectDeque().peek().getClass(), new DefaultRMUnmarshaller());
            rmUnmarshaller.handle(buildNamePath(context), context.getRmObjectDeque().peek(), context.getObjectDeque().peek(), context);
            consumedPaths.addAll(rmUnmarshaller.getConsumedPaths());
        }
    }

    @Override
    protected void postHandle(Context<Map<String, String>> context) {
        List<UnmarshalPostprocessor<? super RMObject>> postprocessor = Stream.concat(Stream.of(context.getRmObjectDeque().peek().getClass()), ClassUtils.getAllSuperclasses(context.getRmObjectDeque().peek().getClass()).stream())
                .map(POSTPROCESSOR_MAP::get)
                .filter(Objects::nonNull)
                .map(p -> (UnmarshalPostprocessor<? super RMObject>) p)
                .collect(Collectors.toList());
        postprocessor.forEach(p -> {
            p.process(buildNamePath(context), context.getRmObjectDeque().peek(), context.getObjectDeque().peek());
            consumedPaths.addAll(p.getConsumedPaths());
        });
    }

    @Override
    protected int calculateSize(Context<Map<String, String>> context, WebTemplateNode childNode) {
        context.getNodeDeque().push(childNode);
        Integer count = context.getObjectDeque().peek().keySet().stream()
                .map(s -> StringUtils.substringBetween(s, buildNamePath(context) + ":", "/"))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .sorted()
                .reduce((first, second) -> second)
                .orElse(0);
        context.getNodeDeque().poll();
        return count;
    }

    public Set<String> getConsumedPaths() {
        return consumedPaths;
    }

}
