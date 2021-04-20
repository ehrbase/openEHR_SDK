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
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.commons.collections4.CollectionUtils;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;

public abstract class AbstractValueInserter<T extends RMObject> implements DefaultValueInserter<T> {

  protected boolean isEmpty(Object rmObject) {
    if (rmObject == null) {
      return false;
    }
    if (rmObject instanceof CodePhrase) {
      return ((CodePhrase) rmObject).getCodeString() == null;
    }

    if (rmObject instanceof DvCodedText) {
      return ((DvCodedText) rmObject).getValue() == null;
    }

    if (rmObject instanceof DvText) {
      return ((DvText) rmObject).getValue() == null;
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

  protected PartyIdentified buildPartyIdentified(
      DefaultValues defaultValues,
      DefaultValuePath<String> name,
      DefaultValuePath<String> id,
      PartyProxy partyProxy) {

    if (partyProxy == null || !PartyIdentified.class.isAssignableFrom(partyProxy.getClass())) {
      partyProxy = new PartyIdentified();
    }

    if (defaultValues.containsDefaultValue(name)) {

      ((PartyIdentified) partyProxy).setName(defaultValues.getDefaultValue(name));
    }
    if (defaultValues.containsDefaultValue(id)) {

      PartyRef partyRef = new PartyRef();
      partyRef.setNamespace(defaultValues.getDefaultValue(DefaultValuePath.ID_NAMESPACE));
      partyRef.setId(
          new GenericId(
              defaultValues.getDefaultValue(id),
              defaultValues.getDefaultValue(DefaultValuePath.ID_SCHEME)));
      partyProxy.setExternalRef(partyRef);
    }

    return (PartyIdentified) partyProxy;
  }
}
