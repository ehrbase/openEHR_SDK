/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.defaultinserter;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.ehrbase.openehr.sdk.serialisation.walker.RMHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;

public abstract class AbstractValueInserter<T extends RMObject> implements DefaultValueInserter<T> {

    protected boolean isEmpty(Object rmObject) {

        return RMHelper.isEmpty(rmObject);
    }

    protected <X extends PartyProxy> X buildPartyIdentified(
            DefaultValues defaultValues, DefaultValuePath<String> name, DefaultValuePath<String> id, X partyProxy) {

        if (defaultValues.containsDefaultValue(name) && partyProxy instanceof PartyIdentified) {

            ((PartyIdentified) partyProxy).setName(defaultValues.getDefaultValue(name));
        }
        if (defaultValues.containsDefaultValue(id)) {

            PartyRef partyRef = new PartyRef();
            partyRef.setNamespace(defaultValues.getDefaultValue(DefaultValuePath.ID_NAMESPACE));
            partyRef.setType("PARTY");
            partyRef.setId(new GenericId(
                    defaultValues.getDefaultValue(id), defaultValues.getDefaultValue(DefaultValuePath.ID_SCHEME)));
            partyProxy.setExternalRef(partyRef);
        }

        addSchemeNamespace(partyProxy.getExternalRef(), defaultValues);

        return partyProxy;
    }

    protected void addSchemeNamespace(PartyRef partyRef, DefaultValues defaultValues) {
        if (partyRef != null) {
            if (RMHelper.isEmpty(partyRef.getNamespace())
                    && defaultValues.containsDefaultValue(DefaultValuePath.ID_NAMESPACE)) {
                partyRef.setNamespace(defaultValues.getDefaultValue(DefaultValuePath.ID_NAMESPACE));
            }
            if (partyRef.getId() instanceof GenericId
                    && RMHelper.isEmpty(((GenericId) partyRef.getId()).getScheme())
                    && defaultValues.containsDefaultValue(DefaultValuePath.ID_NAMESPACE)) {
                ((GenericId) partyRef.getId()).setScheme(defaultValues.getDefaultValue(DefaultValuePath.ID_NAMESPACE));
            }
        }
    }
}
