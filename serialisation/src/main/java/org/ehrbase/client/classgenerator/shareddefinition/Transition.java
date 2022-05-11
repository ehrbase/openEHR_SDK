package org.ehrbase.client.classgenerator.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

import java.util.List;

public enum Transition implements EnumValueSet {
  CANCEL("cancel", "cancel", "openehr", "166", List.of(State.PLANNED, State.SCHEDULED, State.POSTPONED), State.CANCELLED),

  INITIATE("initiate", "initiate", "openehr", "535", State.INITIAL, State.PLANNED),

  SUSPEND("suspend", "suspend", "openehr", "544", State.ACTIVE, State.SUSPENDED),

  SCHEDULED_STEP("scheduled step", "scheduled step", "openehr", "534", State.SCHEDULED, State.SCHEDULED),

  RESTORE("restore", "restore", "openehr", "538", State.POSTPONED, State.PLANNED),

  POSTPONE("postpone", "postpone", "openehr", "537", List.of(State.PLANNED, State.SCHEDULED), State.POSTPONED),

  RESUME("resume", "resume", "openehr", "546", State.SUSPENDED, State.ACTIVE),

  SUSPENDED_STEP("suspended step", "suspended step", "openehr", "545", State.SUSPENDED, State.SUSPENDED),

  NOTIFY_ABORTED("notify aborted", "notify aborted", "openehr", "550", State.EXPIRED, State.ABORTED),

  START("start", "start", "openehr", "540", List.of(State.INITIAL, State.PLANNED, State.SCHEDULED), State.ACTIVE),

  FINISH("finish", "finish", "openehr", "548", State.ACTIVE, State.COMPLETED),

  SCHEDULE("schedule", "schedule", "openehr", "539", List.of(State.INITIAL, State.PLANNED), State.SCHEDULED),

  NOTIFY_COMPLETED("notify completed", "notify completed", "openehr", "551", State.EXPIRED, State.COMPLETED),

  ACTIVE_STEP("active step", "active step", "openehr", "543", State.ACTIVE, State.ACTIVE),

  DO("do", "do", "openehr", "541", State.PLANNED, State.COMPLETED),

  PLAN_STEP("plan step", "plan step", "openehr", "536", State.PLANNED, State.PLANNED),

  TIME_OUT("time out", "time out", "openehr", "549", List.of(State.PLANNED, State.POSTPONED, State.SCHEDULED, State.SUSPENDED, State.ACTIVE), State.EXPIRED),

  NOTIFY_CANCELLED("notify cancelled", "notify cancelled", "openehr", "552", State.EXPIRED, State.CANCELLED),

  POSTPONED_STEP("postponed step", "postponed step", "openehr", "542", State.POSTPONED, State.POSTPONED),

  ABORT("abort", "abort", "openehr", "547", List.of(State.ACTIVE, State.SUSPENDED), State.ABORTED);

  private final String value;

  private final String description;

  private final String terminologyId;

  private final String code;

  private final List<State> sourceStates;

  private final State targetState;

  Transition(String value, String description, String terminologyId, String code,List<State> sourceStates, State targetState) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
    this.sourceStates = sourceStates;
    this.targetState = targetState;
  }

  Transition(String value, String description, String terminologyId, String code, State sourceState, State targetState) {
    this(value, description, terminologyId, code, List.of(sourceState), targetState);
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

  public List<State> getSourceStates() { return sourceStates; }

  public State getTargetState() { return targetState; }
}
