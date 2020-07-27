package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.archetyped.Locatable;
import org.ehrbase.serialisation.dbencoding.*;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

public abstract class LocatableAttributes extends RMAttributes {

    public LocatableAttributes(CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
        super(compositionSerializer, itemStack, map);
    }

    protected Map<String, Object> toMap(Locatable locatable){
        //add complementary attributes

        if (locatable.getArchetypeNodeId() != null){
            map.put(TAG_ARCHETYPE_NODE_ID, locatable.getArchetypeNodeId());
        }
        if (locatable.getArchetypeDetails() != null){
            map = toMap(TAG_ARCHETYPE_DETAILS, locatable.getArchetypeDetails(), NO_NAME);
        }
        if (locatable.getFeederAudit() != null){
            map = toMap(TAG_FEEDER_AUDIT, locatable.getFeederAudit(), NO_NAME);
        }
        if (locatable.getUid() != null){
            map = toMap(TAG_UID, locatable.getUid(), NO_NAME);
        }
        if (!map.containsKey(TAG_NAME) && locatable.getName() != null){ //since name maybe resolved from the archetype node id
            map.put(TAG_NAME, new NameAsDvText(locatable.getName()).toMap());
        }

        return map;
    }
}
