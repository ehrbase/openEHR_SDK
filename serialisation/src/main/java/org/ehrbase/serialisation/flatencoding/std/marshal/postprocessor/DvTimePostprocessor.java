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

import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import java.util.Map;
import org.ehrbase.serialisation.OpenEHRDateTimeSerializationUtils;
import org.ehrbase.serialisation.walker.Context;

public class DvTimePostprocessor extends AbstractMarshalPostprocessor<DvTime> {

    /** {@inheritDoc} Adds the encoding information */
    @Override
    public void process(
            String term, DvTime rmObject, Map<String, Object> values, Context<Map<String, Object>> context) {
        // Serialize temporal accessor here, if part of values, to avoid archie serialization
        values.computeIfPresent(term, (k, v) -> OpenEHRDateTimeSerializationUtils.formatTime(rmObject.getValue()));
    }

    /** {@inheritDoc} */
    @Override
    public Class<DvTime> getAssociatedClass() {
        return DvTime.class;
    }
}
