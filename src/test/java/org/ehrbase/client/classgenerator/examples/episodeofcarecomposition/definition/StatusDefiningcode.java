package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum StatusDefiningcode implements EnumValueSet {
    PLANNED("planned", "*", "local", "at0004"),

    ONHOLD("onhold", "*", "local", "at0007"),

    ENTEREDINERROR("entered-in-error", "*", "local", "at0010"),

    ACTIVE("active", "*", "local", "at0006"),

    FINISHED("finished", "*", "local", "at0008"),

    CANCELLED("cancelled", "*", "local", "at0009"),

    WAITLIST("waitlist", "*", "local", "at0005");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    StatusDefiningcode(String value, String description, String terminologyId, String code) {
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
