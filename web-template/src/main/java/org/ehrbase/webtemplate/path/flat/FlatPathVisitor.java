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

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.util.exception.SdkException;

import java.util.Optional;

public class FlatPathVisitor extends FlatBaseVisitor<Object> {

  @Override
  public FlatPathDto visitPath(FlatParser.PathContext ctx) {
    FlatPathDto first = null;
    FlatPathDto pathDto = null;

    for (ParseTree tree : ctx.children) {
      FlatPathDto next = null;
      if (tree instanceof FlatParser.PathSegmentContext) {
        next = visitPathSegment((FlatParser.PathSegmentContext) tree);
      } else if (tree instanceof FlatParser.PathEndSegmentContext) {
        next = visitPathEndSegment((FlatParser.PathEndSegmentContext) tree);
      }

      if (next != null) {
        if (pathDto != null) {
          pathDto.setChild(next);
        }
        pathDto = next;
        if (first == null) {
          first = pathDto;
        }
      }
    }

    return first;
  }

  @Override
  public FlatPathDto visitPathSegment(FlatParser.PathSegmentContext ctx) {
    FlatPathDto pathDto = new FlatPathDto();
    pathDto.setName(ctx.TEXT().getText());
      String digit = Optional.ofNullable( ctx.DIGITS()).map(ParseTree::getText).orElse(null);
      if (StringUtils.isNotBlank(digit)){
          try{
              pathDto.setCount(Integer.parseInt(digit));
          }catch (RuntimeException e){
              throw new SdkException(e.getMessage());
          }
      }
      return pathDto;
  }

  @Override
  public FlatPathDto visitPathEndSegment(FlatParser.PathEndSegmentContext ctx) {
    FlatPathDto pathDto = visitPathSegment(ctx.pathSegment());
    pathDto.setAttributeName(visitAttribute(ctx.attribute()));
    return pathDto;
  }

  @Override
  public String visitAttribute(FlatParser.AttributeContext ctx) {
    return ctx.getText();
  }
}
