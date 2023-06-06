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
package org.ehrbase.aql.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.ehrbase.aql.dto.LogicalOperator;

public final class AqlQueryParserHelper {

    private AqlQueryParserHelper() {}

    public static <S, T> LogicalOperator<S, T> buildLogicalOperator(
            List<Object> boolList, Function<S, LogicalOperator<S, T>> creator, ToIntFunction<S> precedenceFunction) {
        OperatorStructure<S> structure = buildLogicalOperatorStructure(boolList, precedenceFunction);
        return buildLogicalOperator(structure, creator);
    }

    private static <S, T> LogicalOperator<S, T> buildLogicalOperator(
            OperatorStructure<S> structure, Function<S, LogicalOperator<S, T>> creator) {

        LogicalOperator<S, T> operator = creator.apply(structure.getSymbol());

        Stream<T> stream = structure.getChildren().stream().map(v -> {
            if (v instanceof OperatorStructure) {
                return (T) buildLogicalOperator((OperatorStructure<S>) v, creator);
            } else {
                return (T) v;
            }
        });
        return operator.addValues(stream);
    }

    private static <S> OperatorStructure<S> buildLogicalOperatorStructure(
            List<Object> boolList, ToIntFunction<S> precedenceFunction) {

        S currentSymbol = (S) boolList.get(1);
        OperatorStructure<S> currentOperator = new OperatorStructure(currentSymbol, boolList.get(0));

        OperatorStructure<S> lowestOperator = currentOperator;
        for (int i = 2, l = boolList.size(); i < l; i += 2) {
            S nextSymbol = i + 1 < l ? (S) boolList.get(i + 1) : null;
            Object currentOpValue = boolList.get(i);
            if (nextSymbol == null || Objects.equals(currentSymbol, nextSymbol)) {
                currentOperator.addChild(currentOpValue);

            } else {
                OperatorStructure<S> nextOperator = new OperatorStructure<>(nextSymbol);

                if (hasHigherPrecedence(currentSymbol, nextSymbol, precedenceFunction)) {
                    currentOperator.addChild(currentOpValue);
                    nextOperator.addChild(currentOperator);
                    lowestOperator = nextOperator;
                } else {
                    nextOperator.addChild(currentOpValue);
                    currentOperator.addChild(nextOperator);
                    lowestOperator = currentOperator;
                }

                currentOperator = nextOperator;
            }
            currentSymbol = nextSymbol;
        }
        return lowestOperator;
    }

    private static <S> boolean hasHigherPrecedence(
            S operatorSymbol, S nextOperatorSymbol, ToIntFunction<S> precedenceFunction) {
        if (nextOperatorSymbol == null) {
            return true;
        } else {
            return precedenceFunction.applyAsInt(operatorSymbol) <= precedenceFunction.applyAsInt(nextOperatorSymbol);
        }
    }

    private static final class OperatorStructure<S> {
        private final S symbol;
        private final List<Object> children;

        private OperatorStructure(S symbol, Object... children) {
            this.symbol = symbol;
            this.children = Arrays.stream(children).collect(Collectors.toList());
        }

        public S getSymbol() {
            return symbol;
        }

        public List<Object> getChildren() {
            return children;
        }

        public void addChild(Object child) {
            children.add(child);
        }
    }
}
