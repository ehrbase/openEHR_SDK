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
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.util.exception.SDKErrorListener;
import org.ehrbase.util.exception.SdkException;

public class FlatPathParser {

    private FlatPathParser(){}

   public static FlatPathDto parse(String path){
      FlatPathDto dto = new FlatPathDto();

      if (StringUtils.isNotBlank(path) && !"/".equals(path)){
          String[] tempSplit;
          String tempSubPath;

          //extract Children
          tempSplit = StringUtils.split( StringUtils.removeStart(path,"/"), "/", 2);
          tempSubPath = tempSplit[0];

          if(tempSplit.length >1){
              dto.setChild(parse(tempSplit[1]));
          }

          //extract AttributeName
          tempSplit = StringUtils.split(tempSubPath,"|",2);
          tempSubPath = tempSplit[0];

          if(tempSplit.length >1){
              dto.setAttributeName(tempSplit[1]);
          }

          //extract Count
          tempSplit = StringUtils.split(tempSubPath,":",2);
          tempSubPath = tempSplit[0];

          if(tempSplit.length >1){
              dto.setCount(Integer.valueOf(tempSplit[1]));
          }

          //Rest is the name
          dto.setName(tempSubPath);
      }

      return dto;
   }
}
