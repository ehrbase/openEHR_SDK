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

import care.better.platform.web.template.IspekBuilderTest;

public class IspekBuilderTestOverwrite extends IspekBuilderTest {

  @Override
  /** updating a raw composition is not supported */
  public void compositionUpdate() throws Exception {}

  @Override
  /** updating a raw composition is not supported */
  public void compositionUpdateFailed() throws Exception {}

  @Override
  /** updating a raw composition is not supported */
  public void compositionWithInstructionsAndActionsUpdateFailed() throws Exception {}

  @Override
  /*
  Contains calculation of type and Numerator in a way which is not clear to implement see  https://jira.vitagroup.ag/browse/PEM-526
  */
  public void vitalsSingle() throws Exception {}

  @Override
  /*
  Contains calculation of type and Numerator in a way which is not clear to implement  see https://jira.vitagroup.ag/browse/PEM-526
  */
  public void vitalsSingle1() throws Exception {}

  @Override
  /** Test stuff which is not path of the spec. */
  public void vitalsSingle2() throws Exception {}

  @Override
  /*
  Test count  of validation error messages with is not part of the spec
  */
  public void vitalsFixedflatComposition() throws Exception {
    super.vitalsFixedflatComposition();
  }

  @Override
  /*
  see PEM-534
  */
  public void perinatal() throws Exception {}
}
