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

import com.nedap.archie.base.MultiplicityInterval;
import com.nedap.archie.query.RMObjectWithPath;
import com.nedap.archie.query.RMPathQuery;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.Pathable;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessageIds;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.BooleanUtils;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

/**
 * Default {@link ConstraintValidator} implementation that validates cardinalities.
 *
 * @since 1.7
 */
public class DefaultValidator implements ConstraintValidator<RMObject> {

    private BiFunction<AqlPath, Function<AqlPath, RMPathQuery>, RMPathQuery> rmPathQueryCache;

    public DefaultValidator() {
        Map<AqlPath, RMPathQuery> cache = new HashMap<>();
        rmPathQueryCache = (path, provider) -> cache.computeIfAbsent(path, provider::apply);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<RMObject> getAssociatedClass() {
        return RMObject.class;
    }

    private RMPathQuery getRmPathQuery(AqlPath path) {
        return rmPathQueryCache.apply(path, p -> new RMPathQuery(p.toString()));
    }

    private List<Object> itemsAtPath(AqlPath path, Pathable currentPathable) {
        return getRmPathQuery(path).findList(ArchieRMInfoLookup.getInstance(), currentPathable).stream()
                .map(RMObjectWithPath::getObject)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ConstraintViolation> validate(RMObject object, WebTemplateNode node) {
        if (node == null || !(object instanceof Locatable)) {
            return Collections.emptyList();
        }

        return validate((Locatable) object, node);
    }

    private List<ConstraintViolation> validate(Locatable locatable, WebTemplateNode node) {
        List<ConstraintViolation> result = new ArrayList<>();
        node.getChildren().forEach(childNode -> {
            var count = 0;
            AqlPath relativePath = node.buildRelativePath(childNode, false);
            List<Object> children = itemsAtPath(relativePath, locatable);

            for (var item : children) {
                if (item instanceof Locatable) {
                    if (Objects.equals(((Locatable) item).getNameAsString(), childNode.getName())
                            || !node.isRelativePathNameDependent(childNode)) {
                        count++;
                    }
                } else {
                    count++;
                }
            }

            var interval = getMultiplicityInterval(childNode, node);
            if (!interval.has(count)) {
                String message =
                        RMObjectValidationMessageIds.rm_OCCURRENCE_MISMATCH.getMessage(count, interval.toString());
                result.add(new ConstraintViolation(childNode.getAqlPath(), message));
            }
        });

        return result;
    }

    private MultiplicityInterval getMultiplicityInterval(WebTemplateNode node, WebTemplateNode parentNode) {
        var interval = WebTemplateValidationUtils.getMultiplicityInterval(node);

        parentNode.getCardinalities().stream()
                .filter(cardinality -> BooleanUtils.isTrue(cardinality.getExcludeFromWebTemplate())
                        && cardinality.getIds().contains(node.getId(false)))
                .findFirst()
                .ifPresent(cardinality -> {
                    interval.setLower(cardinality.getMin());
                    interval.setUpper(cardinality.getMax());
                });

        return interval;
    }
}
