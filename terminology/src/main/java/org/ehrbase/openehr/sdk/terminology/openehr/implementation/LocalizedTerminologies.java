/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.terminology.openehr.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyInterface;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyResourceException;

public class LocalizedTerminologies {

    private final Map<String, TerminologyInterface> terminologies = new HashMap<>();

    public LocalizedTerminologies() throws TerminologyResourceException {
        terminologies.put("en", new SimpleTerminologyInterface("en"));
        terminologies.put("ja", new SimpleTerminologyInterface("ja"));
        terminologies.put("pt", new SimpleTerminologyInterface("pt"));
        terminologies.put("es", new SimpleTerminologyInterface("es"));
    }

    public TerminologyInterface locale(String language) {
        return Optional.of(language).map(terminologies::get).orElse(getDefault());
    }

    public TerminologyInterface getDefault() {
        return terminologies.get("en");
    }
}
