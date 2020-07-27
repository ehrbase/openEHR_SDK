package org.ehrbase.serialisation.dbencoding;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

public class PathItem {

    private Map<String, Object> map;
    private final CompositionSerializer.WalkerOutputMode tag_mode;
    private final ItemStack itemStack;


    public PathItem(Map<String, Object> map, WalkerOutputMode tag_mode, ItemStack itemStack) {
        this.map = map;
        this.tag_mode = tag_mode;
        this.itemStack = itemStack;
    }

    public Map<String, Object> encode(String tag){
        Map<String, Object> retMap = map;

        switch (tag_mode) {
            case PATH:
                retMap = new SerialTree(map).insert(null, (Object)null, TAG_PATH, tag == null ? itemStack.pathStackDump() : itemStack.pathStackDump() + tag);
                break;
            case NAMED:
                retMap = new SerialTree(map).insert(null, (Object)null, TAG_PATH, tag == null ? itemStack.namedStackDump() : itemStack.namedStackDump() + tag.substring(1));
                break;
            case EXPANDED:
                retMap = new SerialTree(map).insert(null, (Object)null, TAG_PATH, tag == null ? itemStack.expandedStackDump() : itemStack.expandedStackDump() + tag.substring(1));
                break;
            case RAW:
//				putObject(map, TAG_PATH, tag == null ? itemStack.expandedStackDump() : itemStack.expandedStackDump()+tag.substring(1));
                break;
            default:
                throw new IllegalArgumentException("Invalid tagging mode!");
        }

        return retMap;
    }
}
