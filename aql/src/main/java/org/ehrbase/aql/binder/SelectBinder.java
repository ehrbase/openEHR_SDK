/*
 *
 * Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 * This file is part of Project EHRbase
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
 *
 */

package org.ehrbase.aql.binder;


import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.ehrbase.aql.dto.select.SelectStatementDto;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.NativeSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

import java.util.Map;

public class SelectBinder {

  public SelectAqlField<Object> bind(
          SelectStatementDto dto, Map<Integer, Containment> containmentMap) {
    SelectAqlField<Object> selectAqlField;
    if (dto instanceof SelectFieldDto) {
      selectAqlField =
          new NativeSelectAqlField<>(
              containmentMap.get(((SelectFieldDto) dto).getContainmentId()),
              ((SelectFieldDto) dto).getAqlPath(),
              Object.class);
    } else {
      throw new RuntimeException();
    }
    return selectAqlField;
  }
}
