package org.ehrbase.client.classgenerator.examples.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum TransitionDefiningcode implements EnumValueSet {
    ABORT("abort", "abort", "openehr", "547"),

    POSTPONE("postpone", "postpone", "openehr", "537"),

    TIME_OUT("time out", "time out", "openehr", "549"),

    SCHEDULE("schedule", "schedule", "openehr", "539"),

    DO("do", "do", "openehr", "541"),

    RESUME("resume", "resume", "openehr", "546"),

    NOTIFY_ABORTED("notify aborted", "notify aborted", "openehr", "550"),

    CANCEL("cancel", "cancel", "openehr", "166"),

    RESTORE("restore", "restore", "openehr", "538"),

    ACTIVE_STEP("active step", "active step", "openehr", "543"),

    NOTIFY_CANCELLED("notify cancelled", "notify cancelled", "openehr", "552"),

    POSTPONED_STEP("postponed step", "postponed step", "openehr", "542"),

    FINISH("finish", "finish", "openehr", "548"),

    SUSPENDED_STEP("suspended step", "suspended step", "openehr", "545"),

    INITIATE("initiate", "initiate", "openehr", "535"),

    SUSPEND("suspend", "suspend", "openehr", "544"),

    START("start", "start", "openehr", "540"),

    PLAN_STEP("plan step", "plan step", "openehr", "536"),

    NOTIFY_COMPLETED("notify completed", "notify completed", "openehr", "551"),

    SCHEDULED_STEP("scheduled step", "scheduled step", "openehr", "534");

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
