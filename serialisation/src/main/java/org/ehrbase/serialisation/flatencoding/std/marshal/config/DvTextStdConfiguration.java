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

package org.ehrbase.serialisation.flatencoding.std.marshal.config;

import com.nedap.archie.rm.datavalues.DvText;
import java.util.HashMap;
import java.util.Map;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.model.WebTemplateInput;

public class DvTextStdConfiguration extends AbstractsStdConfig<DvText> {
  @Override
  /** {@inheritDoc} */
  public Class<DvText> getAssociatedClass() {
    return DvText.class;
  }

  @Override
  /** {@inheritDoc} */
  public Map<String, Object> buildChildValues(
      String currentTerm, DvText rmObject, Context<Map<String, Object>> context) {

    Map<String, Object> result = new HashMap<>();
    if (context.getNodeDeque().peek().getInputs().stream()
        .map(WebTemplateInput::getSuffix)
        .anyMatch("other"::equals)) {
      addValue(result, currentTerm, "other", rmObject.getValue());
    } else {
      addValue(result, currentTerm, null, rmObject.getValue());
    }

    return result;
  }
}
