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

package org.ehrbase.serialisation.flatencoding.std.marshal.postprocessor;

import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.generic.PartyIdentified;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.PartyIdentifiedStdConfig;
import org.ehrbase.serialisation.walker.Context;

import java.util.Map;
import java.util.stream.IntStream;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class EntryMarshalPostprocessor extends AbstractMarshalPostprocessor<Entry> {

  /** {@inheritDoc} Adds the encoding information */
  @Override
  public void process(
      String term,
      Entry rmObject,
      Map<String, Object> values,
      Context<Map<String, Object>> context) {
    values.put(term + PATH_DIVIDER + "encoding|code", "UTF-8");
    values.put(term + PATH_DIVIDER + "encoding|terminology", "IANA_character-sets");

    if (rmObject.getProvider() != null) {

      PartyIdentifiedStdConfig partyIdentifiedStdConfig = new PartyIdentifiedStdConfig();

      values.putAll(
          partyIdentifiedStdConfig.buildChildValues(
              term + PATH_DIVIDER + "_provider", (PartyIdentified) rmObject.getProvider(), null));
    }

    if (rmObject.getOtherParticipations() != null) {
      IntStream.range(0, rmObject.getOtherParticipations().size())
          .forEach(
              i -> {
                callMarshal(
                    term,
                    "_other_participation:" + i,
                    rmObject.getOtherParticipations().get(i),
                    values,
                    context,
                    context.getNodeDeque().peek().findChildById("participation").orElse(null));
                callPostprocess(
                    term,
                    "_other_participation:" + i,
                    rmObject.getOtherParticipations().get(i),
                    values,
                    context,
                    context.getNodeDeque().peek().findChildById("participation").orElse(null));
              });
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<Entry> getAssociatedClass() {
    return Entry.class;
  }
}
