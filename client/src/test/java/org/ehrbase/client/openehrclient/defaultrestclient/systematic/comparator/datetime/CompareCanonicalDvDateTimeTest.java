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
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CompareCanonicalDvDateTimeTest extends CanonicalUtil {

    private String jsonDvDateTimeRef = "{\n" +
            "  \"_type\" : \"DV_DATE_TIME\",\n" +
            "  \"value\" : \"2021-02-17T14:34:26,351+07:00\"\n" +
            "}";

    @Test
    public void testComparatorDvDateTime(){
        DvDateTime dvDateTime = (DvDateTime) toRmObject(jsonDvDateTimeRef, DvDateTime.class);

        assertThat(new CompareCanonicalDvDateTime(dvDateTime).isExpectedEqualToCanonicalUsing(dvDateTime)).isNull();
        assertThat(new CompareCanonicalDvDateTime(dvDateTime).setCompareDateOnly(true).isExpectedEqualToCanonicalUsing(dvDateTime)).isNull();
        assertThat(new CompareCanonicalDvDateTime(dvDateTime).setCompareDateOnly(false).isExpectedEqualToCanonicalUsing(dvDateTime)).isNull();
    }

    private String jsonDvTimeRef = "{\n" +
            "  \"_type\" : \"DV_TIME\",\n" +
            "  \"value\" : \"14:34:26,351+07:00\"\n" +
            "}";

    @Test
    public void testComparatorDvTime(){
        DvTime dvTime = (DvTime) toRmObject(jsonDvTimeRef, DvTime.class);

        assertThat(new CompareCanonicalDvTime(dvTime).isExpectedEqualToCanonicalUsing(dvTime)).isNull();
    }


    private String jsonDvDateRef = "{\n" +
            "  \"_type\" : \"DV_DATE\",\n" +
            "  \"value\" : \"2021-02-17\"\n" +
            "}";

    @Test
    public void testComparatorDvDate(){
        DvDate dvDate = (DvDate) toRmObject(jsonDvDateRef, DvDate.class);

        assertThat(new CompareCanonicalDvDate(dvDate).isExpectedEqualToCanonicalUsing(dvDate)).isNull();

    }
}