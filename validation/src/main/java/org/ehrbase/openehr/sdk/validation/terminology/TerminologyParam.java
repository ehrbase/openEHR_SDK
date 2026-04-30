/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation.terminology;

import com.nedap.archie.rm.datatypes.CodePhrase;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TerminologyParam {
    static final Pattern PATTERN = Pattern.compile(
            "(?<api>//[^/]*)/(?<type>CodeSystem|ValueSet)/?(?<op>\\$expand|\\$validate-code)?(?:\\?(?<param>.*))?");
    /**
     * @param url fhir url of format:
     * </br>
     * {@code //<some char>/<"CodeSystem" or "ValueSet">?<req-parameter>}
     * </br>
     * {@code //<some char>/<"CodeSystem" or "ValueSet">/<"$expand" or "$validate-code">?<req-parameter>}
     */
    public static TerminologyParam ofFhir(String url) {
        if (url == null) {
            return new TerminologyParam();
        }

        Matcher matcher = PATTERN.matcher(url);
        if (!matcher.matches()) {
            return new TerminologyParam();
        }

        TerminologyParam tp = new TerminologyParam(matcher.group("api"));

        // tp.useValueSet() is default
        if ("CodeSystem".equals(matcher.group("type"))) {
            tp.useCodeSystem();
        }

        tp.setOperation(matcher.group("op"));
        tp.setParameter(matcher.group("param"));

        return tp;
    }

    private String serviceApi;
    private String operation;
    private boolean useValueSet = true;
    private boolean useCodeSystem = false;
    private String parameter;
    private CodePhrase codePhrase = null;

    private TerminologyParam() {}

    private TerminologyParam(String serviceApi) {
        this.serviceApi = serviceApi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerminologyParam that = (TerminologyParam) o;

        return new EqualsBuilder()
                .append(serviceApi, that.serviceApi)
                .append(operation, that.operation)
                .append(useValueSet, that.useValueSet)
                .append(useCodeSystem, that.useCodeSystem)
                .append(parameter, that.parameter)
                .append(codePhrase, that.codePhrase)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(serviceApi)
                .append(operation)
                .append(useValueSet)
                .append(useCodeSystem)
                .append(parameter)
                .append(codePhrase)
                .hashCode();
    }

    public void useValueSet() {
        useValueSet = true;
        useCodeSystem = false;
    }

    public void useCodeSystem() {
        useCodeSystem = true;
        useValueSet = false;
    }

    public Optional<String> getServiceApi() {
        return Optional.ofNullable(serviceApi);
    }

    public void setOperation(String op) {
        this.operation = op;
    }

    public Optional<String> getOperation() {
        return Optional.ofNullable(operation);
    }

    public Optional<CodePhrase> getCodePhrase() {
        return Optional.ofNullable(codePhrase);
    }

    public void setCodePhrase(CodePhrase cp) {
        this.codePhrase = cp;
    }

    public boolean isUseValueSet() {
        return useValueSet;
    }

    public boolean isUseCodeSystem() {
        return useCodeSystem;
    }

    public void setParameter(String param) {
        this.parameter = param;
    }

    public Optional<String> getParameter() {
        return Optional.ofNullable(parameter);
    }

    public Optional<String> extractFromParameter(Function<String, Optional<String>> extractor) {
        return extractor.apply(parameter);
    }
}
