/*
 * Copyright (c) 2019 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.client.openehrclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nedap.archie.rm.changecontrol.Contribution;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import org.ehrbase.openehr.sdk.response.dto.ContributionCreateDto;

/**
 * The interface Contribution endpoint.
 */
public interface ContributionEndpoint {

    /**
     * Save a new Contribution to remote systems.
     *
     * @param contribution the contribution
     * @return the version uid
     * @throws JsonProcessingException the json processing exception
     */
    UUID saveContribution(ContributionCreateDto contribution) throws JsonProcessingException;

    /**
     *  Get the Contribution by {@code ehrId} and {@code contributionId}.
     *
     * @param contributionId the contribution id
     * @return the optional
     * @throws IOException the io exception
     */
    Optional<Contribution> find(UUID contributionId) throws IOException;
}
