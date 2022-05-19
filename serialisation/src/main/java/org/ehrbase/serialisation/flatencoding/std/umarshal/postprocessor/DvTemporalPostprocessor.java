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
package org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTemporal;
import java.util.Map;
import java.util.Set;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

public class DvTemporalPostprocessor extends AbstractUnmarshalPostprocessor<DvTemporal> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term,
            DvTemporal rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        Map<FlatPathDto, String> filter = FlatHelper.filter(values, term + "/_accuracy", false);
        if (!filter.isEmpty()) {
            rmObject.setAccuracy(new DvDuration());
            handleRmAttribute(term, rmObject.getAccuracy(), filter, consumedPaths, context, "accuracy");
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<DvTemporal> getAssociatedClass() {
        return DvTemporal.class;
    }
}
