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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Stefan Spiska
 */
public class Index {

    private final Map<Integer, Integer> indexMap = new LinkedHashMap<>();
    private Integer index = 0;

    public Index() {}

    public Index(Index other) {
        this.index = other.index;
        indexMap.putAll(other.indexMap);
    }

    public void incrementIndex() {
        index++;
    }

    public void setRepetition(Integer repetition) {

        indexMap.put(index, repetition);
    }

    List<Integer> getRepetitions() {

        return new ArrayList<>(indexMap.values());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Index index1 = (Index) o;
        return Objects.equals(indexMap, index1.indexMap) && Objects.equals(index, index1.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexMap, index);
    }

    @Override
    public String toString() {
        return "Index{" + "indexMap=" + indexMap + ", index=" + index + '}';
    }
}
