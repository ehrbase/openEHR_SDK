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

package org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller;

import com.nedap.archie.rm.datavalues.quantity.DvCount;
import java.util.Map;
import org.ehrbase.serialisation.walker.Context;

public class DvCountRMUnmarshaller extends AbstractRMUnmarshaller<DvCount> {

  /** {@inheritDoc} */
  @Override
  public Class<DvCount> getAssociatedClass() {
    return DvCount.class;
  }

  /** {@inheritDoc} */
  @Override
  public void handle(
      String currentTerm,
      DvCount rmObject,
      Map<String, String> currentValues,
      Context<Map<String, String>> context) {

    setValue(currentTerm, null, currentValues, rmObject::setMagnitude, Long.class);
  }
}
