/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.conformance_test.extern.tests;

import care.better.platform.web.template.InfinnityTest;

public class InfinnityTestOverwrite extends InfinnityTest {

  @Override
  /*
  Test count  of validation error messages with is not part of the spec
   */
  public void validationErrorInfinnityTest() throws Exception {}

  @Override
  /*
  Test count  of validation error messages with is not part of the spec
   */
  public void infinnityTemplateMismatch0_12() throws Exception {}

  @Override
  /*
  Test count  of validation error messages with is not part of the spec
   */
  public void infinnityTemplateMismatch1_17() throws Exception {}

  @Override
  /*
  see https://jira.vitagroup.ag/browse/PEM-492
  */
  public void ru354() throws Exception {}
}
