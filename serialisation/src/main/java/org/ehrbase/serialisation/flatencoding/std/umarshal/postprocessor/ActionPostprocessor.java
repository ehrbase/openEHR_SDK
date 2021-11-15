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

import com.nedap.archie.rm.composition.Action;
import com.nedap.archie.rm.composition.InstructionDetails;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.InstructionDetailsRMUnmarshaller;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class ActionPostprocessor extends AbstractUnmarshalPostprocessor<Action> {

  private static final InstructionDetailsRMUnmarshaller INSTRUCTION_DETAILS_RM_UNMARSHALLER =
      new InstructionDetailsRMUnmarshaller();

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      Action rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

    Map<FlatPathDto, String> instructionDetails =
        values.entrySet().stream()
            .filter(e -> e.getKey().startsWith(term + PATH_DIVIDER + "_instruction_details"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    if (!instructionDetails.isEmpty()) {
      if (rmObject.getInstructionDetails() == null) {
        rmObject.setInstructionDetails(new InstructionDetails());
      }

      handleRmAttribute(
          term,
          rmObject.getInstructionDetails(),
          instructionDetails,
          consumedPaths,
          context,
          "instruction_details");
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<Action> getAssociatedClass() {
    return Action.class;
  }
}
