package org.ehrbase.serialisation.dbencoding;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

import com.nedap.archie.rm.archetyped.Locatable;
import org.apache.commons.collections.map.MultiValueMap;

public class NodeEncoding {

  private final CompositionSerializer.WalkerOutputMode tagMode;

  public NodeEncoding(CompositionSerializer.WalkerOutputMode tagMode) {
    this.tagMode = tagMode;
  }

  public String tag(String prefix, Locatable node, Object container) {
    switch (tagMode) {
      case PATH:
        if (node == null) return prefix;
        else {
          String path = prefix + "[" + node.getArchetypeNodeId() + "]";
          if (!container.getClass().equals(MultiValueMap.class)
              && !(path.startsWith(TAG_DESCRIPTION))
              && !(path.startsWith(TAG_COMPOSITION))
              && (path.contains("[openEHR-")
                  || path.contains(TAG_ACTIVITIES)
                  || path.contains(TAG_ITEMS)
                  || path.contains(TAG_EVENTS))) {

            // expand name in key
            String name = node.getName().getValue();

            if (name != null) {
              path = path.substring(0, path.lastIndexOf("]")) + " and name/value='" + name + "']";
            }
          }

          return path;
        }

      case NAMED:
      case EXPANDED:
      case RAW:
        if (prefix.equals(CompositionSerializer.TAG_ORIGIN)
            || prefix.equals(TAG_TIME)
            || prefix.equals(TAG_TIMING)
            || (prefix.equals(TAG_EVENTS) && node == null)) return "[" + prefix.substring(1) + "]";
        else if (node == null)
          return "!!!INVALID NAMED for " + prefix + " !!!"; // comes from encodeNodeAttribute...
        else {
          /* ISSUE, the name can be a translation hence any query in the JSON structure will be impossible!
          F.e. String name = node.nodeName();
          */
          return node.getArchetypeNodeId();
        }

      default:
        return "*INVALID MODE*";
    }
  }
}
