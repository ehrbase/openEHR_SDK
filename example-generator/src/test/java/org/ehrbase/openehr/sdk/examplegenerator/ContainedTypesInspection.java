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