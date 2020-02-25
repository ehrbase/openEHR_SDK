package org.ehrbase.client.introspect.node;

import org.ehrbase.client.terminology.ValueSet;

import java.util.Objects;

public class SlotNode extends EndNode {

    protected final boolean multi;

    public SlotNode(Class clazz, String name, ValueSet valuset, boolean multi) {
        super(clazz, name, valuset);
        this.multi = multi;
    }

    public boolean isMulti() {
        return multi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SlotNode slotNode = (SlotNode) o;
        return multi == slotNode.multi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), multi);
    }
}
