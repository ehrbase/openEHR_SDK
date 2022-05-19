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
import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import static org.ehrbase.serialisation.walker.FlatHelper.findOrBuildSubNode;

public class FeederAuditPostprocessor extends AbstractUnmarshalPostprocessor<FeederAudit> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String currentTerm,
      FeederAudit rmObject,
      Map<FlatPathDto, String> currentValues,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

    handeFeederAuditDetails(
        currentTerm,
        rmObject::setFeederSystemAudit,
        currentValues,
        consumedPaths,
        context,
        "feeder_system_audit");
    handeFeederAuditDetails(
        currentTerm,
        rmObject::setOriginatingSystemAudit,
        currentValues,
        consumedPaths,
        context,
        "originating_system_audit");
  }

  private void handeFeederAuditDetails(
      String currentTerm,
      Consumer<FeederAuditDetails> auditDetailsConsumer,
      Map<FlatPathDto, String> currentValues,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context,
      String id) {

    Map<FlatPathDto, String> auditValues =
        FlatHelper.filter(currentValues, currentTerm + "/" + id, false);

    if (!auditValues.isEmpty()) {

      FeederAuditDetails auditDetails = new FeederAuditDetails();
      auditDetailsConsumer.accept(auditDetails);

      callUnmarshal(
          currentTerm,
          "/" + id,
          auditDetails,
          auditValues,
          consumedPaths,
          context,
          findOrBuildSubNode(context, id));

      callPostProcess(
          currentTerm,
          "/" + id,
          auditDetails,
          auditValues,
          consumedPaths,
          context,
          findOrBuildSubNode(context, id));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<FeederAudit> getAssociatedClass() {
    return FeederAudit.class;
  }
}
