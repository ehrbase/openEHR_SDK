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

package org.ehrbase.webtemplate.filter;

import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.OPTParser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    public WebTemplate filter(WebTemplate webTemplate) {
        List<WebTemplateNode> filteredChildren = webTemplate.getTree().getChildren().stream().map(n -> filter(n, webTemplate)).flatMap(List::stream).collect(Collectors.toList());
        webTemplate.getTree().getChildren().clear();
        webTemplate.getTree().getChildren().addAll(filteredChildren);

        return webTemplate;
    }

    private List<WebTemplateNode> filter(WebTemplateNode node, WebTemplate context) {
        List<WebTemplateNode> nodes;
        if (skip(node, context)) {
            nodes = node.getChildren().stream().map(n -> filter(n, context)).flatMap(List::stream).collect(Collectors.toList());

        } else {
            nodes = Collections.singletonList(node);
            List<WebTemplateNode> filteredChildren = node.getChildren().stream().map(n -> filter(n, context)).flatMap(List::stream).collect(Collectors.toList());
            node.getChildren().clear();
            node.getChildren().addAll(filteredChildren);
        }
        OPTParser.makeIdUnique(node);
        return nodes;
    }

    private boolean skip(WebTemplateNode node, WebTemplate context) {
        if (List.of("HISTORY", "ITEM_TREE", "ITEM_LIST", "ITEM_SINGLE", "ITEM_TABLE", "ITEM_STRUCTURE").contains(node.getRmType())) {
            return true;
        } else if (node.getRmType().equals("EVENT")) {
            return context.findAllByAqlPath(node.getAqlPath(), false).size() == 1 && node.getMax() == 1;
        } else if (node.getRmType().equals("ELEMENT")) {
            return node.getChildren().size() == 1;
        } else if (node.getRmType().equals("CODE_PHRASE")) {
            List<WebTemplateNode> matching = context.getTree().findMatching(n -> n.getChildren().contains(node));
            return matching.get(0).getRmType().equals("DV_CODED_TEXT");
        }
        return false;
    }
}
