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
package org.ehrbase.webtemplate.interpreter;

import java.io.Serializable;
import java.util.Objects;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateDto;

/**
 * @author Stefan Spiska
 */
public class InterpreterPathNode implements Serializable {

    private AqlPath.AqlNode normalisedNode;
    private PredicateDto otherPredicate;
    private SimpleTemplateNode templateNode;

    private boolean isRepresentingObject;

    public boolean isRepresentingObject() {
        return isRepresentingObject;
    }

    public void setRepresentingObject(boolean representingObject) {
        isRepresentingObject = representingObject;
    }

    public AqlPath.AqlNode getNormalisedNode() {
        return normalisedNode;
    }

    public void setNormalisedNode(AqlPath.AqlNode normalisedNode) {
        this.normalisedNode = normalisedNode;
    }

    public PredicateDto getOtherPredicate() {
        return otherPredicate;
    }

    public void setOtherPredicate(PredicateDto otherPredicate) {
        this.otherPredicate = otherPredicate;
    }

    public SimpleTemplateNode getTemplateNode() {
        return templateNode;
    }

    public void setTemplateNode(SimpleTemplateNode templateNode) {
        this.templateNode = templateNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InterpreterPathNode that = (InterpreterPathNode) o;
        return isRepresentingObject == that.isRepresentingObject
                && Objects.equals(normalisedNode, that.normalisedNode)
                && Objects.equals(otherPredicate, that.otherPredicate)
                && Objects.equals(templateNode, that.templateNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalisedNode, otherPredicate, templateNode, isRepresentingObject);
    }

    @Override
    public String toString() {
        return "InterpreterPathNode{" + "normalisedNode="
                + normalisedNode + ", otherPredicate="
                + otherPredicate + ", templateNode="
                + templateNode + ", isRepresentingObject="
                + isRepresentingObject + '}';
    }
}
