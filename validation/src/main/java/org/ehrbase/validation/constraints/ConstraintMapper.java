/*
* Modifications copyright (C) 2019 Christian Chevalley, Vitasystems GmbH and Hannover Medical School.

* This file is part of Project EHRbase

* Copyright (c) 2015 Christian Chevalley
* This file is part of Project Ethercis
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.ehrbase.validation.constraints;

import com.nedap.archie.base.MultiplicityInterval;
import java.io.Serializable;
import java.util.*;

/** Created by christian on 8/9/2016. */
public abstract class ConstraintMapper implements Serializable {

  protected Map<String, Map<String, String>> localTerminologyLookup;

  public class OccurrenceItem implements Serializable {
    private ConstraintOccurrences constraintOccurrences;
    private MultiplicityInterval existence;

    OccurrenceItem(ConstraintOccurrences constraintOccurrences, MultiplicityInterval existence) {
      this.constraintOccurrences = constraintOccurrences;
      this.existence = existence;
    }

    public ConstraintOccurrences getConstraintOccurrences() {
      return constraintOccurrences;
    }

    public MultiplicityInterval getExistence() {
      return existence;
    }
  }

  public class CardinalityItem implements Serializable {
    private ConstraintOccurrences existence;
    private ConstraintOccurrences cardinality;

    CardinalityItem(ConstraintOccurrences existence, ConstraintOccurrences cardinality) {
      this.existence = existence;
      this.cardinality = cardinality;
    }

    public ConstraintOccurrences getExistence() {
      return existence;
    }

    public ConstraintOccurrences getCardinality() {
      return cardinality;
    }
  }

  protected Map<String, OccurrenceItem> watchList = new HashMap<>(); // required nodes
  protected Set<String> validNodeList = new HashSet<>(); // valid nodes
  protected Map<String, CardinalityItem> cardinalityList = new HashMap<>(); // valid nodes
  protected Map<String, ConstraintOccurrences> occurrencesMap =
      new HashMap<>(); // transitive list of occurrences

  public Map<String, CardinalityItem> getCardinalityList() {
    return cardinalityList;
  }

  public abstract class ConstraintItem implements Serializable {
    private String path;

    ConstraintItem(String path) {
      this.path = path;
    }

    public String getPath() {
      return path;
    }
  }

  Map<String, List<ConstraintItem>> elementConstraintMap = new HashMap<>();

  public List<ConstraintItem> getConstraintItem(String key) {
    return elementConstraintMap.get(key);
  }

  public Map<String, Map<String, String>> getLocalTerminologyLookup() {
    return localTerminologyLookup;
  }

  public boolean isValidNode(String path) {
    return validNodeList.contains(path);
  }

  public void updateWatchList(String path) {
    // retrieve path in watch list and remove it
    watchList.remove(path);
  }

  Iterator<Map.Entry<String, List<ConstraintItem>>> getElementConstraintIterator() {
    return elementConstraintMap.entrySet().iterator();
  }

  public Map<String, ConstraintOccurrences> getOccurrencesMap() {
    return occurrencesMap;
  }
}
