package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum TravelDefiningcode implements EnumValueSet {
  YESINTERNATIONAL("Yes - international", "The individual has travelled internationally within the specified interval of time.", "local", "at0023"),

  NO("No", "The individual has not travelled within the specified interval of time.", "local", "at0005"),

  YESNATIONAL("Yes - national", "The individual has travelled nationally within the specified interval of time.", "local", "at0006");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  TravelDefiningcode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public String getValue() {
     return this.value ;
  }

  public String getDescription() {
     return this.description ;
  }

  public String getTerminologyId() {
     return this.terminologyId ;
  }

  public String getCode() {
     return this.code ;
  }
}
