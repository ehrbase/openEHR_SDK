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

package org.ehrbase.client.std.postprozessor;

import com.nedap.archie.rm.composition.Entry;

import java.util.Map;

import static org.ehrbase.client.introspect.TemplateIntrospect.PATH_DIVIDER;

public class EntryPostprocessor extends AbstractPostprozessor<Entry> {

    @Override
    public void prozess(String term, Entry rmObject, Map<String, String> values) {
        consumedPath.add(term + PATH_DIVIDER + "encoding|code");
        consumedPath.add(term + PATH_DIVIDER + "encoding|terminology");
    }


    @Override
    public Class<Entry> getRMClass() {
        return Entry.class;
    }
}
