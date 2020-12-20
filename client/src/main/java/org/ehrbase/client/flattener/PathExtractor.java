/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.flattener;

import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.webtemplate.parser.FlatPath;

public class PathExtractor {
  private String childPath;
  private String attributeName;
  private String parentPath;
  private String childName;

  public PathExtractor(String path) {
    this.childPath = path;
    invoke();
  }

  public String getChildPath() {
    return childPath;
  }

  public String getAttributeName() {
    return attributeName;
  }

  public String getParentPath() {
    return parentPath;
  }

  public String getChildName() {
    return childName;
  }

  private void invoke() {
    FlatPath flatPath = new FlatPath(childPath);

    while (flatPath.getChild() != null) {
      flatPath = flatPath.getChild();
    }

    parentPath = StringUtils.remove(childPath, flatPath.toString());
    if (StringUtils.isBlank(parentPath)) {
      parentPath = "/";
    }
    childPath =
        StringUtils.remove(
            childPath,
            Optional.ofNullable(flatPath.getAttributeName()).map(s -> "|" + s).orElse(""));
    childName = flatPath.getName();
    attributeName = flatPath.getAttributeName();
  }
}
