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

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.ehrbase.client.openehrclient.ContributionChangeType.*;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.generic.AuditDetails;
import com.nedap.archie.rm.support.identification.ObjectVersionId;

import javax.annotation.Nullable;

import com.nedap.archie.rm.support.identification.UIDBasedId;
import org.ehrbase.client.openehrclient.ContributionChangeType;
import org.ehrbase.response.openehr.ContributionCreateDto;

public class ContributionBuilder {
    private final ContributionCreateDto contributionCreateDto;

    public static ContributionBuilderDtoBuilder builder(AuditDetails audit) {
        return new ContributionBuilderDtoBuilder(audit);
    }

    public ContributionBuilder(ContributionCreateDto contributionCreateDto) {
        this.contributionCreateDto = contributionCreateDto;
    }

    /**
     * Gets contribution dto.
     *
     * @return the contribution create dto
     */
    public ContributionCreateDto getContribution() {
        return contributionCreateDto;
    }

    public static class ContributionBuilderDtoBuilder {

        public static final String MANDATORY_CONTRIBUTOR_AUDIT_DETAILS = "Missing mandatory contributor AuditDetails.";

        public static final String MISSING_MANDATORY_PRECEDING_VERSION_UID = "Missing mandatory precedingVersionUid.";
        public static final String INVALID_PRECEDING_VERSION_UID_IN_VERSION_CONTAINER =
                "Input invalid. Composition can't be modified without pointer to precedingVersionUid in Version container.";

        private final ContributionCreateDto contributionCreateDto = new ContributionCreateDto();

        /**
         * Instantiates a new Contribution.
         *
         * @param audit The audit for contribution and compositions inside
         */
        ContributionBuilderDtoBuilder(AuditDetails audit) {
            this.contributionCreateDto.setAudit(audit);
        }

        /**
         * Add Composition creation change type to contribution.
         *
         * @param composition Composition instance
         * @return the contribution create dto
         */
        public ContributionBuilderDtoBuilder addCompositionCreation(final Composition composition) {
            updateContribution(composition, CREATION, null);

            return this;
        }

        /**
         * Add composition modification change type to contribution.
         *
         * @param composition Locatable composition instance
         * @return the contribution create dto
         */
        public ContributionBuilderDtoBuilder addCompositionModification(final Composition composition) {
            updateContribution(composition, MODIFICATION, null);

            return this;
        }

        /**
         * Add composition modification change type to contribution.
         *
         * @param composition         the composition
         * @param precedingVersionUid the preceding version uid
         * @return the contribution create dto
         */
        public ContributionBuilderDtoBuilder addCompositionModification(
                final Composition composition, final String precedingVersionUid) {
            updateContribution(composition, MODIFICATION, precedingVersionUid);

            return this;
        }

        /**
         * Add composition deletion change type to contribution.
         *
         * @param composition         the composition instance
         * @param precedingVersionUid the preceding version uid
         * @return the contribution create dto
         */
        public ContributionBuilderDtoBuilder addCompositionDeletion(
                final Composition composition, final String precedingVersionUid) {
            updateContribution(composition, DELETED, precedingVersionUid);

            return this;
        }

        /**
         * Add composition deletion change type to contribution.
         * <p>
         * //         * @param composition the composition instance
         *
         * @param precedingVersionUid the preceding version uid
         * @return the contribution create dto
         */
        public ContributionBuilderDtoBuilder addCompositionDeletion(final String precedingVersionUid) {
            updateContribution(null, DELETED, precedingVersionUid);

            return this;
        }

        /**
         * Add composition deletion change type to contribution.
         *
         * @param composition the composition
         * @return the contribution create dto
         */
        public ContributionBuilderDtoBuilder addCompositionDeletion(final Composition composition) {
            updateContribution(composition, DELETED, null);

            return this;
        }

        /**
         * Add original version with composition creation change type to contribution.
         *
         * @param originalVersion the original version
         * @return the contribution create dto
         */
        public ContributionBuilderDtoBuilder addOriginalVersionCreation(
                final OriginalVersion<Locatable> originalVersion) {
            updateContribution(null, CREATION, null, originalVersion);

            return this;
        }

        /**
         * Add original version with composition modification change type to contribution.
         *
         * @param originalVersion     the original version
         * @param precedingVersionUid the preceding version uid
         * @return the contribution create dto
         */
        public ContributionBuilderDtoBuilder addOriginalVersionModification(
                final OriginalVersion<Locatable> originalVersion, final String precedingVersionUid) {
            updateContribution(null, MODIFICATION, precedingVersionUid, originalVersion);

            return this;
        }

        /**
         * Add original version with composition modification change type to contribution.
         *
         * @param originalVersion the original version
         * @return the contribution create dto
         */
        public ContributionBuilderDtoBuilder addOriginalVersionModification(
                final OriginalVersion<Locatable> originalVersion) {
            updateContribution(null, MODIFICATION, null, originalVersion);

            return this;
        }

        /**
         * Add original version with composition deletion change type to contribution.
         *
         * @param originalVersion     the original version
         * @param precedingVersionUid the preceding version uid
         * @return the contribution create dto
         */
        public ContributionBuilderDtoBuilder addOriginalVersionDeletion(
                final OriginalVersion<Locatable> originalVersion, final String precedingVersionUid) {
            updateContribution(null, DELETED, precedingVersionUid, originalVersion);

            return this;
        }

        private void updateContribution(
                Locatable composition, ContributionChangeType type, @Nullable String precedingVersionUid) {
            updateContribution(composition, type, precedingVersionUid, new OriginalVersion<>());
        }

        private void updateContribution(
                @Nullable Locatable composition,
                ContributionChangeType type,
                @Nullable String precedingVersionUid,
                OriginalVersion<Locatable> originalVersion) {

            if (originalVersion == null) {
                throw new IllegalArgumentException("Missing mandatory OriginalVersion Locatable.");
            }

            AuditDetails compositionAudit = getCompositionAudit();
            UIDBasedId versionId = null;
            if (composition != null) {
                versionId = composition.getUid();
            }

            precedingVersionUid = updateCompositionChangeType(type, precedingVersionUid, compositionAudit, versionId);

            if (isNotBlank(precedingVersionUid)) {
                originalVersion.setPrecedingVersionUid(new ObjectVersionId(precedingVersionUid));
            }

            if (originalVersion.getData() == null) {
                originalVersion.setData(composition);
            }

            updateMetadataById(precedingVersionUid, originalVersion, compositionAudit);

            originalVersion.setCommitAudit(compositionAudit);

            this.contributionCreateDto.getVersions().add(originalVersion);
        }

        private AuditDetails getCompositionAudit() {
            AuditDetails audit = this.contributionCreateDto.getAudit();

            if (audit == null) {
                throw new IllegalArgumentException(MANDATORY_CONTRIBUTOR_AUDIT_DETAILS);
            }

            return (AuditDetails) audit.clone();
        }

        private static String updateCompositionChangeType(
                ContributionChangeType type,
                String precedingVersionUid,
                AuditDetails compositionAudit,
                UIDBasedId versionId) {
            switch (type) {
                case CREATION: {
                    modifyCompositionChangeType(compositionAudit, CREATION);
                    break;
                }
                case AMENDMENT:
                case MODIFICATION: {
                    if (versionId != null && isNotBlank(versionId.getValue()) && isBlank(precedingVersionUid)) {
                        precedingVersionUid = versionId.getValue();
                    }

                    if (isBlank(precedingVersionUid)) {
                        throw new IllegalArgumentException(MISSING_MANDATORY_PRECEDING_VERSION_UID);
                    }

                    modifyCompositionChangeType(compositionAudit, MODIFICATION);
                    break;
                }
                case DELETED: {
                    if (versionId != null && isNotBlank(versionId.getValue()) && isBlank(precedingVersionUid)) {
                        precedingVersionUid = versionId.getValue();
                    }

                    if (isBlank(precedingVersionUid)) {
                        throw new IllegalArgumentException(MISSING_MANDATORY_PRECEDING_VERSION_UID);
                    }

                    modifyCompositionChangeType(compositionAudit, DELETED);
                    break;
                }
                default: {
                    modifyCompositionChangeType(compositionAudit, UNKNOWN);

                    break;
                }
            }

            return precedingVersionUid;
        }

        private static void updateMetadataById(
                String precedingVersionUid,
                OriginalVersion<Locatable> originalVersion,
                AuditDetails compositionAudit) {
            if (originalVersion.getData() == null) {
                // version doesn't contain "data", so it is only a metadata one to,
                // for instance, delete a specific object via ID regardless of type
                modifyCompositionChangeType(compositionAudit, DELETED);

                if (isBlank(precedingVersionUid)) {
                    throw new IllegalArgumentException(INVALID_PRECEDING_VERSION_UID_IN_VERSION_CONTAINER);
                }
            }
        }

        private static void modifyCompositionChangeType(AuditDetails audit, ContributionChangeType type) {
            audit.getChangeType().setValue(type.getName());
            audit.getChangeType().getDefiningCode().setCodeString(String.valueOf(type.getCode()));
        }

        /**
         * Build contribution.
         *
         * @return the contribution builder
         */
        public ContributionBuilder build() {
            return new ContributionBuilder(this.contributionCreateDto);
        }
    }
}
