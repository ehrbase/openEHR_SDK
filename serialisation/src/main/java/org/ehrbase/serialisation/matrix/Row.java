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

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class Row {

    private int num;
    private AqlPath entityPath;
    private String archetypeId;

    private Integer[] entityIdx;

    private Integer[] fieldIdx;
    private Map<AqlPath, Object> fields;

    public AqlPath getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(AqlPath entityPath) {
        this.entityPath = entityPath;
    }

    public String getArchetypeId() {
        return archetypeId;
    }

    public void setArchetypeId(String archetypeId) {
        this.archetypeId = archetypeId;
    }

    public Integer[] getEntityIdx() {
        return entityIdx;
    }

    public void setEntityIdx(Integer[] entityIdx) {
        this.entityIdx = entityIdx;
    }

    public Integer[] getFieldIdx() {
        return fieldIdx;
    }

    public void setFieldIdx(Integer[] fieldIdx) {
        this.fieldIdx = fieldIdx;
    }

    public Map<AqlPath, Object> getFields() {
        return fields;
    }

    public void setFields(Map<AqlPath, Object> fields) {
        this.fields = fields;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
        return num == row.num
                && Objects.equals(entityPath, row.entityPath)
                && Objects.equals(archetypeId, row.archetypeId)
                && Arrays.equals(entityIdx, row.entityIdx)
                && Arrays.equals(fieldIdx, row.fieldIdx)
                && Objects.equals(fields, row.fields);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(num, entityPath, archetypeId, fields);
        result = 31 * result + Arrays.hashCode(entityIdx);
        result = 31 * result + Arrays.hashCode(fieldIdx);
        return result;
    }

    @Override
    public String toString() {
        return "Row{" + "num="
                + num + ", pathFromRoot="
                + entityPath + ", archetypeId='"
                + archetypeId + '\'' + ", count="
                + Arrays.toString(entityIdx) + ", index="
                + Arrays.toString(fieldIdx) + ", other="
                + fields + '}';
    }
}
