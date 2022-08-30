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
package org.ehrbase.serialisation.flatencoding.std.marshal.postprocessor;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.composition.CareEntry;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import java.util.Map;
import java.util.Optional;
import org.ehrbase.serialisation.walker.Context;

public class CareEntryMarshalPostprocessor extends AbstractMarshalPostprocessor<CareEntry> {

    /** {@inheritDoc} Adds the encoding information */
    @Override
    public void process(
            String term, CareEntry rmObject, Map<String, Object> values, Context<Map<String, Object>> context) {
        values.put(term + PATH_DIVIDER + "encoding|code", "UTF-8");
        values.put(term + PATH_DIVIDER + "encoding|terminology", "IANA_character-sets");

        if (rmObject.getGuidelineId() != null) {

            addValue(
                    values,
                    term + "/_guideline_id",
                    "id",
                    Optional.of(rmObject.getGuidelineId())
                            .map(ObjectRef::getId)
                            .map(ObjectId::getValue)
                            .orElse(null));
            addValue(
                    values,
                    term + "/_guideline_id",
                    "id_scheme",
                    Optional.of(rmObject.getGuidelineId())
                            .map(ObjectRef::getId)
                            .filter(i -> GenericId.class.isAssignableFrom(i.getClass()))
                            .map(GenericId.class::cast)
                            .map(GenericId::getScheme)
                            .orElse(null));
            addValue(
                    values,
                    term + "/_guideline_id",
                    "namespace",
                    rmObject.getGuidelineId().getNamespace());
            addValue(
                    values,
                    term + "/_guideline_id",
                    "type",
                    rmObject.getGuidelineId().getType());
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<CareEntry> getAssociatedClass() {
        return CareEntry.class;
    }
}
