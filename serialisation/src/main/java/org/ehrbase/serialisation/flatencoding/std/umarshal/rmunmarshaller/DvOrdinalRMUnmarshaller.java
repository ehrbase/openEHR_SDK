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

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class DvOrdinalRMUnmarshaller extends AbstractRMUnmarshaller<DvOrdinal> {

  /** {@inheritDoc} */
  @Override
  public Class<DvOrdinal> getAssociatedClass() {
    return DvOrdinal.class;
  }

  /** {@inheritDoc} */
  @Override
  public void handle(
      String currentTerm,
      DvOrdinal rmObject,
      Map<FlatPathDto, String> currentValues,
      Context<Map<FlatPathDto, String>> context,
      Set<String> consumedPaths) {

    rmObject.setSymbol(new DvCodedText());
    rmObject.getSymbol().setDefiningCode(new CodePhrase());

    // TerminologyId is fixed local for DvOrdinal
    rmObject.getSymbol().getDefiningCode().setTerminologyId(new TerminologyId("local"));
    FlatHelper.consumeAllMatching(currentTerm + "|terminology", currentValues, consumedPaths, false);

    setValue(
        currentTerm,
        "code",
        currentValues,
        rmObject.getSymbol().getDefiningCode()::setCodeString,
        String.class,
        consumedPaths);

    if (rmObject.getSymbol().getDefiningCode().getCodeString() == null) {
      setValue(
          currentTerm,
          null,
          currentValues,
          rmObject.getSymbol().getDefiningCode()::setCodeString,
          String.class,
          consumedPaths);
    }

    WebTemplateInputValue value =
        context.getNodeDeque().peek().getInputs().get(0).getList().stream()
            .filter(
                o -> o.getValue().equals(rmObject.getSymbol().getDefiningCode().getCodeString()))
            .findAny()
            .orElseThrow(
                () ->
                    new SdkException(
                        String.format(
                            "Unknown Ordinal with code %s",
                            (Consumer<String>)
                                rmObject.getSymbol().getDefiningCode()::setCodeString)));

    rmObject.setValue(Long.valueOf(value.getOrdinal()));
    consumedPaths.add(currentTerm + "|ordinal");
    rmObject.getSymbol().setValue(value.getLabel());
    consumedPaths.add(currentTerm + "|value");
  }
}
