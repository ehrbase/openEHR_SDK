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
import java.util.Map;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class WalkerDto {

    private final Map<Resolve, Map<Index, Map<AqlPath, Object>>> matrix;

    private Resolve currentResolve;
    private Index currentIndex;

    private boolean rootFound = false;

    public WalkerDto() {

        this.matrix = new LinkedHashMap<>();
    }

    public WalkerDto(WalkerDto other) {
        this.currentResolve = new Resolve(other.currentResolve);
        this.currentIndex = new Index(other.currentIndex);
        this.matrix = other.matrix;
        this.rootFound = other.rootFound;
    }

    public boolean isRootFound() {
        return rootFound;
    }

    public void setRootFound(boolean rootFound) {
        this.rootFound = rootFound;
    }

    public Map<Resolve, Map<Index, Map<AqlPath, Object>>> getMatrix() {
        return matrix;
    }

    public Resolve getCurrentResolve() {
        return currentResolve;
    }

    public void updateResolve(Resolve currentResolve) {
        this.currentResolve = currentResolve;
        matrix.put(currentResolve, new LinkedHashMap<>());
        Index key = new Index();
        matrix.get(currentResolve).put(key, new LinkedHashMap<>());
        currentIndex = key;
    }

    public Index getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Index currentIndex) {
        this.currentIndex = currentIndex;
    }
}
