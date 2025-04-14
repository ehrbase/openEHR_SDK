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
package org.ehrbase.openehr.sdk.client.openehrclient;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.util.Optional;
import java.util.UUID;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Template;
import org.ehrbase.openehr.sdk.util.exception.ClientException;
import org.ehrbase.openehr.sdk.util.exception.WrongStatusCodeException;

public interface CompositionEndpoint {

    /**
     * Save a Flat-Entity to remote systems.
     *
     * @param entity Flat-Entity to save. Has to be annotated with {@link Template}
     * @return CompositionId
     * @throws ClientException
     * @throws WrongStatusCodeException
     */
    <T> T mergeCompositionEntity(T entity);

    ObjectVersionId mergeRaw(Composition composition);

    /**
     * Finds a Flat-Entity by
     *
     * @param compositionId CompositionId of the flat-Entity to retrieve.
     * @param clazz         class of the flat-Entity to retrieve. Has to be annotated with {@link Template}
     * @return The Flat-Entity
     * @throws ClientException
     * @throws WrongStatusCodeException
     */
    <T> Optional<T> find(UUID compositionId, Class<T> clazz);

    Optional<Composition> findRaw(UUID compositionId);

    /**
     * Deletes a Composition by preceding version uid.
     *
     * @param precedingVersionUid identifier of the Composition to be deleted.
     *                            This MUST be the last (most recent) version.
     * @throws ClientException
     * @throws WrongStatusCodeException
     */
    void delete(ObjectVersionId precedingVersionUid);
}
