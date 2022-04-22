/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller;

import com.nedap.archie.datetime.DateTimeParsers;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.time.*;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Map;
import java.util.Set;

public class DvDateRMUnmarshaller extends AbstractRMUnmarshaller<DvDate> {

  @Override
  public Class<DvDate> getAssociatedClass() {
    return DvDate.class;
  }

  @Override
  public void handle(
      String currentTerm,
      DvDate rmObject,
      Map<FlatPathDto, String> currentValues,
      Context<Map<FlatPathDto, String>> context,
      Set<String> consumedPaths) {

    setValue(
        currentTerm,
        null,
        currentValues,
        s -> {
          if ("now".equals(s)) {
            rmObject.setValue(LocalDate.now());
          } else if (s != null) {
            Temporal temp = DateTimeParsers.parseDateValue(s);
            rmObject.setValue(temp);
          }
        },
        String.class,
        consumedPaths);
  }
}
