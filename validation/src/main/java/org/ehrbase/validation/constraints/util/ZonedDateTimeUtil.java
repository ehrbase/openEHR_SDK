/*
 * Copyright (c) 2019 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.validation.constraints.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.ehrbase.validation.CompositionValidator;

/**
 * @deprecated as of release 1.7, in favor of {@link CompositionValidator}
 */
@Deprecated(since = "1.7")
public class ZonedDateTimeUtil {

    /**
     * @return the inferior limit for zoned date time
     */
    public ZonedDateTime min() {
        return ZonedDateTime.ofInstant(Instant.MIN, ZoneId.systemDefault());
    }

    /**
     * @return the superior limit for zoned date time
     */
    public ZonedDateTime max() {
        return ZonedDateTime.ofInstant(Instant.MAX, ZoneId.systemDefault());
    }
}
