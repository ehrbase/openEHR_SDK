package org.ehrbase.client.introspect.node;

import org.ehrbase.client.terminology.ValueSet;

public class SlotNode extends EndNode {

    protected final boolean multi;

    public SlotNode(Class clazz, String name, ValueSet valuset, boolean multi) {
        super(clazz, name, valuset);
        this.multi = multi;
    }

    public boolean isMulti() {
        return multi;
    }
}
