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

package org.ehrbase.client.aql.field;

import java.util.List;
import org.ehrbase.client.aql.containment.Containment;

public class AqlFieldImp<T> implements SelectAqlField<T> {

  private final String name;
  private final String path;
  private final Class<T> valueClass;
  private final Class<?> entityClass;
  private final Containment containment;
  private final boolean multiValued;

  protected AqlFieldImp(Class<T> clazz) {
    this.valueClass = clazz;
    this.name = null;
    this.path = null;
    this.entityClass = null;
    this.containment = null;
    this.multiValued = List.class.isAssignableFrom(clazz);
  }

  public AqlFieldImp(
      Class<?> entityClass,
      String path,
      String name,
      Class<T> valueClass,
      Containment containment) {
    this.name = name;
    this.path = path;
    this.valueClass = valueClass;
    this.entityClass = entityClass;
    this.containment = containment;
    this.multiValued = false;
  }

  protected AqlFieldImp(
      Class<?> entityClass,
      String path,
      String name,
      Class<T> valueClass,
      boolean multiValued,
      Containment containment) {
    this.name = name;
    this.path = path;
    this.valueClass = valueClass;
    this.entityClass = entityClass;
    this.multiValued = multiValued;
    this.containment = containment;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getPath() {
    return path;
  }

  @Override
  public Class<?> getEntityClass() {
    return entityClass;
  }

  @Override
  public Class<T> getValueClass() {
    return valueClass;
  }

  @Override
  public boolean isMultiValued() {
    return multiValued;
  }

  @Override
  public String buildAQL() {
    return containment.getVariableName() + path.replace("|", "/");
  }
}
