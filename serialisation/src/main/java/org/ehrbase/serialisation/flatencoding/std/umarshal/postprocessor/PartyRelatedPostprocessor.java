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

import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyRelated;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;

import static org.ehrbase.serialisation.walker.FlatHelper.buildDummyChild;

public class PartyRelatedPostprocessor extends AbstractUnmarshalPostprocessor<PartyRelated> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      PartyRelated rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

    Map<FlatPathDto, String> relationshipValues =
        FlatHelper.filter(values, term + "/relationship", false);

    if (!relationshipValues.isEmpty()) {
      rmObject.setRelationship(new DvCodedText());
      callUnmarshal(
          term,
          "relationship",
          rmObject.getRelationship(),
          relationshipValues,
          consumedPaths,
          context,
          context
              .getNodeDeque()
              .peek()
              .findChildById("relationship")
              .orElse(buildDummyChild("relationship", context.getNodeDeque().peek())));
      calPostProcess(
          term,
          "relationship",
          rmObject.getRelationship(),
          relationshipValues,
          consumedPaths,
          context,
          context
              .getNodeDeque()
              .peek()
              .findChildById("relationship")
              .orElse(buildDummyChild("relationship", context.getNodeDeque().peek())));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<PartyRelated> getAssociatedClass() {
    return PartyRelated.class;
  }
}
