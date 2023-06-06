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

import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.serialisation.util.OpenEHRDateTimeSerializationUtils;
import org.ehrbase.util.rmconstants.RmConstants;

/**
 * custom serializer delegating to a custom date-time formatter using '.' as decimal separator
 */
public class TimeSerializer extends AbstractDvTemporalSerializer<TemporalAccessor, DvTime> {

    @Override
    protected String typeName() {
        return RmConstants.DV_TIME;
    }

    @Override
    protected String format(TemporalAccessor toFormat) {
        return OpenEHRDateTimeSerializationUtils.formatTime(toFormat);
    }
}
