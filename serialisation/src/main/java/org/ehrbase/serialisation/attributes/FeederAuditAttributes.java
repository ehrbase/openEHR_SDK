package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.serialisation.dbencoding.PathMap;
import org.ehrbase.serialisation.dbencoding.RmObjectEncoding;
import org.ehrbase.serialisation.dbencoding.SimpleClassName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.TAG_CLASS;

/**
 * populate the attributes for RM FeederAudit
 */
public class FeederAuditAttributes {

    private final FeederAudit feederAudit;

    public FeederAuditAttributes(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    /**
     * encode the attributes lower snake case to comply with UML conventions and make is queryable
     * @return
     */
    public Map<String, Object> toMap(){
        Map<String, Object> valuemap = PathMap.getInstance();

        valuemap.put(TAG_CLASS, new SimpleClassName(feederAudit).toString());

        if (feederAudit.getOriginatingSystemItemIds() != null && !feederAudit.getOriginatingSystemItemIds().isEmpty()) {
            valuemap.put("originating_system_item_ids",encodeDvIdentifiers(feederAudit.getOriginatingSystemItemIds()));
        }

        valuemap.put("feeder_system_audit", new FeederAuditDetailsAttributes(feederAudit.getFeederSystemAudit()).toMap());

        if (feederAudit.getFeederSystemItemIds() != null && !feederAudit.getFeederSystemItemIds().isEmpty()) {
            valuemap.put("feeder_system_item_ids", encodeDvIdentifiers(feederAudit.getFeederSystemItemIds()));
        }

        if (feederAudit.getOriginalContent() != null)
            valuemap.put("original_content", new RmObjectEncoding(feederAudit.getOriginalContent()).toMap());

        valuemap.put("originating_system_audit", new FeederAuditDetailsAttributes(feederAudit.getOriginatingSystemAudit()).toMap());

        return valuemap;
    }

    List<Map<String, Object>> encodeDvIdentifiers(List<DvIdentifier> dvIdentifiers){
        List<Map<String, Object>> idList = new ArrayList<>();
        for (DvIdentifier dvIdentifier: dvIdentifiers)
            idList.add(new RmObjectEncoding(dvIdentifier).toMap());

        return idList;
    }
}
