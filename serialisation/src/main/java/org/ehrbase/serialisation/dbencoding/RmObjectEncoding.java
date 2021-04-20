/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.serialisation.dbencoding;

import com.nedap.archie.rm.RMObject;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;

import java.util.Map;

public class RmObjectEncoding {

    private final RMObject rmObject;

    public RmObjectEncoding(RMObject rmObject) {
        this.rmObject = rmObject;
    }

    public Map<String, Object> toMap(){
        return new CanonicalJson().unmarshalToMap(new CanonicalJson().marshal(rmObject));
    }
}
