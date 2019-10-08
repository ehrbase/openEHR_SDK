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
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.flattener.Unflattener;
import org.ehrbase.client.openehrclient.CompositionEndpoint;

import java.util.Optional;
import java.util.UUID;

import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient.httpGet;
import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient.httpPost;
import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestEhrEndpoint.EHR_PATH;

public class DefaultRestCompositionEndpoint implements CompositionEndpoint {
    public static final String COMPOSITION_PATH = "/composition/";
    private final DefaultRestClient defaultRestClient;
    private UUID ehrId;

    public DefaultRestCompositionEndpoint(DefaultRestClient defaultRestClient, UUID ehrId) {
        this.defaultRestClient = defaultRestClient;
        this.ehrId = ehrId;
    }

    @Override
    public UUID saveCompositionEntity(Object entity) {
        Composition composition = (Composition) new Unflattener(defaultRestClient.getTemplateProvider()).unflatten(entity);

        return httpPost(defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH + ehrId.toString() + COMPOSITION_PATH), composition);
    }

    @Override
    public <T> Optional<T> find(UUID compositionId, Class<T> clazz) {
        Optional<Composition> composition = httpGet(defaultRestClient.getConfig().getBaseUri().resolve(EHR_PATH + ehrId.toString() + COMPOSITION_PATH + compositionId.toString()), Composition.class);
        return composition
                .map(c -> new Flattener().flatten(c, clazz));
    }


}