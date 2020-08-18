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

import java.util.Optional;

public interface TemplateProvider {

    Optional<OPERATIONALTEMPLATE> find(String templateId);

    default Optional<TemplateIntrospect> buildIntrospect(String templateId) {
        return find(templateId).map(TemplateIntrospect::new);
    }
}
