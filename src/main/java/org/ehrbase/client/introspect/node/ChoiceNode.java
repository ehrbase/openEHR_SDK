package org.ehrbase.client.introspect.node;

import java.util.List;

public class ChoiceNode implements Node {

    private final List<EndNode> nodes;
    private final String name;

    public ChoiceNode(String name, List<EndNode> nodes) {
        this.nodes = nodes;
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }

    public List<EndNode> getNodes() {
        return nodes;
    }
}
