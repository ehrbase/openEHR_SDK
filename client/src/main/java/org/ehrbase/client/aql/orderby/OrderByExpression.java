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

package org.ehrbase.client.aql.orderby;

import org.ehrbase.client.aql.field.SelectAqlField;

public interface OrderByExpression {

  String buildAql();

  default OrderByExpression andThen(OrderByExpression other) {
    return new AndThen(this, other);
  }

  default OrderByExpression andThenAscending(SelectAqlField<?> field) {
    return new AndThen(this, new Ascending(field));
  }

  default OrderByExpression andThenDescending(SelectAqlField<?> field) {
    return new AndThen(this, new Descending(field));
  }

  static OrderByExpression ascending(SelectAqlField<?> field) {
    return new Ascending(field);
  }

  static OrderByExpression descending(SelectAqlField<?> field) {
    return new Descending(field);
  }
}
