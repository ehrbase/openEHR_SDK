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

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;

public class FeederAuditPostprocessor extends AbstractUnmarshalPostprocessor<FeederAudit> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      FeederAudit rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

      Map<FlatPathDto, String> locationValues = FlatHelper.filter(values, term + "/originating_system_audit", false);
      if(!locationValues.isEmpty() && rmObject.getOriginatingSystemAudit() != null){

        callPostProcess(term,"originating_system_audit",rmObject.getOriginatingSystemAudit(),locationValues,consumedPaths,context,null );

      }

    Map<FlatPathDto, String> locationValues2 =
        FlatHelper.filter(values, term + "/feeder_system_audit", false);
    if (!locationValues2.isEmpty() && rmObject.getFeederSystemAudit() != null) {

      callPostProcess(
          term,
          "feeder_system_audit",
          rmObject.getFeederSystemAudit(),
          locationValues2,
          consumedPaths,
          context,
          null);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<FeederAudit> getAssociatedClass() {
    return FeederAudit.class;
  }
}
