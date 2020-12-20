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

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.support.identification.ObjectId;
import java.util.Map;
import java.util.Optional;

public class CompositionMarshalPostprocessor implements MarshalPostprocessor<Composition> {

  /** {@inheritDoc} */
  @Override
  public void process(String term, Composition rmObject, Map<String, Object> values) {
    values.put(
        term + PATH_DIVIDER + "_uid",
        Optional.of(rmObject).map(Locatable::getUid).map(ObjectId::getValue).orElse(null));
  }

  /** {@inheritDoc} */
  @Override
  public Class<Composition> getAssociatedClass() {
    return Composition.class;
  }
}
