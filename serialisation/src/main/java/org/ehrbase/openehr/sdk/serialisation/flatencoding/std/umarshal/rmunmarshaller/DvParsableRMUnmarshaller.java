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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal.rmunmarshaller;

import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import java.util.Map;
import java.util.Set;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

public class DvParsableRMUnmarshaller extends AbstractRMUnmarshaller<DvParsable> {

    /** {@inheritDoc} */
    @Override
    public Class<DvParsable> getAssociatedClass() {
        return DvParsable.class;
    }

    /** {@inheritDoc} */
    @Override
    public void handle(
            String currentTerm,
            DvParsable rmObject,
            Map<FlatPathDto, String> currentValues,
            Context<Map<FlatPathDto, String>> context,
            Set<String> consumedPaths) {

        setValue(currentTerm, "value", currentValues, rmObject::setValue, String.class, consumedPaths);

        if (rmObject.getValue() == null) {
            setValue(currentTerm, null, currentValues, rmObject::setValue, String.class, consumedPaths);
        }

        setValue(currentTerm, "formalism", currentValues, rmObject::setFormalism, String.class, consumedPaths);
    }
}
