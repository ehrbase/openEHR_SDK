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

package org.ehrbase.client.walker;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.Pathable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.FlatPath;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Walker<T> {

    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    public void walk(Composition composition, T object, WebTemplate webTemplate) {

        Context<T> context = new Context<>();

        context.getNodeDeque().push(webTemplate.getTree());
        context.getObjectDeque().push(object);
        context.getRmObjectDeque().push(composition);

        handle(context);


    }

    private void handle(Context<T> context) {

        preHandle(context);
        WebTemplateNode currentNode = context.getNodeDeque().peek();
        if (visitChildren(currentNode)) {

            Map<String, List<WebTemplateNode>> choices = currentNode.getChoicesInChildren();
            for (WebTemplateNode childNode : currentNode.getChildren()) {
                final String rmType;
                if (choices.containsKey(childNode.getAqlPath())) {
                    rmType = childNode.getRmType();
                } else {
                    rmType = null;
                }
                Object child = extractRMChild(context.getRmObjectDeque().peek(), currentNode, childNode, rmType);
                if (child != null) {
                    if (childNode.getMax() == 1) {
                        T childObject = extract(context, childNode, null);
                        context.getNodeDeque().push(childNode);
                        context.getObjectDeque().push(childObject);
                        context.getRmObjectDeque().push((RMObject) child);
                        handle(context);
                    } else {

                        int size = calculateSize(context, child);


                        RMObject currentChild = null;

                        for (int i = 0; i <= size; i++) {
                            currentChild = extractFromList((List<RMObject>) child, i);
                            T childObject = extract(context, childNode, i);

                            context.getNodeDeque().push(childNode);
                            context.getObjectDeque().push(childObject);
                            context.getRmObjectDeque().push(currentChild);
                            context.getCountMap().put(childNode, i);
                            handle(context);
                        }

                    }
                }
            }
        }
        postHandle(context);
        context.getRmObjectDeque().remove();
        context.getNodeDeque().remove();
        context.getObjectDeque().remove();
    }

    protected abstract RMObject extractFromList(List<RMObject> child, int i);


    protected Object extractRMChild(RMObject currentRM, WebTemplateNode currentNode, WebTemplateNode childNode, String rmType) {
        final String relativeAql = StringUtils.removeEnd(StringUtils.removeStart(childNode.getAqlPath(), currentNode.getAqlPath()), "/");
        Object child;

        FlatPath childPath = new FlatPath(relativeAql);
        child = ((Pathable) currentRM).itemsAtPath(childPath.format(false));

        if (child == null || ((List) child).isEmpty()) {
            child = ((Pathable) currentRM).itemAtPath(childPath.format(false));
        }

        if (StringUtils.isNotBlank(childPath.findOtherPredicate("name/value")) && child instanceof List && Locatable.class.isAssignableFrom(ARCHIE_RM_INFO_LOOKUP.getClass(childNode.getRmType()))) {
            child = ((List) child).stream()
                    .filter(c -> childPath.findOtherPredicate("name/value").equals(((Locatable) c).getNameAsString()))
                    .collect(Collectors.toList());
            // if name not found return null
            if (((List<?>) child).isEmpty()) {
                child = null;
            }
        }

        if (childNode.getMax() == 1 && child instanceof List) {
            child = ((List) child).get(0);
        }

        if (child instanceof Element && !childNode.getRmType().equals("ELEMENT")) {
            child = ((Element) child).getValue();
        }
        if (StringUtils.isNotBlank(rmType)) {
            System.out.println("");
        }
        return child;
    }

    protected boolean visitChildren(WebTemplateNode node) {
        RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
        return typeInfo != null && (Locatable.class.isAssignableFrom(typeInfo.getJavaClass()) || EventContext.class.isAssignableFrom(typeInfo.getJavaClass()));
    }

    protected abstract T extract(Context<T> context, WebTemplateNode child, Integer i);

    protected abstract void preHandle(Context<T> context);

    protected abstract void postHandle(Context<T> context);

    protected abstract int calculateSize(Context<T> context, Object child);

    protected RMObject deepClone(RMObject rmObject) {
        CanonicalJson canonicalXML = new CanonicalJson();
        return canonicalXML.unmarshal(canonicalXML.marshal(rmObject), rmObject.getClass());
    }

    protected String buildNamePath(Context<T> context) {
        StringBuilder sb = new StringBuilder();
        for (Iterator<WebTemplateNode> iterator = context.getNodeDeque().descendingIterator(); iterator.hasNext(); ) {
            WebTemplateNode node = iterator.next();
            sb.append(node.getId());
            if (node.getMax() != 1 && context.getCountMap().containsKey(node)) {
                sb.append(":")
                        .append(context.getCountMap().get(node));
            }
            if (iterator.hasNext()) {
                sb.append("/");
            }
        }
        return sb.toString();
    }
}