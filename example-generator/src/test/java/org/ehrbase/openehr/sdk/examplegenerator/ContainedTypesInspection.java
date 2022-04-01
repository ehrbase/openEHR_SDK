/*
 * Copyright (c) 2022. vitasystems GmbH and Hannover Medical School.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.openehr.sdk.examplegenerator;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

class ContainedTypesInspection {

    public static MultiValuedMap<String, String> inspect(WebTemplate webTemplate) {
        MultiValuedMap<String, String> types = new ArrayListValuedHashMap<>();

        WebTemplateNode root = webTemplate.getTree();

        Deque<WebTemplateNode> remaining = new ArrayDeque<>();

        remaining.push(root);

        while(!remaining.isEmpty()) {
            var node = remaining.pop();
            types.put(node.getRmType(), node.getAqlPath());
            remaining.addAll(node.getChildren());
        }
        return types;
    }

    @Test
    void testInspect() {
        // get conformance template from one of the examples
        String templateId = OperationalTemplateTestData.CONFORMANCE.getTemplateId();
        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();

        WebTemplate webTemplate = templateProvider.buildIntrospect(templateId).get();

        MultiValuedMap<String, String> types = ContainedTypesInspection.inspect(webTemplate);

        types.asMap().entrySet().stream().sorted(Comparator.comparing(e -> e.getKey()))
                .forEach(e -> {
                    System.out.println(e.getKey() + ": " + e.getValue().size());
                });

    }

}