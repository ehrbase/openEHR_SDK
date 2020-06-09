/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.composition.Composition;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.flattener.Unflattener;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.VersionUid;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient.*;
import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestEhrEndpoint.EHR_PATH;

public class DefaultRestCompositionEndpoint implements CompositionEndpoint {
    public static final String COMPOSITION_PATH = "/composition/";
    private final DefaultRestClient defaultRestClient;
    private final UUID ehrId;

    public DefaultRestCompositionEndpoint(DefaultRestClient defaultRestClient, UUID ehrId) {
        this.defaultRestClient = defaultRestClient;
        this.ehrId = ehrId;
    }

    static <T> void addVersion(T entity, VersionUid versionUid) {
        Optional<Field> idField = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findAny();
        if (idField.isPresent()) {
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(idField.get().getName(), entity.getClass());
                propertyDescriptor.getWriteMethod().invoke(entity, versionUid);
            } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                throw new ClientException(e.getMessage(), e);
            }
        }
    }

    static Optional<VersionUid> extractVersionUid(Object entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findAny()
                .map(idField -> {
                            try {
                                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(idField.getName(), entity.getClass());
                                return (VersionUid) propertyDescriptor.getReadMethod().invoke(entity);
                            } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                                throw new ClientException(e.getMessage(), e);
                            }
                        }
                );
    }

    @Override
    public <T> T mergeCompositionEntity(T entity) {
        Composition composition = (Composition) new Unflattener(defaultRestClient.getTemplateProvider()).unflatten(entity);

        Optional<VersionUid> versionUid = extractVersionUid(entity);

        final VersionUid updatedVersion;
        if (versionUid.isEmpty()) {
            updatedVersion = httpPost(defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH + ehrId.toString() + COMPOSITION_PATH), composition);
        } else {
            updatedVersion = httpPut(defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH + ehrId.toString() + COMPOSITION_PATH + versionUid.get().getUuid()), composition, versionUid.get());
        }
        addVersion(entity, updatedVersion);

        return entity;
    }

    @Override
    public <T> Optional<T> find(UUID compositionId, Class<T> clazz) {
        Optional<Composition> composition = httpGet(defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH + ehrId.toString() + COMPOSITION_PATH + compositionId.toString()), Composition.class);
        Optional<T> t = composition
                .map(c -> new Flattener().flatten(c, clazz));
        if (t.isPresent()) {
            addVersion(t.get(), new VersionUid(composition.get().getUid().getValue()));
        }
        return t;
    }


}