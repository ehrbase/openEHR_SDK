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

package org.ehrbase.serialisation.flatencoding.std.marshal.config;

import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import org.ehrbase.serialisation.walker.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PartySelfStdConfig extends AbstractsStdConfig<PartySelf> {

  /** {@inheritDoc} */
  @Override
  public Class<PartySelf> getAssociatedClass() {
    return PartySelf.class;
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> buildChildValues(
      String currentTerm, PartySelf rmObject, Context<Map<String, Object>> context) {

    Map<String, Object> result = new HashMap<>();

    addValue(
        result,
        currentTerm,
        "id",
        Optional.of(rmObject)
            .map(PartyProxy::getExternalRef)
            .map(ObjectRef::getId)
            .map(ObjectId::getValue)
            .orElse(null));
    addValue(
        result,
        currentTerm,
        "id_namespace",
        Optional.of(rmObject)
            .map(PartyProxy::getExternalRef)
            .map(ObjectRef::getNamespace)
            .orElse(null));

    Optional.of(rmObject)
        .map(PartyProxy::getExternalRef)
        .map(ObjectRef::getId)
        .filter(i -> i.getClass().equals(GenericId.class))
        .map(GenericId.class::cast)
        .ifPresent(genericId -> addValue(result, currentTerm, "id_scheme", genericId.getScheme()));

    return result;
  }
}
