/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.validation.webtemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.client.classgenerator.shareddefinition.State;
import org.ehrbase.client.classgenerator.shareddefinition.Transition;
import org.ehrbase.functional.Either;
import org.ehrbase.validation.ConstraintViolation;
import org.ehrbase.webtemplate.model.WebTemplateNode;

import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datavalues.DvCodedText;


/**
 * @see com.nedap.archie.rm.datavalues.IsmTransition
 * @since 1.7
 */
public class IsmTransitionValidator implements ConstraintValidator<IsmTransition> {

  public IsmTransitionValidator() { }

  @Override
  public Class<IsmTransition> getAssociatedClass() {
    return IsmTransition.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ConstraintViolation> validate(IsmTransition transition, WebTemplateNode node) {
    List<ConstraintViolation> constraintViolations = new ArrayList<>();
      constraintViolations.addAll(new CurrentStateValidator().apply(transition, node));
      constraintViolations.addAll(new CareFlowValidator().apply(transition, node));
    return constraintViolations;
  }

  private String ismToString(IsmTransition ism) {
    return new StringBuilder()
      .append(ism.getTransition().getValue()).append("/")
      .append(ism.getTransition().getDefiningCode().getTerminologyId().getValue()).append("/")
      .append(ism.getTransition().getDefiningCode().getCodeString())
      .toString();
  }
  
  //-------------------------------------------------------------------------------------------------------------------------------------------------------------  
  
  private class CurrentStateValidator implements BiFunction<IsmTransition, WebTemplateNode, List<ConstraintViolation>> {
    private static final String ERR_NO_VALID_TRANSITION = "No valid transition found for ism transition[%s]";

    @SuppressWarnings("unchecked")
    public List<ConstraintViolation> apply(IsmTransition transition, WebTemplateNode node) {
      /* transition is optional see https://specifications.openehr.org/releases/RM/latest/ehr.html#_ism_transition_class*/
      if(transition.getTransition() == null)
        return Collections.emptyList();
      
      List<Transition> allTransitions = Optional.ofNullable(transition)
        .map(ism ->
          candidates(
            Transition.class,
            t -> t.getValue().equals(ism.getTransition().getValue()),
            t -> t.getTerminologyId().equals(ism.getTransition().getDefiningCode().getTerminologyId().getValue()),
            t -> t.getCode().equals(ism.getTransition().getDefiningCode().getCodeString())
        ))
        .orElseGet(() -> Collections.emptyList());
      
      return allTransitions.stream()
        .map(t -> Pair.of(isValidTransition(transition, t), t))
        .filter(p -> !p.getLeft())
        .map(p -> new ConstraintViolation(node.getAqlPath(), String.format(ERR_NO_VALID_TRANSITION, IsmTransitionValidator.this.ismToString(transition))))
        .collect(Collectors.toList());
    }
    
    public boolean isValidTransition(IsmTransition ism, Transition transition) {
      State targetState = transition.getTargetState();
      DvCodedText currentState = ism.getCurrentState();
      
      return
        targetState.getValue().equals(currentState.getValue())
        && targetState.getTerminologyId().equals(currentState.getDefiningCode().getTerminologyId().getValue())
        && targetState.getCode().equals(currentState.getDefiningCode().getCodeString());
    }
    
    @SuppressWarnings("unchecked")
    private <E extends Enum<E> & EnumValueSet> List<E> candidates(Class<E> type, Predicate<E>...pred) {
      Predicate<E> passAll = t -> Stream.of(pred).map(p -> p.test(t)).reduce(true, (b0, b1) -> b0 && b1);
            
      return Stream.of(type.getEnumConstants())
        .filter(passAll)
        .collect(Collectors.toList());
    }
  }
  
  //-------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  private class CareFlowValidator implements BiFunction<IsmTransition, WebTemplateNode, List<ConstraintViolation>> {

    public List<ConstraintViolation> apply(IsmTransition transition, WebTemplateNode node) {
      if(transition.getTransition() == null || transition.getCareflowStep() == null)
        return Collections.emptyList();
      
      Either<List<WebTemplateNode>,ConstraintViolation> careflows = extractCareflow(node);
      
      return careflows.map((l, r) -> {
        if(careflows.isRight())
          return List.of(careflows.getAsRight());
        else {
          Boolean isValid = careflows.getAsLeft().stream()
              .map(cf -> cf.getInputs())
              .flatMap(list -> list.stream())
              .flatMap(tmpl -> tmpl.getList().stream())
              .filter(tmpl -> isMatchingCareflowStep(transition.getCareflowStep(), tmpl.getValue()))
              .map(tmpl -> isCurrentStateValid(transition.getCurrentState(), tmpl.getCurrentStates()))
              .findFirst()
              .orElse(false);
          return isValid ? Collections.emptyList() : List.of(new ConstraintViolation(node.getAqlPath(), "IsmTransition contains invalid current_state"));
        }
      });
    }
    
    private boolean isMatchingCareflowStep(DvCodedText cfsFromIsm, String codeFromWTV) {
      return cfsFromIsm.getDefiningCode().getCodeString().equals(codeFromWTV);
    }
    
    private boolean isCurrentStateValid(DvCodedText currentState, List<String> allowedStates )  {
     return allowedStates.contains(currentState.getDefiningCode().getCodeString());
    }
    
    private Either<List<WebTemplateNode>,ConstraintViolation> extractCareflow(WebTemplateNode node) {
      List<WebTemplateNode> candiate = node.getChildren().stream()
        .filter(n -> n.getAqlPath() != null)
        .filter(n -> n.getAqlPath().endsWith("careflow_step"))
        .collect(Collectors.toList());
      
      if(candiate.size() > 1)
        return Either.right(new ConstraintViolation(node.getAqlPath(), "Specification violation[too many careflow_step]"));
      
      return Either.left(candiate);
    }
  }
}
