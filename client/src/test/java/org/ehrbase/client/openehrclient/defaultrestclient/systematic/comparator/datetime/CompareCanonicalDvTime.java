/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.client.openehrclient.defaultrestclient.systematic.comparator.datetime;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;

import java.text.Format;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class CompareCanonicalDvTime {

    boolean compareMillisecs = true;

    private static String canonicalJsonTemplate = "{\n" +
            "  \"_type\" : \"DV_TIME\",\n" +
            "  \"value\" : \"%s\"\n" +
            "}";

    private DvTime dvTime;

    public CompareCanonicalDvTime(DvTime dvTime){
        this.dvTime = dvTime;
    }

    //modifiers to support various test scenario
    public CompareCanonicalDvTime setCompareMillisecs(boolean compareMillisecs) {
        this.compareMillisecs = compareMillisecs;
        return this;
    }

    public Object isExpectedEqualToCanonicalUsing(DvTime referenceTime){
        //check each date/time part separately
        Format formatter = DateTimeFormatter.ISO_LOCAL_TIME.toFormat();
        DvTime actualDvTime = new DvTime(formatter.format(dvTime.getValue()));
        DvTime expectedDvTime = new DvTime(formatter.format(referenceTime.getValue()));
        ZoneOffset actualZoneOffset = ZoneOffset.from(dvTime.getValue());
        ZoneOffset expectedZoneOffset = ZoneOffset.from(referenceTime.getValue());

        //perform comparison of parts
        TimePartComparator.compare(actualDvTime, expectedDvTime);

        ZoneOffsetPartComparator.compare(actualZoneOffset, expectedZoneOffset);

        return null;

    }
}
