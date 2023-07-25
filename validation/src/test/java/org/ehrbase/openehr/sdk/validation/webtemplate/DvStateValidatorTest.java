/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation.webtemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvState;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.junit.jupiter.api.Test;

/**
 *
 */
class DvStateValidatorTest extends AbstractRMObjectValidatorTest {

    private final DvStateValidator validator = new DvStateValidator();

    @Test
    void testValidate() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_state.json");

        var dvState = new DvState();
        dvState.setValue(new DvCodedText("Test", new CodePhrase(new TerminologyId("local"), "at0010")));

        var result = validator.validate(dvState, node);
        assertTrue(result.isEmpty());
    }

    @Test
    void testValidate_List() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_state_codedtext.json");

        var dvState = new DvState();
        dvState.setValue(new DvCodedText("First", new CodePhrase(new TerminologyId("local"), "at0028")));
        var result = validator.validate(dvState, node);
        assertTrue(result.isEmpty());

        dvState.setValue(new DvCodedText("Test", new CodePhrase(new TerminologyId("local"), "at0010")));
        result = validator.validate(dvState, node);
        assertEquals(1, result.size());
    }
}
