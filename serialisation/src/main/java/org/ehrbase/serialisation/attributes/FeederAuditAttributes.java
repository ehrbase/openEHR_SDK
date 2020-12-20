package org.ehrbase.serialisation.attributes;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.TAG_CLASS;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.Map;
import org.ehrbase.serialisation.dbencoding.PathMap;
import org.ehrbase.serialisation.dbencoding.SimpleClassName;

/** populate the attributes for RM FeederAudit */
public class FeederAuditAttributes {

  private final FeederAudit feederAudit;

  public FeederAuditAttributes(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  /**
   * encode the attributes lower snake case to comply with UML conventions and make is queryable
   *
   * @return
   */
  public Map<String, Object> toMap() {
    Map<String, Object> valuemap = PathMap.getInstance();

    valuemap.put(TAG_CLASS, new SimpleClassName(feederAudit).toString());

    if (feederAudit.getOriginatingSystemItemIds() != null
        && !feederAudit.getOriginatingSystemItemIds().isEmpty()) {
      valuemap.put("originating_system_item_ids", feederAudit.getOriginatingSystemItemIds());
    }

    valuemap.put(
        "feeder_system_audit",
        new FeederAuditDetailsAttributes(feederAudit.getFeederSystemAudit()).toMap());

    if (feederAudit.getFeederSystemItemIds() != null
        && !feederAudit.getFeederSystemItemIds().isEmpty())
      valuemap.put("feeder_system_item_ids", feederAudit.getFeederSystemItemIds());

    valuemap.put("original_content", feederAudit.getOriginalContent());
    valuemap.put(
        "originating_system_audit",
        new FeederAuditDetailsAttributes(feederAudit.getOriginatingSystemAudit()).toMap());

    return valuemap;
  }
}
