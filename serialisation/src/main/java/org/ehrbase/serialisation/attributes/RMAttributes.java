package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.composition.*;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.serialisation.dbencoding.*;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

public abstract class RMAttributes {

    protected final WalkerOutputMode tagMode;
    protected final ItemStack itemStack;
    protected Map<String, Object> map;
    protected CompositionSerializer compositionSerializer;

    public RMAttributes(CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
        this.tagMode = compositionSerializer.tagMode();
        this.itemStack = itemStack;
        this.map = map;
        this.compositionSerializer = compositionSerializer;
    }

    /**
     * encode a single value for example activity timing
     * @param tag
     * @param value
     */
    protected Map<String, Object> toMap(String tag, Object value, DvText name) {
        Map<String, Object> valuemap;
        //CHC: 160317 make name optional ex: timing
        if (name != null && !map.containsKey(TAG_NAME)) {
            valuemap = PathMap.getInstance();
            map.putAll(new SerialTree(valuemap).insert(null, value, TAG_NAME, new NameAsDvText(name).toMap()));
        }


        //CHC: 160317 make value optional ex. simple name for activity
        if (value != null) {
            valuemap = PathMap.getInstance();
            valuemap = new SerialTree(valuemap).insert(new SimpleClassName(value).toString(), value, TAG_VALUE, value);
            valuemap = new PathItem(valuemap, tagMode, itemStack).encode(tag);
            map.put(tag, valuemap);
        }

        return map;
    }

}
