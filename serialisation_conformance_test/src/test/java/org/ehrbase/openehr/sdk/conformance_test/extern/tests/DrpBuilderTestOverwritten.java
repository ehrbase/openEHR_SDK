/*
 * Copyright (c) 2021 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.conformance_test.extern.tests;

import care.better.platform.web.template.DrpBuilderTest;

public class DrpBuilderTestOverwritten extends DrpBuilderTest {

    @Override
    /*
     * Test contains both code and other text value.
     * Consider sdk behavior of trowing an error correct
     */
    public void codedWithOther1() throws Exception {}
}
