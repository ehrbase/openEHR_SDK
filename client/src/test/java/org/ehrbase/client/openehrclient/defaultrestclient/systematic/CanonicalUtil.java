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

package org.ehrbase.client.openehrclient.defaultrestclient.systematic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import org.ehrbase.serialisation.dbencoding.wrappers.json.I_DvTypeAdapter;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.validation.constraints.util.SnakeToCamel;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class CanonicalUtil {

    public static String toJson(Object anRmObjectAsString) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        return gson.toJson(anRmObjectAsString);
    }

    public static Map<String, Object> toMap(RMObject anRmObject) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String asString = new CanonicalJson().marshal(anRmObject);
        return gson.fromJson(asString, Map.class);
    }

    public static String toRmJson(RMObject anRmObject) {
        return new CanonicalJson().marshal(anRmObject);
    }

    public static RMObject toRmObject(Map<String, Object> anRmObjectAsMap, Class rmClass) {
        return new CanonicalJson().unmarshal(toJson(anRmObjectAsMap), rmClass);
    }

    public static Object valueObject(Object anObject) {

        Object retObject = anObject;

        if (anObject instanceof Map) {
            //perform the conversion using the type if any
            if (((Map) anObject).containsKey(I_DvTypeAdapter.AT_TYPE)) {
                //get the actual type from Archie lookup
                Class objectRmClass = ArchieRMInfoLookup.getInstance().getClass((String) ((Map) anObject).get(I_DvTypeAdapter.AT_TYPE));
                if (objectRmClass != null)
                    retObject = toRmObject(((Map<String, Object>) anObject), objectRmClass);
            }
        }

        retObject = new SpecialCase().transform(retObject);

        return retObject;
    }

    public static RMObject toRmObject(String anRmObjectAsString, Class rmClass) {
        return new CanonicalJson().unmarshal(anRmObjectAsString, rmClass);
    }

    public static Object compareArbitraryRmClass(Map<String, Object> anRmObjectAsMap, Class rmClass, RMObject compareExpectedObject) {
        RMObject actualRmObject = toRmObject(anRmObjectAsMap, rmClass);
        assertThat(actualRmObject).isEqualTo(compareExpectedObject);
        return null;
    }

    /**
     * resolves an attribute of a RMObject
     * NB. borrowed from CAttribute.java in validation
     *
     * @param obj
     * @param attributePath
     * @return
     */
    private static Object getAttributeValue(Object obj, String attributePath) {
        if (obj == null)
            return null;

        Class rmClass = obj.getClass();
        Object value;
        Method getter;
        String getterName;

        if (attributePath.startsWith("is_")) {
            //conventionally, a getter for a boolean uses 'is' as a prefix
            getterName = "is" + new SnakeToCamel(attributePath.substring(3)).convert();
        } else
            getterName = "get" + new SnakeToCamel(attributePath).convert();

        try {
            getter = rmClass.getMethod(getterName);
            value = getter.invoke(obj, null);
        } catch (Exception e) {
            throw new IllegalStateException("unresolved attribute:" + attributePath + " for class:" + rmClass);
        }

        return value;
    }

    /**
     * Retrieve the value of an attribute identified by a path.
     * NB. This is not supposed to replace Locatable.itemsAtPath(), but instead it should be used
     * to resolve values in non locatable RMObject
     *
     * @param root
     * @param attributePath
     * @return
     * @see com.nedap.archie.rm.archetyped.Locatable
     */
    private static Object getAttributePathValue(Object root, String attributePath) {
        Object rmObject = root;

        if (rmObject instanceof Locatable) {
            try {
                List<Object> items = ((Locatable) root).itemsAtPath(attributePath);
                if (!items.isEmpty())
                    rmObject = items.get(0);
                else
                    rmObject = null;
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid path:" + attributePath);
            }
        } else {
            if (!attributePath.contains("/"))
                rmObject = getAttributeValue(root, attributePath);
            else {
                String[] pathItems = attributePath.split("/");

                int iteration = 0;

                for (String item : pathItems) {
                    rmObject = getAttributeValue(rmObject, item);
                    iteration++;
                    if (rmObject instanceof Locatable) { //f.e. items in other_details
                        rmObject = locatableValueItem((RMObject) rmObject, iteration, Arrays.asList(pathItems).subList(iteration, pathItems.length));
                        break;
                    }
                }
            }
        }
        return rmObject;
    }

    public static Object attributeValueAt(Object root, String path) {
        Object evaluated = getAttributePathValue(root, path);
        return new SpecialCase().transform(evaluated);
    }

    public static Object locatableValueItem(RMObject rmObject, int iteration, List<String> pathItems) {
        String actualItemPath;
        if (pathItems.isEmpty())
            actualItemPath = "/";
        else
            actualItemPath = String.join("/", pathItems);
        return ((Locatable) rmObject).itemsAtPath(actualItemPath).get(0);
    }
}
