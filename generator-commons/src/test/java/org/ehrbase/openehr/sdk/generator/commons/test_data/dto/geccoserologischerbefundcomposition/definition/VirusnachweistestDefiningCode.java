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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.geccoserologischerbefundcomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;

public enum VirusnachweistestDefiningCode implements EnumValueSet {
    SARS_COV2_COVID19_IGM_AB_UNITS_VOLUME_IN_SERUM_OR_PLASMA_BY_IMMUNOASSAY(
            "SARS-CoV-2 (COVID-19) IgM Ab [Units/volume] in Serum or Plasma by Immunoassay", "", "LOINC", "94506-3"),

    SARS_COV2_COVID19_IGM_AB_PRESENCE_IN_SERUM_OR_PLASMA_BY_IMMUNOASSAY(
            "SARS-CoV-2 (COVID-19) IgM Ab [Presence] in Serum or Plasma by Immunoassay", "", "LOINC", "94564-2"),

    SARS_COV2_COVID19_IGG_AB_UNITS_VOLUME_IN_SERUM_OR_PLASMA_BY_IMMUNOASSAY(
            "SARS-CoV-2 (COVID-19) IgG Ab [Units/volume] in Serum or Plasma by Immunoassay", "", "LOINC", "94505-5"),

    SARS_COV2_COVID19_IGG_AB_PRESENCE_IN_SERUM_OR_PLASMA_BY_IMMUNOASSAY(
            "SARS-CoV-2 (COVID-19) IgG Ab [Presence] in Serum or Plasma by Immunoassay", "", "LOINC", "94563-4"),

    SARS_COV2_COVID19_IGA_AB_UNITS_VOLUME_IN_SERUM_OR_PLASMA_BY_IMMUNOASSAY(
            "SARS-CoV-2 (COVID-19) IgA Ab [Units/volume] in Serum or Plasma by Immunoassay", "", "LOINC", "94720-0"),

    SARS_COV2_COVID19_IGA_AB_PRESENCE_IN_SERUM_OR_PLASMA_BY_IMMUNOASSAY(
            "SARS-CoV-2 (COVID-19) IgA Ab [Presence] in Serum or Plasma by Immunoassay", "", "LOINC", "94562-6"),

    SARS_COV2_COVID19_AB_UNITS_VOLUME_IN_SERUM_OR_PLASMA_BY_IMMUNOASSAY(
            "SARS-CoV-2 (COVID-19) Ab [Units/volume] in Serum or Plasma by Immunoassay", "", "LOINC", "94769-7"),

    SARS_COV2_COVID19_AB_PRESENCE_IN_SERUM_OR_PLASMA_BY_IMMUNOASSAY(
            "SARS-CoV-2 (COVID-19) Ab [Presence] in Serum or Plasma by Immunoassay", "", "LOINC", "94762-2");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    VirusnachweistestDefiningCode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTerminologyId() {
        return this.terminologyId;
    }

    public String getCode() {
        return this.code;
    }
}
