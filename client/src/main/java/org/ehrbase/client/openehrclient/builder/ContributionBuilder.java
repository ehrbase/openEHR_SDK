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

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.generic.AuditDetails;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.ehrbase.response.openehr.ContributionCreateDto;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;

/**
 * The type Contribution builder.
 */
public class ContributionBuilder {
    private final ContributionCreateDto contributionCreateDto;

    /**
     * Builder contribution builder dto builder.
     *
     * @param audit the audit
     * @return the contribution builder dto builder
     */
    public static ContributionBuilderDtoBuilder builder(AuditDetails audit) {
        return new ContributionBuilderDtoBuilder(audit);
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
        /**
         * The constant MANDATORY_CONTRIBUTOR_AUDIT_DETAILS.
         */
        public static final String MANDATORY_CONTRIBUTOR_AUDIT_DETAILS = "Missing mandatory contributor AuditDetails.";
        /**
         * The Contribution create dto.
         */
        ContributionCreateDto contributionCreateDto = new ContributionCreateDto();

        /**
         * Instantiates a new Contribution builder dto builder.
         *
         * @param audit the audit
         */
        ContributionBuilderDtoBuilder(AuditDetails audit) {
            this.contributionCreateDto.setAudit(audit);
        }

        /**
         * Add composition creation contribution builder dto builder.
         *
         * @param composition the composition
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addCompositionCreation(final RMObject composition) {
            LinkedHashMap<String, Object> canonicalComposition = getCanonicalComposition(composition);
            updateContribution(canonicalComposition, null);

            return this;
        }

        /**
         * Add composition creation contribution builder dto builder.
         *
         * @param composition the composition
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addCompositionCreation(final Map<String, Object> composition) {
            updateContribution(composition, null);

            return this;
        }

        private void setAudit(OriginalVersion<Object> originalVersion) {
            AuditDetails audit = this.contributionCreateDto.getAudit();

            if (audit == null) {
                throw new IllegalArgumentException(MANDATORY_CONTRIBUTOR_AUDIT_DETAILS);
            }

            originalVersion.setCommitAudit(audit);
        }

        private void setOriginalVersion(
                OriginalVersion<LinkedHashMap<String, Object>> originalVersion, @Nullable String precedingVersionUid) {
            if (originalVersion != null) {

                AuditDetails audit;
                if (originalVersion.getCommitAudit() != null) {
                    audit = originalVersion.getCommitAudit();
                } else {
                    audit = this.contributionCreateDto.getAudit();
                }

                if (audit != null) {
                    originalVersion.setCommitAudit(audit);
                } else {
                    throw new IllegalArgumentException(MANDATORY_CONTRIBUTOR_AUDIT_DETAILS);
                }

                if (isNotBlank(precedingVersionUid)) {
                    originalVersion.setPrecedingVersionUid(new ObjectVersionId(precedingVersionUid));
                }

                this.contributionCreateDto.getVersions().add(originalVersion);
            }
        }

        /**
         * Add composition modification contribution builder dto builder.
         *
         * @param composition         the composition
         * @param precedingVersionUid the preceding version uid
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addCompositionModification(
                final RMObject composition, final String precedingVersionUid) {
            LinkedHashMap<String, Object> canonicalComposition = getCanonicalComposition(composition);
            updateContribution(canonicalComposition, precedingVersionUid);

            return this;
        }

        /**
         * Add composition modification contribution builder dto builder.
         *
         * @param composition         the composition
         * @param precedingVersionUid the preceding version uid
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addCompositionModification(
                final Map<String, Object> composition, final String precedingVersionUid) {
            updateContribution(composition, precedingVersionUid);

            return this;
        }

        /**
         * Add composition deletion contribution builder dto builder.
         *
         * @param composition         the composition
         * @param precedingVersionUid the preceding version uid
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addCompositionDeletion(
                final RMObject composition, final String precedingVersionUid) {
            LinkedHashMap<String, Object> canonicalComposition = getCanonicalComposition(composition);
            updateContribution(canonicalComposition, precedingVersionUid);

            return this;
        }

        /**
         * Add composition deletion contribution builder dto builder.
         *
         * @param composition         the composition
         * @param precedingVersionUid the preceding version uid
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addCompositionDeletion(
                final Map<String, Object> composition, final String precedingVersionUid) {
            updateContribution(composition, precedingVersionUid);

            return this;
        }

        private void updateContribution(Map<String, Object> composition, @Nullable String precedingVersionUid) {
            OriginalVersion<Object> originalVersion = new OriginalVersion<>();
            setAudit(originalVersion);
            if (isNotBlank(precedingVersionUid)) {
                originalVersion.setPrecedingVersionUid(new ObjectVersionId(precedingVersionUid));
            }
            originalVersion.setData(composition);

            this.contributionCreateDto.getVersions().add(originalVersion);
        }

        /**
         * Add original version creation contribution builder dto builder.
         *
         * @param originalVersion the original version
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addOriginalVersionCreation(
                final OriginalVersion<LinkedHashMap<String, Object>> originalVersion) {
            setOriginalVersion(originalVersion, null);

            return this;
        }

        /**
         * Add original version modification contribution builder dto builder.
         *
         * @param originalVersion     the original version
         * @param precedingVersionUid the preceding version uid
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addOriginalVersionModification(
                final OriginalVersion<LinkedHashMap<String, Object>> originalVersion,
                final String precedingVersionUid) {
            setOriginalVersion(originalVersion, precedingVersionUid);

            return this;
        }

        /**
         * Add original version deletion contribution builder dto builder.
         *
         * @param originalVersion     the original version
         * @param precedingVersionUid the preceding version uid
         * @return the contribution builder dto builder
         */
        public ContributionBuilderDtoBuilder addOriginalVersionDeletion(
                final OriginalVersion<LinkedHashMap<String, Object>> originalVersion,
                final String precedingVersionUid) {
            setOriginalVersion(originalVersion, precedingVersionUid);

            return this;
        }

        private static LinkedHashMap<String, Object> getCanonicalComposition(RMObject composition) {
            String compositionJson = new CanonicalJson().marshal(composition);

            return (LinkedHashMap<String, Object>) new CanonicalJson().unmarshalToMap(compositionJson);
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
