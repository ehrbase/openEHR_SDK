package org.ehrbase.client.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Inherited
public @interface OptionFor {
    public static final String VALUE = "value";

    String value();
}
