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

import java.text.Format;
import java.time.format.DateTimeFormatter;

public class CompareCanonicalDvDate {

    private static String canonicalJsonTemplate = "{\n" +
            "  \"_type\" : \"DV_DATE\",\n" +
            "  \"value\" : \"%s\"\n" +
            "}";

    private DvDate dvDate;

    public CompareCanonicalDvDate(DvDate dvDate){
        this.dvDate = dvDate;
    }

    public Object isExpectedEqualToCanonicalUsing(DvDate referenceDate){
        //check each date/time part separately
        Format formatter = DateTimeFormatter.ISO_LOCAL_DATE.toFormat();
        DvDate actualDvDate = new DvDate(formatter.format(dvDate.getValue()));
        DvDate expectedDvDate = new DvDate(formatter.format(referenceDate.getValue()));

        //perform comparison of parts
        DatePartComparator.compare(actualDvDate, expectedDvDate);

        return null;
    }
}
