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

import com.nedap.archie.rm.composition.Entry;
import java.util.Map;

public class EntryMarshalPostprocessor implements MarshalPostprocessor<Entry> {

  /** {@inheritDoc} Adds the encoding information */
  @Override
  public void process(String term, Entry rmObject, Map<String, Object> values) {
    values.put(term + PATH_DIVIDER + "encoding|code", "UTF-8");
    values.put(term + PATH_DIVIDER + "encoding|terminology", "IANA_character-sets");
  }

  /** {@inheritDoc} */
  @Override
  public Class<Entry> getAssociatedClass() {
    return Entry.class;
  }
}
