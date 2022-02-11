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

import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;

public class PartySelfRMUnmarshaller extends AbstractRMUnmarshaller<PartySelf> {
  @Override
  public Class<PartySelf> getAssociatedClass() {
    return PartySelf.class;
  }

  @Override
  public void handle(
      String currentTerm,
      PartySelf rmObject,
      Map<FlatPathDto, String> currentValues,
      Context<Map<FlatPathDto, String>> context,
      Set<String> consumedPaths) {

    rmObject.setExternalRef(new PartyRef());
    rmObject.getExternalRef().setType("PARTY");
    rmObject.getExternalRef().setId(new GenericId());
    setValue(
        currentTerm,
        "id",
        currentValues,
        rmObject.getExternalRef().getId()::setValue,
        String.class,
        consumedPaths);
    setValue(
        currentTerm,
        "id_scheme",
        currentValues,
        ((GenericId) rmObject.getExternalRef().getId())::setScheme,
        String.class,
        consumedPaths);
    setValue(
        currentTerm,
        "id_namespace",
        currentValues,
        rmObject.getExternalRef()::setNamespace,
        String.class,
        consumedPaths);

    // remove if not set
    if (rmObject.getExternalRef().getId() == null
        || StringUtils.isBlank(rmObject.getExternalRef().getId().getValue())) {
      rmObject.setExternalRef(null);
    }
  }
}
