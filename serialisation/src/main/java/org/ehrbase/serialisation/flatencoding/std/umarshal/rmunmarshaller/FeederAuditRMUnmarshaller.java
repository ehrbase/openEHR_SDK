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

package org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.RMHelper;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ehrbase.serialisation.walker.FlatHelper.consumeAllMatching;
import static org.ehrbase.serialisation.walker.FlatHelper.extractMultiValued;
import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class FeederAuditRMUnmarshaller extends AbstractRMUnmarshaller<FeederAudit> {

  private static final DvParsableRMUnmarshaller DV_PARSABLE_RM_UNMARSHALLER =
      new DvParsableRMUnmarshaller();
  private static final FeederAuditDetailsRMUnmarshaller FEEDER_AUDIT_DETAILS_RM_UNMARSHALLER =
      new FeederAuditDetailsRMUnmarshaller();

  @Override
  public Class<FeederAudit> getAssociatedClass() {
    return FeederAudit.class;
  }

  @Override
  public void handle(
      String currentTerm,
      FeederAudit rmObject,
      Map<FlatPathDto, String> currentValues,
      Context<Map<FlatPathDto, String>> context,
      Set<String> consumedPaths) {

    rmObject.setOriginalContent(new DvParsable());
    DV_PARSABLE_RM_UNMARSHALLER.handle(
        currentTerm + "/original_content",
        (DvParsable) rmObject.getOriginalContent(),
        currentValues,
        context,
        consumedPaths);

    if (RMHelper.isEmpty(rmObject.getOriginalContent())) {
      rmObject.setOriginalContent(null);
    }

    rmObject.setOriginatingSystemAudit(new FeederAuditDetails());
    FEEDER_AUDIT_DETAILS_RM_UNMARSHALLER.handle(
        currentTerm + "/originating_system_audit",
        rmObject.getOriginatingSystemAudit(),
        currentValues,
        context,
        consumedPaths);

    if (RMHelper.isEmpty(rmObject.getOriginatingSystemAudit())) {
      rmObject.setOriginatingSystemAudit(null);
    }

    Map<Integer, Map<String, String>> feederSystemIds =
        extractMultiValued(currentTerm + PATH_DIVIDER + "feeder_system_item_id", currentValues);

    rmObject
        .getFeederSystemItemIds()
        .addAll(
            feederSystemIds.values().stream()
                .map(DefaultValues::toDvIdentifier)
                .collect(Collectors.toList()));

    consumeAllMatching(
        currentTerm + PATH_DIVIDER + "feeder_system_item_id", currentValues, consumedPaths);

    Map<Integer, Map<String, String>> originatingSystemIds =
        extractMultiValued(
            currentTerm + PATH_DIVIDER + "originating_system_item_id", currentValues);

    rmObject
        .getOriginatingSystemItemIds()
        .addAll(
            originatingSystemIds.values().stream()
                .map(DefaultValues::toDvIdentifier)
                .collect(Collectors.toList()));

    consumeAllMatching(
        currentTerm + PATH_DIVIDER + "originating_system_item_id", currentValues, consumedPaths);
  }
}
