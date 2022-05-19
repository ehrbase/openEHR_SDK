/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.conformance_test.extern.tests;

import care.better.platform.web.template.SettersTest;

public class SettersTestOverwrite extends SettersTest {

    @Override
    /*
    Test error behavior which is not path of the spec
    */
    public void testProportionFailed1() throws Exception {}

    @Override
    /*
    Test error behavior which is not path of the spec
    */
    public void testProportionFailed2() throws Exception {}

    @Override
    /*
    see https://jira.vitagroup.ag/browse/CDR-143
    */
    public void testDate1() throws Exception {}

    @Override
    /*
    see https://jira.vitagroup.ag/browse/CDR-143
    */
    public void testDate2() throws Exception {}

    @Override
    /*
    see https://jira.vitagroup.ag/browse/CDR-143
    */
    public void testTime1() throws Exception {}

    @Override
    /*
    see https://jira.vitagroup.ag/browse/CDR-143
    */
    public void testTime() throws Exception {}

    @Override
    /*
    see https://jira.vitagroup.ag/browse/CDR-143
    */
    public void testTime2() throws Exception {}

    @Override
    /*
    see https://jira.vitagroup.ag/browse/CDR-143
    */
    public void testDateTime1() throws Exception {}

    @Override
    /*
    see https://jira.vitagroup.ag/browse/CDR-143
    */
    public void testDateTime2() throws Exception {}

    @Override
    /*
    Test error behavior which is not path of the spec
    */
    public void testMultimediaFailed1() throws Exception {}

    @Override
    /*
    Test error behavior which is not path of the spec
    */
    public void testMultimediaFailed2() throws Exception {}

    @Override
    /*
    see https://jira.vitagroup.ag/browse/CDR-34
    */
    public void testFixedValues() throws Exception {}
}
