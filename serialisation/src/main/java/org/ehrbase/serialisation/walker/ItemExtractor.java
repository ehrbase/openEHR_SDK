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

package org.ehrbase.serialisation.walker;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.Pathable;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.FlatPath;

public class ItemExtractor {
  private RMObject currentRM;
  private WebTemplateNode currentNode;
  private WebTemplateNode childNode;
  private boolean isChoice;
  private String relativeAql;
  private Object child;
  private String parentAql;
  private Object parent;

  public ItemExtractor(
      RMObject currentRM,
      WebTemplateNode currentNode,
      WebTemplateNode childNode,
      boolean isChoice) {
    this.currentRM = currentRM;
    this.currentNode = currentNode;
    this.childNode = childNode;
    this.isChoice = isChoice;
  }

  public FlatPath getRelativeAql() {
    return new FlatPath(relativeAql);
  }

  public Object getChild() {
    return child;
  }

  public ItemExtractor invoke() {
    relativeAql =
        StringUtils.removeEnd(
            StringUtils.removeStart(childNode.getAqlPath(), currentNode.getAqlPath()), "/");
    FlatPath childPath = new FlatPath(relativeAql);
    parentAql =
        StringUtils.removeEnd(
            childPath.format(false),
            childPath.format(false).substring(childPath.format(false).lastIndexOf("/")));
    if (StringUtils.isBlank(parentAql)) {
      parentAql = "/";
    }

    if (currentRM instanceof Pathable) {
      try {
        child = ((Pathable) currentRM).itemsAtPath(childPath.format(false));
        if (child == null || ((List) child).isEmpty()) {
          child = ((Pathable) currentRM).itemAtPath(childPath.format(false));
        }
      } catch (RuntimeException e) {
        child = null;
      }
      parent = ((Pathable) currentRM).itemAtPath(parentAql);
    } else if (currentRM instanceof DvInterval) {
      if (relativeAql.contains("upper_included")) {
        child = new RmBoolean(((DvInterval<?>) currentRM).isUpperIncluded());
      } else if (relativeAql.contains("lower_included")) {
        child = new RmBoolean(((DvInterval<?>) currentRM).isLowerIncluded());
      } else if (relativeAql.contains("lower")) {
        child = ((DvInterval<?>) currentRM).getLower();
      } else if (relativeAql.contains("upper")) {
        child = ((DvInterval<?>) currentRM).getUpper();
      }
      parent = currentRM;
    } else {
      throw new SdkException(
          String.format("Can not extract from class %s", currentRM.getClass().getSimpleName()));
    }

    if (StringUtils.isNotBlank(childPath.findOtherPredicate("name/value"))
        && child instanceof List
        && Locatable.class.isAssignableFrom(
            Walker.ARCHIE_RM_INFO_LOOKUP.getClass(childNode.getRmType()))) {
      child =
          ((List) child)
              .stream()
                  .filter(
                      c ->
                          childPath
                              .findOtherPredicate("name/value")
                              .equals(((Locatable) c).getNameAsString()))
                  .collect(Collectors.toList());
      // if name not found return null
      if (((List<?>) child).isEmpty()) {
        child = null;
      }
    }
    if (isChoice && child instanceof List) {
      child =
          ((List) child)
              .stream()
                  .filter(
                      c ->
                          Walker.ARCHIE_RM_INFO_LOOKUP
                              .getTypeInfo(c.getClass())
                              .getRmName()
                              .equals(childNode.getRmType()))
                  .collect(Collectors.toList());
      // if rmType not found return null
      if (((List<?>) child).isEmpty()) {
        child = null;
      }
    }

    if (childNode.getMax() == 1 && child instanceof List) {

      if (((List<?>) child).isEmpty()) {
        child = null;
      } else {
        child = ((List) child).get(0);
      }
    }

    if (child instanceof Element && !childNode.getRmType().equals("ELEMENT")) {
      child = ((Element) child).getValue();
    }
    return this;
  }

  public FlatPath getParentAql() {
    return new FlatPath(parentAql);
  }

  public Object getParent() {
    return parent;
  }
}
