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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    strings = split(StringUtils.stripStart(path, "/"), 2, "/");

    String[] split;

    if (strings.length == 2) {
      if (StringUtils.endsWith(strings[0], "]")) {
        int fist = strings[0].indexOf("[");
        split = new String[2];
        split[0] = strings[0].substring(0, fist);
        split[1] = strings[0].substring(fist);
      } else {
        split = new String[1];
        split[0] = strings[0];
      }
      name = StringUtils.stripStart(split[0], "/");
      child = new FlatPath("/" + strings[1]);
      attributeName = null;
    } else {
      child = null;
      String[] attributeSplit = split(strings[0], null, "|");

      if (attributeSplit.length == 2) {
        attributeName = attributeSplit[1];
      } else {
        attributeName = null;
      }

      if (StringUtils.endsWith(attributeSplit[0], "]")) {
        int fist = attributeSplit[0].indexOf("[");
        split = new String[2];
        split[0] = attributeSplit[0].substring(0, fist);
        split[1] = attributeSplit[0].substring(fist);
      } else {
        split = new String[1];
        split[0] = attributeSplit[0];
      }
      name = StringUtils.stripStart(split[0], "/");
    }
    if (split.length == 2) {
      String node = StringUtils.removeEnd(StringUtils.removeStart(split[1], "["), "]");

      String[] predicates = split(node, null, "and", ",");
      atCode = predicates[0].trim();
      otherPredicates = new HashMap<>();
      for (int i = 1; i < predicates.length; i++) {
        String[] pair = split(predicates[i], null, "=");
        if (i == 1 && pair.length == 1) {
          otherPredicates.put("name/value", StringUtils.unwrap(pair[0], "'"));
        } else if (pair.length == 2) {
          otherPredicates.put(pair[0].trim(), StringUtils.unwrap(pair[1], "'"));
        }
      }

      /*
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
      */
    } else {
      atCode = null;
      otherPredicates = Collections.emptyMap();
    }
  }

  private String[] split(String path, Integer max, String... search) {
    List<String> strings = new ArrayList<>();
    List<String> searchList = Arrays.asList(search);

    boolean inBrackets = false;
    boolean inQuotes = false;
    boolean escape = false;

    int last = 0;
    for (int i = 0; i < path.length(); i++) {
      String pathAfter = path.substring(i);
      if (pathAfter.startsWith("[") && !inQuotes) {
        inBrackets = true;
        escape = false;
      } else if (pathAfter.startsWith("]") && !inQuotes) {
        inBrackets = false;
        escape = false;
      } else if (pathAfter.startsWith("'") && !escape) {
        inQuotes = !inQuotes;
        escape = false;
      } else if (pathAfter.startsWith("\\") && !escape) {
        escape = true;
      } else if (searchList.stream().anyMatch(pathAfter::startsWith) && !inBrackets && !inQuotes) {
        strings.add(path.substring(last, i));
        last =
            searchList.stream().filter(pathAfter::startsWith).findAny().orElseThrow().length() + i;
        if (max != null && strings.size() == max - 1) {
          strings.add(path.substring(last));
          break;
        }
      } else {
        escape = false;
      }
    }

    if (strings.isEmpty()) {
      strings.add(path);
    } else if (last < path.length() && max == null) {
      strings.add(path.substring(last));
    }
    /*
    final String[] strings;
    int splitPos = StringUtils.indexOf(StringUtils.stripStart(path, "/"), "/", path.indexOf(']'));
    if (splitPos > -1) {
      strings = new String[2];
      strings[0] = path.substring(0, splitPos + 1);
      strings[1] = path.substring(splitPos + 1);
    } else {
      strings = new String[1];
      strings[0] = path;
    }
    return strings;

     */
    return strings.stream().toArray(String[]::new);
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
