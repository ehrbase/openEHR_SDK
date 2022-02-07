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

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.encapsulated.DvEncapsulated;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;

import static org.ehrbase.serialisation.walker.FlatHelper.filter;

public class DvEncapsulatedPostprocessor extends AbstractUnmarshalPostprocessor<DvEncapsulated> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      DvEncapsulated rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

    Map<FlatPathDto, String> languageValues = filter(values, term + "/_language", false);

    if(!languageValues.isEmpty()){
      rmObject.setLanguage(new CodePhrase());
      handleRmAttribute(term,rmObject.getLanguage(),languageValues,consumedPaths,context,"language");
    }

    Map<FlatPathDto, String> charsetValues = filter(values, term + "/_charset", false);

    if(!charsetValues.isEmpty()){
      rmObject.setCharset(new CodePhrase());
      handleRmAttribute(term,rmObject.getCharset(),charsetValues,consumedPaths,context,"charset");
    }

  }

  /** {@inheritDoc} */
  @Override
  public Class<DvEncapsulated> getAssociatedClass() {
    return DvEncapsulated.class;
  }
}
