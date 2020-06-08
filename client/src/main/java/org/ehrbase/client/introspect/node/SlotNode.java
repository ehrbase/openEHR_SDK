package org.ehrbase.client.introspect.node;

import org.ehrbase.client.terminology.ValueSet;

public class SlotNode extends EndNode {


    public SlotNode(Class clazz, String name, ValueSet valuset, boolean multi) {
        super(clazz, name, valuset, multi);

    }


    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
