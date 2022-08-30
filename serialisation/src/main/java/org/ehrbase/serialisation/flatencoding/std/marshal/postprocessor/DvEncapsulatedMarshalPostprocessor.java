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

import com.nedap.archie.rm.datavalues.encapsulated.DvEncapsulated;
import java.util.Map;
import org.ehrbase.serialisation.walker.Context;

public class DvEncapsulatedMarshalPostprocessor extends AbstractMarshalPostprocessor<DvEncapsulated> {

    /** {@inheritDoc} Adds the encoding information */
    @Override
    public void process(
            String term, DvEncapsulated rmObject, Map<String, Object> values, Context<Map<String, Object>> context) {

        if (rmObject.getLanguage() != null) {

            handleRmAttribute(term, rmObject.getLanguage(), values, context, "language");
        }

        if (rmObject.getCharset() != null) {

            handleRmAttribute(term, rmObject.getCharset(), values, context, "charset");
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<DvEncapsulated> getAssociatedClass() {
        return DvEncapsulated.class;
    }
}
