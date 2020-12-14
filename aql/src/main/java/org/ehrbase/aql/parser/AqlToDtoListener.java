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

import java.util.HashMap;
import java.util.Map;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.EhrDto;

public class AqlToDtoListener extends AqlBaseListener {

  private AqlDto aqlDto;
  private int containmentId = 0;
  private final Map<String, Integer> identifierMap = new HashMap<>();

  public AqlDto getAqlDto() {
    return aqlDto;
  }

  @Override
  public void enterQuery(AqlParser.QueryContext ctx) {
    aqlDto = new AqlDto();
  }

  @Override
  public void enterFromEHR(AqlParser.FromEHRContext ctx) {
    aqlDto.setEhr(new EhrDto());
    aqlDto.getEhr().setContainmentId(buildContainmentId());
    if (ctx.IDENTIFIER() != null) {
      identifierMap.put(ctx.IDENTIFIER().getText(), aqlDto.getEhr().getContainmentId());
    }
  }

  private int buildContainmentId() {
    return containmentId++;
  }
}
