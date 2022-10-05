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
package org.ehrbase.serialisation.matrixencoding;

import java.util.Objects;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
class Entity {

    private AqlPath pathFromRoot;
    private String archetypeId;

    private String rmType;

    private Index entityIdx = new Index();

    public Entity() {}

    public Entity(Entity other) {
        this.pathFromRoot = other.pathFromRoot;
        this.archetypeId = other.archetypeId;
        this.entityIdx = new Index(other.entityIdx);
        this.rmType = other.getRmType();
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

    public Index getEntityIdx() {
        return entityIdx;
    }

    public void setEntityIdx(Index entityIdx) {
        this.entityIdx = entityIdx;
    }

    public String getRmType() {
        return rmType;
    }

    public void setRmType(String rmType) {
        this.rmType = rmType;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(pathFromRoot, entity.pathFromRoot)
                && Objects.equals(archetypeId, entity.archetypeId)
                && Objects.equals(rmType, entity.rmType)
                && Objects.equals(entityIdx, entity.entityIdx);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathFromRoot, archetypeId, rmType, entityIdx);
    }

    @Override
    public String toString() {
        return "Entity{" + "pathFromRoot="
                + pathFromRoot + ", archetypeId='"
                + archetypeId + '\'' + ", rmType='"
                + rmType + '\'' + ", entityIdx="
                + entityIdx + '}';
    }
}
