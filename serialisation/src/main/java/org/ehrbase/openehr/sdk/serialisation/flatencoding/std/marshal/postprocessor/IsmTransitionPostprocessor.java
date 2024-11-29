/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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

import com.nedap.archie.rm.composition.IsmTransition;
import java.util.Map;
import java.util.stream.IntStream;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;

public class IsmTransitionPostprocessor extends AbstractMarshalPostprocessor<IsmTransition> {

    /** {@inheritDoc} Adds the encoding information */
    @Override
    public void process(
            String term, IsmTransition rmObject, Map<String, Object> values, Context<Map<String, Object>> context) {

        IntStream.range(0, rmObject.getReason().size()).forEach(i -> {
            callMarshal(
                    term,
                    "_reason:" + i,
                    rmObject.getReason().get(i),
                    values,
                    context,
                    context.getNodeDeque().peek().findChildById("mapping").orElse(null));
            callPostprocess(
                    term,
                    "_reason:" + i,
                    rmObject.getReason().get(i),
                    values,
                    context,
                    context.getNodeDeque().peek().findChildById("mapping").orElse(null));
        });
    }

    /** {@inheritDoc} */
    @Override
    public Class<IsmTransition> getAssociatedClass() {
        return IsmTransition.class;
    }
}
