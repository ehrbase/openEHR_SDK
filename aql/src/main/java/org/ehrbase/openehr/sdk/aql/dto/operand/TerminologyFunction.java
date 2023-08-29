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
package org.ehrbase.openehr.sdk.aql.dto.operand;

import java.util.Objects;

/**
 * @author Stefan Spiska
 */
public class TerminologyFunction implements MatchesOperand, FunctionCall {

    private String operation;
    private String serviceApi;

    private String UriParameters;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getServiceApi() {
        return serviceApi;
    }

    public void setServiceApi(String serviceApi) {
        this.serviceApi = serviceApi;
    }

    public String getUriParameters() {
        return UriParameters;
    }

    public void setUriParameters(String uriParameters) {
        UriParameters = uriParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TerminologyFunction that = (TerminologyFunction) o;
        return Objects.equals(operation, that.operation)
                && Objects.equals(serviceApi, that.serviceApi)
                && Objects.equals(UriParameters, that.UriParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, serviceApi, UriParameters);
    }

    @Override
    public String toString() {
        return "TerminologyFunction{" + "operation='"
                + operation + '\'' + ", serviceApi='"
                + serviceApi + '\'' + ", UriParameters='"
                + UriParameters + '\'' + '}';
    }
}
