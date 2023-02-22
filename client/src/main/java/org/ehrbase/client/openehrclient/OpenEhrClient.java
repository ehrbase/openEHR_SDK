/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.client.openehrclient;

import java.util.UUID;

public interface OpenEhrClient {

    /**
     * Get the {@link EhrEndpoint}
     *
     * @return
     */
    EhrEndpoint ehrEndpoint();

    /**
     * Get the {@link CompositionEndpoint} for ehr with Id {@code ehrId}
     *
     * @param ehrId ehrId of ehr for which to revive compositions
     * @return
     */
    CompositionEndpoint compositionEndpoint(UUID ehrId);

    /**
     * Get the {@link ContributionEndpoint} for ehr with Id {@code ehrId}
     *
     * @param ehrId ehrId of ehr for which to revive contributions
     * @return
     */
    ContributionEndpoint contributionEndpoint(UUID ehrId);

    FolderDAO folder(UUID ehrId, String path);

    /**
     * Get the {@link TemplateEndpoint}
     *
     * @return
     */
    TemplateEndpoint templateEndpoint();

    /**
     * Get the {@link AqlEndpoint}
     *
     * @return
     */
    AqlEndpoint aqlEndpoint();

    /**
     * Get the {@link AdminEhrEndpoint}
     *
     * @return
     */
    AdminEhrEndpoint adminEhrEndpoint();

    AdminTemplateEndpoint adminTemplateEndpoint();

    /**
     * Get the {@link VersionedCompositionEndpoint}.
     *
     * @param ehrId the EHR identifier
     * @return the endpoint
     */
    VersionedCompositionEndpoint versionedCompositionEndpoint(UUID ehrId);
}
