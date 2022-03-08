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

package org.ehrbase.client.flattener;

import org.ehrbase.webtemplate.parser.AqlPath;

import java.lang.reflect.Field;
import java.util.Map;

class DtoWithMatchingFields {

  private final Object dto;
  private final Map<AqlPath, Field> fieldByPath;

  DtoWithMatchingFields(Object dto, Map<AqlPath, Field> fieldByPath) {
    this.dto = dto;
    this.fieldByPath = fieldByPath;
  }

  Object getDto() {
    return dto;
  }

  Map<AqlPath, Field> getFieldByPath() {
    return fieldByPath;
  }
}
