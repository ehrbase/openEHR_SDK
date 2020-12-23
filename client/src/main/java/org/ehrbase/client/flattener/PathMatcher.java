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

package org.ehrbase.client.flattener;

import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.FlatPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PathMatcher {

  Logger logger = LoggerFactory.getLogger(getClass());

  String matchesPath(Context<?> context, WebTemplateNode child, Map.Entry<String, ?> e) {
    String aqlPath =
        FlatPath.removeStart(
                new FlatPath(child.getAqlPath()),
                new FlatPath(context.getNodeDeque().peek().getAqlPath()))
            .toString();
    if (StringUtils.startsWith(e.getKey(), aqlPath)) {
      return remove(e, aqlPath, child);
    } else {
      FlatPath childPath = new FlatPath(aqlPath);
      FlatPath pathLast = childPath.getLast();
      FlatPath pathWithoutLastName =
          FlatPath.addEnd(
              FlatPath.removeEnd(childPath, pathLast), new FlatPath(pathLast.format(false)));
      if (StringUtils.startsWith(e.getKey(), pathWithoutLastName.toString())
          && context.getNodeDeque().peek().getChildren().stream()
                  .filter(n -> Objects.equals(n.getNodeId(), child.getNodeId()))
                  .count()
              == 1) {
        logger.warn("name/value not set in dto for {}", child.getAqlPath());
        return remove(e, pathWithoutLastName.toString(), child);
      } else {
        return null;
      }
    }
  }

  private String remove(Map.Entry<String, ?> e, String s, WebTemplateNode child) {
    if (child.getId().equals("ism_transition")) {
      FlatPath flatPath = new FlatPath(StringUtils.removeStart(e.getKey(), s));
      // fix for old dto model
      if (StringUtils.isBlank(flatPath.getName())) {
        return null;
      }
    }
    return StringUtils.removeStart(e.getKey(), s);
  }
}
