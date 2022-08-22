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
class FromWalkerDto {

    private final Map<Entity, Map<Index, Map<AqlPath, Object>>> matrix;

    private Entity currentEntity;
    private Index currentFieldIndex;

    private boolean rootFound = false;

    public FromWalkerDto() {

        this.matrix = new LinkedHashMap<>();
    }

    public FromWalkerDto(FromWalkerDto other) {
        this.currentEntity = new Entity(other.currentEntity);
        this.currentFieldIndex = new Index(other.currentFieldIndex);
        this.matrix = other.matrix;
        this.rootFound = other.rootFound;
    }

    public boolean isRootFound() {
        return rootFound;
    }

    public void setRootFound(boolean rootFound) {
        this.rootFound = rootFound;
    }

    public Map<Entity, Map<Index, Map<AqlPath, Object>>> getMatrix() {
        return matrix;
    }

    public Entity getCurrentEntity() {
        return currentEntity;
    }

    public void updateEntity(Entity currentEntity) {
        this.currentEntity = currentEntity;
        matrix.put(currentEntity, new LinkedHashMap<>());
        Index key = new Index();
        matrix.get(currentEntity).put(key, new LinkedHashMap<>());
        currentFieldIndex = key;
    }

    public Index getCurrentFieldIndex() {
        return currentFieldIndex;
    }

    public void setCurrentFieldIndex(Index currentFieldIndex) {
        this.currentFieldIndex = currentFieldIndex;
    }
}
