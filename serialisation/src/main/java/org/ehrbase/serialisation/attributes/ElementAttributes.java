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

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.TAG_NULL_FLAVOUR;
import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.TAG_VALUE;

import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datavalues.DvText;
import java.util.Map;
import org.ehrbase.serialisation.dbencoding.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** populate the attributes for RM Elements */
public class ElementAttributes extends ItemAttributes {

  private static final String INITIAL_DUMMY_PREFIX = "$*>";

  private boolean allElements = false;
  private Logger log = LoggerFactory.getLogger(ElementAttributes.class.getSimpleName());

  public ElementAttributes(
      CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
    super(compositionSerializer, itemStack, map);
  }

  /**
   * map the value or null_flavour of an Element
   *
   * @param element
   * @return
   */
  public Map<String, Object> toMap(Element element) {
    Map<String, Object> ltree = map;

    // to deal with ITEM_SINGLE initial value
    if (element.getName().getValue().startsWith(INITIAL_DUMMY_PREFIX)) {
      if (allElements) { // strip the prefix since it is for an example
        DvText elementName = element.getName();
        elementName.setValue(elementName.getValue().substring(INITIAL_DUMMY_PREFIX.length()));
        element.setName(elementName);
      } else return ltree;
    }
    Map<String, Object> valuemap = PathMap.getInstance();

    if (element.getValue() != null && !element.getValue().toString().isEmpty()) {
      log.debug(itemStack.pathStackDump() + "=" + element.getValue());

      if (element.getValue() != null && !element.getValue().toString().isEmpty())
        valuemap =
            new SerialTree(valuemap)
                .insert(
                    new CompositeClassName(element.getValue()).toString(),
                    element,
                    TAG_VALUE,
                    new ElementValue(element.getValue()).normalize());
    } else if (element.getNullFlavour() != null) {
      valuemap =
          new SerialTree(valuemap)
              .insert(
                  null,
                  element,
                  TAG_NULL_FLAVOUR,
                  new ElementValue(element.getNullFlavour()).normalize());
    }

    // set path
    valuemap = new PathItem(valuemap, tagMode, itemStack).encode(null);

    ltree.put(TAG_VALUE, valuemap);

    return ltree;
  }
}
