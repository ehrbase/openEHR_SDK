package org.ehrbase.aql.util;

import org.ehrbase.aql.binder.AqlBinder;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.condition.*;
import org.ehrbase.aql.parser.AqlToDtoParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AqlUtil {

  private AqlUtil() {}

  private static final AqlToDtoParser PARSER = new AqlToDtoParser();
  private static final AqlBinder BINDER = new AqlBinder();

  /**
   * Removes a parameter from the aql
   *
   * @param aql source aql string
   * @param parameterName name of the parameter to be removed
   * @return the aql with the parameter removed
   */
  public static String removeParameter(String aql, String parameterName) {

    AqlDto dto = PARSER.parse(aql);

    dto.setWhere(removeParameter(dto.getWhere(), parameterName));
    return BINDER.bind(dto).getLeft().buildAql();
  }

  private static ConditionDto removeParameter(ConditionDto conditionDto, String parameterName) {
    if (conditionDto instanceof ConditionComparisonOperatorDto) {
      Value value = ((ConditionComparisonOperatorDto) conditionDto).getValue();
      if (value instanceof ParameterValue
          && Objects.equals(((ParameterValue) value).getName(), parameterName)) {
        return null;
      }
    } else if (conditionDto instanceof ConditionLogicalOperatorDto) {
      List<ConditionDto> values = ((ConditionLogicalOperatorDto) conditionDto).getValues();

      for (ConditionDto value : new ArrayList<>(values)) {
        values.remove(value);

        ConditionDto newValue = removeParameter(value, parameterName);

        if (newValue != null) {
          values.add(newValue);
        }
      }

      if (values.isEmpty()) {
        return null;
      } else if (values.size() == 1) {
        return values.get(0);
      } else {
        return conditionDto;
      }
    }

    return conditionDto;
  }
}
