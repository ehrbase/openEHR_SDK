/*
 * Copyright (c) 2020 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal.postprocessor;

import com.nedap.archie.rm.datavalues.quantity.DvAmount;
import java.util.Map;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;

public class DvAmountPostprocessor extends AbstractMarshalPostprocessor<DvAmount> {

    /** {@inheritDoc} Adds the encoding information */
    @Override
    public void process(
            String term, DvAmount rmObject, Map<String, Object> values, Context<Map<String, Object>> context) {

        addValue(values, term, "accuracy", rmObject.getAccuracy());

        addValue(values, term, "accuracy_is_percent", rmObject.getAccuracyIsPercent());
    }

    /** {@inheritDoc} */
    @Override
    public Class<DvAmount> getAssociatedClass() {
        return DvAmount.class;
    }
}
