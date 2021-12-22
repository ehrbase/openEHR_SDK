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

package org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.TermMapping;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class TermMappingRMUnmarshaller extends AbstractRMUnmarshaller<TermMapping> {

  private static final CodePhraseRMUnmarshaller CODE_PHRASE_RM_UNMARSHALLER =
      new CodePhraseRMUnmarshaller();
  private static final DvCodedTextRMUnmarshaller DV_CODED_TEXT_RM_UNMARSHALLER =
      new DvCodedTextRMUnmarshaller();

  /** {@inheritDoc} */
  @Override
  public Class<TermMapping> getAssociatedClass() {
    return TermMapping.class;
  }

  /** {@inheritDoc} */
  @Override
  public void handle(
      String currentTerm,
      TermMapping rmObject,
      Map<FlatPathDto, String> currentValues,
      Context<Map<FlatPathDto, String>> context,
      Set<String> consumedPaths) {

    setValue(
        currentTerm,
        "match",
        currentValues,
        s -> Optional.ofNullable(s).map(c -> c.charAt(0)).ifPresent(rmObject::setMatch),
        String.class,
        consumedPaths);

    Map<FlatPathDto, String> targetValues =
        FlatHelper.filter(currentValues, currentTerm + "/target", false);

    if (!targetValues.isEmpty()) {
      rmObject.setTarget(new CodePhrase());
      CODE_PHRASE_RM_UNMARSHALLER.handle(
          currentTerm + "/target", rmObject.getTarget(), targetValues, context, consumedPaths);
    }
    Map<FlatPathDto, String> purposeValues =
        FlatHelper.filter(currentValues, currentTerm + "/_purpose", false);

    if (!purposeValues.isEmpty()) {
      rmObject.setPurpose(new DvCodedText());
      DV_CODED_TEXT_RM_UNMARSHALLER.handle(
          currentTerm + "/_purpose", rmObject.getPurpose(), purposeValues, context, consumedPaths);
    }
  }
}
