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

import java.util.Optional;
import org.ehrbase.openehr.sdk.response.dto.TemplatesResponseData;
import org.ehrbase.openehr.sdk.util.exception.ClientException;
import org.ehrbase.openehr.sdk.util.exception.WrongStatusCodeException;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

public interface TemplateEndpoint {

    /**
     * Find a template by templateId
     *
     * @param templateId
     * @return {@link OPERATIONALTEMPLATE}
     * @throws ClientException
     * @throws WrongStatusCodeException
     */
    Optional<OPERATIONALTEMPLATE> findTemplate(String templateId);

    /**
     * Retrieves a list of templates
     *
     * @return
     */
    TemplatesResponseData findAllTemplates();

    /**
     * Ensure that the Template with {@code templateId} exists in the remote system.
     *
     * @param templateId Id of the template to check
     * @throws ClientException
     * @throws WrongStatusCodeException
     */
    void ensureExistence(String templateId);
}
