/*
 * Copyright (c) 2022 vitasystems GmbH.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.validation.terminology;

import static org.junit.Assert.assertEquals;

import com.nedap.archie.rm.datatypes.CodePhrase;
import org.junit.Test;

public class ClassBindingTest {

    @Test
    public void bindingTest() {
        assertEquals(
                CodePhrase.class, new org.ehrbase.openehr.sdk.validation.terminology.validator.CodePhrase().rmClass());
    }
}
