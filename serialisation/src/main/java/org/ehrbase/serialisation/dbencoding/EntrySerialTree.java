package org.ehrbase.serialisation.dbencoding;

import com.nedap.archie.rm.composition.*;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;
import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.TAG_STATE;

public class EntrySerialTree extends SerialTree {

    private final CompositionSerializer.WalkerOutputMode tagMode;

    public EntrySerialTree(Map<String, Object> map, CompositionSerializer.WalkerOutputMode tagMode) {
        super(map);
        this.tagMode = tagMode;
    }

    public Map<String, Object> insert(Composition composition, String attribute, Object addStructure){
        Map<String, Object> retMap;
        switch (attribute){
            case TAG_COMPOSITION:
                retMap = insert(new SimpleClassName(composition).toString(), composition, new NodeEncoding(tagMode).tag(TAG_COMPOSITION, composition, map), addStructure);
                break;
            default:
                retMap = map;
        }

        return retMap;
    }

    public Map<String, Object> insert(ContentItem contentItem, String attribute, Object addStructure){
        Map<String, Object> retMap;
        switch (attribute){
            case TAG_CONTENT:
                if (addStructure != null && !((Map)addStructure).containsKey(TAG_NAME))
                    ((Map)addStructure).put(TAG_NAME, new NameAsDvText(contentItem.getName()).toMap()); //this fixes the issue with SECTION name
                retMap = insert(null, contentItem, new NodeEncoding(tagMode).tag(TAG_CONTENT, contentItem, map), addStructure);
                break;
            default:
                retMap = map;
        }

        return retMap;
    }

    public Map<String, Object> insert(Observation observation, String attribute, Object addStructure){
        Map<String, Object> retMap;
        switch (attribute){
            case TAG_PROTOCOL:
                retMap = insert(new SimpleClassName(observation).toString(), observation, new NodeEncoding(tagMode).tag(TAG_PROTOCOL, observation.getProtocol(), map), addStructure);
                break;
            case TAG_DATA:
                retMap = insert(new SimpleClassName(observation).toString(), observation, new NodeEncoding(tagMode).tag(TAG_DATA, observation.getData(), map), addStructure);
                break;
            case TAG_STATE:
                retMap = insert(new SimpleClassName(observation).toString(), observation, new NodeEncoding(tagMode).tag(TAG_STATE, observation.getState(), map), addStructure);
                break;
            default:
                retMap = map;
        }

        return retMap;
    }

    public Map<String, Object> insert(Evaluation evaluation, String attribute, Object addStructure){
        Map<String, Object> retMap;
        switch (attribute){
            case TAG_PROTOCOL:
                retMap = insert(new SimpleClassName(evaluation).toString(), evaluation, new NodeEncoding(tagMode).tag(TAG_PROTOCOL, evaluation.getProtocol(), map), addStructure);
                break;
            case TAG_DATA:
                retMap = insert(new SimpleClassName(evaluation).toString(), evaluation, new NodeEncoding(tagMode).tag(TAG_DATA, evaluation.getData(), map), addStructure);
                break;
            default:
                retMap = map;
        }

        return retMap;
    }

    public Map<String, Object> insert(Instruction instruction, String attribute, Object addStructure){
        Map<String, Object> retMap;
        switch (attribute){
            case TAG_PROTOCOL:
                retMap = insert(new SimpleClassName(instruction).toString(), instruction, new NodeEncoding(tagMode).tag(TAG_PROTOCOL, instruction.getProtocol(), map), addStructure);
                break;
            case TAG_ACTIVITIES:
                retMap = insert(new SimpleClassName(instruction).toString(), instruction, TAG_ACTIVITIES, addStructure);
                break;
            default:
                retMap = map;
        }

        return retMap;
    }

    public Map<String, Object> insert(Activity activity, String attribute, Object addStructure){
        Map<String, Object> retMap;
        switch (attribute){
            case TAG_ACTIVITIES:
                retMap = insert(new SimpleClassName(activity).toString(), activity, new NodeEncoding(tagMode).tag(TAG_ACTIVITIES, activity, map), addStructure);
                break;
            default:
                retMap = map;
        }

        return retMap;
    }

    public Map<String, Object> insert(Action action, String attribute, Object addStructure){
        Map<String, Object> retMap;
        switch (attribute){
            case TAG_PROTOCOL:
                retMap = insert(new SimpleClassName(action).toString(), action, new NodeEncoding(tagMode).tag(TAG_PROTOCOL, action.getProtocol(), map), addStructure);
                break;
            case TAG_DESCRIPTION:
                retMap = insert(new SimpleClassName(action).toString(), action, new NodeEncoding(tagMode).tag(TAG_DATA, action.getDescription(), map), addStructure);
                break;
            default:
                retMap = map;
        }

        return retMap;
    }

    public Map<String, Object> insert(History history, String attribute, Object addStructure){
        Map<String, Object> retMap;
        switch (attribute){
            case TAG_SUMMARY:
                retMap = insert(new SimpleClassName(history).toString(), history, new NodeEncoding(tagMode).tag(TAG_SUMMARY, history, map), addStructure);
                break;
            case TAG_EVENTS:
                retMap = insert(new SimpleClassName(history).toString(), history, new NodeEncoding(tagMode).tag(TAG_EVENTS, null, map), addStructure);
                break;
            default:
                retMap = map;
        }

        return retMap;
    }

    public Map<String, Object> insert(Event event, String attribute, Object addStructure){
        Map<String, Object> retMap;
        switch (attribute){
            case TAG_DATA:
                retMap = insert(new SimpleClassName(event).toString(), event, new NodeEncoding(tagMode).tag(TAG_DATA, event.getData(), map), addStructure);
                break;
            case TAG_STATE:
                retMap = insert(new SimpleClassName(event).toString(), event, new NodeEncoding(tagMode).tag(TAG_STATE, event.getState(), map), addStructure);
                break;
            default:
                retMap = map;
        }

        return retMap;
    }
}
