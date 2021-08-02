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

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.util.exception.SDKErrorListener;

public class AqlToDtoParser {

  public AqlDto parse(String aql) {
    try {
      AqlLexer aqlLexer = new AqlLexer(CharStreams.fromString(aql));
      aqlLexer.addErrorListener(new SDKErrorListener());
      CommonTokenStream commonTokenStream = new CommonTokenStream(aqlLexer);
      AqlParser aqlParser = new AqlParser(commonTokenStream);
      aqlParser.addErrorListener(new SDKErrorListener());
      AqlToDtoVisitor listener = new AqlToDtoVisitor();
      return listener.visitQuery(aqlParser.query());
    } catch (RuntimeException e) {
      throw new AqlParseException(e.getMessage());
    }
  }

}
