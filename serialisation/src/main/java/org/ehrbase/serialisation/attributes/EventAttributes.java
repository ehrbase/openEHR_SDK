package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.composition.*;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.serialisation.dbencoding.CompositionSerializer;
import org.ehrbase.serialisation.dbencoding.ItemStack;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

public class EventAttributes extends LocatableAttributes {

    public EventAttributes(CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
        super(compositionSerializer, itemStack, map);
    }

    public Map<String, Object> toMap(Event<?> event){

        if (event instanceof IntervalEvent) {
            IntervalEvent intervalEvent = (IntervalEvent) event;
            if (intervalEvent.getWidth() != null)
                map = toMap(TAG_WIDTH, intervalEvent.getWidth(), event.getName());
            if (intervalEvent.getMathFunction() != null)
                map = toMap(TAG_MATH_FUNCTION, intervalEvent.getMathFunction(), event.getName());
        }


        if (event.getTime() != null) {
            if (event.getTime().equals(new DvDateTime()))
                map = toMap(TAG_TIME, event.getTime(), event.getName());
        }

        map =  super.toMap(event);

        return map;
    }
}
