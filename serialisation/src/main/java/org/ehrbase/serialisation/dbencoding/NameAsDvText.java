package org.ehrbase.serialisation.dbencoding;

import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import java.util.HashMap;
import java.util.Map;
import org.ehrbase.serialisation.dbencoding.wrappers.json.I_DvTypeAdapter;
import org.ehrbase.serialisation.util.SnakeCase;

/** format a RM object name as a DvText or DvCodedText */
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
    nameMap.put(
        I_DvTypeAdapter.AT_TYPE, new SnakeCase(DvText.class.getSimpleName()).camelToUpperSnake());
    return nameMap;
  }
}
