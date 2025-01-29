/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.terminology.openehr.implementation;

import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyResourceException;

/**
 * Factory for concrete terminology source implementation
 *
 * @author rong.chen
 */
public class TerminologySourceFactory {

    private static final String OPENEHR_TERMINOLOGY_EN = "openehr/en/openehr_terminology.xml";
    private static final String OPENEHR_TERMINOLOGY_JA = "openehr/ja/openehr_terminology.xml";
    private static final String OPENEHR_TERMINOLOGY_PT = "openehr/pt/openehr_terminology.xml";
    private static final String OPENEHR_TERMINOLOGY_ES = "openehr/es/openehr_terminology.xml";
    private static final String EXTERNAL_TERMINOLOGIES = "openehr/openehr_external_terminologies.xml";

    /**
     * Gets an instance of openEHR terminology source
     *
     * @return terminology source instance
     */
    static TerminologySource getOpenEHRTerminology(String language) throws TerminologyResourceException {
        switch (language) {
            case "en":
                return XMLTerminologySource.getInstance(OPENEHR_TERMINOLOGY_EN);
            case "ja":
                return XMLTerminologySource.getInstance(OPENEHR_TERMINOLOGY_JA);
            case "pt":
                return XMLTerminologySource.getInstance(OPENEHR_TERMINOLOGY_PT);
            case "es":
                return XMLTerminologySource.getInstance(OPENEHR_TERMINOLOGY_ES);
            default:
                return XMLTerminologySource.getInstance(OPENEHR_TERMINOLOGY_EN);
        }
    }

    static TerminologySource getOpenEHRTerminology() throws TerminologyResourceException {
        return getOpenEHRTerminology("en");
    }

    /**
     * Gets an instance of external terminologies source
     *
     * @return terminology source instance
     */
    static TerminologySource getExternalTerminologies(String language) throws TerminologyResourceException {
        return XMLTerminologySource.getInstance(EXTERNAL_TERMINOLOGIES);
    }

    public static TerminologySource getAttributeToGroupMappings() throws TerminologyResourceException {
        return XMLTerminologySource.getInstance(EXTERNAL_TERMINOLOGIES);
    }
}
/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is TerminologySourceFactory.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2007
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */
