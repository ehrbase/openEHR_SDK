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

package org.ehrbase.client.building;

import org.openehr.schemas.v1.CCOMPLEXOBJECT;
import org.openehr.schemas.v1.CPRIMITIVEOBJECT;
import org.openehr.schemas.v1.CSINGLEATTRIBUTE;
import org.openehr.schemas.v1.CSTRING;

import java.util.Arrays;
import java.util.Optional;

public class OptNameHelper {

    private OptNameHelper() {
    }

    public static Optional<String> extractName(CCOMPLEXOBJECT ccomplexobject) {
        return Arrays.stream(ccomplexobject.getAttributesArray())
                .filter(a -> CSINGLEATTRIBUTE.class.isAssignableFrom(a.getClass()))
                .map(a -> (CSINGLEATTRIBUTE) a)
                .filter(a -> a.getRmAttributeName().equals("name"))
                .map(a -> a.getChildrenArray(0))
                .map(c -> (CCOMPLEXOBJECT) c)
                .map(c -> c.getAttributesArray(0))
                .map(a -> a.getChildrenArray(0))
                .map(p -> (CPRIMITIVEOBJECT) p)
                .map(CPRIMITIVEOBJECT::getItem)
                .map(s -> (CSTRING) s)
                .map(s -> s.getListArray(0))
                .findAny();
    }
}