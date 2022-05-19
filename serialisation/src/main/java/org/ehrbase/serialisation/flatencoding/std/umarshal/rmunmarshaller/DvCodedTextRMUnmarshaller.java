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
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.*;
import java.util.function.Predicate;

public class DvCodedTextRMUnmarshaller extends AbstractRMUnmarshaller<DvCodedText> {

  /** {@inheritDoc} */
  @Override
  public Class<DvCodedText> getAssociatedClass() {
    return DvCodedText.class;
  }

  /** {@inheritDoc} */
  @Override
  public void handle(
      String currentTerm,
      DvCodedText rmObject,
      Map<FlatPathDto, String> currentValues,
      Context<Map<FlatPathDto, String>> context,
      Set<String> consumedPaths) {

    setValue(currentTerm, "value", currentValues, rmObject::setValue, String.class, consumedPaths);

    rmObject.setDefiningCode(new CodePhrase());
    setValue(
        currentTerm,
        "code",
        currentValues,
        c -> rmObject.getDefiningCode().setCodeString(c),
        String.class,
        consumedPaths);

    rmObject.getDefiningCode().setTerminologyId(new TerminologyId());
    setValue(
        currentTerm,
        "terminology",
        currentValues,
        t -> rmObject.getDefiningCode().getTerminologyId().setValue(t),
        String.class,
        consumedPaths);

    if (rmObject.getDefiningCode().getCodeString() == null && rmObject.getValue() == null) {
      setValue(
          currentTerm,
          null,
          currentValues,
          c -> {
            if (c != null) {
              // try to interpret as code
              setFromNode(rmObject, context, v -> Objects.equals(v.getValue(), c));
              if (rmObject.getValue() == null) {
                // try to interpret as value
                setFromNode(rmObject, context, v -> Objects.equals(v.getLabel(), c));
              }
            }
          },
          String.class,
          consumedPaths);
    } else if (rmObject.getDefiningCode().getCodeString() == null) {
      setValue(
          currentTerm,
          null,
          currentValues,
          // It is the code
          c -> rmObject.getDefiningCode().setCodeString(c),
          String.class,
          consumedPaths);
    }


    if(rmObject.getDefiningCode() == null || rmObject.getDefiningCode().getTerminologyId() == null || rmObject.getDefiningCode().getTerminologyId().getValue() == null) {
      // Set terminology from Node
      Optional.of(context.getNodeDeque().peek().getInputs()).stream()
          .flatMap(List::stream)
          .filter(i -> "code".equals(i.getSuffix()))
          .findAny()
          .map(WebTemplateInput::getTerminology)
          .ifPresent(t -> rmObject.getDefiningCode().getTerminologyId().setValue(t));
    }
    // Set value from Node
    setFromNode(
        rmObject,
        context,
        v -> Objects.equals(v.getValue(), rmObject.getDefiningCode().getCodeString()));

    // set code from value if not set
    if (rmObject.getDefiningCode().getCodeString() == null && rmObject.getValue() != null) {
      setFromNode(rmObject, context, v -> Objects.equals(v.getLabel(), rmObject.getValue()));
    }

    setFromNode(
        rmObject,
        context,
        v -> Objects.equals(v.getValue(), rmObject.getDefiningCode().getCodeString()));

    // consume strange legacy paths
    if (rmObject.getDefiningCode() != null && rmObject.getDefiningCode().getCodeString() != null) {
      currentValues.keySet().stream()
          .map(FlatPathDto::format)
          .filter(
              k ->
                  StringUtils.substringAfter(k, "|")
                      .equals(rmObject.getDefiningCode().getCodeString()))
          .forEach(consumedPaths::add);
    }

    setValue(
        currentTerm,
        "formatting",
        currentValues,
        rmObject::setFormatting,
        String.class,
        consumedPaths);
  }

  private void setFromNode(
      DvCodedText rmObject,
      Context<Map<FlatPathDto, String>> context,
      Predicate<WebTemplateInputValue> filter) {
    Optional.of(context.getNodeDeque().peek().getInputs()).stream()
        .flatMap(List::stream)
        .filter(i -> "code".equals(i.getSuffix()))
        .map(WebTemplateInput::getList)
        .filter(Objects::nonNull)
        .flatMap(List::stream)
        .filter(filter)
        .findAny()
        .ifPresent(
            v -> {
              rmObject.setValue(v.getLabel());
              rmObject.getDefiningCode().setCodeString(v.getValue());
            });
  }
}
