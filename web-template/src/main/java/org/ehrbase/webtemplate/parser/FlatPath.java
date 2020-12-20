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

package org.ehrbase.webtemplate.parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class FlatPath {
  private final String name;
  private final String atCode;
  private FlatPath child;
  private final Map<String, String> otherPredicates;
  private final String attributeName;
  private final boolean startWithSlash;

  public FlatPath(String path) {
    if (StringUtils.isBlank(path)) {
      name = "";
      atCode = null;
      child = null;
      otherPredicates = new HashMap<>();
      attributeName = null;
      startWithSlash = false;
      return;
    }

    final String[] strings;
    startWithSlash = StringUtils.startsWith(path, "/");
    int splitPos = StringUtils.indexOf(StringUtils.stripStart(path, "/"), "/", path.indexOf(']'));
    if (splitPos > -1) {
      strings = new String[2];
      strings[0] = path.substring(0, splitPos + 1);
      strings[1] = path.substring(splitPos + 1);
    } else {
      strings = new String[1];
      strings[0] = path;
    }

    String[] split = StringUtils.split(strings[0], "[", 2);

    if (split.length == 2) {
      String node = StringUtils.substringBefore(split[1], "]");

      String regex;
      if (node.matches(".*\\sand\\s.*")) {
        regex = "\\sand\\s";
        String[] ands = node.split(regex);
        atCode = ands[0].trim();
        otherPredicates =
            Arrays.stream(ands)
                .skip(1)
                .map(s -> s.split("="))
                .collect(Collectors.toMap(s -> s[0].trim(), s -> s[1].replace("'", "").trim()));
      } else {
        regex = ",";
        String[] ands = node.split(regex);
        atCode = ands[0].trim();
        otherPredicates = new HashMap<>();
        if (ands.length > 1) {
          otherPredicates.put("name/value", ands[1].replace("'", "").trim());
        }
      }

    } else {
      atCode = null;
      otherPredicates = Collections.emptyMap();
    }

    if (strings.length == 2) {
      name = StringUtils.stripStart(split[0], "/");
      child = new FlatPath(strings[1]);
      attributeName = null;
    } else {
      child = null;
      String[] atributteSplit = StringUtils.stripStart(strings[0], "/").split("[|\\[]");
      name = atributteSplit[0];
      if (atributteSplit.length > 1 && !atributteSplit[atributteSplit.length - 1].contains("]")) {
        attributeName = atributteSplit[atributteSplit.length - 1];
      } else {
        attributeName = null;
      }
    }
  }

  public String getName() {
    return name;
  }

  public String getAtCode() {
    return atCode;
  }

  public FlatPath getChild() {
    return child;
  }

  public String getPath() {
    return StringUtils.removeEnd(toString(), "|" + getLast().getAttributeName());
  }

  public String getAttributeName() {
    return attributeName;
  }

  public String findOtherPredicate(String name) {
    return otherPredicates.get(name);
  }

  public void addOtherPredicate(String name, String value) {
    otherPredicates.put(name, value);
  }

  @Override
  public String toString() {
    return format(true);
  }

  public String format(boolean withOtherPredicates) {
    StringBuilder sb = new StringBuilder();
    if (startWithSlash) {
      sb.append("/");
    }
    sb.append(name);
    if (StringUtils.isNotBlank(atCode)) {
      sb.append("[").append(atCode);
      if (withOtherPredicates) {
        otherPredicates.forEach(
            (key, value) ->
                sb.append(" and ").append(key).append("=").append("'").append(value).append("'"));
      }
      sb.append("]");
    }
    if (child != null) {
      sb.append(child.format(withOtherPredicates));
    }
    if (attributeName != null) {
      sb.append("|").append(attributeName);
    }
    return sb.toString();
  }

  public FlatPath getLast() {
    FlatPath path = this;
    while (path.getChild() != null) {
      path = path.getChild();
    }
    return path;
  }

  public static FlatPath removeEnd(FlatPath path, FlatPath remove) {
    return new FlatPath(StringUtils.removeEnd(path.toString(), remove.toString()));
  }

  public static FlatPath removeStart(FlatPath path, FlatPath remove) {
    return new FlatPath(StringUtils.removeStart(path.toString(), remove.toString()));
  }

  public static FlatPath addEnd(FlatPath path, FlatPath add) {
    return new FlatPath(path.toString() + "/" + StringUtils.removeStart(add.toString(), "/"));
  }
}
