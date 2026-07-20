/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.webtemplate.templateprovider;

import java.util.Optional;
import javax.cache.Cache;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplate;
import org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

/**
 * Wraps a {@link TemplateProvider} to provide caching.
 */
public class CachedTemplateProvider implements TemplateProvider {

    private final TemplateProvider rootTemplateProvider;
    private final Cache<String, OPERATIONALTEMPLATE> optCache;
    private final Cache<String, WebTemplate> webTemplateCache;

    /**
     * @param rootTemplateProvider The warped {@link TemplateProvider}
     * @param optCache        The {@link Cache} which is used for caching the templates.
     * @deprecated use {@link CachedTemplateProvider#CachedTemplateProvider(TemplateProvider, Cache, Cache)}
     */
    @Deprecated
    public CachedTemplateProvider(TemplateProvider rootTemplateProvider, Cache<String, OPERATIONALTEMPLATE> optCache) {

        this.rootTemplateProvider = rootTemplateProvider;
        this.optCache = optCache;
        this.webTemplateCache = null;
    }

    /**
     * @param rootTemplateProvider The warped {@link TemplateProvider}
     * @param optCache        The {@link Cache} which is used for caching the templates.
     * @param internalWebTemplateCache      The {@link Cache} which is used for caching the templates.
     */
    public CachedTemplateProvider(
            TemplateProvider rootTemplateProvider,
            Cache<String, OPERATIONALTEMPLATE> optCache,
            Cache<String, WebTemplate> internalWebTemplateCache) {
        this.rootTemplateProvider = rootTemplateProvider;
        this.optCache = optCache;
        this.webTemplateCache = internalWebTemplateCache;
    }

    @Override
    public Optional<OPERATIONALTEMPLATE> find(String templateId) {
        OPERATIONALTEMPLATE tpl = optCache.get(templateId);
        if (tpl != null) {
            return Optional.of(tpl);
        }
        var opt = rootTemplateProvider.find(templateId);
        opt.ifPresent(o -> optCache.put(templateId, o));
        return opt;
    }

    @Override
    public Optional<WebTemplate> buildIntrospect(String templateId) {
        if (webTemplateCache != null) {
            WebTemplate wtp = webTemplateCache.get(templateId);
            if (wtp != null) {
                return Optional.of(wtp);
            }
        }
        Optional<WebTemplate> parsed = find(templateId).map(OPTParser::parse);

        if (webTemplateCache != null) {
            parsed.ifPresent(t -> webTemplateCache.put(templateId, t));
        }

        return parsed;
    }
}
