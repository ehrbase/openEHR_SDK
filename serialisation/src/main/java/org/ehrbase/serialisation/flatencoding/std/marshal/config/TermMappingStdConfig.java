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

package org.ehrbase.serialisation.flatencoding.std.marshal.config;

import com.nedap.archie.rm.datavalues.TermMapping;
import org.ehrbase.serialisation.walker.Context;

import java.util.HashMap;
import java.util.Map;

public class TermMappingStdConfig extends AbstractsStdConfig<TermMapping> {

  private static final CodePhraseStdConfig CODE_PHRASE_STD_CONFIG = new CodePhraseStdConfig();
  private static final DvCodedTextStdConfiguration DV_CODED_TEXT_STD_CONFIGURATION =
      new DvCodedTextStdConfiguration();

  @Override
  /** {@inheritDoc} */
  public Map<String, Object> buildChildValues(
      String currentTerm, TermMapping rmObject, Context<Map<String, Object>> context) {

    Map<String, Object> result = new HashMap<>();

    addValue(result, currentTerm, "match", rmObject.getMatch());

    if (rmObject.getTarget() != null) {
      result.putAll(
          CODE_PHRASE_STD_CONFIG.buildChildValues(
              currentTerm + "/target", rmObject.getTarget(), context));
    }

    if (rmObject.getPurpose() != null) {
      result.putAll(
          DV_CODED_TEXT_STD_CONFIGURATION.buildChildValues(
              currentTerm + "/purpose", rmObject.getPurpose(), context));
    }
    return result;
  }

  @Override
  /** {@inheritDoc} */
  public Class<TermMapping> getAssociatedClass() {
    return TermMapping.class;
  }
}
