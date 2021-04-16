/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.serialisation.walker.defaultvalues.defaultinserter;

import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;

public class EventContextValueInserter extends AbstractValueInserter<EventContext> {
  @Override
  public void insert(EventContext rmObject, DefaultValues defaultValues) {

    if (isEmpty(rmObject.getStartTime())
        && defaultValues.containsDefaultValue(DefaultValuePath.TIME)) {
      rmObject.setStartTime(new DvDateTime(defaultValues.getDefaultValue(DefaultValuePath.TIME)));
    }

    if (isEmpty(rmObject.getEndTime())
        && defaultValues.containsDefaultValue(DefaultValuePath.END_TIME)) {
      rmObject.setEndTime(new DvDateTime(defaultValues.getDefaultValue(DefaultValuePath.END_TIME)));
    }

    if (isEmpty(rmObject.getHealthCareFacility())) {
      rmObject.setHealthCareFacility(
          buildPartyIdentified(
              defaultValues,
              DefaultValuePath.HEALTHCARE_FACILITY_NAME,
              DefaultValuePath.HEALTHCARE_FACILITY_ID,
              rmObject.getHealthCareFacility()));
    }

    if (isEmpty(rmObject.getLocation())
        && defaultValues.containsDefaultValue(DefaultValuePath.LOCATION)) {
      rmObject.setLocation(defaultValues.getDefaultValue(DefaultValuePath.LOCATION));
    }

    if (isEmpty(rmObject.getSetting())
        && defaultValues.containsDefaultValue(DefaultValuePath.SETTING)) {
      Setting defaultValue = defaultValues.getDefaultValue(DefaultValuePath.SETTING);
      rmObject.setSetting(new DvCodedText(defaultValue.getValue(), defaultValue.toCodePhrase()));
    }
  }

  @Override
  public Class<EventContext> getAssociatedClass() {
    return EventContext.class;
  }
}
