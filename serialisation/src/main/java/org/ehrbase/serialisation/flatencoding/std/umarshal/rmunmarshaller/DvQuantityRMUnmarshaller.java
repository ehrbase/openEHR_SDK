/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller;

import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.*;

public class DvQuantityRMUnmarshaller extends AbstractRMUnmarshaller<DvQuantity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<DvQuantity> getAssociatedClass() {
        return DvQuantity.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(String currentTerm, DvQuantity rmObject, Map<FlatPathDto, String> currentValues, Context<Map<FlatPathDto, String>> context, Set<String> consumedPaths) {
        setValue(currentTerm, "magnitude", currentValues, rmObject::setMagnitude, Double.class, consumedPaths);
        setValue(currentTerm, "units", currentValues, rmObject::setUnits, String.class, consumedPaths);
        if (rmObject.getUnits() == null) {
            setValue(currentTerm, "unit", currentValues, rmObject::setUnits, String.class, consumedPaths);
        }

        if (StringUtils.isBlank(rmObject.getUnits())) {
            List<WebTemplateInputValue> units = Optional.ofNullable(context.getNodeDeque().peek().getInputs()).stream().flatMap(List::stream).filter(i -> "unit".equals(i.getSuffix())).findAny().map(WebTemplateInput::getList).orElse(Collections.emptyList());
            if (units.size() == 1) {
                rmObject.setUnits(units.get(0).getValue());
            }
        }
    }
}
