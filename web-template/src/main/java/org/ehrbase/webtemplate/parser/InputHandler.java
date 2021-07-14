/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.webtemplate.model.*;
import org.openehr.schemas.v1.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class InputHandler {

  private final Map<String, String> defaultValues;

  public InputHandler(Map<String, String> defaultValues) {
    this.defaultValues = defaultValues;
  }

  public WebTemplateInput extractInput(CPRIMITIVEOBJECT cprimitiveobject) {
    WebTemplateInput input = new WebTemplateInput();
    input.setType(cprimitiveobject.getRmTypeName().replace("_", ""));
    if (input.getType().equals("REAL")) {
      input.setType("DECIMAL");
    }
    if (input.getType().equals("STRING")) {
      input.setType("TEXT");
    }

    CPRIMITIVE item = cprimitiveobject.getItem();
    String pattern = null;
    WebTemplateInterval<?> range = null;
    WebTemplateValidation validation = new WebTemplateValidation();
    boolean addValidation = false;
    if (item instanceof CDATETIME) {

      pattern = ((CDATETIME) item).getPattern();

    } else if (item instanceof CTIME) {
      pattern = ((CTIME) item).getPattern();
    } else if (item instanceof CDATE) {
      pattern = ((CDATE) item).getPattern();
    } else if (item instanceof CSTRING) {
      pattern = ((CSTRING) item).getPattern();
      Arrays.stream(((CSTRING) item).getListArray())
          .forEach(
              i -> {
                WebTemplateInputValue value = new WebTemplateInputValue();
                value.setValue(i);
                value.setLabel(i);
                input.getList().add(value);
              });
      input.setListOpen(((CSTRING) item).getListOpen());
    }

    if (item instanceof CDURATION) {
      pattern = ((CDURATION) item).getPattern();
      range = extractInterval(((CDURATION) item).getRange());
    }

    if (item instanceof CINTEGER) {
      range = extractInterval(((CINTEGER) item).getRange());
      Arrays.stream(((CINTEGER) item).getListArray())
          .forEach(
              i -> {
                WebTemplateInputValue value = new WebTemplateInputValue();
                value.setValue(Integer.toString(i));
                input.getList().add(value);
              });
    } else if (item instanceof CREAL) {
      range = extractInterval(((CREAL) item).getRange());
    }

    if (item instanceof CBOOLEAN) {
      if (((CBOOLEAN) item).getFalseValid() && !((CBOOLEAN) item).getTrueValid()) {
        WebTemplateInputValue falseInputValue = new WebTemplateInputValue();
        falseInputValue.setLabel("false");
        falseInputValue.setValue("false");
        input.getList().add(falseInputValue);
      } else if (!((CBOOLEAN) item).getFalseValid() && ((CBOOLEAN) item).getTrueValid()) {
        WebTemplateInputValue falseInputValue = new WebTemplateInputValue();
        falseInputValue.setLabel("true");
        falseInputValue.setValue("true");
        input.getList().add(falseInputValue);
      }
    }
    if (StringUtils.isNotBlank(pattern)) {
      validation.setPattern(pattern);
      addValidation = true;
    }

    if (range != null) {
      validation.setRange(range);
      addValidation = true;
    }

    if (addValidation) {
      input.setValidation(validation);
    }
    return input;
  }

  <T extends Serializable> WebTemplateInterval<T> extractInterval(Interval interval) {
    if (interval == null) {
      return null;
    }
    WebTemplateInterval<T> webTemplateInterval = new WebTemplateInterval<>();
    if (!interval.getLowerUnbounded()) {
      webTemplateInterval.setMin(extractLower(interval));
      if (interval.getLowerIncluded()) {
        webTemplateInterval.setMinOp(WebTemplateComparisonSymbol.GT_EQ);
      } else {
        webTemplateInterval.setMinOp(WebTemplateComparisonSymbol.GT);
      }
    }
    if (!interval.getUpperUnbounded()) {
      webTemplateInterval.setMax((extractUpper(interval)));
      if (interval.getUpperIncluded()) {
        webTemplateInterval.setMaxOp(WebTemplateComparisonSymbol.LT_EQ);
      } else {
        webTemplateInterval.setMaxOp(WebTemplateComparisonSymbol.LT);
      }
    }
    if (interval instanceof IntervalOfInteger) {
      webTemplateInterval =
          (WebTemplateInterval<T>)
              normalizeInterval((WebTemplateInterval<Integer>) webTemplateInterval);
    }
    return webTemplateInterval;
  }

  WebTemplateInterval<Integer> normalizeInterval(WebTemplateInterval<Integer> interval) {
    if (Objects.equals(interval.getMaxOp(), WebTemplateComparisonSymbol.LT)) {
      interval.setMaxOp(WebTemplateComparisonSymbol.LT_EQ);
      interval.setMax(interval.getMax() - 1);
    }
    if (Objects.equals(interval.getMinOp(), WebTemplateComparisonSymbol.GT)) {
      interval.setMinOp(WebTemplateComparisonSymbol.GT_EQ);
      interval.setMin(interval.getMin() + 1);
    }
    return interval;
  }

  private <T> T extractLower(Interval interval) {
    if (interval instanceof IntervalOfReal) {
      return (T) Double.valueOf(((IntervalOfReal) interval).getLower());
    } else if (interval instanceof IntervalOfInteger) {
      return (T) Integer.valueOf(((IntervalOfInteger) interval).getLower());
    } else if (interval instanceof IntervalOfDuration) {
      return (T) ((IntervalOfDuration) interval).getLower();
    }

    return null;
  }

  private <T> T extractUpper(Interval interval) {
    if (interval instanceof IntervalOfReal) {
      return (T) Double.valueOf(((IntervalOfReal) interval).getUpper());
    } else if (interval instanceof IntervalOfInteger) {
      return (T) Integer.valueOf(((IntervalOfInteger) interval).getUpper());
    } else if (interval instanceof IntervalOfDuration) {
      return (T) ((IntervalOfDuration) interval).getUpper();
    }

    return null;
  }

  WebTemplateInterval<Integer> buildRange(
      Integer min,
      WebTemplateComparisonSymbol minOp,
      Integer max,
      WebTemplateComparisonSymbol maxOp) {

    WebTemplateInterval range = new WebTemplateInterval();
    range.setMax(max);
    range.setMaxOp(maxOp);
    range.setMin(min);
    range.setMinOp(minOp);

    return range;
  }

  public static WebTemplateInput buildWebTemplateInput(String suffix, String type) {
    WebTemplateInput date = new WebTemplateInput();
    date.setType(type);
    date.setSuffix(suffix);
    return date;
  }

  WebTemplateInput buildWebTemplateInput(String suffix, String type, WebTemplateInterval range) {
    WebTemplateInput date = buildWebTemplateInput(suffix, type);
    WebTemplateValidation validation = new WebTemplateValidation();
    validation.setRange(range);
    date.setValidation(validation);
    return date;
  }

  void addInputs(WebTemplateNode node, Map<String, WebTemplateInput> templateInputMap) {
    switch (node.getRmType()) {
      case "DV_DATE":
        node.getInputs()
            .add(templateInputMap.getOrDefault("value", buildWebTemplateInput(null, "DATE")));
        break;
      case "DV_DATE_TIME":
        node.getInputs()
            .add(templateInputMap.getOrDefault("value", buildWebTemplateInput(null, "DATETIME")));
        break;
      case "DV_TIME":
        node.getInputs()
            .add(templateInputMap.getOrDefault("value", buildWebTemplateInput(null, "TIME")));
        break;
      case "DV_ORDINAL":
        node.getInputs()
            .add(templateInputMap.getOrDefault("value", buildWebTemplateInput(null, "CODED_TEXT")));
        break;
      case "PARTY_PROXY":
        node.getInputs().add(buildWebTemplateInput("id", "TEXT"));
        node.getInputs().add(buildWebTemplateInput("id_scheme", "TEXT"));
        node.getInputs().add(buildWebTemplateInput("id_namespace", "TEXT"));
        node.getInputs().add(buildWebTemplateInput("name", "TEXT"));
        break;
      case "DV_PARSABLE":
        node.getInputs().add(buildWebTemplateInput("value", "TEXT"));
        node.getInputs().add(buildWebTemplateInput("formalism", "TEXT"));
        break;
      case "DV_TEXT":
      case "DV_EHR_URI":
      case "DV_URI":
      case "DV_MULTIMEDIA":
      case "STRING":
        node.getInputs()
            .add(templateInputMap.getOrDefault("value", buildWebTemplateInput(null, "TEXT")));
        break;
      case "DV_COUNT":
        {
          WebTemplateInput magnitude =
              templateInputMap.getOrDefault("magnitude", buildWebTemplateInput(null, "INTEGER"));
          findDefaultValue(node, "magnitude").ifPresent(magnitude::setDefaultValue);

          node.getInputs().add(magnitude);
        }
        break;
      case "DV_QUANTITY":
        {
          WebTemplateInput magnitude =
              templateInputMap.getOrDefault(
                  "magnitude", buildWebTemplateInput("magnitude", "DECIMAL"));
          findDefaultValue(node, "magnitude").ifPresent(magnitude::setDefaultValue);

          node.getInputs().add(magnitude);

          node.getInputs().add(buildWebTemplateInput("unit", "TEXT"));
        }
        break;
      case "DV_BOOLEAN":
        node.getInputs()
            .add(templateInputMap.getOrDefault("value", buildWebTemplateInput(null, "BOOLEAN")));
        break;
      case "DV_STATE":
      case "DV_CODED_TEXT":
        node.getInputs().add(buildWebTemplateInput("code", "TEXT"));
        node.getInputs().add(buildWebTemplateInput("value", "TEXT"));
        break;
      case "DV_PROPORTION":
        WebTemplateInput numerator =
            templateInputMap.getOrDefault(
                "numerator", buildWebTemplateInput("numerator", "DECIMAL"));
        numerator.setSuffix("numerator");
        node.getInputs().add(numerator);
        WebTemplateInput denominator =
            templateInputMap.getOrDefault(
                "denominator", buildWebTemplateInput("denominator", "DECIMAL"));
        denominator.setSuffix("denominator");

        List<ProportionType> proportionTypes =
            Optional.ofNullable(templateInputMap.get("type"))
                .map(WebTemplateInput::getList)
                .stream()
                .flatMap(List::stream)
                .map(WebTemplateInputValue::getValue)
                .map(Integer::valueOf)
                .map(ProportionType::findById)
                .collect(Collectors.toList());
        if (proportionTypes.isEmpty()) {
          proportionTypes = Arrays.asList(ProportionType.values());
        } else if (proportionTypes.size() == 1) {
          proportionTypes.get(0).getDenominatorValidator().ifPresent(denominator::setValidation);
        }
        node.getProportionTypes().addAll(proportionTypes);
        node.getInputs().add(denominator);
        break;
      case "DV_IDENTIFIER":
        node.getInputs().add(buildWebTemplateInput("id", "TEXT"));
        node.getInputs().add(buildWebTemplateInput("type", "TEXT"));
        node.getInputs().add(buildWebTemplateInput("issuer", "TEXT"));
        node.getInputs().add(buildWebTemplateInput("assigner", "TEXT"));
        break;

      case "DV_DURATION":
        String pattern =
            Optional.ofNullable(templateInputMap.get("value"))
                .map(WebTemplateInput::getValidation)
                .map(WebTemplateValidation::getPattern)
                .orElse(null);
        Map<String, Integer> minConstrains =
            buildDurationConstrains(
                Optional.ofNullable(templateInputMap.get("value"))
                    .map(WebTemplateInput::getValidation)
                    .map(WebTemplateValidation::getRange)
                    .map(WebTemplateInterval::getMin)
                    .map(Object::toString)
                    .orElse(null));
        WebTemplateComparisonSymbol minOperator =
            Optional.ofNullable(templateInputMap.get("value"))
                .map(WebTemplateInput::getValidation)
                .map(WebTemplateValidation::getRange)
                .map(WebTemplateInterval::getMinOp)
                .orElse(null);

        Map<String, Integer> maxConstrains =
            buildDurationConstrains(
                Optional.ofNullable(templateInputMap.get("value"))
                    .map(WebTemplateInput::getValidation)
                    .map(WebTemplateValidation::getRange)
                    .map(WebTemplateInterval::getMax)
                    .map(Object::toString)
                    .orElse(null));

        Map<String, Integer> defaults =
            buildDurationConstrains(findDefaultValue(node, "value").orElse(null));
        Integer df = 0;
        if (defaults.isEmpty()) {
          df = null;
        }

        WebTemplateComparisonSymbol maxOperator =
            Optional.ofNullable(templateInputMap.get("value"))
                .map(WebTemplateInput::getValidation)
                .map(WebTemplateValidation::getRange)
                .map(WebTemplateInterval::getMaxOp)
                .orElse(null);

        boolean blank = StringUtils.isBlank(pattern);
        buildDurationInput(
            node,
            "Y",
            "year",
            pattern,
            blank,
            minConstrains.get("Y"),
            minOperator,
            maxConstrains.get("Y"),
            maxOperator,
            defaults.getOrDefault("Y", df));
        buildDurationInput(
            node,
            "M",
            "month",
            StringUtils.removeEnd(pattern, StringUtils.substringAfter(pattern, "T")),
            blank,
            minConstrains.get("M"),
            minOperator,
            maxConstrains.get("M"),
            maxOperator,
            defaults.getOrDefault("M", df));
        buildDurationInput(
            node,
            "D",
            "day",
            pattern,
            blank,
            minConstrains.get("D"),
            minOperator,
            maxConstrains.get("D"),
            maxOperator,
            defaults.getOrDefault("D", df));
        buildDurationInput(
            node,
            "W",
            "week",
            pattern,
            blank,
            minConstrains.get("W"),
            minOperator,
            maxConstrains.get("W"),
            maxOperator,
            defaults.getOrDefault("W", df));
        buildDurationInput(
            node,
            "H",
            "hour",
            pattern,
            blank,
            minConstrains.get("H"),
            minOperator,
            maxConstrains.get("H"),
            maxOperator,
            defaults.getOrDefault("H", df));
        buildDurationInput(
            node,
            "M",
            "minute",
            StringUtils.substringAfter(pattern, "D"),
            blank,
            minConstrains.get("MT"),
            minOperator,
            maxConstrains.get("MT"),
            maxOperator,
            defaults.getOrDefault("MT", df));
        buildDurationInput(
            node,
            "S",
            "second",
            pattern,
            blank,
            minConstrains.get("S"),
            minOperator,
            maxConstrains.get("S"),
            maxOperator,
            defaults.getOrDefault("S", df));
        break;
    }
    node.getInputs()
        .forEach(
            i -> {
              Optional.ofNullable(
                      defaultValues.get(
                          node.getAqlPath(false)
                              + "|"
                              + ((StringUtils.isBlank(i.getSuffix())) ? "value" : i.getSuffix())))
                  .ifPresent(i::setDefaultValue);
            });
  }

  public Optional<String> findDefaultValue(WebTemplateNode node, String inputSuffix) {
    return Optional.ofNullable(defaultValues.get(node.getAqlPath(true) + "|" + inputSuffix));
  }

  private Map<String, Integer> buildDurationConstrains(String constrain) {
    if (StringUtils.isBlank(constrain)) {
      return Collections.emptyMap();
    }
    Map<String, Integer> constrainMap = new HashMap<>();
    boolean isDatePath = true;
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < constrain.length(); i++) {
      char c = constrain.charAt(i);
      if (c == 'T') {
        isDatePath = false;
      } else if (CharUtils.isAsciiNumeric(c)) {
        sb.append(c);
      } else if (c != 'P') {
        String key = String.valueOf(c);
        if (isDatePath && key.equals("M")) {
          key = key + "T";
        }
        constrainMap.put(key, Integer.parseInt(sb.toString()));
        sb.delete(0, sb.length());
      }
    }

    return constrainMap;
  }

  private void buildDurationInput(
      WebTemplateNode node,
      String symbol,
      String suffix,
      String pattern,
      boolean emptyPattern,
      Integer minValue,
      WebTemplateComparisonSymbol minOperator,
      Integer maxValue,
      WebTemplateComparisonSymbol maxOperator,
      Integer defaultValue) {
    if (emptyPattern || pattern.contains(symbol)) {

      WebTemplateInterval<Integer> defaultRange =
          buildRange(0, WebTemplateComparisonSymbol.GT_EQ, null, null);
      if (minValue != null) {
        defaultRange.setMin(minValue);
        defaultRange.setMinOp(minOperator);
      }
      if (maxValue != null) {
        defaultRange.setMax(maxValue);
        defaultRange.setMaxOp(maxOperator);
      }
      WebTemplateInput input = buildWebTemplateInput(suffix, "INTEGER", defaultRange);
      if (defaultValue != null) {
        input.setDefaultValue(defaultValue.toString());
      }
      node.getInputs().add(input);
    }
  }
}
