package org.ehrbase.serialisation.dbencoding;

import com.nedap.archie.rm.datavalues.DvText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameInMap {

    private Map map;
    private Map nameValues;

    public NameInMap(Map map, Map nameValues) {
        this.map = map;
        this.nameValues = nameValues;
    }

    public Map<String, Object> toMap() {
        List<Map> nameListMap = new ArrayList<>();
        nameListMap.add(nameValues);
        map.put(CompositionSerializer.TAG_NAME, nameListMap);
        return map;
    }
}
