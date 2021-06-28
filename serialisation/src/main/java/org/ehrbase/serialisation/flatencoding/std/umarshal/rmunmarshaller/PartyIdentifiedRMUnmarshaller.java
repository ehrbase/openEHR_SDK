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

import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.walker.Context;

import java.util.Map;
import java.util.stream.Collectors;

import static org.ehrbase.serialisation.walker.defaultvalues.DefaultValues.ATTRIBUTE_COLLECTOR;
import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class PartyIdentifiedRMUnmarshaller extends AbstractRMUnmarshaller<PartyIdentified> {
  @Override
  public Class<PartyIdentified> getAssociatedClass() {
    return PartyIdentified.class;
  }

  @Override
  public void handle(
      String currentTerm,
      PartyIdentified rmObject,
      Map<String, String> currentValues,
      Context<Map<String, String>> context) {
    setValue(currentTerm, "name", currentValues, rmObject::setName, String.class);
    rmObject.setExternalRef(new PartyRef());
    rmObject.getExternalRef().setId(new GenericId());
    setValue(
        currentTerm,
        "id",
        currentValues,
        rmObject.getExternalRef().getId()::setValue,
        String.class);
    setValue(
        currentTerm,
        "id_scheme",
        currentValues,
        ((GenericId) rmObject.getExternalRef().getId())::setScheme,
        String.class);
    setValue(
        currentTerm,
        "id_namespace",
        currentValues,
        rmObject.getExternalRef()::setNamespace,
        String.class);

    Map<Integer, Map<String, String>> identifiers =
        currentValues.entrySet().stream()
            .filter(
                s -> StringUtils.startsWith(s.getKey(), currentTerm + PATH_DIVIDER + "_identifier"))
            .collect(
                Collectors.groupingBy(
                    e -> {
                      String s =
                          StringUtils.substringBefore(
                              StringUtils.substringAfter(e.getKey(), ":"), "|");
                      return StringUtils.isBlank(s) ? 0 : Integer.parseInt(s);
                    },
                    ATTRIBUTE_COLLECTOR));

    rmObject.setIdentifiers(
        identifiers.values().stream().map(this::toId).collect(Collectors.toList()));
  }

  private DvIdentifier toId(Map<String, String> stringStringMap) {

    DvIdentifier identifier = new DvIdentifier();

    identifier.setId(stringStringMap.get("id"));
    if (identifier.getId() == null) {
      identifier.setId(stringStringMap.get(""));
    }

    identifier.setAssigner(stringStringMap.get("assigner"));
    identifier.setType(stringStringMap.get("type"));
    identifier.setIssuer(stringStringMap.get("issuer"));

    return identifier;
  }
}
