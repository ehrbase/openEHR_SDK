/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.classgenerator;

import java.util.HashMap;
import java.util.Map;

public class ClassGeneratorConfig {

  /** Defines if nodes which belong to are archetype but are single valued generate a new class. */
  private OptimizerSetting optimizerSetting = OptimizerSetting.NONE;
  /** Whether or not to generate null flavor fields for Elements. */
  private boolean addNullFlavor = false;

  /** Map to define Characters in the Node name to be replaced. */
  private final Map<Character, String> replaceChars = new HashMap<>();

  public OptimizerSetting getOptimizerSetting() {
    return optimizerSetting;
  }

  public void setOptimizerSetting(OptimizerSetting optimizerSetting) {
    this.optimizerSetting = optimizerSetting;
  }

  public boolean isAddNullFlavor() {
    return addNullFlavor;
  }

  public void setAddNullFlavor(boolean addNullFlavor) {
    this.addNullFlavor = addNullFlavor;
  }

  public Map<Character, String> getReplaceChars() {
    return replaceChars;
  }
}
