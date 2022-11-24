/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.walker.defaultvalues.defaultinserter;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.serialisation.walker.RMHelper;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.model.WebTemplateNode;

public class CompositionValueInserter extends AbstractValueInserter<Composition> {
    @Override
    public void insert(Composition rmObject, DefaultValues defaultValues, WebTemplateNode node) {

        if (RMHelper.isEmpty(rmObject.getLanguage()) && defaultValues.containsDefaultValue(DefaultValuePath.LANGUAGE)) {
            rmObject.setLanguage(
                    defaultValues.getDefaultValue(DefaultValuePath.LANGUAGE).toCodePhrase());
        }

        if (RMHelper.isEmpty(rmObject.getTerritory())
                && defaultValues.containsDefaultValue(DefaultValuePath.TERRITORY)) {
            rmObject.setTerritory(
                    defaultValues.getDefaultValue(DefaultValuePath.TERRITORY).toCodePhrase());
        }

        if (RMHelper.isEmpty(rmObject.getComposer())
                && defaultValues.containsDefaultValue(DefaultValuePath.COMPOSER_SELF)) {

            rmObject.setComposer(new PartySelf());
        }

        if (rmObject.getComposer() instanceof PartySelf
                && defaultValues.containsDefaultValue(DefaultValuePath.COMPOSER_ID)) {

            rmObject.setComposer(buildPartyIdentified(
                    defaultValues,
                    DefaultValuePath.COMPOSER_NAME,
                    DefaultValuePath.COMPOSER_ID,
                    rmObject.getComposer()));
        }

        if (RMHelper.isEmpty(rmObject.getComposer())
                && (defaultValues.containsDefaultValue(DefaultValuePath.COMPOSER_NAME)
                        || defaultValues.containsDefaultValue(DefaultValuePath.COMPOSER_ID))) {

            rmObject.setComposer(buildPartyIdentified(
                    defaultValues,
                    DefaultValuePath.COMPOSER_NAME,
                    DefaultValuePath.COMPOSER_ID,
                    new PartyIdentified()));
        }

        if (rmObject.getContext() == null) {
            rmObject.setContext(new EventContext());
        }

        new EventContextValueInserter()
                .insert(rmObject.getContext(), defaultValues, FlatHelper.buildDummyChild("context", node));
    }

    @Override
    public Class<Composition> getAssociatedClass() {
        return Composition.class;
    }
}
