package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.archetyped.Locatable;
import org.ehrbase.serialisation.dbencoding.CompositionSerializer;
import org.ehrbase.serialisation.dbencoding.ItemStack;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

public class ItemStructureAttributes extends DataStructureAttributes {

    public ItemStructureAttributes(CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
        super(compositionSerializer, itemStack, map);
    }

    public Map<String, Object> toMap(Locatable locatable) {
        return super.toMap(locatable);
    }
}