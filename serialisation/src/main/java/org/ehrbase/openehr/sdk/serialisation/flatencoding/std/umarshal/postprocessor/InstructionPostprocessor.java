/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal.postprocessor;

import static org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.composition.Instruction;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

public class InstructionPostprocessor extends AbstractUnmarshalPostprocessor<Instruction> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term,
            Instruction rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        Map<FlatPathDto, String> wfDefinition = values.entrySet().stream()
                .filter(e -> e.getKey().startsWith(term + PATH_DIVIDER + "_wf_definition"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (!wfDefinition.isEmpty()) {
            rmObject.setWfDefinition(new DvParsable());
            handleRmAttribute(term, rmObject.getWfDefinition(), wfDefinition, consumedPaths, context, "wf_definition");
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<Instruction> getAssociatedClass() {
        return Instruction.class;
    }
}
