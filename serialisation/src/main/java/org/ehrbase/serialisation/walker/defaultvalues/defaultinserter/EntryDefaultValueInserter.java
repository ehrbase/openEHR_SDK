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

import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.support.identification.GenericId;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;

import java.util.Objects;

public class EntryDefaultValueInserter extends AbstractValueInserter<Entry> {
  @Override
  public void insert(Entry rmObject, DefaultValues defaultValues) {

    if (isEmpty(rmObject.getLanguage())
        && defaultValues.getDefaultValue(DefaultValuePath.LANGUAGE) != null) {
      rmObject.setLanguage(defaultValues.getDefaultValue(DefaultValuePath.LANGUAGE).toCodePhrase());
    }
    if (isEmpty(rmObject.getProvider())) {
      rmObject.setProvider(
          buildPartyIdentified(
              defaultValues,
              DefaultValuePath.PROVIDER_NAME,
              DefaultValuePath.PROVIDER_ID,
              rmObject.getProvider()));
    }

    if (isEmpty(rmObject.getOtherParticipations())
        && defaultValues.containsDefaultValue(DefaultValuePath.PARTICIPATION)) {
      rmObject.setOtherParticipations(
          defaultValues.getDefaultValue(DefaultValuePath.PARTICIPATION));
    }
    if (rmObject.getOtherParticipations() != null) {
      rmObject.getOtherParticipations().stream()
          .map(Participation::getPerformer)
          .map(PartyProxy::getExternalRef)
          .filter(Objects::nonNull)
          .filter(ref -> ref.getId() != null)
          .forEach(
              ref -> {
                if (ref.getNamespace() == null) {
                  ref.setNamespace(defaultValues.getDefaultValue(DefaultValuePath.ID_NAMESPACE));
                }
                if (ref.getId() instanceof GenericId && ref.getNamespace() == null) {
                  ((GenericId) ref.getId())
                      .setScheme(defaultValues.getDefaultValue(DefaultValuePath.ID_SCHEME));
                }
              });
    }

    if (isEmpty(rmObject.getWorkflowId())) {
      rmObject.setWorkflowId(defaultValues.getDefaultValue(DefaultValuePath.WORKFLOW_ID));
    }
    if (rmObject.getWorkflowId() != null) {
      if (rmObject.getWorkflowId().getNamespace() == null) {
        rmObject
            .getWorkflowId()
            .setNamespace(defaultValues.getDefaultValue(DefaultValuePath.ID_NAMESPACE));
      }
      if (rmObject.getWorkflowId().getId() instanceof GenericId
          && ((GenericId) rmObject.getWorkflowId().getId()).getScheme() == null) {
        ((GenericId) rmObject.getWorkflowId().getId())
            .setScheme(defaultValues.getDefaultValue(DefaultValuePath.ID_SCHEME));
      }
    }
    if (rmObject.getProvider() != null) {
      addSchemeNamespace(rmObject.getProvider().getExternalRef(), defaultValues);
    }
  }

  @Override
  public Class<Entry> getAssociatedClass() {
    return Entry.class;
  }
}
