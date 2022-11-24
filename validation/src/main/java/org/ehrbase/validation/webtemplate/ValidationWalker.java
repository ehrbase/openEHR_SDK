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
package org.ehrbase.validation.webtemplate;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.util.List;
import java.util.Map;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FromCompositionWalker;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.validation.ConstraintViolation;
import org.ehrbase.validation.terminology.ExternalTerminologyValidation;
import org.ehrbase.webtemplate.model.WebTemplateNode;
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

    public ValidationWalker(ExternalTerminologyValidation externalTerminologyValidation) {
        if (externalTerminologyValidation != null) {
            VALIDATORS.put(DvCodedText.class, new DvCodedTextValidator(externalTerminologyValidation));
        }
    }

    @Override
    protected void preHandle(Context<List<ConstraintViolation>> context) {
        var node = context.getNodeDeque().element();
        var rmObject = context.getRmObjectDeque().element();
        var result = context.getObjectDeque().element();

        logger.trace("PreHandle: {}, rmObject={}", node, rmObject);

        var validator = getValidator(rmObject);
        result.addAll(validator.validate(rmObject, node));
    }

    @Override
    protected List<ConstraintViolation> extract(
            Context<List<ConstraintViolation>> context, WebTemplateNode child, boolean isChoice, Integer i) {
        return context.getObjectDeque().peek();
    }

    @Override
    protected void postHandle(Context<List<ConstraintViolation>> context) {
        // No-op
    }

    @SuppressWarnings("unchecked")
    private <T extends RMObject> ConstraintValidator<T> getValidator(RMObject object) {
        return VALIDATORS.getOrDefault(object.getClass(), defaultValidator);
    }
}
