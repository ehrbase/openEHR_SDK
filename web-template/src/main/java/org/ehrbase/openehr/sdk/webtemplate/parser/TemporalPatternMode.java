/*
 * Copyright (c) 2026 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.webtemplate.parser;

/**
 * How the patterns of C_DATE, C_TIME and C_DATE_TIME constraints are treated.
 * <ul>
 *  <li>{@link #DISABLED}: values are not validated against patterns; unsupported pattern strings are ignored</li>
 *  <li>{@link #LENIENT}: values are validated; an unsupported pattern string is ignored and logged as a warning when
 *  the template is parsed</li>
 *  <li>{@link #STRICT}: values are validated; an unsupported pattern string rejects the template when it is parsed</li>
 * </ul>
 */
public enum TemporalPatternMode {
    DISABLED,
    LENIENT,
    STRICT
}
