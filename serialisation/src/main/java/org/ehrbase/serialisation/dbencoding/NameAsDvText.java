package org.ehrbase.serialisation.dbencoding;

import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;

import java.util.HashMap;
import java.util.Map;

/**
 * format a RM object name as a DvText or DvCodedText
 */
public class NameAsDvText {

    private DvText aName;

    public NameAsDvText(DvText aName) {
        this.aName = aName;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> nameMap = new HashMap<>();
        if (aName instanceof DvCodedText) {
            nameMap.put("defining_code", ((DvCodedText) (aName)).getDefiningCode());
        }
        if (aName != null) {
            nameMap.put("value", aName.getValue());
        }
        return nameMap;
    }
}
