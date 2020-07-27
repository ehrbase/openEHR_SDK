package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.serialisation.dbencoding.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

public class ElementAttributes {

    private static final String INITIAL_DUMMY_PREFIX = "$*>";

    private boolean allElements = false;
    private final WalkerOutputMode tagMode;
    private final ItemStack itemStack;
    private Logger log = LoggerFactory.getLogger(ElementAttributes.class.getSimpleName());

    public ElementAttributes(boolean allElements, WalkerOutputMode tagMode, ItemStack itemStack) {
        this.allElements = allElements;
        this.tagMode = tagMode;
        this.itemStack = itemStack;
    }

    public ElementAttributes(WalkerOutputMode tagMode, ItemStack itemStack) {
        this.tagMode = tagMode;
        this.itemStack = itemStack;
    }

    public Map<String, Object> toMap(Element element) {
        Map<String, Object> ltree = PathMap.getInstance();

        //to deal with ITEM_SINGLE initial value
        if (element.getName().getValue().startsWith(INITIAL_DUMMY_PREFIX)) {
            if (allElements) { //strip the prefix since it is for an example
                DvText elementName = element.getName();
                elementName.setValue(elementName.getValue().substring(INITIAL_DUMMY_PREFIX.length()));
                element.setName(elementName);
            } else
                return ltree;
        }

        if (element.getValue() != null && !element.getValue().toString().isEmpty()
        ) {
            log.debug(itemStack.pathStackDump() + "=" + element.getValue());
            Map<String, Object> valuemap = PathMap.getInstance();

            //set name
            if (element.getName() != null)
                valuemap = new SerialTree(valuemap).insert(null, element, TAG_NAME, new NameAsDvText(element.getName()).toMap());

            //set value
            if (element.getValue() != null && !element.getValue().toString().isEmpty())
                valuemap = new SerialTree(valuemap).insert(new CompositeClassName(element.getValue()).toString(), element, TAG_VALUE, new ElementValue(element.getValue()).normalize());

            //set path
            valuemap = new PathItem(valuemap, tagMode, itemStack).encode(null);

            ltree.put(TAG_VALUE, valuemap);
        }
        else if (element.getNullFlavour() != null){
            Map<String, Object> valuemap = PathMap.getInstance();

            valuemap = new SerialTree(valuemap).insert(null, element, TAG_NULL_FLAVOUR, new ElementValue(element.getNullFlavour()).normalize());

            //set path
            valuemap = new PathItem(valuemap, tagMode, itemStack).encode(null);

            ltree.put(TAG_VALUE, valuemap);
        }

        return ltree;
    }
}
