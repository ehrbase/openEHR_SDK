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

package org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor;

import com.nedap.archie.rm.composition.CareEntry;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;

public class CareEntryPostprocessor extends AbstractUnmarshalPostprocessor<CareEntry> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      CareEntry rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

    Map<FlatPathDto, String> guidelineValues = FlatHelper.filter(values, term + "/_guideline_id", false);

    if (!guidelineValues.isEmpty()) {
      ObjectRef<GenericId> guidelineId = new ObjectRef<>();
      guidelineId.setId(new GenericId());

      setValue(term+"/_guideline_id","type",guidelineValues,guidelineId::setType,String.class,consumedPaths);
      setValue(term+"/_guideline_id","namespace",guidelineValues,guidelineId::setNamespace,String.class,consumedPaths);
      setValue(term+"/_guideline_id","id",guidelineValues, s -> guidelineId.getId().setValue(s),String.class,consumedPaths);
      setValue(term+"/_guideline_id","id_scheme",guidelineValues, s -> guidelineId.getId().setScheme(s),String.class,consumedPaths);
      rmObject.setGuidelineId(guidelineId);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<CareEntry> getAssociatedClass() {
    return CareEntry.class;
  }
}
