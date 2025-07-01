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
package org.ehrbase.openehr.sdk.webtemplate.path.flat;

import static org.ehrbase.openehr.sdk.util.CharSequenceHelper.removeStart;
import static org.ehrbase.openehr.sdk.util.CharSequenceHelper.splitFirst;

import org.apache.commons.lang3.StringUtils;

public final class FlatPathParser {

    private FlatPathParser() {}

    public static FlatPathDto parse(CharSequence path) {
        if (StringUtils.equals("/", path)) {
            return new FlatPathDto(null, null, null, null);
        }

        String name;
        FlatPathDto child = null;
        String attributeName = null;
        Integer count = null;

        CharSequence[] tempSplit;
        CharSequence tempSubPath;

        // extract Children
        tempSplit = splitFirst(removeStart(path, "/"), '/');
        tempSubPath = tempSplit[0];

        if (tempSplit.length > 1) {
            child = parse(tempSplit[1]);
        }

        // extract AttributeName
        tempSplit = splitFirst(tempSubPath, '|');
        tempSubPath = tempSplit[0];

        if (tempSplit.length > 1) {
            attributeName = tempSplit[1].toString();
        }

        // extract Count
        tempSplit = splitFirst(tempSubPath, ':');
        tempSubPath = tempSplit[0];

        if (tempSplit.length > 1) {
            count = Integer.parseUnsignedInt(tempSplit[1], 0, tempSplit[1].length(), 10);
        }

        // Rest is the name
        name = tempSubPath.toString();

        return new FlatPathDto(name, child, count, attributeName);
    }
}
