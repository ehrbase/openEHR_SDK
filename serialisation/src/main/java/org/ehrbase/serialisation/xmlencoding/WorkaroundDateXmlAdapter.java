/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.xmlencoding;

import java.time.temporal.Temporal;
import org.ehrbase.serialisation.OpenEHRDateTimeParseUtils;

/**
 * This JAXB adapter is used to work around archie parsing date-time values that contain invalid dates (i.e. 2023-13, ignoring leap years)
 * @deprecated TODO: this should be removed when the issue is fixed in archie
 */
@Deprecated
public class WorkaroundDateXmlAdapter extends com.nedap.archie.xml.adapters.DateXmlAdapter {

    @Override
    public Temporal unmarshal(String stringValue) {
        return OpenEHRDateTimeParseUtils.parseDate(stringValue);
    }
}
