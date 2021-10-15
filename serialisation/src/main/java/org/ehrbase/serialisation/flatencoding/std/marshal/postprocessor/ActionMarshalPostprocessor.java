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

import com.nedap.archie.rm.composition.Action;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.InstructionDetailsConfig;

import java.util.Map;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class ActionMarshalPostprocessor implements MarshalPostprocessor<Action> {

  private static final InstructionDetailsConfig INSTRUCTION_DETAILS_CONFIG =
      new InstructionDetailsConfig();

  /** {@inheritDoc} Adds the encoding information */
  @Override
  public void process(String term, Action rmObject, Map<String, Object> values) {

    if (rmObject.getInstructionDetails() != null) {

      values.putAll(
          INSTRUCTION_DETAILS_CONFIG.buildChildValues(
              term + PATH_DIVIDER + "_instruction_details",
              rmObject.getInstructionDetails(),
              null));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<Action> getAssociatedClass() {
    return Action.class;
  }
}
