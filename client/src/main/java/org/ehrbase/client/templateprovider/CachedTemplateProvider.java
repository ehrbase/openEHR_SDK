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

package org.ehrbase.client.templateprovider;

import org.ehrbase.client.introspect.TemplateIntrospect;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

import javax.cache.Cache;
import java.util.Optional;

/**
 * Wraps a {@link TemplateProvider} to provide caching.
 */
public class CachedTemplateProvider implements TemplateProvider {

    private final TemplateProvider rootTemplateProvider;
    private final Cache<String, OPERATIONALTEMPLATE> templateCache;
    private final Cache<String, TemplateIntrospect> introspectCache;

    /**
     * @param rootTemplateProvider The warped {@link TemplateProvider}
     * @param templateCache        The {@link Cache} which is used for caching the templates.
     * @deprecated use {@link CachedTemplateProvider#CachedTemplateProvider(TemplateProvider, Cache, Cache)}
     */
    @Deprecated
    public CachedTemplateProvider(TemplateProvider rootTemplateProvider, Cache<String, OPERATIONALTEMPLATE> templateCache) {

        this.rootTemplateProvider = rootTemplateProvider;
        this.templateCache = templateCache;
        this.introspectCache = null;
    }

    /**
     * @param rootTemplateProvider The warped {@link TemplateProvider}
     * @param templateCache        The {@link Cache} which is used for caching the templates.
     * @param introspectCache      The {@link Cache} which is used for caching the templates.
     */
    public CachedTemplateProvider(TemplateProvider rootTemplateProvider, Cache<String, OPERATIONALTEMPLATE> templateCache, Cache<String, TemplateIntrospect> introspectCache) {
        this.rootTemplateProvider = rootTemplateProvider;
        this.templateCache = templateCache;
        this.introspectCache = introspectCache;
    }

    @Override
    public Optional<OPERATIONALTEMPLATE> find(String templateId) {

        Optional<OPERATIONALTEMPLATE> operationaltemplate = Optional.ofNullable(templateCache.get(templateId));

        if (!operationaltemplate.isPresent()) {
            operationaltemplate = rootTemplateProvider.find(templateId);
            operationaltemplate.ifPresent(o -> templateCache.put(templateId, o));
        }
        return operationaltemplate;
    }

    @Override
    public Optional<TemplateIntrospect> buildIntrospect(String templateId) {
        TemplateIntrospect templateIntrospect = introspectCache.get(templateId);
        if (templateIntrospect == null) {
            templateIntrospect = find(templateId).map(TemplateIntrospect::new).orElse(null);
        }

        return Optional.ofNullable(templateIntrospect);
    }
}
