/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.validation.webtemplate;

import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.State;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Transition;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

/**
 * @see com.nedap.archie.rm.composition.IsmTransition
 * @since 1.7
 */
public class IsmTransitionValidator implements ConstraintValidator<IsmTransition> {

    @Override
    public Class<IsmTransition> getAssociatedClass() {
        return IsmTransition.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ConstraintViolation> validate(IsmTransition transition, WebTemplateNode node) {
        return ConstraintValidator.concat(
                CurrentStateValidator.apply(transition, node), CareFlowValidator.apply(transition, node));
    }

    private static String ismToString(IsmTransition ism) {
        return "%s/%s/%s"
                .formatted(
                        ism.getTransition().getValue(),
                        ism.getTransition().getDefiningCode().getTerminologyId().getValue(),
                        ism.getTransition().getDefiningCode().getCodeString());
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------------------------

    private static class CurrentStateValidator {
        private static final String ERR_NO_VALID_TRANSITION = "No valid transition found for ism transition[%s]";

        @SuppressWarnings("unchecked")
        public static Stream<ConstraintViolation> apply(IsmTransition ismTransition, WebTemplateNode node) {
            /* transition is optional see https://specifications.openehr.org/releases/RM/latest/ehr.html#_ism_transition_class*/
            DvCodedText transition = ismTransition.getTransition();
            if (transition == null) {
                return Stream.empty();
            }

            return candidates(
                            Transition.class,
                            t -> t.getValue().equals(transition.getValue()),
                            t -> t.getTerminologyId()
                                    .equals(transition
                                            .getDefiningCode()
                                            .getTerminologyId()
                                            .getValue()),
                            t -> t.getCode().equals(transition.getDefiningCode().getCodeString()))
                    .filter(t -> !isValidTransition(ismTransition, t))
                    .map(p -> new ConstraintViolation(
                            node.getAqlPath(),
                            String.format(ERR_NO_VALID_TRANSITION, IsmTransitionValidator.ismToString(ismTransition))));
        }

        private static boolean isValidTransition(IsmTransition ism, Transition transition) {
            State targetState = transition.getTargetState();
            DvCodedText currentState = ism.getCurrentState();

            return targetState.getValue().equals(currentState.getValue())
                    && targetState
                            .getTerminologyId()
                            .equals(currentState
                                    .getDefiningCode()
                                    .getTerminologyId()
                                    .getValue())
                    && targetState
                            .getCode()
                            .equals(currentState.getDefiningCode().getCodeString());
        }

        @SuppressWarnings("unchecked")
        private static <E extends Enum<E> & EnumValueSet> Stream<E> candidates(Class<E> type, Predicate<E>... pred) {
            Predicate<E> passAll = t -> {
                for (Predicate<E> ePredicate : pred) {
                    if (!ePredicate.test(t)) {
                        return false;
                    }
                }
                return true;
            };
            return EnumSet.allOf(type).stream().filter(passAll);
        }
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------------------------

    private static class CareFlowValidator {

        public static Stream<ConstraintViolation> apply(IsmTransition transition, WebTemplateNode node) {
            if (transition.getTransition() == null || transition.getCareflowStep() == null) return Stream.empty();

            Iterator<WebTemplateNode> cfStepIt = iterateCareflowSteps(node);

            if (cfStepIt.hasNext()) {
                WebTemplateNode cfStep = cfStepIt.next();
                if (cfStepIt.hasNext()) {
                    return Stream.of(new ConstraintViolation(
                            node.getAqlPath(), "Specification violation[too many careflow_step]"));
                } else {
                    boolean isValid = Stream.of(cfStep)
                            .map(WebTemplateNode::getInputs)
                            .flatMap(Collection::stream)
                            .map(WebTemplateInput::getList)
                            .flatMap(Collection::stream)
                            .filter(tmpl -> isMatchingCareflowStep(transition.getCareflowStep(), tmpl.getValue()))
                            .anyMatch(
                                    tmpl -> isCurrentStateValid(transition.getCurrentState(), tmpl.getCurrentStates()));
                    return isValid
                            ? Stream.empty()
                            : Stream.of(new ConstraintViolation(
                                    node.getAqlPath(), "IsmTransition contains invalid current_state"));
                }
            } else {
                return Stream.of();
            }
        }

        private static boolean isMatchingCareflowStep(DvCodedText cfsFromIsm, String codeFromWTV) {
            return cfsFromIsm.getDefiningCode().getCodeString().equals(codeFromWTV);
        }

        private static boolean isCurrentStateValid(DvCodedText currentState, List<String> allowedStates) {
            return allowedStates.contains(currentState.getDefiningCode().getCodeString());
        }

        private static Iterator<WebTemplateNode> iterateCareflowSteps(WebTemplateNode node) {
            return node.getChildren().stream()
                    .filter(n -> n.getAqlPath() != null)
                    .filter(n -> n.getAqlPath().endsWith("careflow_step"))
                    .iterator();
        }
    }
}
