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

import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.walker.Context;

public class DvIdentifierRMUnmarshaller extends AbstractRMUnmarshaller<DvIdentifier> {

  /** {@inheritDoc} */
  @Override
  public Class<DvIdentifier> getAssociatedClass() {
    return DvIdentifier.class;
  }

  /** {@inheritDoc} */
  @Override
  public void handle(
      String currentTerm,
      DvIdentifier rmObject,
      Map<String, String> currentValues,
      Context<Map<String, String>> context) {

    setValue(currentTerm, "id", currentValues, rmObject::setId, String.class);
    if (StringUtils.isBlank(rmObject.getId())) {
      setValue(currentTerm, null, currentValues, rmObject::setId, String.class);
    }
    setValue(currentTerm, "issuer", currentValues, rmObject::setIssuer, String.class);
    setValue(currentTerm, "assigner", currentValues, rmObject::setAssigner, String.class);
    setValue(currentTerm, "type", currentValues, rmObject::setType, String.class);
  }
}
