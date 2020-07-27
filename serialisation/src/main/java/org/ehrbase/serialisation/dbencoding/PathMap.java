package org.ehrbase.serialisation.dbencoding;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.PredicateUtils;

import java.util.Map;
import java.util.TreeMap;

public class PathMap {

    /**
     * to remain consistent regarding datastructure, we use a map which prevents duplicated keys... and throw
     * an exception if one is detected...
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getInstance() {
        return MapUtils.predicatedMap(new TreeMap<String, Object>(), PredicateUtils.uniquePredicate(), null);
    }
}
