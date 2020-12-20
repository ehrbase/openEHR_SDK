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

package org.ehrbase.response.ehrscape.query;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/** simple container to hold result records allowing duplicate column ids */
public class ResultHolder {

  private Multimap<String, List<Object>> resultMap = LinkedListMultimap.create();

  public void putResult(String columnId, Object result) {
    resultMap.put(columnId, Arrays.asList(result));
  }

  public Collection<String> columnIds() {
    return resultMap.keys();
  }

  public List<Object> values() {
    List<Object> results = new ArrayList<>();
    for (List<Object> item : resultMap.values()) {
      results.addAll(item);
    }
    return results;
  }
}
