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

import org.apache.commons.lang3.StringUtils;
import org.ehrbase.webtemplate.util.CharSequenceHelper;

import java.util.*;

import static org.ehrbase.webtemplate.util.CharSequenceHelper.subSequence;

public class FlatPath {
  private final String name;
  private final String atCode;
  private final FlatPath child;
  private final Map<String, String> otherPredicates;
  private final String attributeName;
  private final boolean startWithSlash;

  public FlatPath(FlatPath flatPath) {
    this.name = flatPath.getName();
    this.atCode = flatPath.getAtCode();
    this.child = flatPath.getChild();
    this.otherPredicates = new HashMap<>(flatPath.otherPredicates);
    this.attributeName = flatPath.getAttributeName();
    this.startWithSlash = flatPath.startWithSlash;
  }

  public enum OtherPredicatesFormate {
    NONE,
    SHORTED,
    FULL;
  }

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

    startWithSlash = StringUtils.startsWith(path, "/");
    final CharSequence[] strings = split(StringUtils.stripStart(path, "/"), 2, "/");

    CharSequence[] split;

    if (strings.length == 2) {
      if (StringUtils.endsWith(strings[0], "]")) {
        int fist = StringUtils.indexOf(strings[0], '[');
        split = new CharSequence[] {
              subSequence(strings[0], 0, fist),
              subSequence(strings[0], fist, strings[0].length())
        };
      } else {
        split = new CharSequence[] {strings[0]};
      }
      name = CharSequenceHelper.removeStart(split[0], "/").toString();
      child = new FlatPath("/" + strings[1]);
      attributeName = null;
    } else {
      child = null;
      CharSequence[] attributeSplit = split(strings[0], null, "|");

      if (attributeSplit.length == 2) {
        attributeName = attributeSplit[1].toString();
      } else {
        attributeName = null;
      }

      if (StringUtils.endsWith(attributeSplit[0], "]")) {
        int fist = StringUtils.indexOf(attributeSplit[0], '[');
        split = new CharSequence[]{
              subSequence(attributeSplit[0], 0, fist),
              subSequence(attributeSplit[0], fist, attributeSplit[0].length())
        };
      } else {
        split = new CharSequence[] { attributeSplit[0] };
      }
      name = CharSequenceHelper.removeStart(split[0], "/").toString();
    }
    if (split.length == 2) {
      CharSequence node = CharSequenceHelper.removeEnd(CharSequenceHelper.removeStart(split[1], "["), "]");

      CharSequence[] predicates = split(node, null, " and", ",");
      atCode = predicates[0].toString().trim();
      otherPredicates = new HashMap<>();
      for (int i = 1; i < predicates.length; i++) {
        CharSequence[] pair = split(predicates[i], null, "=");
        if (i == 1 && pair.length == 1) {
          otherPredicates.put("name/value", StringUtils.unwrap(pair[0].toString(), "'"));
        } else if (pair.length == 2) {
          otherPredicates.put(pair[0].toString().trim(), StringUtils.unwrap(pair[1].toString(), "'"));
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

  private CharSequence[] split(CharSequence path, Integer max, String... search) {
    List<CharSequence> strings = new ArrayList<>();
    Arrays.sort(search, CharSequence::compare);

    boolean inBrackets = false;
    boolean inQuotes = false;
    boolean escape = false;

    int last = 0;
    for (int i = 0; i < path.length(); i++) {
      char ch = path.charAt(i);
      if (!inQuotes && ch == '[') {
        inBrackets = true;
        escape = false;
      } else if (!inQuotes && ch == ']') {
        inBrackets = false;
        escape = false;
      } else if (!escape && ch == '\'') {
        inQuotes = !inQuotes;
      } else if (!escape && ch == '\\') {
        escape = true;
      } else if (inBrackets || inQuotes) {
        escape = false;
      } else {
        CharSequence prefix = findPrefix(path, i, search);
        if (prefix == null) {
          escape = false;
        } else {
          strings.add(subSequence(path, last, i));
          last = prefix.length() + i;
          if (max != null && strings.size() == max - 1) {
            strings.add(subSequence(path, last, path.length()));
            break;
          }
        }
      }
    }

    if (strings.isEmpty()) {
      strings.add(path);
    } else if (last < path.length() && max == null) {
      strings.add(subSequence(path, last, path.length()));
    }
    return strings.stream().toArray(CharSequence[]::new);
  }

  private static CharSequence findPrefix(CharSequence fullPath, int startPos, String[] search) {
    CharSequence pathAfter = subSequence(fullPath, startPos, fullPath.length());
    int insertionPoint = Arrays.binarySearch(search, pathAfter, (a, b) -> CharSequence.compare(a, b));
    if (insertionPoint >= 0) {
      return search[insertionPoint];
    }

    int prefixPos = -insertionPoint - 2;
    if (prefixPos < 0) {
      return null;
    }
    String candidate = search[prefixPos];
    if (candidate.length() <= pathAfter.length() && 0 == CharSequence.compare(candidate, pathAfter.subSequence(0, candidate.length()))) {
      return candidate;
    } else {
      return null;
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FlatPath flatPath = (FlatPath) o;
    return startWithSlash == flatPath.startWithSlash
        && Objects.equals(name, flatPath.name)
        && Objects.equals(atCode, flatPath.atCode)
        && Objects.equals(child, flatPath.child)
        && Objects.equals(otherPredicates, flatPath.otherPredicates)
        && Objects.equals(attributeName, flatPath.attributeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, atCode, child, otherPredicates, attributeName, startWithSlash);
  }

  @Override
  public String toString() {
    return format(true);
  }

  public String format(boolean withOtherPredicates) {
    if (withOtherPredicates) {
      return format(OtherPredicatesFormate.FULL);
    } else {
      return format(OtherPredicatesFormate.NONE);
    }
  }

  public String format(OtherPredicatesFormate otherPredicatesFormate) {
    StringBuilder sb = new StringBuilder();
    appendFormat(sb, otherPredicatesFormate);
    return sb.toString();
  }

  private void appendFormat(StringBuilder sb, OtherPredicatesFormate otherPredicatesFormate) {
    if (startWithSlash) {
      sb.append("/");
    }
    sb.append(name);
    if (StringUtils.isNotBlank(atCode)) {
      sb.append("[").append(atCode);
      if (!otherPredicatesFormate.equals(OtherPredicatesFormate.NONE)) {
        otherPredicates.forEach(
            (key, value) -> {
              if (otherPredicatesFormate.equals(OtherPredicatesFormate.SHORTED)
                  && key.equals("name/value")) {
                sb.append(",").append("'").append(value).append("'");
              } else {
                sb.append(" and ").append(key).append("=").append("'").append(value).append("'");
              }
            });
      }
      sb.append("]");
    }
    if (child != null) {
      child.appendFormat(sb, otherPredicatesFormate);
    }
    if (attributeName != null) {
      sb.append("|").append(attributeName);
    }
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

  /*
  public static FlatPath removeStart(FlatPath path, FlatPath remove) {
    FlatPath other = new FlatPath(remove);
    FlatPath me = new FlatPath(path);
    do{

      if (!new EqualsBuilder().append(me.startWithSlash, other.startWithSlash).append(me.name, other.name).append(me.atCode, other.atCode).append(me.otherPredicates, other.otherPredicates).append(me.attributeName, other.attributeName).isEquals()) break;
      other = other.getChild();
      me = me.child;
    }while (other != null && me != null);

    if (other == null) {
      return me;
    } else {
      return new FlatPath(path);
    }
  }

   */
  public static FlatPath addEnd(FlatPath path, FlatPath add) {
    return new FlatPath(path.toString() + "/" + StringUtils.removeStart(add.toString(), "/"));
  }
}
