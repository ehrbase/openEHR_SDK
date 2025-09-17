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

import static org.junit.Assert.assertTrue;

import java.util.Optional;
import javax.cache.Cache;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

public class CachedTemplateProviderTest {

    @Test
    public void find() {
        OPERATIONALTEMPLATE template = Mockito.mock(OPERATIONALTEMPLATE.class);
        Cache<String, OPERATIONALTEMPLATE> cache = Mockito.mock(Cache.class);
        TemplateProvider templateProvider = Mockito.mock(TemplateProvider.class);

        Mockito.when(templateProvider.find(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(template), Optional.empty());
        CachedTemplateProvider cut = new CachedTemplateProvider(templateProvider, cache);
        assertTrue(cut.find("ehrbase_blood_pressure_simple.de.v0").isPresent());
        Mockito.verify(templateProvider).find(ArgumentMatchers.anyString());
        Mockito.verify(cache).put(ArgumentMatchers.anyString(), ArgumentMatchers.eq(template));
        // read from Cache
        Mockito.when(cache.get(ArgumentMatchers.anyString())).thenReturn(template);
        assertTrue(cut.find("ehrbase_blood_pressure_simple.de.v0").isPresent());
    }
}
