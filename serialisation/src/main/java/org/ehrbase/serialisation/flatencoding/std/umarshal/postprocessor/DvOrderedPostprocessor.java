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

import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.DvOrdered;
import org.ehrbase.building.webtemplateskeletnbuilder.WebTemplateSkeletonBuilder;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.RMUnmarshaller;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;

import static org.ehrbase.serialisation.flatencoding.std.umarshal.StdToCompositionWalker.findRMUnmarshaller;

public class DvOrderedPostprocessor extends AbstractUnmarshalPostprocessor<DvOrdered> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      DvOrdered rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

    Map<FlatPathDto, String> upperValues = FlatHelper.filter(values, term + "/_normal_range/upper");

    if (!upperValues.isEmpty()) {
      if (rmObject.getNormalRange() == null) {
        rmObject.setNormalRange(new DvInterval());
      }
      DvOrdered upper =
          WebTemplateSkeletonBuilder.build(context.getNodeDeque().peek(), false, DvOrdered.class);
      rmObject.getNormalRange().setUpper(upper);
      RMUnmarshaller rmUnmarshaller = findRMUnmarshaller(rmObject.getClass());
      rmUnmarshaller.handle(
          term + "/_normal_range/upper",
          rmObject.getNormalRange().getUpper(),
          upperValues,
          context,
          consumedPaths);
    }

    Map<FlatPathDto, String> lowerValues = FlatHelper.filter(values, term + "/_normal_range/lower");

    if (!lowerValues.isEmpty()) {
      if (rmObject.getNormalRange() == null) {
        rmObject.setNormalRange(new DvInterval());
      }
      DvOrdered lower =
          WebTemplateSkeletonBuilder.build(context.getNodeDeque().peek(), false, DvOrdered.class);
      rmObject.getNormalRange().setLower(lower);
      RMUnmarshaller rmUnmarshaller = findRMUnmarshaller(rmObject.getClass());
      rmUnmarshaller.handle(
          term + "/_normal_range/lower",
          rmObject.getNormalRange().getLower(),
          lowerValues,
          context,
          consumedPaths);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<DvOrdered> getAssociatedClass() {
    return DvOrdered.class;
  }
}
