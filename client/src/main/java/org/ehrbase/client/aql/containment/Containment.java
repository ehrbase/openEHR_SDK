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

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.aql.query.EntityQuery;

/**
 * Represents an AQL Containment. see <a
 * href="https://specifications.openehr.org/releases/QUERY/latest/AQL.html#_containment">Archetype
 * Query Language<a/>
 */
public class Containment implements ContainmentExpression {

  private static final ArchieRMInfoLookup RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

  private final Class<? extends Locatable> type;
  private final String archetype;
  private EntityQuery<?> query;
  private ContainmentExpression contains;
  private String typeName;

  /**
   * Builds a Containment from an archetype HRID
   *
   * @param archetype The Archetype HRID. Sie <a
   *     href="https://specifications.openehr.org/releases/AM/latest/Overview.html#_identification_and_the_virtual_archetype_space">Identification
   *     and the Virtual Archetype Space</a>
   */
  public Containment(String archetype) {

    typeName = StringUtils.substringBetween(archetype, "openEHR-EHR-", ".");
    if (StringUtils.isBlank(typeName)) {
      typeName = archetype;
      archetype = null;
    }
    this.archetype = archetype;

    if ("EHR".equals(typeName)) {
      type = null;
    } else {
      type = RM_INFO_LOOKUP.getClass(typeName);
    }
  }

  @Override
  public String buildAQL() {
    StringBuilder sb = new StringBuilder();
    sb.append(typeName.toUpperCase()).append(" ").append(getVariableName());
    if (archetype != null) {
      sb.append("[").append(archetype).append("]");
    }
    if (contains != null) {
      sb.append(" contains ").append(contains.buildAQL());
    }
    return sb.toString();
  }

  public String getVariableName() {
    return query.buildVariabelName(this);
  }

  @Override
  public void bindQuery(EntityQuery<?> query) {
    this.query = query;
    if (contains != null) {
      contains.bindQuery(query);
    }
  }

  public Class<? extends Locatable> getType() {
    return type;
  }

  public ContainmentExpression getContains() {
    return contains;
  }

  public void setContains(ContainmentExpression contains) {
    this.contains = contains;
  }

  public ContainmentPath contains(Containment contains) {
    ContainmentPath tree = new ContainmentPath(this);
    return tree.contains(contains);
  }

  public ContainmentExpression contains(ContainmentExpression contains) {
    this.contains = contains;
    return this;
  }
}
