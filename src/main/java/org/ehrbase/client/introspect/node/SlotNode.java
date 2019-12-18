package org.ehrbase.client.introspect.node;

import org.ehrbase.client.introspect.TermDefinition;

import java.util.Set;

public class SlotNode extends EndNode {

    protected final boolean multi;

    public SlotNode(Class clazz, String name, Set<TermDefinition> valuset, boolean multi) {
        super(clazz, name, valuset);
        this.multi = multi;
    }

    public boolean isMulti() {
        return multi;
    }
}
