package org.ehrbase.client.classgenerator.examples.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum TransitionDefiningcode implements EnumValueSet {
    RESUME("resume", "resume", "openehr", "546"),

    POSTPONE("postpone", "postpone", "openehr", "537"),

    POSTPONEDSTEP("postponed step", "postponed step", "openehr", "542"),

    PLANSTEP("plan step", "plan step", "openehr", "536"),

    SCHEDULE("schedule", "schedule", "openehr", "539"),

    SUSPENDEDSTEP("suspended step", "suspended step", "openehr", "545"),

    NOTIFYCANCELLED("notify cancelled", "notify cancelled", "openehr", "552"),

    SUSPEND("suspend", "suspend", "openehr", "544"),

    START("start", "start", "openehr", "540"),

    RESTORE("restore", "restore", "openehr", "538"),

    CANCEL("cancel", "cancel", "openehr", "166"),

    DO("do", "do", "openehr", "541"),

    INITIATE("initiate", "initiate", "openehr", "535"),

    SCHEDULEDSTEP("scheduled step", "scheduled step", "openehr", "534"),

    ACTIVESTEP("active step", "active step", "openehr", "543"),

    NOTIFYCOMPLETED("notify completed", "notify completed", "openehr", "551"),

    FINISH("finish", "finish", "openehr", "548"),

    TIMEOUT("time out", "time out", "openehr", "549"),

    ABORT("abort", "abort", "openehr", "547"),

    NOTIFYABORTED("notify aborted", "notify aborted", "openehr", "550");

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
