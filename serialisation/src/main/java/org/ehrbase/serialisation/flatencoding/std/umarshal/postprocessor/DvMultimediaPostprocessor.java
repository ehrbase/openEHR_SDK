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

import static org.ehrbase.serialisation.walker.FlatHelper.filter;

import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import java.util.Map;
import java.util.Set;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

public class DvMultimediaPostprocessor extends AbstractUnmarshalPostprocessor<DvMultimedia> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term,
            DvMultimedia rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        Map<FlatPathDto, String> thumbnailValues = filter(values, term + "/_thumbnail", false);

        if (!thumbnailValues.isEmpty()) {
            rmObject.setThumbnail(new DvMultimedia());
            handleRmAttribute(term, rmObject.getThumbnail(), thumbnailValues, consumedPaths, context, "thumbnail");
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<DvMultimedia> getAssociatedClass() {
        return DvMultimedia.class;
    }
}
