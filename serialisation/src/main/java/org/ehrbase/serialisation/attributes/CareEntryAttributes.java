package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.composition.*;
import org.ehrbase.serialisation.dbencoding.*;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

public abstract class CareEntryAttributes extends EntryAttributes {

    public CareEntryAttributes(CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
        super(compositionSerializer, itemStack, map);
    }

    public Map<String, Object> toMap(CareEntry careEntry){

        if (careEntry.getGuidelineId() != null)
            map = toMap(TAG_GUIDELINE_ID, careEntry.getGuidelineId(), careEntry.getName());

        map = super.toMap(careEntry);

        return map;
    }

}
