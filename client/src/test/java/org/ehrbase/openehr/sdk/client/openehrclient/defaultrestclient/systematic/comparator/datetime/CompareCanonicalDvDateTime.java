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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.systematic.comparator.datetime;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import java.text.Format;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class CompareCanonicalDvDateTime {

    boolean compareDateOnly = false;
    boolean compareMillisecs = true;

    private static String canonicalJsonTemplate =
            "{\n" + "  \"_type\" : \"DV_DATE_TIME\",\n" + "  \"value\" : \"%s\"\n" + "}";

    private DvDateTime dvDateTime;

    public CompareCanonicalDvDateTime(DvDateTime dvDateTime) {
        this.dvDateTime = dvDateTime;
    }

    // modifiers to support various test scenario

    public CompareCanonicalDvDateTime setCompareDateOnly(boolean compareDateOnly) {
        this.compareDateOnly = compareDateOnly;
        return this;
    }

    public CompareCanonicalDvDateTime setCompareMillisecs(boolean compareMillisecs) {
        this.compareMillisecs = compareMillisecs;
        return this;
    }

    public Object isExpectedEqualToCanonicalUsing(DvDateTime referenceDateTime) {
        // check each date/time part separately
        Format formatter = DateTimeFormatter.ISO_LOCAL_DATE.toFormat();
        DvDate actualDvDate = new DvDate(formatter.format(dvDateTime.getValue()));
        DvDate expectedDvDate = new DvDate(formatter.format(referenceDateTime.getValue()));
        formatter = DateTimeFormatter.ISO_LOCAL_TIME.toFormat();
        DvTime actualDvTime = new DvTime(formatter.format(dvDateTime.getValue()));
        DvTime expectedDvTime = new DvTime(formatter.format(referenceDateTime.getValue()));
        ZoneOffset actualZoneOffset = ZoneOffset.from(dvDateTime.getValue());
        ZoneOffset expectedZoneOffset = ZoneOffset.from(referenceDateTime.getValue());

        // perform comparison of parts
        DatePartComparator.compare(actualDvDate, expectedDvDate);

        if (!compareDateOnly) TimePartComparator.compare(actualDvTime, expectedDvTime);

        ZoneOffsetPartComparator.compare(actualZoneOffset, expectedZoneOffset);

        // compare DvDateAttributes if any

        return null;
    }
}
