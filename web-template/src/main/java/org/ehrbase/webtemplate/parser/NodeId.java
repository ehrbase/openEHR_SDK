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

package org.ehrbase.webtemplate.parser;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class NodeId implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String className;
  private final String nodeId;

  public NodeId(String nodeId) {
    // AtCode
    if (nodeId.startsWith("at")) {
      this.nodeId = nodeId;
      className = null;
    }
    // ArchetypeId
    else if (nodeId.startsWith("openEHR-EHR-")) {
      className = StringUtils.substringBetween(nodeId, "openEHR-EHR-", ".");
      this.nodeId = nodeId;
    }
    // just classname
    else {
      className = nodeId;
      this.nodeId = null;
    }
  }

  public NodeId(String className, String nodeId) {
    this.className = className;
    this.nodeId = nodeId;
  }

  public boolean isArchetypeId() {
    return StringUtils.isNotBlank(className) && StringUtils.isNotBlank(nodeId);
  }

  public String getClassName() {
    return className;
  }

  public String getNodeId() {
    return nodeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NodeId nodeId1 = (NodeId) o;
    return Objects.equals(className, nodeId1.className) && Objects.equals(nodeId, nodeId1.nodeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(className, nodeId);
  }

  @Override
  public String toString() {
    return "NodeId{" + "className='" + className + '\'' + ", nodeId='" + nodeId + '\'' + '}';
  }
}
