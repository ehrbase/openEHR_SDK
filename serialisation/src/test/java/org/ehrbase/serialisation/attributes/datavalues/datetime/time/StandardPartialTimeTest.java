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

package org.ehrbase.serialisation.attributes.datavalues.datetime.time;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import junit.framework.TestCase;
import org.junit.Test;

public class StandardPartialTimeTest extends TestCase {

  @Test
  public void testPartial1() {

    DvTime dvTime = new DvTime("10:10+02:00");
    assertTrue(new StandardPartialTime(dvTime).isNonCompactIS8601Representation());
    assertTrue(new StandardPartialTime(dvTime).ishhmm());
    assertFalse(new StandardPartialTime(dvTime).ishhmmssfff());
  }
}
