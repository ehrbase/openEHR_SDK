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

package org.ehrbase.client.aql.containment;

import org.ehrbase.client.aql.query.EntityQuery;

public class ContainmentPath implements ContainmentExpression {

  private final Containment root;

  ContainmentPath(Containment root) {
    this.root = root;
  }

  @Override
  public String buildAQL() {
    return root.buildAQL();
  }

  @Override
  public void bindQuery(EntityQuery<?> query) {
    root.bindQuery(query);
  }

  public ContainmentPath contains(Containment contains) {
    Containment leaf = root;
    while (leaf.getContains() != null) {
      leaf = (Containment) leaf.getContains();
    }
    leaf.setContains(contains);
    return this;
  }

  public ContainmentExpression contains(ContainmentExpression contains) {
    Containment leaf = root;
    while (leaf.getContains() != null) {
      leaf = (Containment) leaf.getContains();
    }
    leaf.setContains(contains);
    return this;
  }
}
