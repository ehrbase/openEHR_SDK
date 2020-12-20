/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.webtemplate.templateprovider;

import static org.junit.Assert.assertTrue;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

public class CachedTemplateProviderTest {

  @Test
  public void find() {

    CachingProvider provider = Caching.getCachingProvider();
    CacheManager cacheManager = provider.getCacheManager();
    MutableConfiguration<String, OPERATIONALTEMPLATE> configuration =
        new MutableConfiguration<String, OPERATIONALTEMPLATE>()
            .setTypes(String.class, OPERATIONALTEMPLATE.class)
            .setStoreByValue(false);
    Cache<String, OPERATIONALTEMPLATE> cache = cacheManager.createCache("jCache", configuration);

    CachedTemplateProvider cut = new CachedTemplateProvider(new TestDataTemplateProvider(), cache);
    assertTrue(cut.find("ehrbase_blood_pressure_simple.de.v0").isPresent());
    // read from Cache
    assertTrue(cut.find("ehrbase_blood_pressure_simple.de.v0").isPresent());
  }
}
