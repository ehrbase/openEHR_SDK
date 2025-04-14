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

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.PartyRef;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

public class PartyIdentifiedRMUnmarshaller extends AbstractRMUnmarshaller<PartyIdentified> {
    @Override
    public Class<PartyIdentified> getAssociatedClass() {
        return PartyIdentified.class;
    }

    @Override
    public void handle(
            String currentTerm,
            PartyIdentified rmObject,
            Map<FlatPathDto, String> currentValues,
            Context<Map<FlatPathDto, String>> context,
            Set<String> consumedPaths) {

        setValue(currentTerm, "name", currentValues, rmObject::setName, String.class, consumedPaths);

        if (rmObject.getName() == null) {
            // betters implementation uses /name or  /_name instead of |name for subject
            setValue(currentTerm + "/name", null, currentValues, rmObject::setName, String.class, consumedPaths);
            if (rmObject.getName() == null) {
                setValue(currentTerm + "/_name", null, currentValues, rmObject::setName, String.class, consumedPaths);
            }
        }

        rmObject.setExternalRef(new PartyRef());
        rmObject.getExternalRef().setType("PARTY");
        rmObject.getExternalRef().setId(new GenericId());
        setValue(
                currentTerm,
                "id",
                currentValues,
                rmObject.getExternalRef().getId()::setValue,
                String.class,
                consumedPaths);
        setValue(
                currentTerm,
                "id_scheme",
                currentValues,
                ((GenericId) rmObject.getExternalRef().getId())::setScheme,
                String.class,
                consumedPaths);
        setValue(
                currentTerm,
                "id_namespace",
                currentValues,
                rmObject.getExternalRef()::setNamespace,
                String.class,
                consumedPaths);

        // remove if not set
        if (rmObject.getExternalRef().getId() == null
                || StringUtils.isBlank(rmObject.getExternalRef().getId().getValue())) {
            rmObject.setExternalRef(null);
        }

        Map<Integer, Map<FlatPathDto, String>> identifiers =
                FlatHelper.extractMultiValued(currentTerm, "_identifier", currentValues);

        rmObject.setIdentifiers(identifiers.entrySet().stream()
                .map(e -> DefaultValues.toDvIdentifier(e.getValue(), currentTerm + "/_identifier:" + e.getKey()))
                .collect(Collectors.toList()));

        FlatHelper.consumeAllMatching(currentTerm + PATH_DIVIDER + "_identifier", currentValues, consumedPaths, false);
    }
}
