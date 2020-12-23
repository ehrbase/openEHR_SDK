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

import com.nedap.archie.datetime.DateTimeParsers;
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
import org.ehrbase.aql.dto.LogicalOperatorSymbol;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorDto;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorSymbol;
import org.ehrbase.aql.dto.condition.ConditionDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorSymbol;
import org.ehrbase.aql.dto.condition.ParameterValue;
import org.ehrbase.aql.dto.condition.SimpleValue;
import org.ehrbase.aql.dto.condition.Value;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.containment.ContainmentExpresionDto;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperator;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperatorSymbol;
import org.ehrbase.aql.dto.orderby.OrderByExpressionDto;
import org.ehrbase.aql.dto.orderby.OrderByExpressionSymbol;
import org.ehrbase.aql.dto.select.SelectDto;
import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.ehrbase.aql.dto.select.SelectStatementDto;
import org.ehrbase.client.aql.top.Direction;

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
    if (ctx.queryExpr().where() != null) {
      aqlDto.setWhere(visitIdentifiedExpr(ctx.queryExpr().where().identifiedExpr()));
    }

    if (ctx.queryExpr().orderBy() != null) {
      aqlDto.setOrderBy(visitOrderBySeq(ctx.queryExpr().orderBy().orderBySeq()));
    }

    if (ctx.queryExpr().limit() != null) {
      aqlDto.setLimit(Integer.parseInt(ctx.queryExpr().limit().INTEGER().getText()));
    }
    if (ctx.queryExpr().offset() != null) {
      aqlDto.setOffset(Integer.parseInt(ctx.queryExpr().offset().INTEGER().getText()));
    }
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
      ehrDto.setIdentifier(ctx.IDENTIFIER().getText());
    }

    return ehrDto;
  }

  @Override
  public SelectDto visitSelect(AqlParser.SelectContext ctx) {
    SelectDto selectDto = new SelectDto();
    selectDto.setStatement(visitSelectExpr(ctx.selectExpr()));
    if (ctx.topExpr() != null) {
      selectDto.setTopDirection(extractSymbol(ctx.topExpr()));
      selectDto.setTopCount(Integer.parseInt(ctx.topExpr().INTEGER().getText()));
    }
    return selectDto;
  }

  private Direction extractSymbol(AqlParser.TopExprContext topExpr) {
    if (topExpr.BACKWARD() != null) {
      return Direction.BACKWARD;
    } else {
      return Direction.FORWARD;
    }
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

  @Override
  public ConditionDto visitIdentifiedExpr(AqlParser.IdentifiedExprContext ctx) {

    List<Object> boolList = new ArrayList<>();

    for (int i = 0; i < ctx.identifiedEquality().size(); i++) {
      boolList.add(visitIdentifiedEquality(ctx.identifiedEquality(i)));
      ConditionLogicalOperatorSymbol symbol = extractSymbol(ctx, i);
      if (symbol != null) {
        boolList.add(symbol);
      }
    }
    if (boolList.size() == 1) {
      return (ConditionDto) boolList.get(0);
    } else {
      return buildConditionLogicalOperator(boolList);
    }
  }

  private ConditionLogicalOperatorDto buildConditionLogicalOperator(List<Object> boolList) {

    ConditionLogicalOperatorDto currentOperator = new ConditionLogicalOperatorDto();
    ConditionLogicalOperatorSymbol currentSymbol = (ConditionLogicalOperatorSymbol) boolList.get(1);
    currentOperator.setSymbol(currentSymbol);
    currentOperator.setValues(new ArrayList<>());
    currentOperator.getValues().add((ConditionDto) boolList.get(0));
    ConditionLogicalOperatorDto lowestOperator = currentOperator;
    for (int i = 2; i < boolList.size(); i = i + 2) {
      ConditionLogicalOperatorSymbol nextSymbol =
          i + 1 < boolList.size() ? (ConditionLogicalOperatorSymbol) boolList.get(i + 1) : null;
      if (nextSymbol == null || Objects.equals(currentSymbol, nextSymbol)) {
        currentOperator.getValues().add((ConditionDto) boolList.get(i));
        currentSymbol = nextSymbol;
      } else {
        ConditionLogicalOperatorDto nextOperator = new ConditionLogicalOperatorDto();
        nextOperator.setSymbol(nextSymbol);
        nextOperator.setValues(new ArrayList<>());

        if (hasHigherPrecedence(currentSymbol, nextSymbol)) {
          currentOperator.getValues().add((ConditionDto) boolList.get(i));
          nextOperator.getValues().add(currentOperator);
          lowestOperator = nextOperator;
        } else {
          nextOperator.getValues().add((ConditionDto) boolList.get(i));
          currentOperator.getValues().add(nextOperator);
          lowestOperator = currentOperator;
        }

        currentOperator = nextOperator;
        currentSymbol = nextSymbol;
      }
    }
    return lowestOperator;
  }

  private ConditionLogicalOperatorSymbol extractSymbol(AqlParser.IdentifiedExprContext ctx, int i) {
    if (ctx.AND(i) != null) {
      return ConditionLogicalOperatorSymbol.AND;
    } else if (ctx.OR(i) != null) {
      return ConditionLogicalOperatorSymbol.OR;
    } else if (ctx.XOR(i) != null) {
      throw new AqlParseException("XOR not supported");
    }
    return null;
  }

  @Override
  public ConditionDto visitIdentifiedEquality(AqlParser.IdentifiedEqualityContext ctx) {
    ConditionDto conditionDto = null;

    if (ctx.identifiedExpr() != null) {
      conditionDto = visitIdentifiedExpr(ctx.identifiedExpr());
    } else if (ctx.COMPARABLEOPERATOR() != null) {
      ConditionComparisonOperatorSymbol comparisonOperatorSymbol = extractSymbol(ctx);
      ConditionComparisonOperatorDto operatorDto = new ConditionComparisonOperatorDto();
      operatorDto.setSymbol(comparisonOperatorSymbol);
      operatorDto.setStatement(visitIdentifiedPath(ctx.identifiedOperand(0).identifiedPath()));
      operatorDto.setValue(visitOperand(ctx.identifiedOperand(1).operand()));

      conditionDto = operatorDto;
    }
    return conditionDto;
  }

  @Override
  public Value visitOperand(AqlParser.OperandContext ctx) {
    final Value value;

    if (ctx.BOOLEAN() != null) {
      SimpleValue simpleValue = new SimpleValue();
      simpleValue.setValue(Boolean.parseBoolean(ctx.getText()));
      value = simpleValue;
    } else if (ctx.DATE() != null) {
      SimpleValue simpleValue = new SimpleValue();
      String unwrap = StringUtils.unwrap(StringUtils.unwrap(ctx.getText(), "'"), "\"");
      simpleValue.setValue(DateTimeParsers.parseTimeValue(unwrap));
      value = simpleValue;
    } else if (ctx.FLOAT() != null) {
      SimpleValue simpleValue = new SimpleValue();
      simpleValue.setValue(Double.valueOf(ctx.getText()));
      value = simpleValue;
    } else if (ctx.INTEGER() != null) {
      SimpleValue simpleValue = new SimpleValue();
      simpleValue.setValue(Integer.valueOf(ctx.getText()));
      value = simpleValue;
    } else if (ctx.STRING() != null) {
      SimpleValue simpleValue = new SimpleValue();
      String unwrap = StringUtils.unwrap(StringUtils.unwrap(ctx.getText(), "'"), "\"");
      simpleValue.setValue(unwrap);
      value = simpleValue;
    } else if (ctx.PARAMETER() != null) {
      ParameterValue simpleValue = new ParameterValue();
      simpleValue.setName(StringUtils.removeStart(ctx.getText(), "$"));
      simpleValue.setType("?");
      value = simpleValue;
    } else {
      throw new AqlParseException("Can not handle value " + ctx.getText());
    }
    return value;
  }

  @Override
  public List<OrderByExpressionDto> visitOrderBySeq(AqlParser.OrderBySeqContext ctx) {
    List<OrderByExpressionDto> orderByExpressionDtoList = new ArrayList<>();

    orderByExpressionDtoList.add(visitOrderByExpr(ctx.orderByExpr()));
    if (ctx.orderBySeq() != null) {
      orderByExpressionDtoList.addAll(visitOrderBySeq(ctx.orderBySeq()));
    }
    return orderByExpressionDtoList;
  }

  @Override
  public OrderByExpressionDto visitOrderByExpr(AqlParser.OrderByExprContext ctx) {
    OrderByExpressionDto orderByExpressionDto = new OrderByExpressionDto();
    orderByExpressionDto.setStatement(visitIdentifiedPath(ctx.identifiedPath()));
    orderByExpressionDto.setSymbol(extractSymbol(ctx));
    return orderByExpressionDto;
  }

  private OrderByExpressionSymbol extractSymbol(AqlParser.OrderByExprContext ctx) {
    if (ctx.DESC() != null || ctx.DESCENDING() != null) {
      return OrderByExpressionSymbol.DESC;
    } else {
      return OrderByExpressionSymbol.ASC;
    }
  }

  private ConditionComparisonOperatorSymbol extractSymbol(AqlParser.IdentifiedEqualityContext ctx) {
    switch (ctx.COMPARABLEOPERATOR().getText()) {
      case "=":
        return ConditionComparisonOperatorSymbol.EQ;
      case "!=":
        return ConditionComparisonOperatorSymbol.NEQ;
      case ">":
        return ConditionComparisonOperatorSymbol.GT;
      case ">=":
        return ConditionComparisonOperatorSymbol.GT_EQ;
      case "<":
        return ConditionComparisonOperatorSymbol.LT;
      case "<=":
        return ConditionComparisonOperatorSymbol.LT_EQ;
      default:
        throw new AqlParseException("Unknown Token " + ctx.COMPARABLEOPERATOR().getText());
    }
  }

  private boolean hasHigherPrecedence(
      LogicalOperatorSymbol operatorSymbol, LogicalOperatorSymbol nextOperatorSymbol) {
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
      throw new AqlParseException("XOR not supported");
    } else {
      return null;
    }
  }
}
