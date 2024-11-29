/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum TransitionDefiningcode implements EnumValueSet {
    CANCEL("cancel", "cancel", "openehr", "166"),

    INITIATE("initiate", "initiate", "openehr", "535"),

    SUSPEND("suspend", "suspend", "openehr", "544"),

    SCHEDULED_STEP("scheduled step", "scheduled step", "openehr", "534"),

    RESTORE("restore", "restore", "openehr", "538"),

    POSTPONE("postpone", "postpone", "openehr", "537"),

    RESUME("resume", "resume", "openehr", "546"),

    SUSPENDED_STEP("suspended step", "suspended step", "openehr", "545"),

    NOTIFY_ABORTED("notify aborted", "notify aborted", "openehr", "550"),

    START("start", "start", "openehr", "540"),

    FINISH("finish", "finish", "openehr", "548"),

    SCHEDULE("schedule", "schedule", "openehr", "539"),

    NOTIFY_COMPLETED("notify completed", "notify completed", "openehr", "551"),

    ACTIVE_STEP("active step", "active step", "openehr", "543"),

    DO("do", "do", "openehr", "541"),

    PLAN_STEP("plan step", "plan step", "openehr", "536"),

    TIME_OUT("time out", "time out", "openehr", "549"),

    NOTIFY_CANCELLED("notify cancelled", "notify cancelled", "openehr", "552"),

    POSTPONED_STEP("postponed step", "postponed step", "openehr", "542"),

    ABORT("abort", "abort", "openehr", "547");

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
