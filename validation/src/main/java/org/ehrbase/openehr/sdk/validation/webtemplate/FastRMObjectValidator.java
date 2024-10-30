/*
 * Copyright (c) 2024 vitasystems GmbH and Hannover Medical School.
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

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.nedap.archie.adlparser.modelconstraints.ModelConstraintImposer;
import com.nedap.archie.adlparser.modelconstraints.ReflectionConstraintImposer;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.ArchetypeSlot;
import com.nedap.archie.aom.CAttribute;
import com.nedap.archie.aom.CAttributeTuple;
import com.nedap.archie.aom.CComplexObject;
import com.nedap.archie.aom.CObject;
import com.nedap.archie.aom.CPrimitiveObject;
import com.nedap.archie.aom.CPrimitiveTuple;
import com.nedap.archie.aom.OperationalTemplate;
import com.nedap.archie.aom.primitives.COrdered;
import com.nedap.archie.aom.primitives.CString;
import com.nedap.archie.aom.primitives.CTemporal;
import com.nedap.archie.aom.primitives.CTerminologyCode;
import com.nedap.archie.aom.terminology.ArchetypeTerm;
import com.nedap.archie.aom.terminology.ArchetypeTerminology;
import com.nedap.archie.aom.utils.AOMUtils;
import com.nedap.archie.base.Cardinality;
import com.nedap.archie.base.Interval;
import com.nedap.archie.base.MultiplicityInterval;
import com.nedap.archie.base.terminology.TerminologyCode;
import com.nedap.archie.flattener.OperationalTemplateProvider;
import com.nedap.archie.query.RMObjectAttributes;
import com.nedap.archie.query.RMObjectWithPath;
import com.nedap.archie.query.RMPathQuery;
import com.nedap.archie.rminfo.InvariantMethod;
import com.nedap.archie.rminfo.MetaModel;
import com.nedap.archie.rminfo.ModelInfoLookup;
import com.nedap.archie.rminfo.RMAttributeInfo;
import com.nedap.archie.rminfo.RMTypeInfo;
import com.nedap.archie.rmobjectvalidator.APathQueryCache;
import com.nedap.archie.rmobjectvalidator.ConstraintToStringUtil;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessage;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessageIds;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessageType;
import com.nedap.archie.rmobjectvalidator.RMObjectValidator;
import com.nedap.archie.rmobjectvalidator.ValidationConfiguration;
import com.nedap.archie.terminology.OpenEHRTerminologyAccess;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.openehr.utils.message.I18n;

public class FastRMObjectValidator extends RMObjectValidator {

    private final MetaModel metaModel;
    private final OperationalTemplateProvider operationalTemplateProvider;
    private APathQueryCache queryCache = new APathQueryCache();
    private ModelInfoLookup lookup;
    private ReflectionConstraintImposer constraintImposer;
    private final ValidationConfiguration validationConfiguration;
    private boolean validateInvariants = true;
    private final RmOccurrenceValidator rmOccurrenceValidator;
    private final RmPrimitiveObjectValidator rmPrimitiveObjectValidator;
    private final RmTupleValidator rmTupleValidator;
    private final RmMultiplicityValidator rmMultiplicityValidator;

    /**
     * Creates an RM Object Validator with the given ModelInfoLook class, and the given OperationalTemplateProvider
     * The ModelInfoLookup is used for model access, and model specific constructions.
     * The OperationalTemplateProvider is used to retrieve other referenced archetypes in case of ArchetypeSlots.
     * @param lookup
     * @param provider
     * @deprecated Use {@link #FastRMObjectValidator(ModelInfoLookup, OperationalTemplateProvider, ValidationConfiguration)} instead.
     */
    @Deprecated
    public FastRMObjectValidator(ModelInfoLookup lookup, OperationalTemplateProvider provider) {
        super(null, null, new ValidationConfiguration.Builder().build());
        this.lookup = lookup;
        this.metaModel = new MetaModel(lookup, null);
        constraintImposer = new ReflectionConstraintImposer(lookup);
        this.operationalTemplateProvider = provider;

        this.validationConfiguration = null; // Leave this null to indicate that no ValidationConfiguration was provided
        ValidationConfiguration dummyValidationConfiguration = new ValidationConfiguration.Builder()
                .failOnUnknownTerminologyId(com.nedap.archie.ValidationConfiguration.isFailOnUnknownTerminologyId())
                .build();
        ValidationHelper validationHelper = new ValidationHelper(this.lookup, dummyValidationConfiguration);
        rmOccurrenceValidator = new RmOccurrenceValidator();
        rmPrimitiveObjectValidator = new RmPrimitiveObjectValidator(validationHelper);
        rmTupleValidator = new RmTupleValidator(this.lookup, validationHelper, rmPrimitiveObjectValidator);
        rmMultiplicityValidator = new RmMultiplicityValidator();
    }

    /**
     * Creates an RM Object Validator with the given ModelInfoLook class, and the given OperationalTemplateProvider
     * The ModelInfoLookup is used for model access, and model specific constructions.
     * The OperationalTemplateProvider is used to retrieve other referenced archetypes in case of ArchetypeSlots.
     */
    public FastRMObjectValidator(
            ModelInfoLookup lookup,
            OperationalTemplateProvider provider,
            ValidationConfiguration validationConfiguration) {
        super(null, null, new ValidationConfiguration.Builder().build());
        this.lookup = lookup;
        this.metaModel = new MetaModel(lookup, null);
        constraintImposer = new ReflectionConstraintImposer(lookup);
        this.operationalTemplateProvider = provider;

        this.validationConfiguration = validationConfiguration;
        ValidationHelper validationHelper = new ValidationHelper(this.lookup, validationConfiguration);
        validateInvariants = validationConfiguration.isValidateInvariants();
        rmOccurrenceValidator = new RmOccurrenceValidator();
        rmPrimitiveObjectValidator = new RmPrimitiveObjectValidator(validationHelper);
        rmTupleValidator = new RmTupleValidator(this.lookup, validationHelper, rmPrimitiveObjectValidator);
        rmMultiplicityValidator = new RmMultiplicityValidator();
    }

    public APathQueryCache getQueryCache() {
        return queryCache;
    }

    public void setQueryCache(APathQueryCache queryCache) {
        this.queryCache = queryCache;
    }

    /**
     * @deprecated Use {@link ValidationConfiguration.Builder#validateInvariants(boolean)} instead.
     */
    @Deprecated
    public void setRunInvariantChecks(boolean validateInvariants) {
        if (this.validationConfiguration != null) {
            throw new IllegalStateException(
                    "validateInvariants is already set via validationConfiguration, cannot set it again via setRunInvariantChecks");
        }
        this.validateInvariants = validateInvariants;
    }

    public List<RMObjectValidationMessage> validate(OperationalTemplate template, Object rmObject) {
        clearMessages();
        List<RMObjectWithPath> objects = List.of(new RMObjectWithPath(rmObject, ""));
        addAllMessages(runArchetypeValidations(objects, LazyPath.of(""), template.getDefinition()));
        return getMessages();
    }

    public List<RMObjectValidationMessage> validate(Object rmObject) {
        clearMessages();
        List<RMObjectWithPath> objects = List.of(new RMObjectWithPath(rmObject, "/"));
        addAllMessages(runArchetypeValidations(objects, LazyPath.of(""), null));
        return getMessages();
    }

    public static <T> void addAll(List<T> target, Collection<T> source) {
        if (!source.isEmpty()) {
            target.addAll(source);
        }
    }

    protected void addAllMessages(Collection<RMObjectValidationMessage> messages) {
        if (!messages.isEmpty()) {
            super.addAllMessages(messages);
        }
    }

    interface LazyPath {

        static LazyPath of(String path) {
            return new SimpleLazyPath(null, path, null);
        }

        default LazyPath add(String rmAttributeName) {
            return new SimpleLazyPath(this, rmAttributeName, null);
        }

        default LazyPath add(String attributeName, CPrimitiveObject<?, ?> cPrimitiveObject) {
            return new SimpleLazyPath(this, attributeName, cPrimitiveObject.getNodeId());
        }

        default LazyPath joinPaths(String other, boolean enforceSlash) {
            return new LazyPathJoin(this, enforceSlash, other);
        }

        default LazyPath stripLastPathSegment() {
            return new LazyStripLastPathSegment(this);
        }

        String toString();

        final class SimpleLazyPath implements LazyPath {
            private final LazyPath parent;
            private final String path;
            private final String nodeId;

            public SimpleLazyPath(LazyPath parent, String path, String nodeId) {
                this.parent = parent;
                this.path = path;
                this.nodeId = nodeId;
            }

            @Override
            public String toString() {
                String p = path;
                if (nodeId != null) {
                    p = p + '[' + nodeId + ']';
                }
                if (parent == null) {
                    return p;
                } else {
                    return parent + "/" + p;
                }
            }
        }

        final class LazyPathJoin implements LazyPath {
            private final LazyPath parent;
            private final String path;
            private final boolean enforceSlash;

            public LazyPathJoin(LazyPath parent, boolean enforceSlash, String path) {
                this.parent = parent;
                this.enforceSlash = enforceSlash;
                this.path = path;
            }

            @Override
            public String toString() {
                if (enforceSlash) {
                    return FastRMObjectValidator.joinPaths(parent.toString(), "/", path);
                } else {
                    return FastRMObjectValidator.joinPaths(parent.toString(), path);
                }
            }
        }

        // RMObjectValidationUtil.stripLastPathSegment(pathSoFar)
        final class LazyStripLastPathSegment implements LazyPath {
            private final LazyPath child;

            public LazyStripLastPathSegment(LazyPath child) {
                this.child = child;
            }

            @Override
            public String toString() {
                return RMObjectValidationUtil.stripLastPathSegment(child.toString());
            }
        }
    }

    private List<RMObjectValidationMessage> runArchetypeValidations(
            List<RMObjectWithPath> rmObjects, LazyPath path, CObject cobject) {
        List<RMObjectValidationMessage> result = rmOccurrenceValidator.validate(metaModel, rmObjects, path, cobject);
        if (rmObjects.isEmpty()) {
            // if this branch of the archetype tree is null in the reference model, we're done validating
            // this has to be done after validateOccurrences(), or required fields do not get validated
            return result;
        }
        for (RMObjectWithPath objectWithPath : rmObjects) {
            List<RMObjectValidationMessage> messages = validateInvariants(objectWithPath, path);
            addAll(result, messages);
        }
        if (cobject == null) {
            // add default validations
            for (RMObjectWithPath objectWithPath : rmObjects) {
                validateUnconstrainedObjectWithPath(result, path, objectWithPath);
            }
        } else if (cobject instanceof CPrimitiveObject) {
            addAll(result, rmPrimitiveObjectValidator.validate(rmObjects, path, (CPrimitiveObject<?, ?>) cobject));
        } else if (cobject instanceof ArchetypeSlot) {
            validateArchetypeSlot(rmObjects, path, cobject, result);
        } else {
            if (cobject instanceof CComplexObject) {
                CComplexObject cComplexObject = (CComplexObject) cobject;
                for (CAttributeTuple tuple : cComplexObject.getAttributeTuples()) {
                    addAll(result, rmTupleValidator.validate(cobject, path, rmObjects, tuple));
                }
            }
            for (RMObjectWithPath objectWithPath : rmObjects) {
                validateConstrainedObjectWithPath(result, cobject, path, objectWithPath);
            }
        }
        return result;
    }

    private List<RMObjectValidationMessage> validateInvariants(RMObjectWithPath objectWithPath, LazyPath pathSoFar) {
        if (!validateInvariants) {
            return Collections.emptyList();
        }
        // pathSoFar ends with an attribute, but objectWithPath contains it, so remove that.
        pathSoFar = pathSoFar.stripLastPathSegment();
        Object rmObject = objectWithPath.getObject();
        if (rmObject != null) {
            List<RMObjectValidationMessage> result = new ArrayList<>();
            RMTypeInfo typeInfo = lookup.getTypeInfo(rmObject.getClass());
            if (typeInfo != null) {
                for (InvariantMethod invariantMethod : typeInfo.getInvariants()) {
                    if (!invariantMethod.getAnnotation().ignored()) {
                        try {
                            boolean passed =
                                    (boolean) invariantMethod.getMethod().invoke(rmObject);
                            if (!passed) {
                                result.add(new RMObjectValidationMessage(
                                        null,
                                        pathSoFar
                                                .joinPaths(objectWithPath.getPath(), false)
                                                .toString(),
                                        I18n.t(
                                                "Invariant {0} failed on type " + typeInfo.getRmName(),
                                                invariantMethod.getAnnotation().value()),
                                        RMObjectValidationMessageType.INVARIANT_ERROR));
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            result.add(new RMObjectValidationMessage(
                                    null,
                                    pathSoFar
                                            .joinPaths(objectWithPath.getPath(), false)
                                            .toString(),
                                    I18n.t(
                                            "Exception {0} invoking invariant {1} on {2}: {3}\n{4}",
                                            e.getCause() == null
                                                    ? e.getClass().getSimpleName()
                                                    : e.getCause().getClass().getSimpleName(),
                                            invariantMethod.getAnnotation().value(),
                                            typeInfo.getRmName(),
                                            e.getCause() == null
                                                    ? e.getMessage()
                                                    : e.getCause().getMessage(),
                                            Joiner.on("\n\t").join(e.getStackTrace())),
                                    RMObjectValidationMessageType.EXCEPTION));
                        }
                    }
                }
            }
            return result;
        } else {
            return Collections.emptyList();
        }
    }

    private void validateUnconstrainedObjectWithPath(
            List<RMObjectValidationMessage> result, LazyPath path, RMObjectWithPath objectWithPath) {
        Object rmObject = objectWithPath.getObject();
        String archetypeId = lookup.getArchetypeIdFromArchetypedRmObject(rmObject);
        if (archetypeId != null) {
            validateArchetypedObject(result, null, path, objectWithPath, archetypeId);
        } else {
            validateObjectAttributes(result, null, path, objectWithPath);
        }
    }

    private void validateArchetypeSlot(
            List<RMObjectWithPath> rmObjects, LazyPath path, CObject cobject, List<RMObjectValidationMessage> result) {
        ArchetypeSlot slot = (ArchetypeSlot) cobject;
        for (RMObjectWithPath objectWithPath : rmObjects) {

            Object object = objectWithPath.getObject();

            String archetypeId = metaModel.getSelectedModel().getArchetypeIdFromArchetypedRmObject(object);

            if (archetypeId != null) {
                if (!AOMUtils.archetypeRefMatchesSlotExpression(archetypeId, slot)) {
                    // invalid archetype id, add message
                    this.addMessage(
                            slot,
                            objectWithPath.getPath(),
                            RMObjectValidationMessageIds.rm_ARCHETYPE_ID_SLOT_MISMATCH.getMessage(archetypeId),
                            RMObjectValidationMessageType.ARCHETYPE_SLOT_ID_MISMATCH);
                }
                // but do continue validation!
                validateArchetypedObject(result, cobject, path, objectWithPath, archetypeId);
            } else {
                this.addMessage(
                        slot,
                        objectWithPath.getPath(),
                        RMObjectValidationMessageIds.rm_SLOT_WITHOUT_ARCHETYPE_ID.getMessage(),
                        RMObjectValidationMessageType.ARCHETYPE_SLOT_ID_MISMATCH);
                // but continue validating the RM Objects, of course
                validateConstrainedObjectWithPath(result, cobject, path, objectWithPath);
            }
        }
    }

    private void validateArchetypedObject(
            List<RMObjectValidationMessage> result,
            CObject cobject,
            LazyPath path,
            RMObjectWithPath objectWithPath,
            String archetypeId) {
        OperationalTemplate operationalTemplate = operationalTemplateProvider.getOperationalTemplate(archetypeId);
        if (operationalTemplate != null) {
            // occurrences already validated, so nothing left to validate from the archetyepe root
            // from now on, validate from the root of the found OPT
            CObject newRoot = operationalTemplate.getDefinition();
            validateConstrainedObjectWithPath(result, newRoot, path, objectWithPath);
        } else {
            this.addMessage(
                    cobject,
                    objectWithPath.getPath(),
                    RMObjectValidationMessageIds.rm_ARCHETYPE_NOT_FOUND.getMessage(archetypeId),
                    RMObjectValidationMessageType.ARCHETYPE_NOT_FOUND);
            // but continue validating the RM Objects, of course
            if (cobject != null) {
                validateConstrainedObjectWithPath(result, cobject, path, objectWithPath);
            } else {
                validateObjectAttributes(result, null, path, objectWithPath);
            }
        }
    }

    private void validateConstrainedObjectWithPath(
            List<RMObjectValidationMessage> result, CObject cobject, LazyPath path, RMObjectWithPath objectWithPath) {
        Class<?> classInConstraint = this.lookup.getClass(cobject.getRmTypeName());
        if (!classInConstraint.isAssignableFrom(objectWithPath.getObject().getClass())) {
            // not a matching constraint. Cannot validate. add error message and stop validating.
            // If another constraint is present, that one will succeed
            result.add(new RMObjectValidationMessage(
                    cobject,
                    objectWithPath.getPath(),
                    RMObjectValidationMessageIds.rm_INCORRECT_TYPE.getMessage(
                            cobject.getRmTypeName(),
                            objectWithPath.getObject().getClass().getSimpleName()),
                    RMObjectValidationMessageType.WRONG_TYPE));
        } else {
            validateObjectAttributes(result, cobject, path, objectWithPath);
        }
    }

    private void validateObjectAttributes(
            List<RMObjectValidationMessage> result, CObject cobject, LazyPath path, RMObjectWithPath objectWithPath) {
        Object rmObject = objectWithPath.getObject();
        List<CAttribute> attributes;
        if (cobject == null) {
            RMTypeInfo typeInfo = lookup.getTypeInfo(rmObject.getClass());
            if (typeInfo != null) {
                attributes = RMObjectValidationUtil.getDefaultAttributeConstraints(
                        typeInfo.getRmName(), List.of(), lookup, constraintImposer);
            } else {
                return; // Type unknown, nothing to validate
            }
        } else {
            List<CAttribute> cobjectAttributes = cobject.getAttributes();
            List<CAttribute> defaultAttributeConstraints = RMObjectValidationUtil.getDefaultAttributeConstraints(
                    cobject, cobjectAttributes, lookup, constraintImposer);
            if (defaultAttributeConstraints.isEmpty()) {
                attributes = cobjectAttributes;
            } else if (cobjectAttributes.isEmpty()) {
                attributes = defaultAttributeConstraints;
            } else {
                attributes = new ArrayList<>();
                attributes.addAll(cobjectAttributes);
                attributes.addAll(defaultAttributeConstraints);
            }
        }
        validateCAttributes(result, path, objectWithPath, rmObject, cobject, attributes);
    }

    private void validateCAttributes(
            List<RMObjectValidationMessage> result,
            LazyPath path,
            RMObjectWithPath objectWithPath,
            Object rmObject,
            CObject cObject,
            List<CAttribute> attributes) {
        // the path contains an attribute, but is missing the [idx] part. So strip the attribute, and add the attribute
        // plus the [idx] part.
        // String pathSoFar = joinPaths(RMObjectValidationUtil.stripLastPathSegment(path), objectWithPath.getPath());
        LazyPath pathSoFar = path.stripLastPathSegment().joinPaths(objectWithPath.getPath(), false);
        for (CAttribute attribute : attributes) {
            validateAttributes(result, attribute, cObject, rmObject, pathSoFar);
        }
    }

    private void validateAttributes(
            List<RMObjectValidationMessage> result,
            CAttribute attribute,
            CObject cobject,
            Object rmObject,
            LazyPath pathSoFar) {
        String rmAttributeName = attribute.getRmAttributeName();
        RMPathQuery aPathQuery = queryCache.getApathQuery("/" + attribute.getRmAttributeName());
        Object attributeValue = aPathQuery.find(lookup, rmObject);
        List<RMObjectValidationMessage> emptyObservationErrors =
                isObservationEmpty(attribute, rmAttributeName, attributeValue, pathSoFar, cobject);
        addAll(result, emptyObservationErrors);

        if (emptyObservationErrors.isEmpty()) {

            addAll(
                    result,
                    rmMultiplicityValidator.validate(
                            attribute,
                            pathSoFar.joinPaths(rmAttributeName, true) /*joinPaths(pathSoFar, "/", rmAttributeName)*/,
                            attributeValue));

            if (attribute.getChildren() == null || attribute.getChildren().isEmpty()) {
                // no child CObjects. Cardinality/existence has already been validated. Run default RM validations
                String query = "/" + rmAttributeName;
                aPathQuery = queryCache.getApathQuery(query);
                List<RMObjectWithPath> childRmObjects = aPathQuery.findList(lookup, rmObject);
                addAll(result, runArchetypeValidations(childRmObjects, pathSoFar.joinPaths(query, false), null));
            } else if (attribute.isSingle()) {
                validateSingleAttribute(result, attribute, rmObject, pathSoFar);
            } else {

                for (CObject childCObject : attribute.getChildren()) {
                    String query = "/" + rmAttributeName + "[" + childCObject.getNodeId() + "]";
                    aPathQuery = queryCache.getApathQuery(query);
                    List<RMObjectWithPath> childRmObjects = aPathQuery.findList(lookup, rmObject);
                    addAll(
                            result,
                            runArchetypeValidations(childRmObjects, pathSoFar.joinPaths(query, false), childCObject));
                    // TODO: find all other child RM Objects that don't match with a given node id (eg unconstraint in
                    // archetype) and
                    // run default validations against them!
                }
            }
        }
    }

    private void validateSingleAttribute(
            List<RMObjectValidationMessage> result, CAttribute attribute, Object rmObject, LazyPath pathSoFar) {
        List<List<RMObjectValidationMessage>> subResults = new ArrayList<>();

        // a single attribute with multiple CObjects means you can choose which CObject you use
        // for example, a data value can be a string or an integer.
        // in this case, only one of the CObjects will validate to a correct value
        // so as soon as one is correct, so is the data!

        for (CObject childCObject : attribute.getChildren()) {
            String query = "/" + attribute.getRmAttributeName() + "[" + childCObject.getNodeId() + "]";
            RMPathQuery aPathQuery = queryCache.getApathQuery(query);
            List<RMObjectWithPath> childNodes = aPathQuery.findList(lookup, rmObject);
            List<RMObjectValidationMessage> subResult =
                    runArchetypeValidations(childNodes, pathSoFar.joinPaths(query, false), childCObject);
            if (subResult.isEmpty()) {
                // cObjectWithoutErrorsFound
                return;
            }
            subResults.add(subResult);
        }

        boolean atLeastOneWithoutWrongTypeFound =
                subResults.stream().anyMatch(RMObjectValidationUtil::hasNoneWithWrongType);
        if (atLeastOneWithoutWrongTypeFound) {
            for (List<RMObjectValidationMessage> subResult : subResults) {
                // at least one has the correct type, we can filter out all others
                subResult.stream()
                        .filter((message) -> message.getType() != RMObjectValidationMessageType.WRONG_TYPE)
                        .forEach(result::add);
            }
        } else {
            subResults.forEach(result::addAll);
        }
    }

    /**
     * Check if an observation is empty. This is the case if its event contains an empty data attribute.
     *
     * @param attribute       The attribute that is checked
     * @param rmAttributeName The name of the attribute
     * @param attributeValue  The value of the attribute
     * @param pathSoFar       The path of the attribute
     * @param cobject         The constraints that the attribute is checked against
     */
    private List<RMObjectValidationMessage> isObservationEmpty(
            CAttribute attribute, String rmAttributeName, Object attributeValue, LazyPath pathSoFar, CObject cobject) {
        List<RMObjectValidationMessage> result = new ArrayList<>();
        CObject parent = attribute.getParent();
        boolean parentIsEvent = parent != null && parent.getRmTypeName().contains("EVENT");
        boolean attributeIsData = rmAttributeName.equals("data");
        boolean attributeIsEmpty = attributeValue == null;
        boolean attributeShouldNotBeEmpty =
                attribute.getExistence() != null && !attribute.getExistence().has(0);

        if (parentIsEvent && attributeIsData && attributeIsEmpty && attributeShouldNotBeEmpty) {
            String message = "Observation " + RMObjectValidationUtil.getParentObservationTerm(attribute)
                    + " contains no results";
            result.add(new RMObjectValidationMessage(
                    cobject == null ? null : cobject.getParent().getParent(),
                    pathSoFar.toString(),
                    message,
                    RMObjectValidationMessageType.EMPTY_OBSERVATION));
        }
        return result;
    }

    private static String joinPaths(String... pathElements) {
        StringBuilder result = new StringBuilder();
        boolean lastCharacterWasSlash = false;
        for (String pathElement : pathElements) {
            if (lastCharacterWasSlash && pathElement.startsWith("/")) {
                result.append(pathElement.substring(1));
            } else {
                result.append(pathElement);
            }
            if (!pathElement.isEmpty()) {
                lastCharacterWasSlash = pathElement.charAt(pathElement.length() - 1) == '/';
            }
        }
        return result.toString();
    }

    static class RmOccurrenceValidator {
        List<RMObjectValidationMessage> validate(
                MetaModel metaModel, List<RMObjectWithPath> rmObjects, LazyPath pathSoFar, CObject cobject) {
            if (cobject != null) {
                MultiplicityInterval occurrences =
                        cobject.effectiveOccurrences(metaModel::referenceModelPropMultiplicity);
                if (occurrences != null && !occurrences.has(rmObjects.size())) {
                    String message = RMObjectValidationMessageIds.rm_OCCURRENCE_MISMATCH.getMessage(
                            rmObjects.size(), occurrences.toString());
                    RMObjectValidationMessageType messageType = occurrences.isMandatory()
                            ? RMObjectValidationMessageType.REQUIRED
                            : RMObjectValidationMessageType.DEFAULT;
                    return Lists.newArrayList(
                            new RMObjectValidationMessage(cobject, pathSoFar.toString(), message, messageType));
                }
            }

            return new ArrayList<>();
        }
    }

    static class RmPrimitiveObjectValidator {
        private final ValidationHelper validationHelper;

        public RmPrimitiveObjectValidator(ValidationHelper validationHelper) {
            this.validationHelper = validationHelper;
        }

        public List<RMObjectValidationMessage> validate(
                List<RMObjectWithPath> rmObjects, LazyPath pathSoFar, CPrimitiveObject<?, ?> cobject) {
            if (cobject == null) {
                return new ArrayList<>();
            }
            if (cobject.getSocParent() != null) {
                // validate the tuple, not the primitive object directly
                return Collections.emptyList();
            }
            if (rmObjects.size() != 1) {
                List<RMObjectValidationMessage> result = new ArrayList<>();
                result.add(createValidationMessage(rmObjects, pathSoFar, cobject));
                return result;
            }
            Object rmObject = rmObjects.get(0).getObject();
            return validate_inner(rmObject, pathSoFar, cobject);
        }

        List<RMObjectValidationMessage> validate_inner(
                Object rmObject, LazyPath pathSoFar, CPrimitiveObject<?, ?> cobject) {
            List<RMObjectValidationMessage> result = new ArrayList<>();
            if (!validationHelper.isValidValue(cobject, rmObject)) {
                result.add(createValidationMessage(rmObject, pathSoFar, cobject));
            }
            return result;
        }

        private RMObjectValidationMessage createValidationMessage(
                Object value, LazyPath pathSoFar, CPrimitiveObject<?, ?> cobject) {
            List<?> constraint = cobject.getConstraint();
            String message;

            if (constraint.size() == 1) {
                String constraintStr = ConstraintToStringUtil.constraintElementToString(constraint.get(0));
                message = RMObjectValidationMessageIds.rm_INVALID_FOR_CONSTRAINT.getMessage(
                        getValueString(value), constraintStr);
            } else {
                String constraintStr = ConstraintToStringUtil.constraintListToString(constraint);
                message = RMObjectValidationMessageIds.rm_INVALID_FOR_CONSTRAINT_MULTIPLE.getMessage(
                                getValueString(value))
                        + "\n" + constraintStr;
            }
            return new RMObjectValidationMessage(cobject, pathSoFar.toString(), message);
        }

        private String getValueString(Object value) {
            if (value == null) {
                return I18n.t("empty");
            }

            return (value instanceof String) ? "\"" + value.toString() + "\"" : value.toString();
        }
    }

    static class RmTupleValidator {
        private final ModelInfoLookup lookup;
        private final ValidationHelper validationHelper;
        private final RmPrimitiveObjectValidator rmPrimitiveObjectValidator;

        RmTupleValidator(
                ModelInfoLookup lookup,
                ValidationHelper validationHelper,
                RmPrimitiveObjectValidator rmPrimitiveObjectValidator) {
            this.lookup = lookup;
            this.validationHelper = validationHelper;
            this.rmPrimitiveObjectValidator = rmPrimitiveObjectValidator;
        }

        List<RMObjectValidationMessage> validate(
                CObject cobject, LazyPath pathSoFar, List<RMObjectWithPath> rmObjects, CAttributeTuple tuple) {
            List<RMObjectValidationMessage> result = new ArrayList<>();
            if (rmObjects.size() != 1) {
                String message = RMObjectValidationMessageIds.rm_TUPLE_CONSTRAINT.getMessage(
                        cobject.toString(), rmObjects.toString());
                result.add(new RMObjectValidationMessage(cobject, pathSoFar.toString(), message));
                return result;
            }
            Object rmObject = rmObjects.get(0).getObject();
            if (!validationHelper.isValid(tuple, rmObject)) {
                if (tuple.getTuples().size() == 1) {
                    // Try to make useful validation messages
                    result.addAll(validateSingleTuple(pathSoFar, rmObject, tuple));
                }

                if (result.isEmpty()) {
                    // Fall back to generic validation message
                    String message = RMObjectValidationMessageIds.rm_TUPLE_MISMATCH.getMessage(tuple.toString());
                    result.add(new RMObjectValidationMessage(cobject, pathSoFar.toString(), message));
                }
            }
            return result;
        }

        /**
         * Validate a CAttributeTuple with a single tuple.
         *
         * This will check each attribute in the tuple individually to get more specific validation messages.
         */
        private List<RMObjectValidationMessage> validateSingleTuple(
                LazyPath pathSoFar, Object rmObject, CAttributeTuple attributeTuple) {
            List<RMObjectValidationMessage> result = new ArrayList<>();

            CPrimitiveTuple tuple = attributeTuple.getTuples().get(0);

            int index = 0;
            for (CAttribute attribute : attributeTuple.getMembers()) {
                String attributeName = attribute.getRmAttributeName();
                CPrimitiveObject<?, ?> cPrimitiveObject = tuple.getMembers().get(index);
                Object value = RMObjectAttributes.getAttributeValueFromRMObject(rmObject, attributeName, lookup);
                LazyPath path = pathSoFar.add(attributeName, cPrimitiveObject);

                result.addAll(rmPrimitiveObjectValidator.validate_inner(value, path, cPrimitiveObject));

                index++;
            }

            return result;
        }
    }

    static class RmMultiplicityValidator {
        List<RMObjectValidationMessage> validate(CAttribute attribute, LazyPath pathSoFar, Object attributeValue) {
            if (attributeValue instanceof Collection) {
                Collection<?> collectionValue = (Collection<?>) attributeValue;
                // validate multiplicity
                Cardinality cardinality = attribute.getCardinality();
                if (cardinality != null) {
                    if (!cardinality.getInterval().has(collectionValue.size())) {
                        String message = RMObjectValidationMessageIds.rm_CARDINALITY_MISMATCH.getMessage(
                                cardinality.getInterval().toString());
                        return Lists.newArrayList(new RMObjectValidationMessage(
                                attribute,
                                pathSoFar.toString(),
                                message,
                                RMObjectValidationMessageType.CARDINALITY_MISMATCH));
                    }
                }
            } else {
                MultiplicityInterval existence = attribute.getExistence();
                if (existence != null) {
                    if (!existence.has(attributeValue == null ? 0 : 1)) {
                        String message = RMObjectValidationMessageIds.rm_EXISTENCE_MISMATCH.getMessage(
                                attribute.getRmAttributeName(),
                                attribute.getParent() == null
                                        ? "Unknown type"
                                        : attribute.getParent().getRmTypeName(),
                                existence.toString());

                        return Lists.newArrayList((new RMObjectValidationMessage(
                                attribute, pathSoFar.toString(), message, RMObjectValidationMessageType.REQUIRED)));
                    }
                }
            }
            return new ArrayList<>();
        }
    }

    static class ValidationHelper {
        private final ModelInfoLookup lookup;
        private final PrimitiveObjectConstraintHelper primitiveObjectConstraintHelper;

        public ValidationHelper(ModelInfoLookup lookup, ValidationConfiguration validationConfiguration) {
            this.lookup = lookup;
            this.primitiveObjectConstraintHelper = new PrimitiveObjectConstraintHelper(validationConfiguration);
        }

        /**
         * True if the given value is a valid value for this constraint
         * first Converts the value to a checkable value using the given ModelInfoLookup
         * For example when it is an interval or pattern
         *
         * @param value
         * @return
         */
        public <ValueType> boolean isValidValue(CPrimitiveObject<?, ValueType> cPrimitiveObject, Object value) {
            Object convertedValue = lookup.convertToConstraintObject(value, cPrimitiveObject);
            return primitiveObjectConstraintHelper.isValidValue(cPrimitiveObject, (ValueType) convertedValue);
        }

        /**
         * Given a hashmap of attribute names mapping to its values, check the validity of this set of values
         * return true if and only if the given values are valid.
         */
        boolean isValid(CAttributeTuple cAttributeTuple, HashMap<String, Object> values) {
            for (CAttribute attribute : cAttributeTuple.getMembers()) {
                if (!values.containsKey(attribute.getRmAttributeName())) {
                    return false;
                }
            }

            for (CPrimitiveTuple tuple : cAttributeTuple.getTuples()) {
                if (isValid(cAttributeTuple, tuple, values)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isValid(
                CAttributeTuple cAttributeTuple, CPrimitiveTuple tuple, HashMap<String, Object> values) {

            int index = 0;
            for (CAttribute attribute : cAttributeTuple.getMembers()) {
                String attributeName = attribute.getRmAttributeName();

                CPrimitiveObject<?, ?> cPrimitiveObject = tuple.getMembers().get(index);
                Object value = values.get(attributeName);
                if (value == null) {
                    return false;
                    // alternatively, look at occurrences or parent attribute existence?
                    // not sure if we should in a tuple - a constrained value that is null is generally an error
                }
                if (!isValidValue(cPrimitiveObject, value)) {
                    return false;
                }
                index++;
            }
            return true;
        }

        /**
         * Given a reference model object, check if it is valid
         * return true if and only if the given values are valid.
         */
        boolean isValid(CAttributeTuple cAttributeTuple, Object value) {

            HashMap<String, Object> members = new HashMap<>();
            for (CAttribute attribute : cAttributeTuple.getMembers()) {
                RMAttributeInfo attributeInfo =
                        lookup.getAttributeInfo(value.getClass(), attribute.getRmAttributeName());
                try {
                    if (attributeInfo != null && attributeInfo.getGetMethod() != null) {
                        members.put(
                                attribute.getRmAttributeName(),
                                attributeInfo.getGetMethod().invoke(value));
                    } else {
                        // warn? throw exception?
                    }
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            return isValid(cAttributeTuple, members);
        }
    }

    static class PrimitiveObjectConstraintHelper {
        private final boolean failOnUnknownTerminologyId;

        PrimitiveObjectConstraintHelper(ValidationConfiguration validationConfiguration) {
            this.failOnUnknownTerminologyId = validationConfiguration.isFailOnUnknownTerminologyId();
        }

        /**
         * True if the given value is a valid value for this constraint
         * Must be overridden in classes where the AssumedAndDefaultValue is not the actual value.
         * For example when it is an interval or pattern
         *
         * @param value
         * @return
         */
        <ValueType> boolean isValidValue(CPrimitiveObject<?, ValueType> cPrimitiveObject, ValueType value) {
            if (cPrimitiveObject instanceof COrdered) {
                return isValidValue((COrdered<ValueType>) cPrimitiveObject, value);
            } else if (cPrimitiveObject instanceof CString) {
                return isValidValue((CString) cPrimitiveObject, (String) value);
            } else if (cPrimitiveObject instanceof CTerminologyCode) {
                return isValidValue((CTerminologyCode) cPrimitiveObject, (TerminologyCode) value);
            } else {
                return isValidValue_inner(cPrimitiveObject, value);
            }
        }

        private <ValueType> boolean isValidValue_inner(
                CPrimitiveObject<?, ValueType> cPrimitiveObject, ValueType value) {
            if (cPrimitiveObject.getConstraint().isEmpty()) {
                return true;
            }
            for (Object constraint : cPrimitiveObject.getConstraint()) {
                if (Objects.equals(constraint, value)) {
                    return true;
                }
            }
            return false;
        }

        private <T> boolean isValidValue(COrdered<T> cOrdered, T value) {
            if (cOrdered instanceof CTemporal) {
                return isValidValue((CTemporal<T>) cOrdered, value);
            } else {
                return isValidValue_inner(cOrdered, value);
            }
        }

        private <T> boolean isValidValue_inner(COrdered<T> cOrdered, T value) {
            if (cOrdered.getConstraint().isEmpty()) {
                return true;
            }
            for (Interval<T> constraint : cOrdered.getConstraint()) {
                if (constraint.has(value)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isValidValue(CString cString, String value) {
            if (cString.getConstraint().isEmpty()) {
                return true;
            }
            for (String constraint : cString.getConstraint()) {
                if (constraint.length() > 1 && CString.isRegexConstraint(constraint)) {
                    // regexp. Strip first and last character and match. If you want to input
                    // data starting and ending with '/', you cannot in the AOM, although ADL lets you express if just
                    // fine.
                    // perhaps we should make the constraint object something more expressive than a String?
                    if (matchesRegexp(value, constraint)) {
                        return true;
                    }
                } else {
                    // TODO: does case matter here?
                    if (Objects.equals(value, constraint)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean matchesRegexp(String value, String constraint) {
            return value.matches(constraint.substring(1).substring(0, constraint.length() - 2));
        }

        private <T> boolean isValidValue(CTemporal<T> cTemporal, T value) {
            if (cTemporal.getConstraint().isEmpty() && cTemporal.getPatternConstraint() == null) {
                return true;
            }
            if (cTemporal.getPatternConstraint() == null) {
                return isValidValue_inner(cTemporal, value);
            } else {
                // TODO: find a library that validates ISO 8601 patterns
                return true;
            }
        }

        private boolean isValidValue(CTerminologyCode terminologyCode, TerminologyCode value) {
            if (terminologyCode.getConstraint().isEmpty()) {
                return true;
            }
            if (terminologyCode.isConstraintRequired()) {
                if (value == null) return false;

                List<String> values;
                String terminologyId = value.getTerminologyId();
                if (terminologyId == null
                        || terminologyId.equalsIgnoreCase("local")
                        || AOMUtils.isValueSetCode(value.getTerminologyId())) {
                    values = terminologyCode.getValueSetExpanded();
                } else if (terminologyId.equalsIgnoreCase("openehr")) {
                    values = getOpenEHRValueSetExpanded(terminologyCode);
                } else {
                    // This is not a local nor an openehr terminology.
                    // If a term binding is there, we may be able to validate, if external, we wil not be able to.
                    // Return true for now for non-local terminology values.
                    // TODO: implement checking for direct term bindings later
                    return !failOnUnknownTerminologyId;
                }

                if (values != null && !values.isEmpty()) {
                    return value.getCodeString() != null && values.contains(value.getCodeString());
                }
            } else {
                return true;
            }

            return false;
        }

        private List<String> getOpenEHRValueSetExpanded(CTerminologyCode terminologyCode) {
            List<String> atCodes = terminologyCode.getValueSetExpanded();
            ArchetypeTerminology terminology = getTerminology(terminologyCode);
            OpenEHRTerminologyAccess terminologyAccess = OpenEHRTerminologyAccess.getInstance();
            List<String> result = new ArrayList<>();

            if (terminology == null) {
                return result;
            }

            for (String atCode : atCodes) {
                URI termBinding = terminology.getTermBinding("openehr", atCode);
                if (termBinding != null) {
                    String code = terminologyAccess.parseTerminologyURI(termBinding.toString());
                    if (code != null) {
                        result.add(code);
                    }
                }
            }

            return result;
        }

        private ArchetypeTerminology getTerminology(CTerminologyCode cTerminologyCode) {
            Archetype archetype = cTerminologyCode.getArchetype();
            if (archetype != null) {
                // ideally this would not happen, but no reference to archetype exists in leaf constraints in rules so
                // far
                // so for now fix it so it doesn't throw a NullPointerException
                return archetype.getTerminology(cTerminologyCode);
            }
            return null;
        }
    }

    static class RMObjectValidationUtil {

        /**
         * Retrieve the terminology name of the observation that an attribute is a part of.
         *
         * @param attribute The attribute for which the observation is retrieved
         * @return The name of the observation
         */
        public static String getParentObservationTerm(CAttribute attribute) {
            String result = "";
            CObject parent = attribute.getParent();
            CAttribute attributeParent = parent.getParent();
            while (result.equals("") && parent != null && attributeParent != null) {
                parent = attributeParent.getParent();
                if (parent != null && parent.getRmTypeName().equals("OBSERVATION")) {
                    ArchetypeTerm parentTerm = parent.getTerm();
                    if (parentTerm != null) {
                        result = parentTerm.getText();
                    }
                }
                attributeParent = parent == null ? null : parent.getParent();
            }
            return result;
        }

        public static boolean hasNoneWithWrongType(List<RMObjectValidationMessage> subResult) {
            return subResult.stream()
                    .noneMatch((message) -> message.getType() == RMObjectValidationMessageType.WRONG_TYPE);
        }

        public static List<CAttribute> getDefaultAttributeConstraints(
                CObject cObject,
                List<CAttribute> attributes,
                ModelInfoLookup lookup,
                ModelConstraintImposer constraintImposer) {
            List<CAttribute> result = new ArrayList<>();
            Set<String> alreadyConstrainedAttributes = attributes.stream()
                    .map(attribute -> attribute.getRmAttributeName())
                    .collect(Collectors.toSet());

            RMTypeInfo typeInfo = lookup.getTypeInfo(cObject.getRmTypeName());

            if (typeInfo != null) {
                for (RMAttributeInfo defaultAttribute : typeInfo.getAttributes().values()) {
                    if (!defaultAttribute.isComputed()) {
                        if (!alreadyConstrainedAttributes.contains(defaultAttribute.getRmName())) {
                            CAttribute attribute = constraintImposer.getDefaultAttribute(
                                    cObject.getRmTypeName(), defaultAttribute.getRmName());
                            attribute.setParent(cObject);
                            result.add(attribute);
                        }
                    }
                }
            }
            return result;
        }

        public static List<CAttribute> getDefaultAttributeConstraints(
                String rmTypeName,
                List<CAttribute> attributes,
                ModelInfoLookup lookup,
                ModelConstraintImposer constraintImposer) {

            CComplexObject fakeParent = new CComplexObject();
            fakeParent.setRmTypeName(rmTypeName);
            return getDefaultAttributeConstraints(fakeParent, attributes, lookup, constraintImposer);
        }

        public static String stripLastPathSegment(String path) {
            if (path.equals("/")) {
                return "";
            }
            int lastSlashIndex = path.lastIndexOf('/');
            if (lastSlashIndex == -1) {
                return path;
            }
            return path.substring(0, lastSlashIndex);
        }
    }
}
