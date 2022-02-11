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

package org.ehrbase.serialisation.flatencoding.std.marshal.config;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import org.ehrbase.serialisation.walker.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FeederAuditConfig extends AbstractsStdConfig<FeederAudit> {

  /** {@inheritDoc} */
  @Override
  public Class<FeederAudit> getAssociatedClass() {
    return FeederAudit.class;
  }

  private static final DvIdentifierConfig DV_IDENTIFIER_CONFIG = new DvIdentifierConfig();
  private static final DvMultimediaConfig DV_MULTIMEDIA_CONFIG = new DvMultimediaConfig();
  private static final DvParsableConfig DV_PARSABLE_CONFIG = new DvParsableConfig();
  private static final FeederAudiDetailsStdConfig FEEDER_AUDI_DETAILS_STD_CONFIG =
      new FeederAudiDetailsStdConfig();

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> buildChildValues(
      String currentTerm, FeederAudit rmObject, Context<Map<String, Object>> context) {

    Map<String, Object> result = new HashMap<>();

    if (rmObject.getOriginalContent() instanceof DvParsable) {

      result.putAll(
          DV_PARSABLE_CONFIG.buildChildValues(
              currentTerm + "/original_content",
              (DvParsable) rmObject.getOriginalContent(),
              context));

    } else if (rmObject.getOriginalContent() instanceof DvMultimedia) {

      result.putAll(
          DV_MULTIMEDIA_CONFIG.buildChildValues(
              currentTerm + "/original_content_multimedia",
              (DvMultimedia) rmObject.getOriginalContent(),
              context));
    }

    IntStream.range(0, rmObject.getFeederSystemItemIds().size())
        .forEach(
            i -> {
              DvIdentifier identifier = rmObject.getFeederSystemItemIds().get(i);

              result.putAll(
                  DV_IDENTIFIER_CONFIG.buildChildValues(
                      currentTerm + "/feeder_system_item_id:" + i, identifier, context));
            });

    IntStream.range(0, rmObject.getOriginatingSystemItemIds().size())
        .forEach(
            i -> {
              DvIdentifier identifier = rmObject.getOriginatingSystemItemIds().get(i);

              result.putAll(
                  DV_IDENTIFIER_CONFIG.buildChildValues(
                      currentTerm + "/originating_system_item_id:" + i, identifier, context));
            });

    if (rmObject.getOriginatingSystemAudit() != null) {

      result.putAll(
          FEEDER_AUDI_DETAILS_STD_CONFIG.buildChildValues(
              currentTerm + "/originating_system_audit",
              rmObject.getOriginatingSystemAudit(),
              context));
    }

    if (rmObject.getFeederSystemAudit() != null) {

      result.putAll(
          FEEDER_AUDI_DETAILS_STD_CONFIG.buildChildValues(
              currentTerm + "/feeder_system_audit", rmObject.getFeederSystemAudit(), context));
    }

    return result;
  }
}
