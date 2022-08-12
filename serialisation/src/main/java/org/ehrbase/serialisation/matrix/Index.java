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

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * @author Stefan Spiska
 */
class Index {

    private final LinkedHashMap<String, Integer> indexMap = new LinkedHashMap<>();

    public Index() {}

    public Index(Index other) {

        indexMap.putAll(other.indexMap);
    }

    public void add(String key, Integer value) {
        indexMap.put(key, value);
    }

    Integer[] getRepetitions() {

        return indexMap.values().toArray(new Integer[0]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Index index = (Index) o;
        return Objects.equals(indexMap, index.indexMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexMap);
    }

    @Override
    public String toString() {
        return "Index{" + "indexMap=" + indexMap + '}';
    }
}
