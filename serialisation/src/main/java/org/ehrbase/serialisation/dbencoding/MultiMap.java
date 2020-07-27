package org.ehrbase.serialisation.dbencoding;

import org.apache.commons.collections.map.MultiValueMap;

import java.util.Map;

public class MultiMap {

    public static Map<String, Object> getInstance() {
        return new MultiValueMap();
    }
}
