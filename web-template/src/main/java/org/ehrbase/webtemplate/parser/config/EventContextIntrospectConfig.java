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

package org.ehrbase.webtemplate.parser.config;

import static org.ehrbase.terminology.client.terminology.TerminologyProvider.OPENEHR;

import com.nedap.archie.rm.composition.EventContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.ehrbase.terminology.client.terminology.TerminologyProvider;
import org.ehrbase.terminology.client.terminology.ValueSet;

public class EventContextIntrospectConfig implements RmIntrospectConfig {

  private static final Set<String> FIELDS =
      Stream.of(
              "startTime", "endTime", "location", "setting", "healthCareFacility", "participations")
          .collect(Collectors.toSet());

  @Override
  public Class getAssociatedClass() {
    return EventContext.class;
  }

  @Override
  public Set<String> getNonTemplateFields() {
    return FIELDS;
  }

  @Override
  public ValueSet findExternalValueSet(String fieldName) {
    switch (fieldName) {
      case "setting":
        return TerminologyProvider.findOpenEhrValueSet(OPENEHR, "setting");
      default:
        return ValueSet.EMPTY_VALUE_SET;
    }
  }
}
