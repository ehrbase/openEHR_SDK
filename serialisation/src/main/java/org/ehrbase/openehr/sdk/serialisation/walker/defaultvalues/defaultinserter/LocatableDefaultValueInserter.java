/*
 * Copyright (c) 2021 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.defaultinserter;

import com.nedap.archie.rm.archetyped.Locatable;
import java.util.ArrayList;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

public class LocatableDefaultValueInserter extends AbstractValueInserter<Locatable> {
    @Override
    public void insert(Locatable rmObject, DefaultValues defaultValues, WebTemplateNode node) {

        if (defaultValues.containsDefaultValue(DefaultValuePath.LINKS)) {
            if (rmObject.getLinks() == null) {
                rmObject.setLinks(new ArrayList<>());
            }
            if (defaultValues.containsDefaultValue(DefaultValuePath.LINKS)) {
                rmObject.getLinks().addAll(defaultValues.getDefaultValue(DefaultValuePath.LINKS));
            }
        }
    }

    @Override
    public Class<Locatable> getAssociatedClass() {
        return Locatable.class;
    }
}
