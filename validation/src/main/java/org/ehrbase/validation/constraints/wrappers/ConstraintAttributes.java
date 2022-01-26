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

package org.ehrbase.validation.constraints.wrappers;

import org.ehrbase.validation.CompositionValidator;
import org.openehr.schemas.v1.CATTRIBUTE;

/**
 * @deprecated as of release 1.7, in favor of {@link CompositionValidator}
 */
@Deprecated(since = "1.7")
public class ConstraintAttributes {

  private CATTRIBUTE[] attributes;

  public ConstraintAttributes(CATTRIBUTE[] attributesArray) {
    this.attributes = attributesArray;
  }

  public boolean isMonoAttribute() {
    String attributeName = null;

    for (CATTRIBUTE cattribute : attributes) {
      if (attributeName == null) {
        attributeName = cattribute.getRmAttributeName();
      } else if (!attributeName.equals(cattribute.getRmAttributeName())) {
        return false;
      }

    }
    return true;
  }
}
