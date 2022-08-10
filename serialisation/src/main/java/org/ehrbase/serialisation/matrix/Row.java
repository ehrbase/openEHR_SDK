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

import java.util.Map;
import java.util.Objects;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class Row {

    private AqlPath pathFromRoot;
    private String archetypeId;

    private Integer[] count;

    private Integer[] index;
    private Map<AqlPath, Object> other;

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

    public Integer[] getCount() {
        return count;
    }

    public void setCount(Integer[] count) {
        this.count = count;
    }

    public Integer[] getIndex() {
        return index;
    }

    public void setIndex(Integer[] index) {
        this.index = index;
    }

    public Map<AqlPath, Object> getOther() {
        return other;
    }

    public void setOther(Map<AqlPath, Object> other) {
        this.other = other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Row row = (Row) o;
        return Objects.equals(pathFromRoot, row.pathFromRoot)
                && Objects.equals(archetypeId, row.archetypeId)
                && Objects.equals(count, row.count)
                && Objects.equals(index, row.index)
                && Objects.equals(other, row.other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathFromRoot, archetypeId, count, index, other);
    }

    @Override
    public String toString() {
        return "Row{" + "pathFromRoot="
                + pathFromRoot + ", archetypeId='"
                + archetypeId + '\'' + ", count="
                + count + ", index="
                + index + ", other="
                + other + '}';
    }
}
