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

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.support.identification.HierObjectId;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class CompositionUnmarshalPostprocessor extends AbstractUnmarshalPostprocessor<Composition> {

  /** {@inheritDoc} Unmarshalls {@link Composition#setUid} */
  @Override
  public void process(String term, Composition rmObject, Map<String, String> values) {
    String strip = StringUtils.strip(values.get(term + PATH_DIVIDER + "_uid") + "", "\"");
    if (StringUtils.isNotBlank(strip)) {
      rmObject.setUid(new HierObjectId(strip));
    }
    consumedPath.add(term + PATH_DIVIDER + "_uid");
  }

  /** {@inheritDoc} */
  @Override
  public Class<Composition> getAssociatedClass() {
    return Composition.class;
  }
}
