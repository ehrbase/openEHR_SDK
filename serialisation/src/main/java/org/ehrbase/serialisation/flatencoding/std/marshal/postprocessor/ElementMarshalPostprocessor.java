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

import com.nedap.archie.rm.datastructures.Element;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.DvCodedTextStdConfiguration;

import java.util.Map;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class ElementMarshalPostprocessor implements MarshalPostprocessor<Element> {

  private static final DvCodedTextStdConfiguration DV_CODED_TEXT_STD_CONFIGURATION =
      new DvCodedTextStdConfiguration();

  /** {@inheritDoc} Adds the encoding information */
  @Override
  public void process(String term, Element rmObject, Map<String, Object> values) {

    if (rmObject.getNullFlavour() != null) {

      values.putAll(
          DV_CODED_TEXT_STD_CONFIGURATION.buildChildValues(
              term + PATH_DIVIDER + "_null_flavour", rmObject.getNullFlavour(), null));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<Element> getAssociatedClass() {
    return Element.class;
  }
}
