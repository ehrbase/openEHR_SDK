/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation.terminology;

import static com.nedap.archie.rmutil.InvariantUtil.ENGLISH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.ehrbase.openehr.sdk.terminology.TerminologyProvider.OPENEHR;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.openehr.sdk.validation.terminology.check.DvCodedTextCheck;
import org.junit.jupiter.api.Test;

class ItemValidatorTest {

    @Test
    void validate_valid() {
        ItemValidator itemValidator = new ItemValidator(new DvCodedTextCheck());

        DvCodedText dvCodedText =
                new DvCodedText("secondary allied health care", new CodePhrase(new TerminologyId(OPENEHR), "234"));

        assertThat(itemValidator.isValidatedRmObjectType(dvCodedText)).isTrue();

        assertDoesNotThrow(() -> itemValidator.validate("setting", dvCodedText, ENGLISH));
    }

    @Test
    void validate_invalid() {
        ItemValidator itemValidator = new ItemValidator(new DvCodedTextCheck());

        // unknown code: not present in the openehr terminology group for "setting"
        DvCodedText unknownCode = new DvCodedText("whatever", new CodePhrase(new TerminologyId(OPENEHR), "99999"));

        assertThatThrownBy(() -> itemValidator.validate("setting", unknownCode, ENGLISH))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("99999");

        // valid code but mismatched value text
        DvCodedText wrongValue = new DvCodedText("wrong label", new CodePhrase(new TerminologyId(OPENEHR), "234"));

        assertThatThrownBy(() -> itemValidator.validate("setting", wrongValue, ENGLISH))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("wrong label");
    }
}
