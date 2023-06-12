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
package org.ehrbase.aql.dto.operand;

import org.ehrbase.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.AqlPredicate;

/**
 * @author Stefan Spiska
 */
public class IdentifiedPath implements ColumnExpression, Operand, ComparisonLeftOperand {

    private AbstractContainmentExpression from;

    private AqlPredicate rootPredicate;

    private AqlPath path;

    public AbstractContainmentExpression getFrom() {
        return from;
    }

    public void setFrom(AbstractContainmentExpression from) {
        this.from = from;
    }

    public AqlPredicate getRootPredicate() {
        return rootPredicate;
    }

    public void setRootPredicate(AqlPredicate rootPredicate) {
        this.rootPredicate = rootPredicate;
    }

    public AqlPath getPath() {
        return path;
    }

    public void setPath(AqlPath path) {
        this.path = path;
    }
}
