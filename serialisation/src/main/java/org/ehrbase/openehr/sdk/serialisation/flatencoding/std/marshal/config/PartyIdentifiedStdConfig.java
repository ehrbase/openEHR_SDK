/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal.config;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;

public class PartyIdentifiedStdConfig extends AbstractsStdConfig<PartyIdentified> {

    public static final DvIdentifierConfig DV_IDENTIFIER_CONFIG = new DvIdentifierConfig();

    /** {@inheritDoc} */
    @Override
    public Class<PartyIdentified> getAssociatedClass() {
        return PartyIdentified.class;
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Object> buildChildValues(
            String currentTerm, PartyIdentified rmObject, Context<Map<String, Object>> context) {

        Map<String, Object> result = new HashMap<>();
        addValue(result, currentTerm, "name", rmObject.getName());
        addValue(
                result,
                currentTerm,
                "id",
                Optional.of(rmObject)
                        .map(PartyProxy::getExternalRef)
                        .map(ObjectRef::getId)
                        .map(ObjectId::getValue)
                        .orElse(null));
        addValue(
                result,
                currentTerm,
                "id_namespace",
                Optional.of(rmObject)
                        .map(PartyProxy::getExternalRef)
                        .map(ObjectRef::getNamespace)
                        .orElse(null));

        GenericId genericId = Optional.of(rmObject)
                .map(PartyProxy::getExternalRef)
                .map(ObjectRef::getId)
                .filter(i -> i.getClass().equals(GenericId.class))
                .map(i -> (GenericId) i)
                .orElse(null);
        if (genericId != null) {
            addValue(result, currentTerm, "id_scheme", genericId.getScheme());
        }

        if (rmObject.getIdentifiers() != null) {
            IntStream.range(0, rmObject.getIdentifiers().size())
                    .forEach(i -> result.putAll(DV_IDENTIFIER_CONFIG.buildChildValues(
                            currentTerm + "/_identifier:" + i,
                            rmObject.getIdentifiers().get(i),
                            context)));
        }
        return result;
    }
}
