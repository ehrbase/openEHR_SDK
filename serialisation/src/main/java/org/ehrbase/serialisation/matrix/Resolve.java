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
package org.ehrbase.serialisation.matrix;

import java.util.Objects;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
class Resolve {

    private AqlPath pathFromRoot;
    private String archetypeId;

    private Index count = new Index();

    public Resolve() {}

    public Resolve(Resolve other) {
        this.pathFromRoot = AqlPath.parse(other.pathFromRoot.getPath());
        this.archetypeId = other.archetypeId;
        this.count = new Index(other.count);
    }

    public AqlPath getPathFromRoot() {
        return pathFromRoot;
    }

    public void setPathFromRoot(AqlPath pathFromRoot) {
        this.pathFromRoot = pathFromRoot;
    }

    public String getArchetypeId() {
        return archetypeId;
    }

    public void setArchetypeId(String archetypeId) {
        this.archetypeId = archetypeId;
    }

    public Index getCount() {
        return count;
    }

    public void setCount(Index count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resolve resolve = (Resolve) o;
        return Objects.equals(pathFromRoot, resolve.pathFromRoot)
                && Objects.equals(archetypeId, resolve.archetypeId)
                && Objects.equals(count, resolve.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathFromRoot, archetypeId, count);
    }

    @Override
    public String toString() {
        return "Resolve{" + "pathFromRoot="
                + pathFromRoot + ", archetypeId='"
                + archetypeId + '\'' + ", count="
                + count + '}';
    }
}
