package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum OverallTestStatusDefiningCode implements EnumValueSet {
  AMENDED(
      "Amended",
      "The result has been modified subsequent to being Final, and is complete and verified by the responsible pathologist, and result data has been changed.",
      "local",
      "at0040"),

  PRELIMINARY(
      "Preliminary",
      "Verified early results are available, but not all results are final. This is a sub-category of 'Partial'.",
      "local",
      "at0120"),

  FINAL(
      "Final",
      "The Test result is complete and verified by an authorised person.",
      "local",
      "at0038"),

  APPENDED(
      "Appended",
      "Subsequent to being final, the report has been modified by adding new content. The existing content is unchanged. This is a sub-category of 'Amended'.",
      "local",
      "at0119"),

  CORRECTED(
      "Corrected",
      "The result has been modified subsequent to being Final, and is complete and verified by the responsible pathologist. This is a sub-category of 'Amended'.",
      "local",
      "at0115"),

  CANCELLED(
      "Cancelled",
      "The result is unavailable because the test was not started or not completed (also sometimes called 'aborted').",
      "local",
      "at0074"),

  ENTERED_IN_ERROR(
      "Entered in error",
      "The Test Result has been withdrawn following previous Final release.",
      "local",
      "at0116"),

  PARTIAL(
      "Partial",
      "This is a partial (e.g. initial, interim or preliminary) Test Result: data in the Test Result may be incomplete or unverified.",
      "local",
      "at0037");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  OverallTestStatusDefiningCode(
      String value, String description, String terminologyId, String code) {
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
