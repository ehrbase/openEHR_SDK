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

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.serialisation.util.OpenEHRDateTimeParseUtils;

/**
 * Workaround for archie date-time parsing issues
 */
public class DateTimeDeserializer extends AbstractDvTemporalDeserializer<TemporalAccessor, DvDateTime> {

    @Override
    DvDateTime createInstance() {
        return new DvDateTime();
    }

    @Override
    TemporalAccessor parseValue(String valueString) {
        return OpenEHRDateTimeParseUtils.parseDateTime(valueString);
    }
}
