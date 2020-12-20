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

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.model.WebTemplateInputValue;

public class DvOrdinalConfig extends AbstractsStdConfig<DvOrdinal> {

  /** {@inheritDoc} */
  @Override
  public Class<DvOrdinal> getAssociatedClass() {
    return DvOrdinal.class;
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> buildChildValues(
      String currentTerm, DvOrdinal rmObject, Context<Map<String, Object>> context) {
    Map<String, Object> result = new HashMap<>();
    String codeString =
        Optional.of(rmObject)
            .map(DvOrdinal::getSymbol)
            .map(DvCodedText::getDefiningCode)
            .map(CodePhrase::getCodeString)
            .orElse(null);
    addValue(result, currentTerm, "code", codeString);

    WebTemplateInputValue value =
        context.getNodeDeque().peek().getInputs().get(0).getList().stream()
            .filter(o -> o.getValue().equals(codeString))
            .findAny()
            .orElseThrow(
                () -> new SdkException(String.format("Unknown Ordinal with code %s", codeString)));

    addValue(result, currentTerm, "ordinal", value.getOrdinal());
    addValue(result, currentTerm, "value", value.getLabel());

    return result;
  }
}
