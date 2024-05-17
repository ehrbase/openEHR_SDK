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
package org.ehrbase.openehr.sdk.generator.commons.interfaces;

import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Category;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Setting;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Territory;

public interface CompositionEntity extends LocatableEntity {

    void setParticipations(List<Participation> participations);

    List<Participation> getParticipations();

    void setHealthCareFacility(PartyIdentified healthCareFacility);

    PartyIdentified getHealthCareFacility();

    void setComposer(PartyProxy composer);

    PartyProxy getComposer();

    void setLanguage(Language language);

    Language getLanguage();

    void setCategoryDefiningCode(Category categoryDefiningCode);

    Category getCategoryDefiningCode();

    void setTerritory(Territory territory);

    Territory getTerritory();

    ObjectVersionId getVersionUid();

    void setVersionUid(ObjectVersionId versionUid);

    void setStartTimeValue(TemporalAccessor startTimeValue);

    TemporalAccessor getStartTimeValue();

    void setEndTimeValue(TemporalAccessor endTimeValue);

    TemporalAccessor getEndTimeValue();

    void setLocation(String location);

    String getLocation();

    void setSettingDefiningCode(Setting settingDefiningCode);

    Setting getSettingDefiningCode();
}
