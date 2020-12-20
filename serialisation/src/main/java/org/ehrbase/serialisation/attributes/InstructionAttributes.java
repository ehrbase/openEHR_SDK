/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.serialisation.attributes;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.TAG_NARRATIVE;

import com.nedap.archie.rm.composition.Instruction;
import java.util.Map;
import org.ehrbase.serialisation.dbencoding.CompositionSerializer;
import org.ehrbase.serialisation.dbencoding.ItemStack;

/** populate the attributes for RM Instruction (narrative) */
public class InstructionAttributes extends CareEntryAttributes {

  public InstructionAttributes(
      CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
    super(compositionSerializer, itemStack, map);
  }

  public Map<String, Object> toMap(Instruction instruction) {

    if (instruction.getNarrative() != null)
      map = toMap(TAG_NARRATIVE, instruction.getNarrative(), instruction.getName());

    map = super.toMap(instruction);

    return map;
  }
}
