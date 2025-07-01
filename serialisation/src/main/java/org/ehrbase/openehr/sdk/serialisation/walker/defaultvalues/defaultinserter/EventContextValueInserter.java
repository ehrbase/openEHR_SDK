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
package org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.defaultinserter;

import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.support.identification.GenericId;
import java.util.Objects;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Setting;
import org.ehrbase.openehr.sdk.serialisation.walker.RMHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

public class EventContextValueInserter extends AbstractValueInserter<EventContext> {
    @Override
    public void insert(EventContext rmObject, DefaultValues defaultValues, WebTemplateNode node) {

        if (RMHelper.isEmpty(rmObject.getStartTime()) && defaultValues.containsDefaultValue(DefaultValuePath.TIME)) {
            rmObject.setStartTime(new DvDateTime(defaultValues.getDefaultValue(DefaultValuePath.TIME)));
        }

        if (RMHelper.isEmpty(rmObject.getEndTime()) && defaultValues.containsDefaultValue(DefaultValuePath.END_TIME)) {
            rmObject.setEndTime(new DvDateTime(defaultValues.getDefaultValue(DefaultValuePath.END_TIME)));
        }

        if (RMHelper.isEmpty(rmObject.getHealthCareFacility())
                && (defaultValues.containsDefaultValue(DefaultValuePath.HEALTHCARE_FACILITY_NAME)
                        || defaultValues.containsDefaultValue(DefaultValuePath.HEALTHCARE_FACILITY_ID))) {

            rmObject.setHealthCareFacility(new PartyIdentified());
            rmObject.setHealthCareFacility(buildPartyIdentified(
                    defaultValues,
                    DefaultValuePath.HEALTHCARE_FACILITY_NAME,
                    DefaultValuePath.HEALTHCARE_FACILITY_ID,
                    rmObject.getHealthCareFacility()));
        }

        if (RMHelper.isEmpty(rmObject.getLocation()) && defaultValues.containsDefaultValue(DefaultValuePath.LOCATION)) {
            rmObject.setLocation(defaultValues.getDefaultValue(DefaultValuePath.LOCATION));
        }

        if (RMHelper.isEmpty(rmObject.getSetting()) && defaultValues.containsDefaultValue(DefaultValuePath.SETTING)) {
            Setting defaultValue = defaultValues.getDefaultValue(DefaultValuePath.SETTING);
            rmObject.setSetting(new DvCodedText(defaultValue.getValue(), defaultValue.toCodePhrase()));
        }

        if (RMHelper.isEmpty(rmObject.getParticipations())
                && defaultValues.containsDefaultValue(DefaultValuePath.PARTICIPATION)) {
            rmObject.setParticipations(defaultValues.getDefaultValue(DefaultValuePath.PARTICIPATION));
        }

        if (rmObject.getParticipations() != null) {
            rmObject.getParticipations().stream()
                    .map(Participation::getPerformer)
                    .filter(Objects::nonNull)
                    .map(PartyProxy::getExternalRef)
                    .filter(Objects::nonNull)
                    .filter(ref -> ref.getId() != null)
                    .forEach(ref -> {
                        if (ref.getNamespace() == null
                                && defaultValues.containsDefaultValue(DefaultValuePath.ID_NAMESPACE)) {
                            ref.setNamespace(defaultValues.getDefaultValue(DefaultValuePath.ID_NAMESPACE));
                        }
                        if (ref.getId() instanceof GenericId
                                && ref.getNamespace() == null
                                && defaultValues.containsDefaultValue(DefaultValuePath.ID_SCHEME)) {
                            ((GenericId) ref.getId())
                                    .setScheme(defaultValues.getDefaultValue(DefaultValuePath.ID_SCHEME));
                        }
                    });
        }
    }

    @Override
    public Class<EventContext> getAssociatedClass() {
        return EventContext.class;
    }
}
