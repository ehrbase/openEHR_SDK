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

package org.ehrbase.serialisation.flatencoding.std.marshal.postprocessor;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;

import java.util.Map;

public class CompositionPostprocessor extends AbstractMarshalPostprocessor<Composition> {

  /** {@inheritDoc} Adds the encoding information */
  @Override
  public void process(
      String term,
      Composition rmObject,
      Map<String, Object> values,
      Context<Map<String, Object>> context) {

    if (rmObject.getComposer() instanceof PartySelf) {

      addValue(values, "ctx/" + DefaultValuePath.COMPOSER_SELF.getPath(), null, true);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<Composition> getAssociatedClass() {
    return Composition.class;
  }
}
