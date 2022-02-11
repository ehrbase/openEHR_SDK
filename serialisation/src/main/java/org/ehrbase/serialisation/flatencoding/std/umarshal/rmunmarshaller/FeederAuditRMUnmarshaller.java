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
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
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
  private static final DvMultimediaRMUnmarshaller DV_MULTIMEDIA_RM_UNMARSHALLER =
      new DvMultimediaRMUnmarshaller();
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

    Map<FlatPathDto, String> originalContentValues =
        FlatHelper.filter(currentValues, currentTerm + "/original_content", false);

    if (!originalContentValues.isEmpty()) {
      rmObject.setOriginalContent(new DvParsable());
      DV_PARSABLE_RM_UNMARSHALLER.handle(
          currentTerm + "/original_content",
          (DvParsable) rmObject.getOriginalContent(),
          originalContentValues,
          context,
          consumedPaths);
    }

    Map<FlatPathDto, String> originalContentMultimediaValues =
        FlatHelper.filter(currentValues, currentTerm + "/original_content_multimedia", false);

    if (!originalContentMultimediaValues.isEmpty()) {
      rmObject.setOriginalContent(new DvMultimedia());
      DV_MULTIMEDIA_RM_UNMARSHALLER.handle(
          currentTerm + "/original_content_multimedia",
          (DvMultimedia) rmObject.getOriginalContent(),
          originalContentMultimediaValues,
          context,
          consumedPaths);
    }

    Map<FlatPathDto, String> originatingSystemAuditValues =
        FlatHelper.filter(currentValues, currentTerm + "/originating_system_audit", false);
    if (!originatingSystemAuditValues.isEmpty()) {
      rmObject.setOriginatingSystemAudit(new FeederAuditDetails());
      FEEDER_AUDIT_DETAILS_RM_UNMARSHALLER.handle(
          currentTerm + "/originating_system_audit",
          rmObject.getOriginatingSystemAudit(),
          originatingSystemAuditValues,
          context,
          consumedPaths);
    }

    Map<FlatPathDto, String> feederSystemAuditValues =
        FlatHelper.filter(currentValues, currentTerm + "/feeder_system_audit", false);
    if (!feederSystemAuditValues.isEmpty()) {
      rmObject.setFeederSystemAudit(new FeederAuditDetails());
      FEEDER_AUDIT_DETAILS_RM_UNMARSHALLER.handle(
          currentTerm + "/feeder_system_audit",
          rmObject.getFeederSystemAudit(),
          feederSystemAuditValues,
          context,
          consumedPaths);
    }

    Map<Integer, Map<FlatPathDto, String>> feederSystemIds =
        extractMultiValued(currentTerm, "feeder_system_item_id", currentValues);

    rmObject
        .getFeederSystemItemIds()
        .addAll(
            feederSystemIds.entrySet().stream()
                .map(
                    e ->
                        DefaultValues.toDvIdentifier(
                            e.getValue(), currentTerm + "/feeder_system_item_id:" + e.getKey()))
                .collect(Collectors.toList()));

    consumeAllMatching(
        currentTerm + PATH_DIVIDER + "feeder_system_item_id", currentValues, consumedPaths, false);

    Map<Integer, Map<FlatPathDto, String>> originatingSystemIds =
        extractMultiValued(currentTerm, "originating_system_item_id", currentValues);

    rmObject
        .getOriginatingSystemItemIds()
        .addAll(
            originatingSystemIds.entrySet().stream()
                .map(
                    e ->
                        DefaultValues.toDvIdentifier(
                            e.getValue(),
                            currentTerm + "/originating_system_item_id:" + e.getKey()))
                .collect(Collectors.toList()));

    consumeAllMatching(
        currentTerm + PATH_DIVIDER + "originating_system_item_id", currentValues, consumedPaths, false);
  }
}
