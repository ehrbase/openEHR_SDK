package org.ehrbase.serialisation.dbencoding;

public class SimpleClassName {

    Object object;

    public SimpleClassName(Object object) {
        this.object = object;
    }

    public String toString(){
        return object.getClass().getSimpleName();
    }
}
