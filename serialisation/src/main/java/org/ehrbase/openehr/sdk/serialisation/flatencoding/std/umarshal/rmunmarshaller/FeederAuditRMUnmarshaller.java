/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal.rmunmarshaller;

import static org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

public class FeederAuditRMUnmarshaller extends AbstractRMUnmarshaller<FeederAudit> {

    private static final DvParsableRMUnmarshaller DV_PARSABLE_RM_UNMARSHALLER = new DvParsableRMUnmarshaller();
    private static final DvMultimediaRMUnmarshaller DV_MULTIMEDIA_RM_UNMARSHALLER = new DvMultimediaRMUnmarshaller();

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

        Map<Integer, Map<FlatPathDto, String>> feederSystemIds =
                FlatHelper.extractMultiValued(currentTerm, "feeder_system_item_id", currentValues);

        rmObject.getFeederSystemItemIds()
                .addAll(feederSystemIds.entrySet().stream()
                        .map(e -> DefaultValues.toDvIdentifier(
                                e.getValue(), currentTerm + "/feeder_system_item_id:" + e.getKey()))
                        .collect(Collectors.toList()));

        FlatHelper.consumeAllMatching(
                currentTerm + PATH_DIVIDER + "feeder_system_item_id", currentValues, consumedPaths, false);

        Map<Integer, Map<FlatPathDto, String>> originatingSystemIds =
                FlatHelper.extractMultiValued(currentTerm, "originating_system_item_id", currentValues);

        rmObject.getOriginatingSystemItemIds()
                .addAll(originatingSystemIds.entrySet().stream()
                        .map(e -> DefaultValues.toDvIdentifier(
                                e.getValue(), currentTerm + "/originating_system_item_id:" + e.getKey()))
                        .collect(Collectors.toList()));

        FlatHelper.consumeAllMatching(
                currentTerm + PATH_DIVIDER + "originating_system_item_id", currentValues, consumedPaths, false);
    }
}
