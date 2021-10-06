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

import com.nedap.archie.rm.composition.InstructionDetails;
import com.nedap.archie.rm.datavalues.DvURI;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.LocatableRef;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class InstructionDetailsRMUnmarshaller extends AbstractRMUnmarshaller<InstructionDetails> {

  /** {@inheritDoc} */
  @Override
  public Class<InstructionDetails> getAssociatedClass() {
    return InstructionDetails.class;
  }

  /** {@inheritDoc} */
  @Override
  public void handle(
      String currentTerm,
      InstructionDetails rmObject,
      Map<FlatPathDto, String> currentValues,
      Context<Map<FlatPathDto, String>> context,
      Set<String> consumedPaths) {

    setValue(
        currentTerm,
        "activity_id",
        currentValues,
        s -> rmObject.setActivityId(s),
        String.class,
        consumedPaths);

    rmObject.setInstructionId(new LocatableRef());
    rmObject.getInstructionId().setNamespace("local");
    rmObject.getInstructionId().setType("INSTRUCTION");

    setValue(
            currentTerm,
            "composition_uid",
            currentValues,
            s -> Optional.ofNullable(s).ifPresent(p -> rmObject.getInstructionId().setId(new HierObjectId(p))),
            String.class,
            consumedPaths);

    setValue(
            currentTerm,
            "path",
            currentValues,
            s -> rmObject.getInstructionId().setPath(s),
            String.class,
            consumedPaths);


  }
}
