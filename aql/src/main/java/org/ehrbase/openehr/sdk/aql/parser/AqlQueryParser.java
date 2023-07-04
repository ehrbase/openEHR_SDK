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

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlLexer;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser;
import org.ehrbase.openehr.sdk.util.exception.SDKErrorListener;

public final class AqlQueryParser {

    private AqlQueryParser() {
        // NOOP
    }

    public static AqlQuery parse(String aql) {

        AqlLexer aqlLexer = new AqlLexer(CharStreams.fromString(aql));
        aqlLexer.removeErrorListeners();
        aqlLexer.addErrorListener(SDKErrorListener.INSTANCE);
        CommonTokenStream commonTokenStream = new CommonTokenStream(aqlLexer);
        AqlParser aqlParser = new AqlParser(commonTokenStream);
        aqlParser.removeErrorListeners();
        aqlParser.addErrorListener(SDKErrorListener.INSTANCE);
        AqlQueryVisitor listener = new AqlQueryVisitor();

        AqlQuery aqlQuery;
        try {
            aqlQuery = listener.visitSelectQuery(aqlParser.selectQuery());
        } catch (ParseCancellationException e) {
            throw new AqlParseException(e.getMessage(), e);
        }
        if (!listener.getErrors().isEmpty()) {
            throw new AqlParseException(
                    String.format("Cannot parse aql %s: %s", aql, String.join(",", listener.getErrors())));
        }
        return aqlQuery;
    }
}
