/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.client.openehrclient.builder;

import static java.util.Collections.EMPTY_LIST;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.ehrbase.client.openehrclient.ContributionChangeType.CREATION;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.AuditDetails;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.PartyRef;
import java.util.*;
import javax.annotation.Nullable;
import org.ehrbase.client.openehrclient.ContributionChangeType;
import org.ehrbase.response.openehr.ContributionCreateDto;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;

/**
 * TODO:: Refactor duplicate code part and check uui  + composition CRUD.
 * Clarify about CRUD for composition and contribution
 *
 * The type Contribution builder.
 */
public class ContributionBuilder {
    private final ContributionCreateDto contributionCreateDto;

    /**
     * Builder contribution builder dto builder.
     *
     * @return the contribution builder dto builder
     */
    public static ContributionBuilderDtoBuilder builder() {
        return new ContributionBuilderDtoBuilder();
    }

    /**
     * Instantiates a new Contribution builder.
     *
     * @param contributionCreateDto the contribution create dto
     */
    public ContributionBuilder(ContributionCreateDto contributionCreateDto) {
        this.contributionCreateDto = contributionCreateDto;
    }

    /**
     * Gets contribution create dto.
     *
     * @return the contribution create dto
     */
    public ContributionCreateDto getContributionCreateDto() {
        return contributionCreateDto;
    }

    /**
     * The type Contribution builder dto builder.
     */
    public static class ContributionBuilderDtoBuilder {

        public static final String OPTIONAL_NAME_OF_THE_COMMITTER = "<optional name of the committer>";
        /**
         * The Contribution create dto.
         */
        ContributionCreateDto contributionCreateDto = new ContributionCreateDto();

        /**
         * Instantiates a new Contribution builder dto builder.
         */
        ContributionBuilderDtoBuilder() {}

        /**
         * Add contribution uuid contribution builder dto builder.
         *
         * @param uuid the uuid
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addContributionUuid(@Nullable UUID uuid) {
            HierObjectId uid = new HierObjectId();
            if (uuid != null) {
                uid.setValue(uuid.toString());
            }
            this.contributionCreateDto.setUid(uid);

            return this;
        }

        /**
         * Add composition contribution builder dto builder.
         *
         * @param rmObject the rm object
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addComposition(final RMObject rmObject) {
            if (rmObject != null) {
                String rmObjectJson = new CanonicalJson().marshal(rmObject);
                LinkedHashMap<String, Object> secondComposition =
                        (LinkedHashMap<String, Object>) new CanonicalJson().unmarshalToMap(rmObjectJson);

                OriginalVersion<Object> objectOriginalVersion = new OriginalVersion<>();

                objectOriginalVersion.setData(secondComposition);
                objectOriginalVersion.setCommitAudit(createAuditDetails(CREATION, OPTIONAL_NAME_OF_THE_COMMITTER));
                this.contributionCreateDto.setAudit(createAuditDetails(CREATION, OPTIONAL_NAME_OF_THE_COMMITTER));
                this.contributionCreateDto.getVersions().add(objectOriginalVersion);
            }

            return this;
        }

        /**
         * Add composition contribution builder dto builder.
         *
         * @param rmObject                 the rm object
         * @param compositionCommitterName the composition committer name
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addComposition(
                final RMObject rmObject, final String compositionCommitterName) {
            if (rmObject != null) {
                String rmObjectJson = new CanonicalJson().marshal(rmObject);
                LinkedHashMap<String, Object> secondComposition =
                        (LinkedHashMap<String, Object>) new CanonicalJson().unmarshalToMap(rmObjectJson);

                OriginalVersion<Object> objectOriginalVersion = new OriginalVersion<>();

                objectOriginalVersion.setData(secondComposition);

                objectOriginalVersion.setCommitAudit(createAuditDetails(CREATION, compositionCommitterName));

                this.contributionCreateDto.getVersions().add(objectOriginalVersion);
            }

            return this;
        }

        /**
         * Add composition contribution builder dto builder.
         *
         * @param rmObject the rm object
         * @param type     the type
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addComposition(
                final RMObject rmObject, final ContributionChangeType type) {
            if (rmObject != null) {
                String rmObjectJson = new CanonicalJson().marshal(rmObject);
                LinkedHashMap<String, Object> secondComposition =
                        (LinkedHashMap<String, Object>) new CanonicalJson().unmarshalToMap(rmObjectJson);

                OriginalVersion<Object> objectOriginalVersion = new OriginalVersion<>();

                objectOriginalVersion.setData(secondComposition);

                objectOriginalVersion.setCommitAudit(createAuditDetails(type, "<optional name of the committer>"));

                this.contributionCreateDto.getVersions().add(objectOriginalVersion);
            }

            return this;
        }

        /**
         * Add composition contribution builder dto builder.
         *
         * @param rmObject                 the rm object
         * @param type                     the type
         * @param compositionCommitterName the composition committer name
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addComposition(
                final RMObject rmObject, final ContributionChangeType type, final String compositionCommitterName) {
            if (rmObject != null) {
                String rmObjectJson = new CanonicalJson().marshal(rmObject);
                LinkedHashMap<String, Object> secondComposition =
                        (LinkedHashMap<String, Object>) new CanonicalJson().unmarshalToMap(rmObjectJson);

                OriginalVersion<Object> objectOriginalVersion = new OriginalVersion<>();

                objectOriginalVersion.setData(secondComposition);

                objectOriginalVersion.setCommitAudit(createAuditDetails(type, compositionCommitterName));

                this.contributionCreateDto.getVersions().add(objectOriginalVersion);
            }

            return this;
        }

        /**
         * Add composition contribution builder dto builder.
         *
         * @param canonicalObject          the canonical object
         * @param compositionCommitterName the composition committer name
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addComposition(
                final Map<String, Object> canonicalObject, String compositionCommitterName) {
            if (canonicalObject != null) {
                OriginalVersion<Object> objectOriginalVersion = new OriginalVersion<>();
                // Audit Details
                objectOriginalVersion.setCommitAudit(createAuditDetails(CREATION, compositionCommitterName));
                objectOriginalVersion.setData(canonicalObject);

                this.contributionCreateDto.getVersions().add(objectOriginalVersion);
                this.contributionCreateDto.getVersions().add(objectOriginalVersion);
                this.contributionCreateDto.setAudit(createAuditDetails(CREATION, compositionCommitterName));
            }

            return this;
        }

        /**
         * Add composition contribution builder dto builder.
         *
         * @param canonicalObject the canonical object
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addComposition(final Map<String, Object> canonicalObject) {
            if (canonicalObject != null) {
                OriginalVersion<Object> objectOriginalVersion = new OriginalVersion<>();
                // Audit Details
                objectOriginalVersion.setCommitAudit(createAuditDetails(CREATION, "<optional name of the committer>"));
                objectOriginalVersion.setData(canonicalObject);

                this.contributionCreateDto.getVersions().add(objectOriginalVersion);
                this.contributionCreateDto.getVersions().add(objectOriginalVersion);
                this.contributionCreateDto.setAudit(createAuditDetails(CREATION, "<optional name of the committer>"));
            }

            return this;
        }

        /**
         * Add composition contribution builder dto builder.
         *
         * @param canonicalObject the canonical object
         * @param type            the type
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addComposition(
                final Map<String, Object> canonicalObject, final ContributionChangeType type) {
            if (canonicalObject != null) {
                // Can be created OriginalVersionBuilder to make more customizable
                OriginalVersion<Object> objectOriginalVersion = new OriginalVersion<>();
                objectOriginalVersion.setCommitAudit(createAuditDetails(type, "<optional name of the committer>"));
                objectOriginalVersion.setData(canonicalObject);
                this.contributionCreateDto.getVersions().add(objectOriginalVersion);
            }

            return this;
        }

        private static AuditDetails createAuditDetails(final ContributionChangeType type, String committerName) {

            //           need to provide AuditDetailsBuilder to make easier to generate and hide some default logic
            AuditDetails commitAudit = new AuditDetails();
            // changeType
            DvCodedText changeType = new DvCodedText();
            changeType.setDefiningCode(new CodePhrase(null, String.valueOf(type.getCode())));
            changeType.setValue(type.getName());
            commitAudit.setChangeType(changeType);

            commitAudit.setSystemId("test-system-id");

            PartyIdentified committer = new PartyIdentified();
            committer.setName(committerName);
            committer.setIdentifiers(EMPTY_LIST);
            PartyRef partyRef = new PartyRef();
            partyRef.setNamespace("demographic");
            partyRef.setType("PERSON");
            GenericId id = new GenericId();
            id.setScheme("<ID SCHEME NAME>");
            id.setValue("<OBJECT_ID>");
            partyRef.setId(id);
            //            partyRef.setNamespace(defaultValues.getDefaultValue(DefaultValuePath.ID_NAMESPACE));
            //            partyRef.setType("PARTY");
            //            partyRef.setId(new GenericId(defaultValues.getDefaultValue(id),
            // defaultValues.getDefaultValue(DefaultValuePath.ID_SCHEME)));
            //            partyProxy.setExternalRef(partyRef);
            committer.setExternalRef(partyRef);

            commitAudit.setCommitter(committer);

            return commitAudit;
        }

        /**
         * Add hier object id contribution builder dto builder.
         *
         * @param uid the uid
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addHierObjectId(final HierObjectId uid) {
            if (uid != null) {
                this.contributionCreateDto.setUid(uid);
            }

            return this;
        }

        /**
         * Add contribution audit details contribution builder dto builder.
         *
         * @param audit the audit
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addContributionAuditDetails(final AuditDetails audit) {
            if (audit != null) {
                this.contributionCreateDto.setAudit(audit);
            }

            return this;
        }

        /**
         * Add contribution committer name contribution builder dto builder.
         *
         * @param name the name
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addContributionCommitterName(String name) {
            AuditDetails audit = this.contributionCreateDto.getAudit();
            // TODO:: Check the behaviour if something except of PartyIdentified anf the logic below
            if (audit != null && audit.getCommitter() != null && audit.getCommitter() instanceof PartyIdentified) {
                PartyIdentified committer = (PartyIdentified) audit.getCommitter();
                if (isBlank(committer.getName())) {
                    committer.setName(name);
                }
            } else {
                this.contributionCreateDto.setAudit(createAuditDetails(CREATION, name));
            }

            return this;
        }

        /**
         * Add original version contribution builder dto builder.
         *
         * @param originalVersion          the original version
         * @param compositionCommitterName the composition committer name
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addOriginalVersion(
                OriginalVersion<LinkedHashMap<String, Object>> originalVersion, String compositionCommitterName) {
            if (originalVersion != null) {
                originalVersion.setCommitAudit(createAuditDetails(CREATION, compositionCommitterName));
                this.contributionCreateDto.getVersions().add(originalVersion);
            }

            return this;
        }

        /**
         * Add original version contribution builder dto builder.
         *
         * @param originalVersion the original version
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addOriginalVersion(
                OriginalVersion<LinkedHashMap<String, Object>> originalVersion) {
            if (originalVersion != null) {
                originalVersion.setCommitAudit(createAuditDetails(CREATION, "<optional name of the committer>"));

                this.contributionCreateDto.setAudit(createAuditDetails(CREATION, "<optional name of the committer>"));
                this.contributionCreateDto.getVersions().add(originalVersion);
            }

            return this;
        }

        /**
         * Add original version contribution builder dto builder.
         *
         * @param originalVersion the original version
         * @param type            the type
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addOriginalVersion(
                OriginalVersion<LinkedHashMap<String, Object>> originalVersion, final ContributionChangeType type) {
            if (originalVersion != null) {
                originalVersion.setCommitAudit(createAuditDetails(type, "<optional name of the committer>"));

                this.contributionCreateDto.getVersions().add(originalVersion);
            }

            return this;
        }

        /**
         * Add original version contribution builder dto builder.
         *
         * @param originalVersion          the original version
         * @param type                     the type
         * @param compositionCommitterName the composition committer name
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addOriginalVersion(
                OriginalVersion<LinkedHashMap<String, Object>> originalVersion,
                final ContributionChangeType type,
                String compositionCommitterName) {
            if (originalVersion != null) {
                originalVersion.setCommitAudit(createAuditDetails(CREATION, "<optional name of the committer>"));

                this.contributionCreateDto.getVersions().add(originalVersion);
            }

            return this;
        }

        /**
         * Add original version contribution builder dto builder.
         *
         * @param originalVersion the original version
         * @param audit           the audit
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addOriginalVersion(
                OriginalVersion<LinkedHashMap<String, Object>> originalVersion, @Nullable final AuditDetails audit) {
            if (originalVersion != null) {
                if (audit != null) {
                    originalVersion.setCommitAudit(audit);
                } else {
                    originalVersion.setCommitAudit(createAuditDetails(CREATION, "<optional name of the committer>"));
                }

                this.contributionCreateDto.getVersions().add(originalVersion);
            }

            return this;
        }

        /**
         * Build contribution builder.
         *
         * @return the contribution builder
         */
        public ContributionBuilder build() {
            return new ContributionBuilder(this.contributionCreateDto);
        }
    }
}
