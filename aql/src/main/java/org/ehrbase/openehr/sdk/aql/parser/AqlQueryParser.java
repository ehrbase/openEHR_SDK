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
package org.ehrbase.openehr.sdk.aql.parser;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.dto.path.AndOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPath;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlLexer;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser;
import org.ehrbase.openehr.sdk.util.exception.SDKErrorListener;

public final class AqlQueryParser {

    private AqlQueryParser() {
        // NOOP
    }

    public static AqlQuery parse(String aql) {
        return parseQueryComponent(aql, AqlParser::selectQuery, AqlQueryVisitor::visitSelectQuery);
    }

    private static <T> T parseQueryComponent(String source, BiFunction<AqlParser, AqlQueryVisitor, T> extractor) {

        AqlLexer aqlLexer = new AqlLexer(CharStreams.fromString(source));
        aqlLexer.removeErrorListeners();
        aqlLexer.addErrorListener(SDKErrorListener.INSTANCE);
        CommonTokenStream commonTokenStream = new CommonTokenStream(aqlLexer);
        AqlParser aqlParser = new AqlParser(commonTokenStream);
        aqlParser.removeErrorListeners();
        aqlParser.addErrorListener(SDKErrorListener.INSTANCE);
        AqlQueryVisitor listener = new AqlQueryVisitor();

        T component;
        try {
            component = extractor.apply(aqlParser, listener);
        } catch (ParseCancellationException e) {
            throw new AqlParseException(e.getMessage(), e);
        }
        if (!listener.getErrors().isEmpty()) {
            throw new AqlParseException(
                    String.format("Cannot parse %s: %s", source, String.join(",", listener.getErrors())));
        }
        return component;
    }

    public static <T, U> U parseQueryComponent(
            String source, Function<AqlParser, T> parserFunc, BiFunction<AqlQueryVisitor, T, U> visitorFunc) {
        return parseQueryComponent(source, (p, l) -> visitorFunc.apply(l, parserFunc.apply(p)));
    }

    public static AqlObjectPath parsePath(String aqlPath) {
        return parseQueryComponent(aqlPath, AqlParser::objectPath, AqlQueryVisitor::visitObjectPath);
    }

    public static List<AndOperatorPredicate> parsePredicate(String aqlPathPredicate) {
        return parseQueryComponent(aqlPathPredicate, AqlParser::pathPredicate, AqlQueryVisitor::visitPathPredicate);
    }
}
