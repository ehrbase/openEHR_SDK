package org.ehrbase.client.introspect.node;

import java.util.Map;

public class EntityNode implements Node {
    protected final String name;
    protected final boolean multi;
    protected final Map<String, Node> children;

    public EntityNode(String name, boolean multi, Map<String, Node> children) {
        this.name = name;
        this.multi = multi;
        this.children = children;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isMulti() {
        return multi;
    }

    public Map<String, Node> getChildren() {
        return children;
    }
}
