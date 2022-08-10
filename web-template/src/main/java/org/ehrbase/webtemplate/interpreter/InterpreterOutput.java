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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.ehrbase.aql.dto.containment.Containment;

/**
 * @author Stefan Spiska
 */
public class InterpreterOutput implements Serializable {

    private int rootContainment;

    private List<Containment> originalContain;
    private List<Containment> contain;

    private InterpreterPath pathFromRootToValue = new InterpreterPath();

    public InterpreterOutput() {}

    public InterpreterOutput(InterpreterOutput other) {
        this.rootContainment = other.rootContainment;
        this.contain = other.contain.stream().map(c -> new Containment(c)).collect(Collectors.toList());
        this.originalContain =
                other.originalContain.stream().map(c -> new Containment(c)).collect(Collectors.toList());
        this.pathFromRootToValue = new InterpreterPath();
        this.pathFromRootToValue.setNodeList(new ArrayList<>(other.pathFromRootToValue.getNodeList()));
    }

    public boolean isRepresentingObject() {
        return pathFromRootToValue.getNodeList().isEmpty()
                || pathFromRootToValue
                        .getNodeList()
                        .get(pathFromRootToValue.getNodeList().size() - 1)
                        .isRepresentingObject();
    }

    public int getRootContainment() {
        return rootContainment;
    }

    public void setRootContainment(int rootContainment) {
        this.rootContainment = rootContainment;
    }

    public List<Containment> getContain() {
        return contain;
    }

    public void setContain(List<Containment> contain) {
        this.contain = contain;
    }

    public List<Containment> getOriginalContain() {
        return originalContain;
    }

    public void setOriginalContain(List<Containment> originalContain) {
        this.originalContain = originalContain;
    }

    public InterpreterPath getPathFromRootToValue() {
        return pathFromRootToValue;
    }

    public void setPathFromRootToValue(InterpreterPath pathFromRootToValue) {
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
                && Objects.equals(originalContain, that.originalContain)
                && Objects.equals(contain, that.contain)
                && Objects.equals(pathFromRootToValue, that.pathFromRootToValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rootContainment, originalContain, contain, pathFromRootToValue);
    }

    @Override
    public String toString() {
        return "InterpreterOutput{" + "rootContainment="
                + rootContainment + ", originalContain="
                + originalContain + ", contain="
                + contain + ", pathFromRootToValue="
                + pathFromRootToValue + '}';
    }
}
