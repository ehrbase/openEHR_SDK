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

import java.util.List;
import java.util.Objects;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class InterpreterOutput {

    private int rootContainment;

    private List<AqlPath.AqlNode> contain;

    private List<InterpreterPathNode> pathFromRootToValue;

    public int getRootContainment() {
        return rootContainment;
    }

    public void setRootContainment(int rootContainment) {
        this.rootContainment = rootContainment;
    }

    public List<AqlPath.AqlNode> getContain() {
        return contain;
    }

    public void setContain(List<AqlPath.AqlNode> contain) {
        this.contain = contain;
    }

    public List<InterpreterPathNode> getPathFromRootToValue() {
        return pathFromRootToValue;
    }

    public void setPathFromRootToValue(List<InterpreterPathNode> pathFromRootToValue) {
        this.pathFromRootToValue = pathFromRootToValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InterpreterOutput that = (InterpreterOutput) o;
        return rootContainment == that.rootContainment
                && Objects.equals(contain, that.contain)
                && Objects.equals(pathFromRootToValue, that.pathFromRootToValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rootContainment, contain, pathFromRootToValue);
    }

    @Override
    public String toString() {
        return "InterpreterOutput{" + "rootContainment="
                + rootContainment + ", contain="
                + contain + ", pathFromRootToValue="
                + pathFromRootToValue + '}';
    }
}
