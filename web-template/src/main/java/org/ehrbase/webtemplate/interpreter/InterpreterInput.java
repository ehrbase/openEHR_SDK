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

import static org.ehrbase.aql.dto.path.AqlPath.*;

import java.util.List;
import java.util.Objects;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class InterpreterInput {

    private AqlPath pathFromContentment;
    private AqlNode containment;
    private List<AqlNode> containmentPath;

    public AqlPath getPathFromContentment() {
        return pathFromContentment;
    }

    public void setPathFromContentment(AqlPath pathFromContentment) {
        this.pathFromContentment = pathFromContentment;
    }

    public AqlNode getContainment() {
        return containment;
    }

    public void setContainment(AqlNode containment) {
        this.containment = containment;
    }

    public List<AqlNode> getContainmentPath() {
        return containmentPath;
    }

    public void setContainmentPath(List<AqlNode> containmentPath) {
        this.containmentPath = containmentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InterpreterInput that = (InterpreterInput) o;
        return Objects.equals(pathFromContentment, that.pathFromContentment)
                && Objects.equals(containment, that.containment)
                && Objects.equals(containmentPath, that.containmentPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathFromContentment, containment, containmentPath);
    }

    @Override
    public String toString() {
        return "InterpreterInput{" + "pathFromContentment="
                + pathFromContentment + ", containment="
                + containment + ", containmentPath="
                + containmentPath + '}';
    }
}
