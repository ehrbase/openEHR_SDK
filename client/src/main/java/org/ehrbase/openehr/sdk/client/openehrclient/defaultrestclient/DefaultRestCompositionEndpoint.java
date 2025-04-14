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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import static org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.DefaultRestEhrEndpoint.EHR_PATH;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.ehrbase.openehr.sdk.client.openehrclient.CompositionEndpoint;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Id;
import org.ehrbase.openehr.sdk.serialisation.dto.GeneratedDtoToRmConverter;
import org.ehrbase.openehr.sdk.serialisation.dto.RmToGeneratedDtoConverter;
import org.ehrbase.openehr.sdk.util.exception.ClientException;
import org.ehrbase.openehr.sdk.webtemplate.templateprovider.TemplateProvider;

public class DefaultRestCompositionEndpoint implements CompositionEndpoint {
    public static final String COMPOSITION_PATH = "/composition/";
    private final DefaultRestClient defaultRestClient;
    private final UUID ehrId;

    public DefaultRestCompositionEndpoint(DefaultRestClient defaultRestClient, UUID ehrId) {
        this.defaultRestClient = defaultRestClient;
        this.ehrId = ehrId;
    }

    static Optional<ObjectVersionId> extractVersionUid(Object entity) {
        return Arrays.stream(FieldUtils.getAllFields(entity.getClass()))
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findAny()
                .map(idField -> {
                    try {
                        PropertyDescriptor propertyDescriptor =
                                new PropertyDescriptor(idField.getName(), entity.getClass());
                        return (ObjectVersionId)
                                propertyDescriptor.getReadMethod().invoke(entity);
                    } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                });
    }

    @Override
    public <T> T mergeCompositionEntity(T entity) {
        Composition composition = (Composition) new GeneratedDtoToRmConverter(
                        defaultRestClient.getTemplateProvider(), defaultRestClient.getDefaultValuesProvider())
                .toRMObject(entity);

        Optional<ObjectVersionId> versionUid = extractVersionUid(entity);

        final ObjectVersionId updatedVersion = internalMerge(composition, versionUid.orElse(null));
        RmToGeneratedDtoConverter.addVersion(entity, updatedVersion);
        entity = (T)
                createFlattener(defaultRestClient.getTemplateProvider()).toGeneratedDto(composition, entity.getClass());
        RmToGeneratedDtoConverter.addVersion(entity, updatedVersion);

        return entity;
    }

    private ObjectVersionId internalMerge(Composition composition, ObjectVersionId versionUid) {
        final ObjectVersionId updatedVersion;
        if (versionUid == null) {
            updatedVersion = defaultRestClient.httpPost(
                    defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH + ehrId.toString() + COMPOSITION_PATH),
                    composition);
        } else {
            updatedVersion = defaultRestClient.httpPut(
                    defaultRestClient
                            .getConfig()
                            .getBaseUri()
                            .resolve(EHR_PATH
                                    + ehrId.toString()
                                    + COMPOSITION_PATH
                                    + versionUid.getObjectId().getValue()),
                    composition,
                    versionUid);
        }
        return updatedVersion;
    }

    @Override
    public ObjectVersionId mergeRaw(Composition composition) {

        Optional<ObjectVersionId> versionUid = Optional.ofNullable(composition.getUid())
                .map(ObjectId::toString)
                .map(ObjectVersionId::new);

        return internalMerge(composition, versionUid.orElse(null));
    }

    @Override
    public <T> Optional<T> find(UUID compositionId, Class<T> clazz) {
        Optional<Composition> composition = findRaw(compositionId);

        return composition.map(
                c -> createFlattener(defaultRestClient.getTemplateProvider()).toGeneratedDto(c, clazz));
    }

    @Override
    public Optional<Composition> findRaw(UUID compositionId) {
        return defaultRestClient.httpGet(
                defaultRestClient
                        .getConfig()
                        .getBaseUri()
                        .resolve(EHR_PATH + ehrId.toString() + COMPOSITION_PATH + compositionId.toString()),
                Composition.class);
    }

    @Override
    public void delete(ObjectVersionId precedingVersionUid) {
        if (precedingVersionUid == null) {
            throw new ClientException("precedingVersionUid mush not be null");
        }

        URI uri = defaultRestClient
                .getConfig()
                .getBaseUri()
                .resolve(EHR_PATH + ehrId.toString() + COMPOSITION_PATH + precedingVersionUid);
        defaultRestClient.internalDelete(uri, new HashMap<>());
    }

    protected RmToGeneratedDtoConverter createFlattener(TemplateProvider templateProvider) {
        return new RmToGeneratedDtoConverter(templateProvider);
    }
}
