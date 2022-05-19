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

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.generic.PartyRelated;
import org.ehrbase.serialisation.flatencoding.std.marshal.StdFromCompositionWalker;
import org.ehrbase.serialisation.flatencoding.std.marshal.postprocessor.MarshalPostprocessor;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.model.WebTemplateNode;

import java.util.Map;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class PartyRelatedStdConfig extends AbstractsStdConfig<PartyRelated> {

  public static final PartyIdentifiedStdConfig PARTY_IDENTIFIED_STD_CONFIG =
      new PartyIdentifiedStdConfig();

  /** {@inheritDoc} */
  @Override
  public Class<PartyRelated> getAssociatedClass() {
    return PartyRelated.class;
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> buildChildValues(
      String currentTerm, PartyRelated rmObject, Context<Map<String, Object>> context) {

    // PartyRelated is also PartyIdentified
    Map<String, Object> values =
        PARTY_IDENTIFIED_STD_CONFIG.buildChildValues(currentTerm, rmObject, context);

    addRelationship(currentTerm, rmObject, context, values);
    return values;
  }

  static void addRelationship(
      String currentTerm,
      PartyRelated rmObject,
      Context<Map<String, Object>> context,
      Map<String, Object> values) {
    if (rmObject.getRelationship() != null) {

      WebTemplateNode subNode = FlatHelper.findOrBuildSubNode(context, "relationship");

      context.getNodeDeque().push(subNode);

      String newTerm = currentTerm + PATH_DIVIDER + "relationship";

      values.putAll(
          ((StdConfig)
                  StdFromCompositionWalker.findStdConfig(
                      ((RMObject) rmObject.getRelationship()).getClass()))
              .buildChildValues(newTerm, rmObject.getRelationship(), context));

      context.getNodeDeque().poll();

      context.getNodeDeque().push(subNode);

      StdFromCompositionWalker.findPostprocessors(rmObject.getClass())
          .forEach(
              p ->
                  ((MarshalPostprocessor) p)
                      .process(newTerm, rmObject.getRelationship(), values, context));

      context.getNodeDeque().poll();
    }
  }
}
