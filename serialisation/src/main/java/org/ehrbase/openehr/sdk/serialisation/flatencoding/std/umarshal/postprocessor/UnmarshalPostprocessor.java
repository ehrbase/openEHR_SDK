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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal.postprocessor;

import com.nedap.archie.rm.RMObject;
import java.util.Map;
import java.util.Set;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.util.reflection.ClassDependent;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

public interface UnmarshalPostprocessor<T extends RMObject> extends ClassDependent<T> {

    /**
     * Adds or removes Values from {@code values} depending on {@code rmObject}.
     *
     * @param term current term in the unmarshal recursion.
     * @param rmObject current rmObject in the unmarshal recursion.
     * @param values current values in the unmarshal recursion.
     * @param consumedPaths
     * @param context
     */
    void process(
            String term,
            T rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context);
}
