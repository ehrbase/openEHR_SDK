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

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.support.identification.HierObjectId;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class LocatableUnmarshalPostprocessor extends AbstractUnmarshalPostprocessor<Locatable> {

  /** {@inheritDoc} Unmarshalls {@link Composition#setUid} */
  @Override
  public void process(String term, Locatable rmObject, Map<FlatPathDto, String> values, Set<String> consumedPaths) {

    setValue(
        term + PATH_DIVIDER + "_uid",
        null,
        values,
        s -> rmObject.setUid(new HierObjectId(s)),
        String.class, consumedPaths);

    Map<Integer, Map<String, String>> links =
        values.entrySet().stream()
            .filter(s -> s.getKey().startsWith(term + PATH_DIVIDER + "_link"))
            .collect(
                Collectors.groupingBy(
                    e -> Optional.ofNullable(e.getKey().getLast().getCount()).orElse(0),
                        Collectors.toMap(
                                e1 -> e1.getKey().getLast().getAttributeName(),
                            stringStringEntry -> StringUtils.unwrap(stringStringEntry.getValue(), '"'))));

    if (rmObject.getLinks() == null) {
      rmObject.setLinks(new ArrayList<>());
    }

    rmObject
        .getLinks()
        .addAll(
            links.values().stream().map(DefaultValues::createLink).collect(Collectors.toList()));
  }

  /** {@inheritDoc} */
  @Override
  public Class<Locatable> getAssociatedClass() {
    return Locatable.class;
  }
}
