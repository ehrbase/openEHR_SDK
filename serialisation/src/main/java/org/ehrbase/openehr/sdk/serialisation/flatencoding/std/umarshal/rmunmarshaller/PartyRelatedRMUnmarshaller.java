/*
 * Copyright (c) 2020 vitasystems GmbH.
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

import com.nedap.archie.rm.generic.PartyRelated;
import java.util.Map;
import java.util.Set;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

public class PartyRelatedRMUnmarshaller extends AbstractRMUnmarshaller<PartyRelated> {
    @Override
    public Class<PartyRelated> getAssociatedClass() {
        return PartyRelated.class;
    }

    private static final PartyIdentifiedRMUnmarshaller PARTY_IDENTIFIED_RM_UNMARSHALLER =
            new PartyIdentifiedRMUnmarshaller();

    @Override
    public void handle(
            String currentTerm,
            PartyRelated rmObject,
            Map<FlatPathDto, String> currentValues,
            Context<Map<FlatPathDto, String>> context,
            Set<String> consumedPaths) {

        // PartyRelated is a PartyIdentified
        PARTY_IDENTIFIED_RM_UNMARSHALLER.handle(currentTerm, rmObject, currentValues, context, consumedPaths);
    }
}
