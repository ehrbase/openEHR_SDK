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
package org.ehrbase.openehr.sdk.client.openehrclient.builder;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.generic.AuditDetails;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import com.nedap.archie.rm.support.identification.UIDBasedId;
import javax.annotation.Nullable;
import org.ehrbase.openehr.sdk.client.openehrclient.ContributionChangeType;
import org.ehrbase.openehr.sdk.response.dto.ContributionCreateDto;

public class ContributionBuilder {

    private static final String CONTRIBUTION_MUST_HAVE_AT_LEAST_ONE_VERSION_OBJECT =
            "Invalid Contribution, must have at least one Version object.";
    private static final String MISSING_ORIGINAL_VERSION = "Missing mandatory OriginalVersion Locatable.";
    private static final String MISSING_MANDATORY_CONTRIBUTOR_AUDIT_DETAILS =
            "Missing mandatory contributor AuditDetails.";
    private static final String MISSING_MANDATORY_PRECEDING_VERSION_UID = "Missing mandatory precedingVersionUid.";
    private static final String INVALID_PRECEDING_VERSION_UID_IN_VERSION_CONTAINER =
            "Input invalid. Composition can't be modified without pointer to precedingVersionUid in Version container.";

    public static ContributionBuilder builder(AuditDetails audit) {
        return new ContributionBuilder(audit);
    }

    private final ContributionCreateDto contributionCreateDto = new ContributionCreateDto();

    /**
     * Instantiates a new Contribution.
     *
     * @param audit The audit for contribution and compositions inside
     */
    ContributionBuilder(AuditDetails audit) {
        this.contributionCreateDto.setAudit(audit);
    }

    /**
     * Add Folder creation change type to contribution.
     *
     * @param folder Folder instance
     * @return the contribution create dto
     */
    public ContributionBuilder addFolderCreation(final Folder folder) {
        updateContribution(folder, ContributionChangeType.CREATION, null);

        return this;
    }

    /**
     * Add Folder deletion change type to contribution.
     *
     * @param folder Folder instance
     * @param precedingVersionUid the preceding version uid
     * @return the contribution create dto
     */
    public ContributionBuilder addFolderDeletion(final Folder folder, final String precedingVersionUid) {
        updateContribution(folder, ContributionChangeType.DELETED, precedingVersionUid);

        return this;
    }

    /**
     * Add Folder Modification change type to contribution.
     *
     * @param folder Folder instance
     * @return the contribution create dto
     */
    public ContributionBuilder addFolderModification(final Folder folder) {
        updateContribution(folder, ContributionChangeType.MODIFICATION, null);

        return this;
    }

    /**
     * Add Folder Modification change type to contribution.
     *
     * @param folder Folder instance
     * @param precedingVersionUid the preceding version uid
     * @return the contribution create dto
     */
    public ContributionBuilder addFolderModification(final Folder folder, final String precedingVersionUid) {
        updateContribution(folder, ContributionChangeType.MODIFICATION, precedingVersionUid);

        return this;
    }

    /**
     * Add Composition creation change type to contribution.
     *
     * @param composition Composition instance
     * @return the contribution create dto
     */
    public ContributionBuilder addCompositionCreation(final Composition composition) {
        updateContribution(composition, ContributionChangeType.CREATION, null);

        return this;
    }

    /**
     * Add composition modification change type to contribution.
     *
     * @param composition Locatable composition instance
     * @return the contribution create dto
     */
    public ContributionBuilder addCompositionModification(final Composition composition) {
        updateContribution(composition, ContributionChangeType.MODIFICATION, null);

        return this;
    }

    /**
     * Add composition modification change type to contribution.
     *
     * @param composition         the composition
     * @param precedingVersionUid the preceding version uid
     * @return the contribution create dto
     */
    public ContributionBuilder addCompositionModification(
            final Composition composition, final String precedingVersionUid) {
        updateContribution(composition, ContributionChangeType.MODIFICATION, precedingVersionUid);

        return this;
    }

    /**
     * Add composition deletion change type to contribution.
     * <p>
     *
     * @param precedingVersionUid the preceding version uid
     * @return the contribution create dto
     */
    public ContributionBuilder addCompositionDeletion(final String precedingVersionUid) {
        updateContribution(null, ContributionChangeType.DELETED, precedingVersionUid);

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
            throw new IllegalArgumentException(MISSING_ORIGINAL_VERSION);
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
            throw new IllegalArgumentException(MISSING_MANDATORY_CONTRIBUTOR_AUDIT_DETAILS);
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
                modifyCompositionChangeType(compositionAudit, ContributionChangeType.CREATION);
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

                modifyCompositionChangeType(compositionAudit, ContributionChangeType.MODIFICATION);
                break;
            }
            case DELETED: {
                if (isBlank(precedingVersionUid)) {
                    throw new IllegalArgumentException(MISSING_MANDATORY_PRECEDING_VERSION_UID);
                }

                modifyCompositionChangeType(compositionAudit, ContributionChangeType.DELETED);
                break;
            }
            default: {
                modifyCompositionChangeType(compositionAudit, ContributionChangeType.UNKNOWN);

                break;
            }
        }

        return precedingVersionUid;
    }

    private static void updateMetadataById(
            String precedingVersionUid, OriginalVersion<Locatable> originalVersion, AuditDetails compositionAudit) {
        if (originalVersion.getData() == null) {
            // version doesn't contain "data", so it is only a metadata one to,
            // for instance, delete a specific object via ID regardless of type
            modifyCompositionChangeType(compositionAudit, ContributionChangeType.DELETED);

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
    public ContributionCreateDto build() {
        if (contributionCreateDto.getVersions() == null
                || contributionCreateDto.getVersions().isEmpty()) {
            throw new IllegalArgumentException(CONTRIBUTION_MUST_HAVE_AT_LEAST_ONE_VERSION_OBJECT);
        }

        return this.contributionCreateDto;
    }
}
