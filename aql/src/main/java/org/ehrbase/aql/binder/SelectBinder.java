/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.aql.binder;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.select.FunctionDto;
import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.ehrbase.aql.dto.select.SelectStatementDto;
import org.ehrbase.aql.parser.AqlParseException;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.NativeSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.funtion.Function;
import org.ehrbase.util.exception.SdkException;

public class SelectBinder {

    public SelectAqlField<Object> bind(SelectStatementDto dto, Map<Integer, Containment> containmentMap) {
        SelectAqlField<Object> selectAqlField;
        if (dto instanceof SelectFieldDto) {
            selectAqlField = handleSelectFieldDto((SelectFieldDto) dto, containmentMap);
        } else if (dto instanceof FunctionDto) {
            selectAqlField = handleFunctionDto((FunctionDto) dto, containmentMap);
        } else {
            throw new SdkException(
                    String.format("Unexpected class: %s", dto.getClass().getSimpleName()));
        }
        return selectAqlField;
    }

    private SelectAqlField<Object> handleFunctionDto(FunctionDto dto, Map<Integer, Containment> containmentMap) {

        switch (dto.getAqlFunction()) {
            case COUNT:
                return (SelectAqlField) Function.count(bind(dto.getParameters().get(0), containmentMap), dto.getName());
            case MAX:
                return (SelectAqlField) Function.max(bind(dto.getParameters().get(0), containmentMap), dto.getName());
            case MIN:
                return (SelectAqlField) Function.min(bind(dto.getParameters().get(0), containmentMap), dto.getName());
            case AVG:
                return (SelectAqlField) Function.avg(bind(dto.getParameters().get(0), containmentMap), dto.getName());
            default:
                throw new AqlParseException(String.format(
                        "Unsupported Funktion %s", dto.getAqlFunction().name()));
        }
    }

    public SelectAqlField<Object> handleSelectFieldDto(SelectFieldDto dto, Map<Integer, Containment> containmentMap) {
        SelectAqlField<Object> selectAqlField;
        selectAqlField = new NativeSelectAqlField<>(
                containmentMap.get(dto.getContainmentId()),
                dto.getAqlPath(),
                StringUtils.isNotBlank(dto.getName()) ? dto.getName() : null,
                Object.class);
        return selectAqlField;
    }
}
