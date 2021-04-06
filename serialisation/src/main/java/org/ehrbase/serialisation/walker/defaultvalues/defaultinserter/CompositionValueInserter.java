/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.serialisation.walker.defaultvalues.defaultinserter;

import com.nedap.archie.rm.composition.Composition;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;

public class CompositionValueInserter implements DefaultValueInserter<Composition> {
  @Override
  public void insert(Composition rmObject, DefaultValues defaultValues) {

    if ((rmObject.getLanguage() == null || rmObject.getLanguage().getCodeString() == null)
        && defaultValues.getDefaultValue(DefaultValuePath.LANGUAGE) != null) {
      rmObject.setLanguage(defaultValues.getDefaultValue(DefaultValuePath.LANGUAGE).toCodePhrase());
    }

    if ((rmObject.getTerritory() == null || rmObject.getTerritory().getCodeString() == null)
        && defaultValues.getDefaultValue(DefaultValuePath.TERRITORY) != null) {
      rmObject.setTerritory(
          defaultValues.getDefaultValue(DefaultValuePath.TERRITORY).toCodePhrase());
    }
  }

  @Override
  public Class<Composition> getAssociatedClass() {
    return Composition.class;
  }
}
