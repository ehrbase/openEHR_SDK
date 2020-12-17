/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.aql.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.EhrDto;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.containment.ContainmentExpresionDto;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperator;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperatorSymbol;
import org.ehrbase.aql.dto.select.SelectDto;
import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.ehrbase.aql.dto.select.SelectStatementDto;

public class AqlToDtoVisitor extends AqlBaseVisitor<Object> {

  private int containmentId = 0;
  private final Map<String, Integer> identifierMap = new HashMap<>();
  private final MultiValuedMap<String, SelectFieldDto> selectFieldDtoMultiMap =
      new ArrayListValuedHashMap<>();

  @Override
  public AqlDto visitQuery(AqlParser.QueryContext ctx) {
    AqlDto aqlDto = new AqlDto();

    aqlDto.setEhr(visitFromEHR(ctx.queryExpr().from().fromEHR()));
    aqlDto.setContains(visitContainsExpression(ctx.queryExpr().from().containsExpression()));
    aqlDto.setSelect(visitSelect(ctx.queryExpr().select()));

    selectFieldDtoMultiMap
        .entries()
        .forEach(
            e ->
                e.getValue()
                    .setContainmentId(
                        Optional.ofNullable(identifierMap.get(e.getKey())).orElseThrow()));
    return aqlDto;
  }

  @Override
  public EhrDto visitFromEHR(AqlParser.FromEHRContext ctx) {
    EhrDto ehrDto = new EhrDto();
    ehrDto.setContainmentId(buildContainmentId());
    if (ctx.IDENTIFIER() != null) {
      identifierMap.put(ctx.IDENTIFIER().getText(), ehrDto.getContainmentId());
    }

    return ehrDto;
  }

  @Override
  public SelectDto visitSelect(AqlParser.SelectContext ctx) {
    SelectDto selectDto = new SelectDto();
    selectDto.setStatement(visitSelectExpr(ctx.selectExpr()));
    return selectDto;
  }

  @Override
  public List<SelectStatementDto> visitSelectExpr(AqlParser.SelectExprContext ctx) {
    List<SelectStatementDto> selectStatementDtos = new ArrayList<>();

    if (ctx.identifiedPath() != null) {
      SelectFieldDto selectFieldDto = visitIdentifiedPath(ctx.identifiedPath());
      selectFieldDto.setName(ctx.IDENTIFIER().getText());
      selectStatementDtos.add(selectFieldDto);
    }

    if (ctx.selectExpr() != null) {
      selectStatementDtos.addAll(visitSelectExpr(ctx.selectExpr()));
    }
    return selectStatementDtos;
  }

  @Override
  public SelectFieldDto visitIdentifiedPath(AqlParser.IdentifiedPathContext ctx) {
    SelectFieldDto selectStatementDto = new SelectFieldDto();
    selectFieldDtoMultiMap.put(ctx.IDENTIFIER().getText(), selectStatementDto);
    selectStatementDto.setAqlPath(
        StringUtils.removeStart(ctx.getText(), ctx.IDENTIFIER().getText()));

    return selectStatementDto;
  }

  @Override
  public ContainmentExpresionDto visitContainsExpression(AqlParser.ContainsExpressionContext ctx) {

    List<Object> boolList = new ArrayList<>();

    AqlParser.ContainsExpressionContext currentContext = ctx;
    while (currentContext != null) {
      ContainmentLogicalOperatorSymbol symbol = extractSymbol(currentContext);
      boolList.add(visitContainExpressionBool(currentContext.containExpressionBool()));

      if (symbol != null) {
        boolList.add(symbol);
      }
      currentContext = currentContext.containsExpression();
    }

    if (boolList.size() == 1) {
      return (ContainmentExpresionDto) boolList.get(0);
    } else {
      return buildContainmentLogicalOperator(boolList);
    }
  }

  private ContainmentLogicalOperator buildContainmentLogicalOperator(List<Object> boolList) {

    ContainmentLogicalOperator currentOperator = new ContainmentLogicalOperator();
    ContainmentLogicalOperatorSymbol currentSymbol =
        (ContainmentLogicalOperatorSymbol) boolList.get(1);
    currentOperator.setSymbol(currentSymbol);
    currentOperator.setValues(new ArrayList<>());
    currentOperator.getValues().add((ContainmentExpresionDto) boolList.get(0));
    ContainmentLogicalOperator lowestOperator = currentOperator;
    for (int i = 2; i < boolList.size(); i = i + 2) {
      ContainmentLogicalOperatorSymbol nextSymbol =
          i + 1 < boolList.size() ? (ContainmentLogicalOperatorSymbol) boolList.get(i + 1) : null;
      if (nextSymbol == null || Objects.equals(currentSymbol, nextSymbol)) {
        currentOperator.getValues().add((ContainmentExpresionDto) boolList.get(i));
        currentSymbol = nextSymbol;
      } else {
        ContainmentLogicalOperator nextOperator = new ContainmentLogicalOperator();
        nextOperator.setSymbol(nextSymbol);
        nextOperator.setValues(new ArrayList<>());

        if (hasHigherPrecedence(currentSymbol, nextSymbol)) {
          currentOperator.getValues().add((ContainmentExpresionDto) boolList.get(i));
          nextOperator.getValues().add(currentOperator);
          lowestOperator = nextOperator;
        } else {
          nextOperator.getValues().add((ContainmentExpresionDto) boolList.get(i));
          currentOperator.getValues().add(nextOperator);
          lowestOperator = currentOperator;
        }

        currentOperator = nextOperator;
        currentSymbol = nextSymbol;
      }
    }
    return lowestOperator;
  }

  @Override
  public ContainmentExpresionDto visitContainExpressionBool(
      AqlParser.ContainExpressionBoolContext ctx) {

    if (ctx.contains() != null) {
      return visitContains(ctx.contains());
    } else {
      return visitContainsExpression(ctx.containsExpression());
    }
  }

  @Override
  public ContainmentDto visitContains(AqlParser.ContainsContext ctx) {

    ContainmentDto containmentDto = visitSimpleClassExpr(ctx.simpleClassExpr());
    if (ctx.containsExpression() != null) {
      containmentDto.setContains(visitContainsExpression(ctx.containsExpression()));
    }
    return containmentDto;
  }

  @Override
  public ContainmentDto visitSimpleClassExpr(AqlParser.SimpleClassExprContext ctx) {
    ContainmentDto currentContainment = new ContainmentDto();
    currentContainment.setId(buildContainmentId());
    AqlParser.ArchetypedClassExprContext archetypedClassExprContext =
        ctx.getChild(AqlParser.ArchetypedClassExprContext.class, 0);
    if (archetypedClassExprContext != null) {
      if (archetypedClassExprContext.IDENTIFIER().size() == 2) {
        currentContainment.setIdentifier(archetypedClassExprContext.IDENTIFIER(1).getText());
        identifierMap.put(currentContainment.getIdentifier(), currentContainment.getId());
      }
      currentContainment.setArchetypeId(archetypedClassExprContext.ARCHETYPEID().getText());

    } else {
      currentContainment.setArchetypeId(ctx.IDENTIFIER(0).getText());
    }

    return currentContainment;
  }

  private boolean hasHigherPrecedence(
      ContainmentLogicalOperatorSymbol operatorSymbol,
      ContainmentLogicalOperatorSymbol nextOperatorSymbol) {
    if (nextOperatorSymbol == null) {
      return true;
    } else {
      return operatorSymbol.getPrecedence() <= nextOperatorSymbol.getPrecedence();
    }
  }

  private int buildContainmentId() {
    return containmentId++;
  }

  private ContainmentLogicalOperatorSymbol extractSymbol(AqlParser.ContainsExpressionContext ctx) {
    if (ctx == null) {
      return null;
    }
    if (ctx.OR() != null) {
      return ContainmentLogicalOperatorSymbol.OR;
    } else if (ctx.AND() != null) {
      return ContainmentLogicalOperatorSymbol.AND;
    } else if (ctx.XOR() != null) {
      return ContainmentLogicalOperatorSymbol.XOR;
    } else {
      return null;
    }
  }
}
