/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.flattener;

import com.google.common.reflect.TypeToken;
import com.nedap.archie.aom.CComplexObject;
import com.nedap.archie.creation.RMObjectCreator;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rm.support.identification.TerminologyId;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMAttributeInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.text.CaseUtils;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.building.OptSkeletonBuilder;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.normalizer.Normalizer;
import org.ehrbase.client.templateprovider.TemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class Unflattener {

    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();
    public static final Normalizer NORMALIZER = new Normalizer();
    public static final OptSkeletonBuilder OPT_SKELETON_BUILDER = new OptSkeletonBuilder();
    private static final RMObjectCreator RM_OBJECT_CREATOR = new RMObjectCreator(ARCHIE_RM_INFO_LOOKUP);
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TemplateProvider templateProvider;

    public Unflattener(TemplateProvider templateProvider) {

        this.templateProvider = templateProvider;
    }

    public RMObject unflatten(Object dto) {
        Template template = dto.getClass().getAnnotation(Template.class);

        OPERATIONALTEMPLATE operationalTemplate = templateProvider.find(template.value()).orElseThrow(() -> new ClientException(String.format("Unknown Template %s", template.value())));
        Locatable generate = (Locatable) OPT_SKELETON_BUILDER.generate(operationalTemplate);

        mapDtoToEntity(dto, generate);
        return NORMALIZER.normalize(generate);
    }

    private void mapDtoToEntity(Object dto, RMObject generate) {
        Map<String, Object> valueMap = buildValueMap(dto);
        valueMap.forEach((key, value) -> setValueAtPath(generate, key, value));
    }

    private void setValueAtPath(RMObject locatable, String path, Object value) {

        boolean multi = value instanceof List;
        ItemExtractor itemExtractor = new ItemExtractor(locatable, path, multi);
        String childName = itemExtractor.getChildName();
        Object child = itemExtractor.getChild();
        Object parent = itemExtractor.getParent();

        if (multi) {
            List valueList = (List) value;
            if (CollectionUtils.isNotEmpty((List) child)) {
                List childList = new ArrayList();
                Object prototype = ((List) child).get(0);
                childList.add(prototype);
                for (int i = 1; i < valueList.size(); i++) {
                    RMObject deepClone = deepClone((RMObject) prototype);
                    childList.add(deepClone);
                    RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(parent, childName, deepClone);
                }
                for (int i = 0; i < valueList.size(); i++) {
                    handleSingleValue(valueList.get(i), childName, childList.get(i), parent);
                }
            } else {
                for (Object o : valueList) {
                    handleSingleValue(o, childName, null, parent);
                }
            }
        } else {
            handleSingleValue(value, childName, child, parent);
        }
    }

    private void handleSingleValue(Object value, String childName, Object child, Object parent) {

        if (value != null && value.getClass().isAnnotationPresent(OptionFor.class)) {

            String rmclass = value.getClass().getAnnotation(OptionFor.class).value();
            CComplexObject elementConstraint = new CComplexObject();
            elementConstraint.setRmTypeName(rmclass);
            Object newChild = RM_OBJECT_CREATOR.create(elementConstraint);
            if (Event.class.isAssignableFrom(newChild.getClass())) {
                Event newEvent = (Event) newChild;
                Event oldEvent = (Event) child;
                newEvent.setState(oldEvent.getState());
                newEvent.setData(oldEvent.getData());
                newEvent.setArchetypeDetails(oldEvent.getArchetypeDetails());
                newEvent.setArchetypeNodeId(oldEvent.getArchetypeNodeId());
                newEvent.setName(oldEvent.getName());
                newEvent.setTime(oldEvent.getTime());
                if (IntervalEvent.class.isAssignableFrom(newEvent.getClass())) {
                    ((IntervalEvent) newEvent).setWidth(new DvDuration());
                }

            }
            RMAttributeInfo attributeInfo = ARCHIE_RM_INFO_LOOKUP.getAttributeInfo(parent.getClass(), childName);
            if (attributeInfo.isMultipleValued()) {
                try {
                    Object invoke = attributeInfo.getGetMethod().invoke(parent);
                    if (Collection.class.isAssignableFrom(invoke.getClass())) {
                        ((Collection) invoke).remove(child);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.warn(e.getMessage(), e);
                }
            }
            child = newChild;
            RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(parent, childName, Collections.singletonList(child));
        }

        if (value == null) {
            //NOP
        } else if (EnumValueSet.class.isAssignableFrom(value.getClass()) && DvCodedText.class.isAssignableFrom(parent.getClass())) {
            EnumValueSet valueSet = (EnumValueSet) value;
            DvCodedText dvCodedText = (DvCodedText) parent;
            dvCodedText.setValue(valueSet.getValue());
            dvCodedText.setDefiningCode(new CodePhrase(new TerminologyId(valueSet.getTerminologyId()), valueSet.getCode()));
        } else if (EnumValueSet.class.isAssignableFrom(value.getClass()) && DvCodedText.class.isAssignableFrom(ARCHIE_RM_INFO_LOOKUP.getAttributeInfo(parent.getClass(), childName).getType())) {
            EnumValueSet valueSet = (EnumValueSet) value;
            DvCodedText dvCodedText = new DvCodedText();
            dvCodedText.setValue(valueSet.getValue());
            dvCodedText.setDefiningCode(new CodePhrase(new TerminologyId(valueSet.getTerminologyId()), valueSet.getCode()));
            RM_OBJECT_CREATOR.set(parent, childName, Collections.singletonList(dvCodedText));
        } else if (EnumValueSet.class.isAssignableFrom(value.getClass())) {
            EnumValueSet valueSet = (EnumValueSet) value;
            CodePhrase codePhrase = new CodePhrase(new TerminologyId(valueSet.getTerminologyId()), valueSet.getCode());
            RM_OBJECT_CREATOR.set(parent, childName, Collections.singletonList(codePhrase));
        } else if (extractType(toCamelCase(childName), parent).isAssignableFrom(value.getClass())) {
            RMAttributeInfo attributeInfo = ARCHIE_RM_INFO_LOOKUP.getAttributeInfo(parent.getClass(), childName);
            if (attributeInfo.isMultipleValued()) {
                try {
                    Object invoke = attributeInfo.getGetMethod().invoke(parent);
                    if (Collection.class.isAssignableFrom(invoke.getClass())) {
                        ((Collection) invoke).remove(child);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.warn(e.getMessage(), e);
                }
            }
            RM_OBJECT_CREATOR.addElementToListOrSetSingleValues(parent, childName, Collections.singletonList(value));
        } else if (value.getClass().isAnnotationPresent(Entity.class)) {
            mapDtoToEntity(value, (RMObject) child);
        } else {
            logger.warn("Unhandled child {} in {}", childName, parent);
        }

    }

    public Class<?> unwarap(Field field) {
        try {
            if (List.class.isAssignableFrom(field.getType())) {
                Type actualTypeArgument = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];

                Class<?> aClass = TypeToken.of(actualTypeArgument).getRawType();
                if (aClass != null) {
                    return aClass;
                } else {
                    return Dummy.class;
                }

            } else {
                return field.getType();
            }
        } catch (Throwable e) {
            return Dummy.class;
        }
    }

    private Class<?> extractType(String childName, Object parent) {

        Optional<? extends Class<?>> type = Arrays.stream(FieldUtils.getAllFields(parent.getClass()))
                .filter(f -> f.getName().equals(childName))
                .map(this::unwarap)
                .findAny();
        if (type.isPresent()) {
            return type.get();
        } else {
            logger.warn("No field {} for class {}", childName, parent.getClass());
            return Dummy.class;
        }
    }

    private <T extends RMObject> T deepClone(RMObject rmObject) {
        CanonicalJson canonicalXML = new CanonicalJson();
        return (T) canonicalXML.unmarshal(canonicalXML.marshal(rmObject), rmObject.getClass());
    }

    private Map<String, Object> buildValueMap(Object dto) {
        Map<String, Object> valueMap = new HashMap<>();

        for (Field field : dto.getClass().getDeclaredFields()) {
            Path path = field.getAnnotation(Path.class);
            if (path != null) {
                valueMap.put(path.value(), readField(field, dto));
            }
        }
        return valueMap;
    }

    private Object readField(Field field, Object dto) {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), dto.getClass());
            return propertyDescriptor.getReadMethod().invoke(dto);
        } catch (InvocationTargetException | IllegalAccessException | IntrospectionException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    private String toCamelCase(String childName) {
        return CaseUtils.toCamelCase(childName, false, '_');
    }
}