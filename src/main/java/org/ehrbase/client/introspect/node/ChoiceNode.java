package org.ehrbase.client.introspect.node;

import java.util.List;
import java.util.Objects;

public class ChoiceNode implements Node {

    private final List<Node> nodes;
    private final String name;
    private final boolean multi;


    public ChoiceNode(String name, List<Node> nodes, boolean multi) {
        this.nodes = nodes;
        this.name = name;
        this.multi = multi;
    }


    @Override
    public String getName() {
        return name;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public boolean isMulti() {
        return multi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoiceNode that = (ChoiceNode) o;
        return multi == that.multi &&
                Objects.equals(nodes, that.nodes) &&
                Objects.equals(getFirstName(), that.getFirstName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, getFirstName(), multi);
    }
}
