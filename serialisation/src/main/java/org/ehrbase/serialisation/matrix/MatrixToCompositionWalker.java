/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.matrix;

import static org.ehrbase.serialisation.jsonencoding.CanonicalJson.MARSHAL_OM;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Archetyped;
import com.nedap.archie.rm.archetyped.TemplateId;
import com.nedap.archie.rm.support.identification.ArchetypeID;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.building.webtemplateskeletnbuilder.WebTemplateSkeletonBuilder;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.ToCompositionWalker;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * @author Stefan Spiska
 */
public class MatrixToCompositionWalker extends ToCompositionWalker<List<Entry>> {
    @Override
    protected List<Entry> extract(Context<List<Entry>> context, WebTemplateNode child, boolean isChoice, Integer i) {
        List<Entry> filter = filter(context.getObjectDeque().peek(), child.getAqlPathDto(), i != null, i);

        if (isChoice
                && filter(filter, child.getAqlPathDto().addEnd("/_type"), false, null).stream()
                        .noneMatch(e -> e.value.equals(child.getRmType()))) {
            return null;
        }

        if (filter.isEmpty()) {
            return null;
        }
        return filter;
    }

    @Override
    protected void preHandle(Context<List<Entry>> context) {

        WebTemplateNode node = context.getNodeDeque().peek();

        if (node.getChildren().isEmpty()) {

            Map<String, Object> unflatten = unflatten(context.getObjectDeque().peek().stream()
                    .map(o -> {
                        Entry entry = new Entry();
                        entry.value = o.value;
                        entry.path = o.path.removeStart(node.getAqlPathDto());
                        entry.index = o.index;
                        return entry;
                    })
                    .collect(Collectors.toList()));

            RMObject peek = context.getRmObjectDeque().peek();
            if (peek instanceof Archetyped) {

                ((Archetyped) peek).setRmVersion(unflatten.get("rm_version").toString());

                ((Archetyped) peek)
                        .setArchetypeId(
                                new ArchetypeID(unflatten.get("archetype_id").toString()));
                if (unflatten.containsKey("template_id")) {

                    TemplateId templateId = new TemplateId();
                    templateId.setValue(unflatten.get("template_id").toString());
                    ((Archetyped) peek).setTemplateId(templateId);
                }
            } else {

                Object newRmObject;
                if (unflatten.size() > 1 || unflatten.containsKey("_type")) {
                    newRmObject = MARSHAL_OM.convertValue(unflatten, RMObject.class);
                } else {
                    newRmObject = unflatten.values().stream().findAny().get();
                }

                RMObject oldRM = context.getRmObjectDeque().poll();
                RMObject parentRM = context.getRmObjectDeque().peek();
                WebTemplateNode currentNode = context.getNodeDeque().poll();
                WebTemplateNode parentNode = context.getNodeDeque().peek();

                WebTemplateSkeletonBuilder.remove(parentNode, parentRM, currentNode, oldRM);
                WebTemplateSkeletonBuilder.insert(parentNode, parentRM, currentNode, newRmObject);
                context.getRmObjectDeque().push((RMObject) wrap(newRmObject));
                context.getNodeDeque().push(currentNode);
            }
        }
    }

    Map<String, Object> unflatten(List<Entry> entries) {

        Map<String, List<Entry>> collect = entries.stream()
                .collect(Collectors.groupingBy(e -> e.path.getBaseNode().getName()));

        return collect.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> {
            if (e.getValue().size() == 1) {
                return e.getValue().get(0).value;
            }
            return unflatten(e.getValue().stream()
                    .map(o -> {
                        Entry entry = new Entry();
                        entry.value = o.value;
                        entry.path = o.path.removeStart(1);
                        entry.index = o.index;
                        return entry;
                    })
                    .collect(Collectors.toList()));
        }));
    }

    @Override
    protected int calculateSize(Context<List<Entry>> context, WebTemplateNode childNode) {

        return filter(context.getObjectDeque().peek(), childNode.getAqlPathDto(), true, null).stream()
                .mapToInt(e -> e.index.get(0))
                .max()
                .orElse(0);
    }

    static List<Entry> filter(List<Entry> list, AqlPath path, boolean checkIndex, Integer index) {

        return list.stream().filter(e -> matches(e, path, checkIndex, index)).collect(Collectors.toList());
    }

    private static boolean matches(Entry entry, AqlPath path, boolean checkIndex, Integer index) {

        if (!entry.path.startsWith(path)) {
            return false;
        }

        if (checkIndex && entry.index.isEmpty()) {
            return false;
        }

        return index == null || entry.index.get(0).equals(index);
    }
}
