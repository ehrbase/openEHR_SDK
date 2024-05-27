/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.dto.operand;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.openehr.sdk.aql.dto.path.AndOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPath;
import org.ehrbase.openehr.sdk.aql.render.AqlRenderer;
import org.ehrbase.openehr.sdk.aql.serializer.ObjectPathDeserializer;
import org.ehrbase.openehr.sdk.aql.serializer.ObjectPathSerializer;
import org.ehrbase.openehr.sdk.aql.serializer.PredicateDeserializer;
import org.ehrbase.openehr.sdk.aql.serializer.PredicateSerializer;

/**
 * @author Stefan Spiska
 */
public final class IdentifiedPath implements ColumnExpression, Operand, ComparisonLeftOperand {
    @JsonIdentityReference(alwaysAsId = true)
    private AbstractContainmentExpression root;

    private List<AndOperatorPredicate> rootPredicate;

    @JsonSerialize(using = ObjectPathSerializer.class)
    @JsonDeserialize(using = ObjectPathDeserializer.class)
    private AqlObjectPath path;

    public AbstractContainmentExpression getRoot() {
        return root;
    }

    public void setRoot(AbstractContainmentExpression root) {
        this.root = root;
    }

    @JsonSerialize(using = PredicateSerializer.class)
    public List<AndOperatorPredicate> getRootPredicate() {
        return rootPredicate;
    }

    @JsonIgnore
    @JsonDeserialize(using = PredicateDeserializer.class)
    public void setRootPredicate(List<AndOperatorPredicate> rootPredicate) {
        this.rootPredicate = new ArrayList<>(rootPredicate);
    }

    public AqlObjectPath getPath() {
        return path;
    }

    public void setPath(AqlObjectPath path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifiedPath that = (IdentifiedPath) o;
        return Objects.equals(root, that.root)
                && Objects.equals(rootPredicate, that.rootPredicate)
                && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, rootPredicate, path);
    }

    @Override
    public String toString() {
        return "IdentifiedPath{" + "root=" + root + ", rootPredicate=" + rootPredicate + ", path=" + path + '}';
    }

    public String render() {
        return AqlRenderer.render(this);
    }
}
