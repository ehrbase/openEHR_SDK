/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.serialisation.jsonencoding;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import java.time.temporal.Temporal;
import org.ehrbase.serialisation.DateTimeHelpers;

/**
 * Workaround for archie date-time parsing issues
 * @deprecated TODO remove when archie parser rejects invalid values and adjusts to match the spec
 */
@Deprecated
public class DateDeserializer extends AbstractDateTimeWorkaroundDeserializer<Temporal, DvDate> {

    @Override
    DvDate createInstance() {
        return new DvDate();
    }

    @Override
    Temporal parseValue(String valueString) {
        return DateTimeHelpers.parseDate(valueString);
    }
}
