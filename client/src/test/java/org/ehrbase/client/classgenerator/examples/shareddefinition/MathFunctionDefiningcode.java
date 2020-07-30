package org.ehrbase.client.classgenerator.examples.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MathFunctionDefiningcode implements EnumValueSet {
  MODE("mode", "mode", "openehr", "267"),

  MEDIAN("median", "median", "openehr", "268"),

  MEAN("mean", "mean", "openehr", "146"),

  VARIATION("variation", "variation", "openehr", "149"),

  MAXIMUM("maximum", "maximum", "openehr", "144"),

  ACTUAL("actual", "actual", "openehr", "640"),

  MINIMUM("minimum", "minimum", "openehr", "145"),

  TOTAL("total", "total", "openehr", "148"),

  INCREASE("increase", "increase", "openehr", "522"),

  DECREASE("decrease", "decrease", "openehr", "521"),

  CHANGE("change", "change", "openehr", "147");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MathFunctionDefiningcode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public String getValue() {
     return this.value;
  }

  public String getDescription() {
    return this.description;
  }

  public String getTerminologyId() {
    return this.terminologyId;
  }

  public String getCode() {
    return this.code;
  }
}
