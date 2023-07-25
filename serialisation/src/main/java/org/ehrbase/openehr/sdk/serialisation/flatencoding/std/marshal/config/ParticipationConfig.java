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

import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartyRelated;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import com.nedap.archie.rm.support.identification.PartyRef;
import java.util.*;
import java.util.stream.IntStream;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;

public class ParticipationConfig extends AbstractsStdConfig<Participation> {

    /** {@inheritDoc} */
    @Override
    public Class<Participation> getAssociatedClass() {
        return Participation.class;
    }

    public static final DvIdentifierConfig DV_IDENTIFIER_CONFIG = new DvIdentifierConfig();
    /** {@inheritDoc} */
    @Override
    public Map<String, Object> buildChildValues(
            String currentTerm, Participation rmObject, Context<Map<String, Object>> context) {
        Map<String, Object> result = new HashMap<>();
        addValue(
                result,
                currentTerm,
                "id",
                Optional.of(rmObject)
                        .map(Participation::getPerformer)
                        .map(PartyProxy::getExternalRef)
                        .map(ObjectRef::getId)
                        .map(ObjectId::getValue)
                        .orElse(null));
        addValue(
                result,
                currentTerm,
                "id_namespace",
                Optional.of(rmObject)
                        .map(Participation::getPerformer)
                        .map(PartyProxy::getExternalRef)
                        .map(ObjectRef::getNamespace)
                        .orElse(null));
        addValue(
                result,
                currentTerm,
                "id_scheme",
                Optional.of(rmObject)
                        .map(Participation::getPerformer)
                        .map(PartyProxy::getExternalRef)
                        .map(PartyRef::getId)
                        .filter(cls -> GenericId.class.isAssignableFrom(cls.getClass()))
                        .map(GenericId.class::cast)
                        .map(GenericId::getScheme)
                        .orElse(null));
        addValue(
                result,
                currentTerm,
                "name",
                Optional.of(rmObject)
                        .map(Participation::getPerformer)
                        .filter(p -> PartyIdentified.class.isAssignableFrom(p.getClass()))
                        .map(PartyIdentified.class::cast)
                        .map(PartyIdentified::getName)
                        .orElse(null));
        addValue(
                result,
                currentTerm,
                "mode",
                Optional.of(rmObject)
                        .map(Participation::getMode)
                        .map(DvText::getValue)
                        .orElse(null));
        addValue(
                result,
                currentTerm,
                "function",
                Optional.of(rmObject)
                        .map(Participation::getFunction)
                        .map(DvText::getValue)
                        .orElse(null));

        List<DvIdentifier> dvIdentifiers = Optional.of(rmObject)
                .map(Participation::getPerformer)
                .filter(p -> PartyIdentified.class.isAssignableFrom(p.getClass()))
                .map(PartyIdentified.class::cast)
                .map(PartyIdentified::getIdentifiers)
                .orElse(Collections.emptyList());

        IntStream.range(0, dvIdentifiers.size()).forEach(i -> {
            DvIdentifier identifier = dvIdentifiers.get(i);

            addValue(result, currentTerm, "identifiers_id:" + i, identifier.getId());
            addValue(result, currentTerm, "identifiers_issuer:" + i, identifier.getIssuer());
            addValue(result, currentTerm, "identifiers_assigner:" + i, identifier.getAssigner());
            addValue(result, currentTerm, "identifiers_type:" + i, identifier.getType());
        });

        if (rmObject.getPerformer() instanceof PartyRelated) {
            PartyRelatedStdConfig.addRelationship(currentTerm, (PartyRelated) rmObject.getPerformer(), context, result);
        }

        return result;
    }
}
