/*
 * Copyright (c) 2022 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.OptionFor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class StatusDiagnostischeSicherheitDvcodedtext implements StatusDiagnostischeSicherheitChoice {
    @Path("|defining_code")
    private DiagnostischeSicherheitDefiningcode diagnostischeSicherheitDefiningcode;

    public void setDiagnostischeSicherheitDefiningcode(
            DiagnostischeSicherheitDefiningcode diagnostischeSicherheitDefiningcode) {
        this.diagnostischeSicherheitDefiningcode = diagnostischeSicherheitDefiningcode;
    }

    public DiagnostischeSicherheitDefiningcode getDiagnostischeSicherheitDefiningcode() {
        return this.diagnostischeSicherheitDefiningcode;
    }
}
