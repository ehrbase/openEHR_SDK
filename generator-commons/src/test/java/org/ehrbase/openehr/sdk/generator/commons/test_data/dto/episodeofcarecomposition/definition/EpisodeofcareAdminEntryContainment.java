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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.episodeofcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.net.URI;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class EpisodeofcareAdminEntryContainment extends Containment {
    public SelectAqlField<EpisodeofcareAdminEntry> EPISODEOFCARE_ADMIN_ENTRY = new AqlFieldImp<EpisodeofcareAdminEntry>(
            EpisodeofcareAdminEntry.class, "", "EpisodeofcareAdminEntry", EpisodeofcareAdminEntry.class, this);

    public ListSelectAqlField<EpisodeofcareIdentifierElement> IDENTIFIER =
            new ListAqlFieldImp<EpisodeofcareIdentifierElement>(
                    EpisodeofcareAdminEntry.class,
                    "/data[at0001]/items[at0002]",
                    "identifier",
                    EpisodeofcareIdentifierElement.class,
                    this);

    public SelectAqlField<StatusDefiningCode> STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode>(
            EpisodeofcareAdminEntry.class,
            "/data[at0001]/items[at0003]/value|defining_code",
            "statusDefiningCode",
            StatusDefiningCode.class,
            this);

    public SelectAqlField<String> TYPE_VALUE = new AqlFieldImp<String>(
            EpisodeofcareAdminEntry.class, "/data[at0001]/items[at0011]/value|value", "typeValue", String.class, this);

    public SelectAqlField<TemporalAccessor> UPPER_VALUE = new AqlFieldImp<TemporalAccessor>(
            EpisodeofcareAdminEntry.class,
            "/data[at0001]/items[at0014]/value/upper|value",
            "upperValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<TemporalAccessor> LOWER_VALUE = new AqlFieldImp<TemporalAccessor>(
            EpisodeofcareAdminEntry.class,
            "/data[at0001]/items[at0014]/value/lower|value",
            "lowerValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<Boolean> LOWER_INCLUDED = new AqlFieldImp<Boolean>(
            EpisodeofcareAdminEntry.class,
            "/data[at0001]/items[at0014]/value/lower_included",
            "lowerIncluded",
            Boolean.class,
            this);

    public SelectAqlField<Boolean> UPPER_INCLUDED = new AqlFieldImp<Boolean>(
            EpisodeofcareAdminEntry.class,
            "/data[at0001]/items[at0014]/value/upper_included",
            "upperIncluded",
            Boolean.class,
            this);

    public ListSelectAqlField<EpisodeofcareDiagnosisCluster> DIAGNOSIS =
            new ListAqlFieldImp<EpisodeofcareDiagnosisCluster>(
                    EpisodeofcareAdminEntry.class,
                    "/data[at0001]/items[at0018]",
                    "diagnosis",
                    EpisodeofcareDiagnosisCluster.class,
                    this);

    public SelectAqlField<URI> CARE_MANAGER_VALUE = new AqlFieldImp<URI>(
            EpisodeofcareAdminEntry.class,
            "/data[at0001]/items[at0012]/value|value",
            "careManagerValue",
            URI.class,
            this);

    public SelectAqlField<URI> MANAGING_ORGANIZATION_VALUE = new AqlFieldImp<URI>(
            EpisodeofcareAdminEntry.class,
            "/data[at0001]/items[at0017]/value|value",
            "managingOrganizationValue",
            URI.class,
            this);

    public ListSelectAqlField<EpisodeofcareTeamElement> TEAM = new ListAqlFieldImp<EpisodeofcareTeamElement>(
            EpisodeofcareAdminEntry.class, "/data[at0001]/items[at0013]", "team", EpisodeofcareTeamElement.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(EpisodeofcareAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(EpisodeofcareAdminEntry.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            EpisodeofcareAdminEntry.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private EpisodeofcareAdminEntryContainment() {
        super("openEHR-EHR-ADMIN_ENTRY.episodeofcare.v0");
    }

    public static EpisodeofcareAdminEntryContainment getInstance() {
        return new EpisodeofcareAdminEntryContainment();
    }
}
