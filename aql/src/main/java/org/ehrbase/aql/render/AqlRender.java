/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.aql.render;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.containment.Containment;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.containment.ContainmentExpresionDto;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.select.SelectDto;
import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.ehrbase.aql.dto.select.SelectStatementDto;
import org.ehrbase.util.exception.SdkException;

/**
 * @author Stefan Spiska
 */
public class AqlRender {

    private final AqlDto dto;

    private final Map<Integer, ContainmentDto> containmentByIdMap = new HashMap<>();

    public AqlRender(AqlDto dto) {
        this.dto = dto;
    }

    public String render() {

        StringBuilder sb = new StringBuilder();

        String from = buildFrom();

        renderSelect(sb, dto.getSelect());

        sb.append(from);

        return sb.toString();
    }

    private void renderSelect(StringBuilder sb, SelectDto dto) {

        sb.append("SELECT ");

        sb.append(dto.getStatement().stream()
                .map(s -> {
                    StringBuilder sbInner = new StringBuilder();
                    renderSelectStatementDto(sbInner, s);
                    return sbInner.toString();
                })
                .collect(Collectors.joining(", ")));

        sb.append(" ");
    }

    private void renderSelectStatementDto(StringBuilder sb, SelectStatementDto dto) {

        if (dto instanceof SelectFieldDto) {
            renderSelectFieldDto(sb, (SelectFieldDto) dto);
        }
    }

    private void renderSelectFieldDto(StringBuilder sb, SelectFieldDto dto) {

        ContainmentDto containmentExpresionDto = containmentByIdMap.get(dto.getContainmentId());

        if (containmentExpresionDto == null) {
            throw new SdkException("Select without corresponding contains");
        }

        sb.append(containmentExpresionDto.getIdentifier());

        sb.append(dto.getAqlPathDto().format(AqlPath.OtherPredicatesFormat.SHORTED, false));

        if (dto.getName() != null) {
            sb.append(" AS ").append(dto.getName());
        }
    }

    private String buildFrom() {
        StringBuilder sb = new StringBuilder();

        sb.append("FROM ");

        renderContainmentExpresionDto(sb, dto.getContains());

        return sb.toString();
    }

    private void renderContainmentExpresionDto(StringBuilder sb, ContainmentExpresionDto dto) {

        if (dto instanceof ContainmentDto) {
            renderContainmentDto(sb, (ContainmentDto) dto);
        }
    }

    private void renderContainmentDto(StringBuilder sb, ContainmentDto dto) {

        containmentByIdMap.put(dto.getId(), dto);

        Containment containment = dto.getContainment();

        sb.append(containment.getType()).append(" ");

        if (dto.getIdentifier() != null) {
            sb.append(dto.getIdentifier());
        }

        if (containment.getOtherPredicates() != null) {

            sb.append(PredicateHelper.format(containment.getOtherPredicates(), AqlPath.OtherPredicatesFormat.SHORTED));
        }

        if (dto.getContains() != null) {
            sb.append(" CONTAINS ");
            renderContainmentExpresionDto(sb, dto.getContains());
        }
    }
}
