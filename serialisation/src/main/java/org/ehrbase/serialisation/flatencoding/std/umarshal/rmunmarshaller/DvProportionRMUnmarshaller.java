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
package org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.model.ProportionType;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

public class DvProportionRMUnmarshaller extends AbstractRMUnmarshaller<DvProportion> {

    /** {@inheritDoc} */
    @Override
    public Class<DvProportion> getAssociatedClass() {
        return DvProportion.class;
    }

    /** {@inheritDoc} */
    @Override
    public void handle(
            String currentTerm,
            DvProportion rmObject,
            Map<FlatPathDto, String> currentValues,
            Context<Map<FlatPathDto, String>> context,
            Set<String> consumedPaths) {

        setValue(currentTerm, "numerator", currentValues, rmObject::setNumerator, Double.class, consumedPaths);
        setValue(currentTerm, "denominator", currentValues, rmObject::setDenominator, Double.class, consumedPaths);
        setValue(currentTerm, "type", currentValues, rmObject::setType, Long.class, consumedPaths);

        setValue(currentTerm, "precision", currentValues, rmObject::setPrecision, Long.class, consumedPaths);

        if (rmObject.getType() == null) {
            List<ProportionType> proportionTypes = context.getNodeDeque().peek().getProportionTypes();
            if (CollectionUtils.isNotEmpty(proportionTypes) && proportionTypes.size() == 1) {
                rmObject.setType(Long.valueOf(proportionTypes.get(0).getId()));
            }
        }

        // may contain magnitude
        FlatHelper.consumeAllMatching(currentTerm, currentValues, consumedPaths, true);
    }
}
