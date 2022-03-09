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

import com.nedap.archie.base.MultiplicityInterval;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessageIds;
import org.apache.commons.lang3.BooleanUtils;
import org.ehrbase.validation.ConstraintViolation;
import org.ehrbase.webtemplate.model.WebTemplateNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Default {@link ConstraintValidator} implementation that validates cardinalities.
 *
 * @since 1.7
 */
public class DefaultValidator implements ConstraintValidator<RMObject> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<RMObject> getAssociatedClass() {
    return RMObject.class;
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

    node.getChildren()
        .forEach(
            childNode -> {
              var count = 0;
              for (var item :
                  locatable.itemsAtPath(node.buildRelativePath(childNode, false).toString())) {
                if (item instanceof Locatable) {
                  if (!node.isRelativePathNameDependent(childNode)
                      || Objects.equals(
                          ((Locatable) item).getNameAsString(), childNode.getName())) {
                    count++;
                  }
                } else {
                  count++;
                }
              }

              var interval = getMultiplicityInterval(childNode, node);
              if (!interval.has(count)) {
                String message =
                    RMObjectValidationMessageIds.rm_OCCURRENCE_MISMATCH.getMessage(
                        count, interval.toString());
                result.add(new ConstraintViolation(childNode.getAqlPath(), message));
              }
            });

    return result;
  }

  private MultiplicityInterval getMultiplicityInterval(WebTemplateNode node,
      WebTemplateNode parentNode) {
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
