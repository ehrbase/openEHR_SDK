/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.normalizer;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.generic.PartySelf;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

public class Normalizer {

  public <T> T normalize(T t) {
    return normalize(t, true);
  }

  private <T> T normalize(T t, boolean root) {
    if (t == null) {
      return null;
    }

    if (DvInterval.class.isAssignableFrom(t.getClass())) {
      ((DvInterval) t).setLower(normalize(((DvInterval) t).getLower(), false));
      ((DvInterval) t).setUpper(normalize(((DvInterval) t).getUpper(), false));
      return t.equals(new DvInterval<>()) ? null : t;
    }

    List<Field> allFields =
        Arrays.stream(FieldUtils.getAllFields(t.getClass())).collect(Collectors.toList());
    boolean empty =
        allFields.stream()
            .map(f -> normalizeField(f, t))
            .reduce(!allFields.isEmpty(), (b1, b2) -> b1 && b2);
    if (PartySelf.class.isAssignableFrom(t.getClass())) {
      return t;
    } else if (empty && !root) {
      return null;
    } else {
      return t;
    }
  }

  @SuppressWarnings(value = "rawtypes,unchecked")
  private boolean normalizeField(Field field, Object object) {
    try {
      PropertyDescriptor propertyDescriptor =
          new PropertyDescriptor(field.getName(), object.getClass());
      Object value = propertyDescriptor.getReadMethod().invoke(object);

      Object normalize;
      if (value == null) {
        normalize = null;
      } else if (Collection.class.isAssignableFrom(value.getClass())) {

        Collection collection = (Collection) value;
        Collection newColl = new ArrayList();
        for (Object subValue : collection) {
          Object subNormal = normalize(subValue, false);
          if (subNormal != null) {
            newColl.add(subValue);
          }
        }
        collection.clear();
        collection.addAll(newColl);
        normalize = value;
      } else if (RMObject.class.isAssignableFrom(value.getClass())) {
        normalize = normalize(value, false);
      } else {
        normalize = value;
      }

      propertyDescriptor.getWriteMethod().invoke(object, normalize);
      return checkIsEmpty(field, normalize);
    } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
      return true;
    }
  }

  private boolean checkIsEmpty(Field field, Object normalize) {
    if (field.getDeclaringClass().equals(Locatable.class)
        || field.getDeclaringClass().equals(Entry.class)) {
      return true;
    } else if (Collection.class.isAssignableFrom(field.getType())) {
      return CollectionUtils.isEmpty((Collection<?>) normalize);
    } else {
      return normalize == null;
    }
  }
}
