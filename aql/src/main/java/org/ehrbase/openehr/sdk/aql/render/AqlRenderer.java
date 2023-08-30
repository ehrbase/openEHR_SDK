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
package org.ehrbase.openehr.sdk.aql.render;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.dto.condition.ComparisonOperatorCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.ExistsCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.LikeCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.LogicalOperatorCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.MatchesCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.NotCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.WhereCondition;
import org.ehrbase.openehr.sdk.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.Containment;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentClassExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentNotOperator;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentSetOperator;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentVersionExpression;
import org.ehrbase.openehr.sdk.aql.dto.operand.AggregateFunction;
import org.ehrbase.openehr.sdk.aql.dto.operand.AggregateFunction.AggregateFunctionName;
import org.ehrbase.openehr.sdk.aql.dto.operand.ColumnExpression;
import org.ehrbase.openehr.sdk.aql.dto.operand.ComparisonLeftOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.CountDistinctAggregateFunction;
import org.ehrbase.openehr.sdk.aql.dto.operand.DoublePrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.IdentifiedPath;
import org.ehrbase.openehr.sdk.aql.dto.operand.LikeOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.MatchesOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.Operand;
import org.ehrbase.openehr.sdk.aql.dto.operand.PathPredicateOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.Primitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.QueryParameter;
import org.ehrbase.openehr.sdk.aql.dto.operand.SingleRowFunction;
import org.ehrbase.openehr.sdk.aql.dto.operand.StringPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.TerminologyFunction;
import org.ehrbase.openehr.sdk.aql.dto.orderby.OrderByExpression;
import org.ehrbase.openehr.sdk.aql.dto.path.AndOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPath;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPath.PathNode;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPathUtil;
import org.ehrbase.openehr.sdk.aql.dto.path.ComparisonOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.dto.path.ComparisonOperatorPredicate.PredicateComparisonOperator;
import org.ehrbase.openehr.sdk.aql.dto.select.SelectClause;
import org.ehrbase.openehr.sdk.aql.dto.select.SelectExpression;
import org.ehrbase.openehr.sdk.util.exception.SdkException;

/**
 * @author Stefan Spiska
 */
public final class AqlRenderer {

    private AqlRenderer() {}

    public static String render(AqlQuery dto) {
        StringBuilder sb = new StringBuilder();

        renderSelect(sb, dto.getSelect());
        renderFromClause(sb, dto.getFrom());

        if (dto.getWhere() != null) {
            sb.append(" WHERE ");
            renderWhere(sb, dto.getWhere());
        }

        if (CollectionUtils.isNotEmpty(dto.getOrderBy())) {

            renderOrderByClause(sb, dto.getOrderBy());
        }

        if (dto.getLimit() != null) {
            sb.append(" LIMIT ").append(dto.getLimit());
            if (dto.getOffset() != null) {
                sb.append(" OFFSET ").append(dto.getOffset());
            }
        }

        return sb.toString();
    }

    private static void renderOrderByClause(StringBuilder sb, List<OrderByExpression> orderBy) {
        sb.append(" ORDER BY ");

        var it = orderBy.iterator();
        while (it.hasNext()) {
            OrderByExpression next = it.next();
            renderIdentifiedPath(sb, next.getStatement());
            sb.append(" ").append(next.getSymbol());

            if (it.hasNext()) {
                sb.append(", ");
            }
        }
    }

    private static void renderWhere(StringBuilder sb, WhereCondition where) {
        if (where instanceof ComparisonOperatorCondition comparisonOperatorCondition) {
            renderConditionComparisonOperatorDto(sb, comparisonOperatorCondition);
        } else if (where instanceof NotCondition notCondition) {
            renderNotConditionOperatorDto(sb, notCondition);
        } else if (where instanceof ExistsCondition existsCondition) {
            renderExistsCondition(sb, existsCondition);
        } else if (where instanceof LogicalOperatorCondition logicalOperatorCondition) {
            renderConditionLogical(sb, logicalOperatorCondition);
        } else if (where instanceof LikeCondition likeCondition) {
            renderLike(sb, likeCondition);
        } else if (where instanceof MatchesCondition matchesCondition) {
            renderMatches(sb, matchesCondition);
        } else {
            throw new SdkException("Cannot handle %s".formatted(where.getClass().getName()));
        }
    }

    private static void renderMatches(StringBuilder sb, MatchesCondition matchesCondition) {

        renderIdentifiedPath(sb, matchesCondition.getStatement());
        sb.append(" ").append("MATCHES").append(" ");
        sb.append("{");
        Iterator<MatchesOperand> iterator = matchesCondition.getValues().iterator();
        while (iterator.hasNext()) {
            MatchesOperand next = iterator.next();
            renderMatchesOperand(sb, next);
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("}");
    }

    private static void renderMatchesOperand(StringBuilder sb, MatchesOperand next) {

        if (next instanceof QueryParameter queryParameter) {
            renderParameterDto(sb, queryParameter);
        } else if (next instanceof Primitive primitive) {
            sb.append(renderPrimitive(primitive));
        } else if (next instanceof TerminologyFunction terminologyFunction) {
            renderTerminologyFunction(sb, terminologyFunction);
        } else {
            throw new SdkException("Cannot handle %s".formatted(next.getClass().getName()));
        }
    }

    private static void renderTerminologyFunction(StringBuilder sb, TerminologyFunction terminologyFunction) {

        sb.append("TERMINOLOGY")
                .append("(")
                .append(encodeString(terminologyFunction.getOperation()))
                .append(",")
                .append(encodeString(terminologyFunction.getServiceApi()))
                .append(",")
                .append(encodeString(terminologyFunction.getUriParameters()))
                .append(")");
    }

    private static void renderLike(StringBuilder sb, LikeCondition likeCondition) {
        renderIdentifiedPath(sb, likeCondition.getStatement());
        sb.append(" LIKE ");
        renderLikeOperand(sb, likeCondition.getValue());
    }

    private static void renderLikeOperand(StringBuilder sb, LikeOperand value) {

        if (value instanceof QueryParameter queryParameter) {
            renderParameterDto(sb, queryParameter);
        } else {
            sb.append(renderPrimitive((StringPrimitive) value));
        }
    }

    private static void renderConditionLogical(StringBuilder sb, LogicalOperatorCondition logicalOperatorCondition) {

        Iterator<WhereCondition> iterator = logicalOperatorCondition.getValues().iterator();
        sb.append("(");
        while (iterator.hasNext()) {

            renderWhere(sb, iterator.next());
            if (iterator.hasNext()) {
                sb.append(" ").append(logicalOperatorCondition.getSymbol()).append(" ");
            }
        }
        sb.append(")");
    }

    private static void renderExistsCondition(StringBuilder sb, ExistsCondition existsCondition) {
        sb.append("EXISTS ");
        renderIdentifiedPath(sb, existsCondition.getValue());
    }

    private static void renderNotConditionOperatorDto(StringBuilder sb, NotCondition notCondition) {
        sb.append("NOT ");
        renderWhere(sb, notCondition.getConditionDto());
    }

    private static void renderConditionComparisonOperatorDto(
            StringBuilder sb, ComparisonOperatorCondition comparisonOperatorCondition) {
        ComparisonLeftOperand statement = comparisonOperatorCondition.getStatement();

        renderComparisonLeftOperator(sb, statement);
        sb.append(" ")
                .append(comparisonOperatorCondition.getSymbol().getSymbol())
                .append(" ");
        sb.append(renderTerminal(comparisonOperatorCondition.getValue()));
    }

    private static void renderComparisonLeftOperator(StringBuilder sb, ComparisonLeftOperand statement) {
        if (statement instanceof IdentifiedPath identifiedPath) {
            renderIdentifiedPath(sb, identifiedPath);
        } else if (statement instanceof SingleRowFunction singleRowFunktion) {
            renderSingleRowFunctionDto(sb, singleRowFunktion);
        } else if (statement instanceof TerminologyFunction terminologyFunction) {
            renderTerminologyFunction(sb, terminologyFunction);
        } else {
            throw new SdkException(
                    "Cannot handle %s".formatted(statement.getClass().getName()));
        }
    }

    private static void renderSelect(StringBuilder sb, SelectClause dto) {
        sb.append("SELECT ");

        if (dto.isDistinct()) {
            sb.append("DISTINCT ");
        }

        var it = dto.getStatement().stream().iterator();
        while (it.hasNext()) {
            renderSelectStatementDto(sb, it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append(" ");
    }

    private static void renderSelectStatementDto(StringBuilder sb, SelectExpression dto) {
        ColumnExpression columnExpression = dto.getColumnExpression();

        if (columnExpression instanceof IdentifiedPath) {
            renderIdentifiedPath(sb, (IdentifiedPath) columnExpression);
        } else if (columnExpression instanceof Primitive) {
            renderSelectPrimitiveDto(sb, (Primitive) columnExpression);
        } else if (columnExpression instanceof AggregateFunction) {
            renderAggregateFunctionDto(sb, (AggregateFunction) columnExpression);
        } else if (columnExpression instanceof SingleRowFunction singleRowFunktion) {
            renderSingleRowFunctionDto(sb, singleRowFunktion);
        } else {
            throw new UnsupportedOperationException(
                    ("Cannot handle %s").formatted(dto.getClass().getName()));
        }

        if (dto.getAlias() != null) {
            sb.append(" AS ").append(dto.getAlias());
        }
    }

    private static void renderAggregateFunctionDto(StringBuilder sb, AggregateFunction aggregateFunction) {

        sb.append(aggregateFunction.getFunctionName().name()).append("(");

        if (aggregateFunction instanceof CountDistinctAggregateFunction) {
            if (aggregateFunction.getIdentifiedPath() == null) {
                throw new IllegalStateException("COUNT(DISTINCT *) is not allowed");
            }
            sb.append("DISTINCT ");
        }

        if (AggregateFunctionName.COUNT.equals(aggregateFunction.getFunctionName())
                && aggregateFunction.getIdentifiedPath() == null) {
            sb.append('*');
        } else {
            renderIdentifiedPath(sb, aggregateFunction.getIdentifiedPath());
        }
        sb.append(")");
    }

    private static void renderSingleRowFunctionDto(StringBuilder sb, SingleRowFunction singleRowFunktion) {

        sb.append(singleRowFunktion.getFunctionName().getName()).append("(");
        sb.append(singleRowFunktion.getOperandList().stream()
                .map(AqlRenderer::renderTerminal)
                .collect(Collectors.joining(", ")));
        sb.append(")");
    }

    private static String renderTerminal(Operand operand) {

        StringBuilder sb = new StringBuilder();
        if (operand instanceof SingleRowFunction singleRowFunktion) {
            renderSingleRowFunctionDto(sb, singleRowFunktion);
        } else if (operand instanceof IdentifiedPath identifiedPath) {
            renderIdentifiedPath(sb, identifiedPath);
        } else if (operand instanceof Primitive primitive) {
            sb.append(renderPrimitive(primitive));
        } else if (operand instanceof QueryParameter queryParameter) {
            renderParameterDto(sb, queryParameter);
        } else {
            throw new UnsupportedOperationException(
                    "Cannot handle %s".formatted(operand.getClass().getName()));
        }
        return sb.toString();
    }

    private static void renderParameterDto(StringBuilder sb, QueryParameter queryParameter) {

        sb.append("$").append(queryParameter.getName());
    }

    private static void renderIdentifiedPath(StringBuilder sb, IdentifiedPath dto) {

        AbstractContainmentExpression containmentDto = dto.getRoot();

        if (containmentDto == null) {
            throw new SdkException("SelectClause without corresponding contains");
        }

        sb.append(containmentDto.getIdentifier());
        Optional.of(dto).map(IdentifiedPath::getRootPredicate).ifPresent(p -> renderPredicate(sb, p));

        Optional.of(dto).map(IdentifiedPath::getPath).ifPresent(p -> {
            sb.append('/');
            renderPath(sb, p);
        });
    }

    public static String renderPredicate(List<AndOperatorPredicate> or) {
        StringBuilder sb = new StringBuilder();
        renderPredicate(sb, or);

        return sb.toString();
    }

    private static void renderPredicate(StringBuilder sb, List<AndOperatorPredicate> or) {
        if (or.isEmpty() || or.size() == 1 && or.get(0).isEmpty()) {
            return;
        }

        join(sb, " OR ", "[", "]", or.stream().map(a -> (s -> renderPredicateAnd(s, a))));
    }

    private static void renderPredicateAnd(StringBuilder sb, AndOperatorPredicate and) {
        if (and.isEmpty()) {
            throw new UnsupportedOperationException("Found empty AndOperatorPredicate");
        }
        List<ComparisonOperatorPredicate> operands = and.getOperands();

        ComparisonOperatorPredicate archetypeNodeId =
                getSpecialPredicate(operands, AqlObjectPathUtil.ARCHETYPE_NODE_ID, true);

        if (archetypeNodeId != null) {
            if (operands.size() == 2) {
                ComparisonOperatorPredicate nameValue =
                        getSpecialPredicate(operands, AqlObjectPathUtil.NAME_VALUE, true);
                if (nameValue != null) {
                    appendPlainValue(sb, archetypeNodeId);
                    sb.append(", ");
                    renderPathPredicateOperand(sb, nameValue.getValue());
                    return;
                }

            } else if (operands.size() == 3) {
                ComparisonOperatorPredicate nameTerminologyId =
                        getSpecialPredicate(operands, AqlObjectPathUtil.NAME_TERMINOLOGY, false);
                ComparisonOperatorPredicate nameCodeString =
                        getSpecialPredicate(operands, AqlObjectPathUtil.NAME_CODE_STRING, false);
                if (ObjectUtils.allNotNull(nameTerminologyId, nameCodeString)) {
                    appendPlainValue(sb, archetypeNodeId);
                    sb.append(", ");
                    appendPlainValue(sb, nameTerminologyId);
                    sb.append("::");
                    appendPlainValue(sb, nameCodeString);
                    return;
                }
            }
        }

        String prefix;
        if (archetypeNodeId != null) {
            appendPlainValue(sb, archetypeNodeId);
            prefix = " AND ";
        } else {
            prefix = "";
        }

        // move archetypeNodeId to front, if present
        Stream<ComparisonOperatorPredicate> stream = and.getOperands().stream().filter(a -> a != archetypeNodeId);

        join(sb, " AND ", prefix, "", stream.map(a -> (s -> renderComparisonPredicate(s, a))));
    }

    private static void appendPlainValue(StringBuilder sb, ComparisonOperatorPredicate predicate) {
        PathPredicateOperand value = predicate.getValue();
        if (value instanceof StringPrimitive sp) {
            sb.append(sp.getValue());
        } else if (value instanceof QueryParameter qp) {
            sb.append("$").append(qp.getName());
        }
    }

    private static ComparisonOperatorPredicate getSpecialPredicate(
            List<ComparisonOperatorPredicate> operands, AqlObjectPath path, boolean parameterAllowed) {
        return operands.stream()
                .filter(op -> isSpecialPredicate(op, path, parameterAllowed))
                .findFirst()
                .orElse(null);
    }

    private static boolean isSpecialPredicate(
            ComparisonOperatorPredicate operand, AqlObjectPath path, boolean parameterAllowed) {
        return Optional.of(operand)
                .filter(op -> op.getOperator() == ComparisonOperatorPredicate.PredicateComparisonOperator.EQ)
                .filter(op -> path.equals(op.getPath()))
                .filter(op -> op.getValue() instanceof StringPrimitive || (parameterAllowed && isParameter(op)))
                .isPresent();
    }

    private static boolean isParameter(ComparisonOperatorPredicate op) {
        return op.getValue() instanceof QueryParameter;
    }

    private static void join(
            StringBuilder sb, String delimiter, String prefix, String suffix, Stream<Consumer<StringBuilder>> stream) {
        Iterator<Consumer<StringBuilder>> it = stream.iterator();
        if (!it.hasNext()) {
            return;
        }
        sb.append(prefix);
        it.next().accept(sb);

        while (it.hasNext()) {
            sb.append(delimiter);
            it.next().accept(sb);
        }
        sb.append(suffix);
    }

    private static void renderComparisonPredicate(StringBuilder sb, ComparisonOperatorPredicate predicate) {
        renderPath(sb, predicate.getPath());

        boolean comparingPaths = predicate.getValue() instanceof AqlObjectPath;
        if (comparingPaths) {
            sb.append(' ');
        }
        sb.append(predicate.getOperator().getSymbol());
        if (comparingPaths) {
            sb.append(' ');
        }
        if (predicate.getOperator() == PredicateComparisonOperator.MATCHES) {
            sb.append(predicate.getMatchesOperand().getEscapedRegex());
        } else {
            renderPathPredicateOperand(sb, predicate.getValue());
        }
    }

    private static void renderPathPredicateOperand(StringBuilder sb, PathPredicateOperand operand) {

        if (operand instanceof QueryParameter o) {
            renderParameterDto(sb, o);
        } else if (operand instanceof Primitive o) {
            sb.append(renderPrimitive(o));
        } else if (operand instanceof AqlObjectPath o) {
            renderPath(sb, o);
        } else {
            throw new UnsupportedOperationException("Unsupported operand type %s".formatted(operand.getClass()));
        }
    }

    public static String renderPath(AqlObjectPath p) {
        StringBuilder sb = new StringBuilder();
        renderPath(sb, p);
        return sb.toString();
    }

    private static void renderPath(StringBuilder sb, AqlObjectPath p) {
        if (p.getPathNodes().isEmpty()) {
            throw new UnsupportedOperationException("Found empty AqlObjectPath");
        }
        join(sb, "/", "", "", p.getPathNodes().stream().map(a -> (s -> renderPathNode(s, a))));
    }

    private static void renderPathNode(StringBuilder sb, PathNode n) {
        sb.append(n.getAttribute());
        renderPredicate(sb, n.getPredicateOrOperands());
    }

    private static void renderSelectPrimitiveDto(StringBuilder sb, Primitive dto) {
        sb.append(renderPrimitive(dto));
    }

    private static String renderPrimitive(Primitive primitive) {

        if (primitive.getValue() == null) {
            return "NULL";
        } else if (primitive instanceof DoublePrimitive d) {
            return d.getStringRepresentation();
        } else if (primitive instanceof StringPrimitive s) {
            return encodeString(s.getValue());
        } else {
            return primitive.getValue().toString();
        }
    }

    /**
     * @see org.ehrbase.openehr.sdk.aql.parser.AqlQueryVisitor::unwrapText
     *
     * @param value
     * @return
     */
    static String encodeString(String value) {
        if (value == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder(value.length() + 5);
        sb.append("'");

        // ' \ abfnrtv

        for (int i = 0, l = value.length(); i < l; i++) {
            char ch = value.charAt(i);
            switch (ch) {
                case '\'':
                case '\\':
                    sb.append('\\').append(ch);
                    break;

                case 0x00007:
                    sb.append('\\').append('a');
                    break;
                case 0x00008:
                    sb.append('\\').append('b');
                    break;
                case 0x0000c:
                    sb.append('\\').append('f');
                    break;
                case 0x0000a:
                    sb.append('\\').append('n');
                    break;
                case 0x0000d:
                    sb.append('\\').append('r');
                    break;
                case 0x00009:
                    sb.append('\\').append('t');
                    break;
                case 0x0000b:
                    sb.append('\\').append('v');
                    break;

                default:
                    sb.append(ch);
            }
        }

        sb.append("'");

        return sb.toString();
    }

    private static void renderFromClause(StringBuilder sb, Containment from) {
        sb.append("FROM ");
        renderContainmentExpresionDto(sb, from);
    }

    private static void renderContainmentExpresionDto(StringBuilder sb, Containment dto) {

        if (dto instanceof AbstractContainmentExpression classExpressionDto) {
            renderContainmentDto(sb, classExpressionDto);
        } else if (dto instanceof ContainmentSetOperator containmentSetOperator) {
            renderContainmentLogicalOperator(sb, containmentSetOperator);
        } else {
            throw new UnsupportedOperationException(
                    "Cannot handle %s".formatted(dto.getClass().getName()));
        }
    }

    private static void renderContainmentLogicalOperator(
            StringBuilder sb, ContainmentSetOperator containmentSetOperator) {

        Iterator<Containment> iterator = containmentSetOperator.getValues().iterator();
        sb.append("(");
        while (iterator.hasNext()) {
            Containment next = iterator.next();
            boolean requiresParenthesis = requiresParenthesis(next);
            if (requiresParenthesis) {
                sb.append('(');
            }
            renderContainmentExpresionDto(sb, next);
            if (requiresParenthesis) {
                sb.append(')');
            }
            if (iterator.hasNext()) {
                sb.append(" ").append(containmentSetOperator.getSymbol()).append(" ");
            }
        }
        sb.append(")");
    }

    private static boolean requiresParenthesis(Containment c) {
        return c instanceof AbstractContainmentExpression e && e.getContains() != null;
    }

    private static void renderContainmentDto(StringBuilder sb, AbstractContainmentExpression dto) {

        if (dto instanceof ContainmentClassExpression classExpression) {
            sb.append(classExpression.getType());
        } else {
            sb.append("VERSION");
        }

        if (dto.getIdentifier() != null) {
            sb.append(" ").append(dto.getIdentifier());
        }

        if (dto instanceof ContainmentVersionExpression classExpression
                && !classExpression
                        .getVersionPredicateType()
                        .equals(ContainmentVersionExpression.VersionPredicateType.STANDARD_PREDICATE)) {
            if (classExpression
                    .getVersionPredicateType()
                    .equals(ContainmentVersionExpression.VersionPredicateType.ALL_VERSIONS)) {
                sb.append("[ALL_VERSIONS]");
            } else {
                sb.append("[LATEST_VERSION]");
            }
        } else {
            Optional.of(dto).map(AbstractContainmentExpression::getPredicates).ifPresent(p -> renderPredicate(sb, p));
        }

        if (dto.getContains() != null) {

            if (dto.getContains() instanceof ContainmentNotOperator containmentNotOperator) {
                sb.append(" NOT CONTAINS ");
                renderContainmentExpresionDto(sb, containmentNotOperator.getContainmentExpression());
            } else {
                sb.append(" CONTAINS ");
                renderContainmentExpresionDto(sb, dto.getContains());
            }
        }
    }
}
