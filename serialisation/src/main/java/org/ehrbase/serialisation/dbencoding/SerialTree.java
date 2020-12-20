package org.ehrbase.serialisation.dbencoding;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

import com.nedap.archie.rm.archetyped.Locatable;
import java.util.Map;
import org.apache.commons.collections4.map.PredicatedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** insert the attributes of a structural object into the DB encoded structure */
public class SerialTree {

  Map<String, Object> map;

  Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

  public SerialTree(Map<String, Object> map) {
    this.map = map;
  }

  /**
   * put a key=value pair in a map and detects duplicates.
   *
   * @param key
   * @param addStructure
   * @return
   * @throws Exception
   */
  public Map<String, Object> insert(String clazz, Object node, String key, Object addStructure) {

    if (addStructure == null) return null;
    if (addStructure instanceof Map
        && ((Map) addStructure).size() == 0
        && !clazz.equalsIgnoreCase("COMPOSITION")) return null;

    if (key.equals(TAG_NAME)) {
      if (addStructure instanceof Map) return new NameInMap(map, (Map) addStructure).toMap();
      else
        throw new IllegalStateException(
            "INTERNAL: addStructure is not a map, found:" + addStructure.getClass());
    }

    try {
      map.put(key, addStructure);
      // add explicit name
      if (node instanceof Locatable && map instanceof PredicatedMap && !map.containsKey(TAG_NAME)) {
        new NameInMap(map, new NameAsDvText(((Locatable) node).getName()).toMap()).toMap();
      }

    } catch (IllegalArgumentException e) {
      log.error("Ignoring duplicate key in path detected:" + key + " Exception:" + e);
    }

    if (clazz != null && !key.equals(TAG_PATH) && !map.containsKey(TAG_CLASS))
      map.put(CompositionSerializer.TAG_CLASS, clazz);
    else log.debug(map.containsKey(TAG_CLASS) ? "duplicate TAG_CLASS" : "null clazz");

    return map;
  }

  public Map<String, Object> insert(Object node, String key, Object addStructure) {

    String clazz = new SimpleClassName(node).toString();

    return insert(clazz, node, key, addStructure);
  }
}
