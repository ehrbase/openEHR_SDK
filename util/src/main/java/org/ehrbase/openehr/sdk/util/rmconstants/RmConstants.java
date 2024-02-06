/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.util.rmconstants;

public class RmConstants {
    
    private RmConstants() {}

    /**
     * @deprecated RM 1.1.0 will be introduced later
     */
    @Deprecated(forRemoval = false)
    public static final String RM_VERSION_1_1_0 = "1.1.0";

    public static final String RM_VERSION_1_0_4 = "1.0.4";
    /**
     * @deprecated typo in constant name
     */
    @Deprecated(forRemoval = true, since = "1.24.0")
    public static final String RM_VERSION_1_4_0 = RM_VERSION_1_0_4;

    public static final String RM_OBJECT = "RM_OBJECT";

    public static final String ORIGINAL_VERSION = "ORIGINAL_VERSION";
    public static final String AUDIT_DETAILS = "AUDIT_DETAILS";

    public static final String EHR = "EHR";
    public static final String EHR_STATUS = "EHR_STATUS";
    public static final String COMPOSITION = "COMPOSITION";
    public static final String SECTION = "SECTION";
    public static final String OBSERVATION = "OBSERVATION";
    public static final String EVALUATION = "EVALUATION";
    public static final String INSTRUCTION = "INSTRUCTION";
    public static final String ACTION = "ACTION";
    public static final String ADMIN_ENTRY = "ADMIN_ENTRY";

    public static final String HISTORY = "HISTORY";
    public static final String ITEM_TREE = "ITEM_TREE";
    public static final String ITEM_LIST = "ITEM_LIST";
    public static final String ITEM_SINGLE = "ITEM_SINGLE";
    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String ITEM_STRUCTURE = "ITEM_STRUCTURE";

    public static final String ACTIVITY = "ACTIVITY";
    public static final String ISM_TRANSITION = "ISM_TRANSITION";
    public static final String CLUSTER = "CLUSTER";
    public static final String ELEMENT = "ELEMENT";
    public static final String CODE_PHRASE = "CODE_PHRASE";
    public static final String PARTY_PROXY = "PARTY_PROXY";
    public static final String PARTY_SELF = "PARTY_SELF";
    public static final String PARTY_IDENTIFIED = "PARTY_IDENTIFIED";
    public static final String PARTY_RELATED = "PARTY_RELATED";
    public static final String EVENT = "EVENT";
    public static final String POINT_EVENT = "POINT_EVENT";
    public static final String INTERVAL_EVENT = "INTERVAL_EVENT";
    public static final String FEEDER_AUDIT = "FEEDER_AUDIT";

    public static final String DV_TEXT = "DV_TEXT";
    public static final String DV_CODED_TEXT = "DV_CODED_TEXT";

    public static final String DV_DURATION = "DV_DURATION";
    public static final String DV_INTERVAL = "DV_INTERVAL";

    public static final String DV_TIME = "DV_TIME";
    public static final String DV_DATE = "DV_DATE";
    public static final String DV_DATE_TIME = "DV_DATE_TIME";

    public static final String DV_BOOLEAN = "DV_BOOLEAN";
    public static final String DV_COUNT = "DV_COUNT";
    public static final String DV_IDENTIFIER = "DV_IDENTIFIER";
    public static final String DV_MULTIMEDIA = "DV_MULTIMEDIA";
    public static final String DV_PARSABLE = "DV_PARSABLE";
    public static final String DV_PROPORTION = "DV_PROPORTION";
    public static final String DV_QUANTITY = "DV_QUANTITY";
    public static final String DV_STATE = "DV_STATE";
    public static final String DV_URI = "DV_URI";
    public static final String DV_EHR_URI = "DV_EHR_URI";
    public static final String DV_ORDINAL = "DV_ORDINAL";
    /**
     * @deprecated will be introduced in RM 1.1.0
     */
    @Deprecated(forRemoval = false)
    public static final String DV_SCALE = "DV_SCALE";

    public static final String UID_BASED_ID = "UID_BASED_ID";
}
