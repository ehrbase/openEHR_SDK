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
import com.nedap.archie.paths.PathSegment;
import com.nedap.archie.query.RMObjectWithPath;
import com.nedap.archie.query.RMPathQuery;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.Pathable;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessageIds;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;
import org.apache.commons.lang3.BooleanUtils;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.ehrbase.openehr.sdk.webtemplate.model.WebtemplateCardinality;

/**
 * Default {@link ConstraintValidator} implementation that validates cardinalities.
 *
 * @since 1.7
 */
public class DefaultValidator implements ConstraintValidator<RMObject> {

    private final BiFunction<AqlPath, Function<AqlPath, RMPathQuery>, RMPathQuery> rmPathQueryCache;

    public DefaultValidator() {
        Map<AqlPath, RMPathQuery> cache = new HashMap<>();
        rmPathQueryCache = cache::computeIfAbsent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<RMObject> getAssociatedClass() {
        return RMObject.class;
    }

    private RMPathQuery getRmPathQuery(AqlPath path) {
        return rmPathQueryCache.apply(path, AqlPathRMPathQuery::new);
    }

    /**
     * Omits String concatenation + parsing
     */
    public static final class AqlPathRMPathQuery extends RMPathQuery {

        public AqlPathRMPathQuery(AqlPath path) {
            super("");
            List<PathSegment> pathSegments = getPathSegments();
            pathSegments.clear();
            path.getNodes().forEach(node -> pathSegments.add(new PathSegment(node.getName(), node.getAtCode())));
        }
    }

    private Stream<Object> itemsAtPath(AqlPath path, Pathable currentPathable) {
        return getRmPathQuery(path).findList(ArchieRMInfoLookup.getInstance(), currentPathable).stream()
                .map(RMObjectWithPath::getObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ConstraintViolation> validate(RMObject object, WebTemplateNode node) {
        if (node == null || !(object instanceof Locatable locatable)) {
            return List.of();
        }
        return validate(locatable, node);
    }

    private List<ConstraintViolation> validate(Locatable locatable, WebTemplateNode node) {
        List<WebTemplateNode> children = node.getChildren();

        List<ConstraintViolation> result = List.of();
        for (WebTemplateNode childNode : children) {
            ConstraintViolation violation = validateChild(locatable, node, childNode);
            if (violation != null) {
                result = ConstraintValidator.concat(result, List.of(violation));
            }
        }
        return result;
    }

    private ConstraintViolation validateChild(Locatable locatable, WebTemplateNode node, WebTemplateNode childNode) {
        AqlPath relativePath = node.buildRelativePath(childNode, false);

        var count = (int) itemsAtPath(relativePath, locatable)
                .filter(item -> {
                    if (item instanceof Locatable loc) {
                        return Objects.equals(loc.getNameAsString(), childNode.getName())
                                || !node.isRelativePathNameDependent(childNode);
                    } else {
                        return true;
                    }
                })
                .count();

        var interval = getMultiplicityInterval(childNode, node);
        if (interval.has(count)) {
            return null;
        } else {
            String message = RMObjectValidationMessageIds.rm_OCCURRENCE_MISMATCH.getMessage(count, interval.toString());
            return new ConstraintViolation(childNode.getAqlPath(), message);
        }
    }

    private MultiplicityInterval getMultiplicityInterval(WebTemplateNode node, WebTemplateNode parentNode) {
        var interval = WebTemplateValidationUtils.getMultiplicityInterval(node);
        for (WebtemplateCardinality cardinality : parentNode.getCardinalities()) {
            if (BooleanUtils.isTrue(cardinality.getExcludeFromWebTemplate())
                    && cardinality.getIds().contains(node.getId(false))) {
                interval.setLower(cardinality.getMin());
                interval.setUpper(cardinality.getMax());
                break;
            }
        }
        return interval;
    }
}
