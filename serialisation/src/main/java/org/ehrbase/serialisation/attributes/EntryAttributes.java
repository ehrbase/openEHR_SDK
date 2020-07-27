package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.composition.*;
import org.ehrbase.serialisation.dbencoding.*;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

public abstract class EntryAttributes extends LocatableAttributes {

    public EntryAttributes(CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
        super(compositionSerializer, itemStack, map);
    }

    protected Map<String, Object> toMap(Entry entry){
        //add complementary attributes

        if (entry.getSubject() != null){
            map.put(TAG_SUBJECT, new SubjectAttributes(entry.getSubject(), compositionSerializer).toMap());
        }
        if (entry.getLanguage() != null){
            map.put(TAG_LANGUAGE, entry.getLanguage());
        }
        if (entry.getProvider() != null){
            map.put(TAG_PROVIDER, new SubjectAttributes(entry.getProvider(), compositionSerializer).toMap());
        }
        if (entry.getEncoding() != null){
            map.put(TAG_ENCODING, entry.getEncoding());
        }

        if (entry.getWorkflowId() != null)
            map = toMap(TAG_WORKFLOW_ID, entry.getWorkflowId(), entry.getName());

        map = super.toMap(entry);

        return map;
    }
}
