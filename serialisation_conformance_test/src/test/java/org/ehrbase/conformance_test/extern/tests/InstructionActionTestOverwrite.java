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

import care.better.platform.web.template.InstructionActionTest;

public class InstructionActionTestOverwrite extends InstructionActionTest {

    @Override
    /*
    see https://jira.vitagroup.ag/browse/PEM-492
    */
    public void instructionDetailsWithIndexOnRmPath() throws Exception {
        super.instructionDetailsWithIndexOnRmPath();
    }

    @Override
    /*
    see https://jira.vitagroup.ag/browse/PEM-492
    */
    public void action() throws Exception {
        super.action();
    }

    @Override
    /*
    see https://jira.vitagroup.ag/browse/PEM-492
    */
    public void instructionDetailsWithUUID() throws Exception {
        super.instructionDetailsWithUUID();
    }

    @Override
    /*
    see https://jira.vitagroup.ag/browse/PEM-492
    */
    public void instructionDetailsWithIndex() throws Exception {
        super.instructionDetailsWithIndex();
    }

    @Override
    /*
    see https://jira.vitagroup.ag/browse/PEM-492
    */
    public void singleInstructionDetails() throws Exception {
        super.singleInstructionDetails();
    }

    @Override
    /*
    see https://jira.vitagroup.ag/browse/PEM-492
    */
    public void instructionDetailsWithUUIDOnRmPath() throws Exception {
        super.instructionDetailsWithUUIDOnRmPath();
    }

    @Override
    /*
    see https://jira.vitagroup.ag/browse/PEM-492
    */
    public void multipleActivities() throws Exception {
        super.multipleActivities();
    }

    @Override
    /*
    see https://jira.vitagroup.ag/browse/PEM-492
    */
    public void multipleActivitiesPathAndIndex() throws Exception {
        super.multipleActivitiesPathAndIndex();
    }

    @Override
    /*
    better seams to generate a random uuid for the reference. I prefer to throw a validation error here.
     */
    public void invalidState() throws Exception {
        super.invalidState();
    }

    @Override
    /*
    the sdk expects nulls to be handled by not sending the path.
    */
    public void emptyInstruction() throws Exception {
        super.emptyInstruction();
    }
}
