/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.serialisation.walker.defaultvalues.defaultinserter;

import com.nedap.archie.rm.composition.Instruction;
import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;

public class InstructionValueInserter extends AbstractValueInserter<Instruction> {

  @Override
  public void insert(Instruction rmObject, DefaultValues defaultValues) {

    if (isEmpty(rmObject.getNarrative())
        && defaultValues.containsDefaultValue(DefaultValuePath.INSTRUCTION_NARRATIVE)) {

      rmObject.setNarrative(
          new DvText(defaultValues.getDefaultValue(DefaultValuePath.INSTRUCTION_NARRATIVE)));
    }
  }

  @Override
  public Class<Instruction> getAssociatedClass() {
    return Instruction.class;
  }
}
