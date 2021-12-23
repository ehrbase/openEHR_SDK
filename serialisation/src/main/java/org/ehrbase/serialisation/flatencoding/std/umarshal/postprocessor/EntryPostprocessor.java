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
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.PartyIdentifiedRMUnmarshaller;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ehrbase.serialisation.walker.FlatHelper.*;
import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class EntryPostprocessor extends AbstractUnmarshalPostprocessor<Entry> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      Entry rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {
    consumedPaths.add(term + PATH_DIVIDER + "encoding|code");
    consumedPaths.add(term + PATH_DIVIDER + "encoding|terminology");

    Map<FlatPathDto, String> subjectValues = FlatHelper.filter(values, term + "/subject", false);

    if (!subjectValues.isEmpty()) {

      if (rmObject.getSubject() == null) {
        // If it was PartyRelated it would be set by now do to the relationship  and if it was
        // PartySelf subjectValues would be empty
        rmObject.setSubject(new PartyIdentified());
      }

      callUnmarshal(
          term,
          "subject",
          rmObject.getSubject(),
          values,
          consumedPaths,
          context,
          context
              .getNodeDeque()
              .peek()
              .findChildById("subject")
              .orElse(buildDummyChild("subject", context.getNodeDeque().peek())));
    }

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
          null,
          consumedPaths);
    }

    Map<Integer, Map<String, String>> other =
        extractMultiValued(term, "_other_participation", values);

    other.values().stream()
        .map(Map::entrySet)
        .map(
            s ->
                s.stream()
                    .collect(
                        Collectors.toMap(
                            e ->
                                "ctx/"
                                    + DefaultValuePath.PARTICIPATION.getPath()
                                    + "_"
                                    + e.getKey().replace("identifiers_", "identifiers|"),
                            e -> StringUtils.wrap(e.getValue(), '"')))
                    .entrySet())
        .map(DefaultValues::buildParticipation)
        .forEach(rmObject::addOtherParticipant);
    consumeAllMatching(term + PATH_DIVIDER + "_other_participation", values, consumedPaths);

    Map<FlatPathDto, String> workflowIdValues = filter(values, term + "/_work_flow_id", false);
    if (!workflowIdValues.isEmpty()) {
      ObjectRef<GenericId> ref = new ObjectRef<>();
      ref.setId(new GenericId());
      rmObject.setWorkflowId(ref);
      setValue(
          term + "/_work_flow_id",
          "id",
          workflowIdValues,
          s -> ref.getId().setValue(s),
          String.class,
          consumedPaths);
      setValue(
          term + "/_work_flow_id",
          "id_scheme",
          workflowIdValues,
          s -> ref.getId().setScheme(s),
          String.class,
          consumedPaths);
      setValue(
          term + "/_work_flow_id",
          "namespace",
          workflowIdValues,
          ref::setNamespace,
          String.class,
          consumedPaths);
      setValue(
          term + "/_work_flow_id",
          "type",
          workflowIdValues,
          ref::setType,
          String.class,
          consumedPaths);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<Entry> getAssociatedClass() {
    return Entry.class;
  }
}
