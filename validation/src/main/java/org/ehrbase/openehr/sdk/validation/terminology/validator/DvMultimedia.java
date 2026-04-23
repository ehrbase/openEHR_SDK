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
package org.ehrbase.openehr.sdk.validation.terminology.validator;

public class DvMultimedia extends TerminologyCheck {

    public DvMultimedia() {
        this.RM_CLASS = com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia.class;
    }

    public static void check(String context, com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia dvMultimedia)
            throws IllegalArgumentException {
        check(context, dvMultimedia, "en");
    }

    @SuppressWarnings("java:S1172")
    public static void check(
            String context, com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia dvMultimedia, String language)
            throws IllegalArgumentException {
        if (dvMultimedia.getIntegrityCheckAlgorithm() != null)
            validate("integrity_check_algorithm", dvMultimedia.getIntegrityCheckAlgorithm(), language);

        if (dvMultimedia.getCompressionAlgorithm() != null)
            validate("compression_algorithm", dvMultimedia.getCompressionAlgorithm(), language);

        if (dvMultimedia.getMediaType() != null) validate("media_type", dvMultimedia.getMediaType(), language);
    }
}
