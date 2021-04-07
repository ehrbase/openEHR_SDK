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

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.PartyIdentified;
import org.apache.commons.collections4.CollectionUtils;

public abstract class AbstractValueInserter<T extends RMObject> implements DefaultValueInserter<T> {

  protected boolean isEmpty(Object rmObject) {
    if (rmObject == null) {
      return false;
    }
    if (rmObject instanceof CodePhrase) {
      return ((CodePhrase) rmObject).getCodeString() == null;
    }
    if (rmObject instanceof PartyIdentified) {
      return ((PartyIdentified) rmObject).getName() == null
          && CollectionUtils.isEmpty(((PartyIdentified) rmObject).getIdentifiers());
    }

    if (rmObject instanceof DvDateTime) {
      return ((DvDateTime) rmObject).getValue() == null;
    }

    if (rmObject instanceof DvParsable) {
      return ((DvParsable) rmObject).getValue() == null
          && ((DvParsable) rmObject).getFormalism() == null;
    }
    return false;
  }
}
