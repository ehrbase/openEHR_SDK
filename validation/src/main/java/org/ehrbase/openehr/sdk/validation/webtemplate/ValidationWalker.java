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

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.FromCompositionWalker;
import org.ehrbase.openehr.sdk.util.reflection.ReflectionHelper;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.validation.terminology.ExternalTerminologyValidation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 1.7
 */
public class ValidationWalker extends FromCompositionWalker<List<ConstraintViolation>> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static final Map<Class<? extends RMObject>, ConstraintValidator> VALIDATORS =
            ReflectionHelper.buildMap(ConstraintValidator.class);

    private final DefaultValidator defaultValidator = new DefaultValidator();

    private final boolean checkForChildrenNotInTemplate;

    public ValidationWalker(
            ExternalTerminologyValidation externalTerminologyValidation, boolean checkForChildrenNotInTemplate) {
        if (externalTerminologyValidation != null) {
            VALIDATORS.put(DvCodedText.class, new DvCodedTextValidator(externalTerminologyValidation));
        }

        this.checkForChildrenNotInTemplate = checkForChildrenNotInTemplate;
    }

    @Override
    protected void preHandle(Context<List<ConstraintViolation>> context) {
        var node = context.getNodeDeque().element();
        var rmObject = context.getRmObjectDeque().element();

        logger.trace("PreHandle: {}, rmObject={}", node, rmObject);

        List<ConstraintViolation> toBeAdded = getValidator(rmObject).validate(rmObject, node);
        if (!toBeAdded.isEmpty()) {
            var result = context.getObjectDeque().element();
            result.addAll(toBeAdded);
        }
    }

    @Override
    protected List<ConstraintViolation> extract(
            Context<List<ConstraintViolation>> context, WebTemplateNode child, BooleanSupplier isChoice, Integer i) {
        return context.getObjectDeque().peek();
    }

    @Override
    protected void postHandle(Context<List<ConstraintViolation>> context) {
        // No-op
    }

    protected void handleChildrenNotInTemplate(
            Context<List<ConstraintViolation>> context, String attributeName, Locatable locatable) {

        WebTemplateNode peek = context.getNodeDeque().peek();

        boolean foundArchetypeSlot = peek.getChildren().stream()
                .filter(WebTemplateNode::isArchetypeSlot)
                .filter(n -> Objects.equals(n.getAqlPathDto().getLastNode().getName(), attributeName))
                .filter(n -> {
                    String otherPredicate = n.getAqlPathDto().getLastNode().findOtherPredicate("name/value");
                    return otherPredicate == null
                            || otherPredicate.equals(locatable.getName().getValue());
                })
                .anyMatch(c -> locatable.getArchetypeNodeId().startsWith("openEHR-EHR-" + c.getRmType() + "."));

        if (!foundArchetypeSlot) {
            context.getObjectDeque()
                    .peek()
                    .add(new ConstraintViolation(
                            peek.getAqlPath(),
                            "RmObject with type:%s, nodeId:%s,name:%s; not in template"
                                    .formatted(
                                            locatable.getClass().getSimpleName(),
                                            locatable.getArchetypeNodeId(),
                                            locatable.getName().getValue())));
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends RMObject> ConstraintValidator<T> getValidator(RMObject object) {
        return VALIDATORS.getOrDefault(object.getClass(), defaultValidator);
    }

    @Override
    protected void postVisitChildren(Context<List<ConstraintViolation>> context, WebTemplateNode currentNode) {

        if (checkForChildrenNotInTemplate) {
            Stream<? extends Pair<String, Locatable>> childrenNotInTemplate =
                    findChildrenNotInTemplate(context, currentNode);

            childrenNotInTemplate.forEach(c -> handleChildrenNotInTemplate(context, c.getLeft(), c.getRight()));
        }
    }

    protected <T> Stream<? extends Pair<String, Locatable>> findChildrenNotInTemplate(
            Context<T> context, WebTemplateNode currentNode) {
        RMObject curentRmObject = context.getRmObjectDeque().peek();

        return getChildLocatable(curentRmObject).filter(Objects::nonNull).filter(c -> currentNode.getChildren().stream()
                .filter(n -> !n.isArchetypeSlot())
                .noneMatch(n -> matches(c.getLeft(), c.getRight(), n)));
    }

    private static boolean matches(String attributeName, Locatable locatable, WebTemplateNode n) {
        AqlPath.AqlNode lastNode = n.getAqlPathDto().getLastNode();

        if (!attributeName.equals(lastNode.getName())) {
            return false;
        }

        if (!Objects.equals(n.getNodeId(), locatable.getArchetypeNodeId())) {
            return false;
        }

        String otherPredicate = lastNode.findOtherPredicate("name/value");
        return otherPredicate == null
                || otherPredicate.equals(locatable.getName().getValue());
    }

    private static Stream<? extends Pair<String, Locatable>> getChildLocatable(RMObject curentRmObject) {

        return ArchieRMInfoLookup.getInstance().getTypeInfo(curentRmObject.getClass()).getAttributes().values().stream()
                .filter(s -> !s.isComputed())
                .filter(s -> Locatable.class.isAssignableFrom(s.getTypeInCollection()))
                .flatMap(a -> {
                    Object invoke;
                    try {
                        invoke = a.getGetMethod().invoke(curentRmObject);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }

                    if (invoke == null) {
                        return Stream.empty();
                    } else if (invoke instanceof Collection<?> c) {
                        return c.stream().map(Locatable.class::cast).map(l -> Pair.of(a.getRmName(), l));
                    } else {
                        return Stream.of(invoke).map(Locatable.class::cast).map(l -> Pair.of(a.getRmName(), l));
                    }
                });
    }
}
