/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.archetyped.Locatable;
import org.ehrbase.serialisation.dbencoding.CompositionSerializer;
import org.ehrbase.serialisation.dbencoding.ItemStack;
import org.ehrbase.serialisation.dbencoding.NameAsDvText;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;
/**
 * populate the attributes for RM Locatable
 * Most RM object in a Composition inherit from this class
 */
public abstract class LocatableAttributes extends RMAttributes {

    public LocatableAttributes(CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
        super(compositionSerializer, itemStack, map);
    }

    /**
     * map Locatable attributes in a queryable (AQL) form, that is the key follows openEHR RM UML conventions
     * lower case, snake_case
     * @param locatable
     * @return
     */
    protected Map<String, Object> toMap(Locatable locatable){
        //add complementary attributes

        if (locatable.getArchetypeNodeId() != null){
            map.put(TAG_ARCHETYPE_NODE_ID, locatable.getArchetypeNodeId());
        }
        if (locatable.getArchetypeDetails() != null){
            map = toMap(TAG_ARCHETYPE_DETAILS, locatable.getArchetypeDetails(), NO_NAME);
        }
        if (locatable.getFeederAudit() != null){
            map.put(TAG_FEEDER_AUDIT, new FeederAuditAttributes(locatable.getFeederAudit()).toMap());
        }
        if (locatable.getUid() != null){
            map = toMap(TAG_UID, locatable.getUid(), NO_NAME);
        }
        if (locatable.getLinks() != null && !locatable.getLinks().isEmpty()){
            map.put(TAG_LINKS, new LinksAttributes(locatable.getLinks()).toMap());
        }
        if (!map.containsKey(TAG_NAME) && locatable.getName() != null){ //since name maybe resolved from the archetype node id
            map.put(TAG_NAME, new NameAsDvText(locatable.getName()).toMap());
        }

        return map;
    }
}
