/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.webtemplate.path.flat;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.ehrbase.util.exception.SdkException;

public class FlatPathParser {

    private FlatPathParser(){}

   public static FlatPathDto parse(String path){
       try {
           FlatLexer aqlLexer = new FlatLexer(CharStreams.fromString(path));
         //  aqlLexer.addErrorListener(new AqlErrorListener());
           CommonTokenStream commonTokenStream = new CommonTokenStream(aqlLexer);
           FlatParser aqlParser = new FlatParser(commonTokenStream);
        //   aqlParser.addErrorListener(new AqlErrorListener());
           FlatPathVisitor listener = new FlatPathVisitor();
           return listener.visitPath(aqlParser.path());
       } catch (RuntimeException e) {
           throw new SdkException(e.getMessage());
       }
   }
}
