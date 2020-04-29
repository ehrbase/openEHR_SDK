package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum DiagnosticStatusDefiningcode implements EnumValueSet {
  WORKING("Working", "Interim diagnosis, based on a reasonable amount of clinical certainty but pending further test results or clinical advice. It may still change as test results or advice become available.", "local", "at0017");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  DiagnosticStatusDefiningcode(String value, String description, String terminologyId,
      String code) {
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
