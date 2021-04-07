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

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;

public class CompositionValueInserter extends AbstractValueInserter<Composition> {
  @Override
  public void insert(Composition rmObject, DefaultValues defaultValues) {

    if (isEmpty(rmObject.getLanguage())
        && defaultValues.containsDefaultValue(DefaultValuePath.LANGUAGE)) {
      rmObject.setLanguage(defaultValues.getDefaultValue(DefaultValuePath.LANGUAGE).toCodePhrase());
    }

    if (isEmpty(rmObject.getTerritory())
        && defaultValues.containsDefaultValue(DefaultValuePath.TERRITORY)) {
      rmObject.setTerritory(
          defaultValues.getDefaultValue(DefaultValuePath.TERRITORY).toCodePhrase());
    }

    if (isEmpty(rmObject.getComposer())) {

      if (defaultValues.containsDefaultValue(DefaultValuePath.COMPOSER_SELF)) {
        rmObject.setComposer(new PartySelf());
      }

      if (defaultValues.containsDefaultValue(DefaultValuePath.COMPOSER_NAME)) {

        if (rmObject.getComposer() == null
            || !PartyIdentified.class.isAssignableFrom(rmObject.getComposer().getClass())) {
          rmObject.setComposer(new PartyIdentified());
        }

        ((PartyIdentified) rmObject.getComposer())
            .setName(defaultValues.getDefaultValue(DefaultValuePath.COMPOSER_NAME));
      }
      if (defaultValues.containsDefaultValue(DefaultValuePath.COMPOSER_ID)) {
        if (rmObject.getComposer() == null
            || !PartyIdentified.class.isAssignableFrom(rmObject.getComposer().getClass())) {
          rmObject.setComposer(new PartyIdentified());
        }
        PartyRef partyRef = new PartyRef();
        partyRef.setNamespace(defaultValues.getDefaultValue(DefaultValuePath.ID_NAMESPACE));
        partyRef.setId(
            new GenericId(
                defaultValues.getDefaultValue(DefaultValuePath.COMPOSER_ID),
                defaultValues.getDefaultValue(DefaultValuePath.ID_SCHEME)));
        rmObject.getComposer().setExternalRef(partyRef);
      }
    }
  }

  @Override
  public Class<Composition> getAssociatedClass() {
    return Composition.class;
  }
}
