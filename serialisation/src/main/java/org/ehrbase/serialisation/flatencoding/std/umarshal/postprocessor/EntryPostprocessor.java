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

package org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor;

import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartyRelated;
import com.nedap.archie.rm.generic.PartySelf;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.PartyIdentifiedRMUnmarshaller;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class EntryPostprocessor extends AbstractUnmarshalPostprocessor<Entry> {

  /** {@inheritDoc} */
  @Override
  public void process(String term, Entry rmObject, Map<FlatPathDto, String> values, Set<String> consumedPaths) {
    consumedPaths.add(term + PATH_DIVIDER + "encoding|code");
    consumedPaths.add(term + PATH_DIVIDER + "encoding|terminology");

    PartyProxy subject = rmObject.getSubject();
    if (subject == null
        || (subject instanceof PartyIdentified
            && ((PartyIdentified) subject).getName() == null
            && CollectionUtils.isEmpty(((PartyIdentified) subject).getIdentifiers())
            && subject.getExternalRef() == null
            && (!(subject instanceof PartyRelated)
                || ((PartyRelated) subject).getRelationship() == null
                || StringUtils.isEmpty(((PartyRelated) subject).getRelationship().getValue())))) {

      rmObject.setSubject(new PartySelf());
    }

    Map<FlatPathDto, String> providerList =
        values.entrySet().stream()
            .filter(e -> e.getKey().startsWith(term + PATH_DIVIDER + "_provider"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    if (!MapUtils.isEmpty(providerList)) {
      if (!(rmObject.getProvider() instanceof PartyIdentified)) {
        rmObject.setProvider(new PartyIdentified());
      }
      PartyIdentifiedRMUnmarshaller partyIdentifiedRMUnmarshaller =
          new PartyIdentifiedRMUnmarshaller();

      partyIdentifiedRMUnmarshaller.handle(
          term + PATH_DIVIDER + "_provider",
          (PartyIdentified) rmObject.getProvider(),
          providerList,
          null, consumedPaths);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<Entry> getAssociatedClass() {
    return Entry.class;
  }
}
