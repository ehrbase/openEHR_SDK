package org.ehrbase.client.classgenerator.examples.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum TransitionDefiningcode implements EnumValueSet {
  RESUME("resume", "resume", "openehr", "546"),

  TIME_OUT("time out", "time out", "openehr", "549"),

  SUSPENDED_STEP("suspended step", "suspended step", "openehr", "545"),

  NOTIFY_CANCELLED("notify cancelled", "notify cancelled", "openehr", "552"),

  POSTPONED_STEP("postponed step", "postponed step", "openehr", "542"),

  ACTIVE_STEP("active step", "active step", "openehr", "543"),

  INITIATE("initiate", "initiate", "openehr", "535"),

  ABORT("abort", "abort", "openehr", "547"),

  NOTIFY_ABORTED("notify aborted", "notify aborted", "openehr", "550"),

  SCHEDULE("schedule", "schedule", "openehr", "539"),

  PLAN_STEP("plan step", "plan step", "openehr", "536"),

  DO("do", "do", "openehr", "541"),

  RESTORE("restore", "restore", "openehr", "538"),

  NOTIFY_COMPLETED("notify completed", "notify completed", "openehr", "551"),

  CANCEL("cancel", "cancel", "openehr", "166"),

  POSTPONE("postpone", "postpone", "openehr", "537"),

  START("start", "start", "openehr", "540"),

  SUSPEND("suspend", "suspend", "openehr", "544"),

  SCHEDULED_STEP("scheduled step", "scheduled step", "openehr", "534"),

  FINISH("finish", "finish", "openehr", "548");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  TransitionDefiningcode(String value, String description, String terminologyId, String code) {
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
