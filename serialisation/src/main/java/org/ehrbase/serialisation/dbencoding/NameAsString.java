package org.ehrbase.serialisation.dbencoding;

import com.nedap.archie.rm.datavalues.DvText;

import java.util.HashMap;
import java.util.Map;

public class NameAsString {

    private String aName;

    public NameAsString(String aName) {
        this.aName = aName;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> nameMap = new HashMap<>();
        nameMap.put("value", new DvText(aName));
        return nameMap;
    }
}
