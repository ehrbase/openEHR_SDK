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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal.config;

import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import java.util.HashMap;
import java.util.Map;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;

public class DvQuantityConfig extends AbstractsStdConfig<DvQuantity> {

    /** {@inheritDoc} */
    @Override
    public Class<DvQuantity> getAssociatedClass() {
        return DvQuantity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Object> buildChildValues(
            String currentTerm, DvQuantity rmObject, Context<Map<String, Object>> context) {

        Map<String, Object> result = new HashMap<>();

        addValue(result, currentTerm, "magnitude", rmObject.getMagnitude());
        addValue(result, currentTerm, "precision", rmObject.getPrecision());
        addValue(result, currentTerm, "unit", rmObject.getUnits());
        addValue(result, currentTerm, "units_system", rmObject.getUnitsSystem());
        addValue(result, currentTerm, "units_display_name", rmObject.getUnitsDisplayName());

        return result;
    }
}
