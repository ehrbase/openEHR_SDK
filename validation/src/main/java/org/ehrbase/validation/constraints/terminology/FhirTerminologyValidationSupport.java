/*
 * Copyright (c) 2021 Vitasystems GmbH.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.validation.constraints.terminology;

import com.google.common.net.HttpHeaders;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.nedap.archie.rm.datatypes.CodePhrase;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.ehrbase.validation.constraints.wrappers.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * {@link ExternalTerminologyValidationSupport} that supports FHIR terminology validation.
 */
public class FhirTerminologyValidationSupport implements ExternalTerminologyValidationSupport {

    private static final String CODE_SYSTEM_PREFIX = "terminology://fhir.hl7.org/CodeSystem";

    private static final String VALUE_SET_PREFIX = "terminology://fhir.hl7.org/ValueSet/$expand";

    private static final Logger LOG = LoggerFactory.getLogger(FhirTerminologyValidationSupport.class);

    private final String baseUrl;

    private final boolean failOnError;

    private final Executor executor;

    public FhirTerminologyValidationSupport(String baseUrl) {
        this(baseUrl, true);
    }

    public FhirTerminologyValidationSupport(String baseUrl, boolean failOnError) {
        this(baseUrl, failOnError, HttpClients.createDefault());
    }

    public FhirTerminologyValidationSupport(String baseUrl, boolean failOnError, HttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.failOnError = failOnError;
        executor = Executor.newInstance(httpClient);
    }

    /**
     * @see ExternalTerminologyValidationSupport#supports(String)
     */
    @Override
    public boolean supports(String referenceSetUri) {
        if (!isCodeSystem(referenceSetUri) && !isValueSet(referenceSetUri)) {
            return false;
        }

        DocumentContext context;
        try {
            context = internalGet(buildUri(referenceSetUri));
        } catch (IOException e) {
            if (failOnError) {
                throw new ExternalTerminologyValidationException("An error occurred while checking if FHIR terminology server " +
                        "supports the referenceSetUri: " + referenceSetUri, e);
            }
            LOG.warn("The following error occurred: {}", e.getMessage());
            return false;
        }
        int total = context.read("$.total", int.class);
        return total > 0;
    }

    /**
     * @see ExternalTerminologyValidationSupport#validate(String, String, CodePhrase)
     */
    @Override
    public void validate(String path, String referenceSetUri, CodePhrase codePhrase) {
        String url = extractUrl(referenceSetUri);
        if (!StringUtils.equals(url, codePhrase.getTerminologyId().getValue())) {
            ValidationException.raise(path, "CodePhrase terminology does not match, expected: " + url +
                    ", found: " + codePhrase.getTerminologyId().getValue(), "CODE_PHRASE_02");
        }

        if (isCodeSystem(referenceSetUri)) {
            validateCode(path, url, codePhrase);
        } else if (isValueSet(referenceSetUri)) {
            expandValueSet(path, url, codePhrase);
        }
    }

    /**
     * Validates that the code comes from the CodeSystem using <code>validate-code</code> method.
     *
     * @param url the URL of the CodeSystem
     * @param codePhrase the concept code
     */
    private void validateCode(String path, String url, CodePhrase codePhrase) {
        DocumentContext context;
        try {
            context = internalGet(baseUrl + "/CodeSystem/$validate-code?url=" + url + "&code=" + codePhrase.getCodeString());
        } catch (IOException e) {
            if (failOnError) {
                throw new ExternalTerminologyValidationException("An error occurred while validating the code in CodeSystem", e);
            }
            LOG.warn("An error occurred while validating the code in CodeSystem: {}", e.getMessage());
            return;
        }
        boolean result = context.read("$.parameter[0].valueBoolean", boolean.class);
        if (!result) {
            String message = context.read("$.parameter[1].valueString", String.class);
            ValidationException.raise(path, message, "CODE_PHRASE_03");
        }
    }

    /**
     * Validates that the code comes from the ValueSet using <code>$expand</code> method.
     *
     * @param url the URL of the ValueSet
     * @param codePhrase the concept code
     */
    private void expandValueSet(String path, String url, CodePhrase codePhrase) {
        DocumentContext context;
        try {
            context = internalGet(baseUrl + "/ValueSet/$expand?url=" + url);
        } catch (IOException e) {
            if (failOnError) {
                throw new ExternalTerminologyValidationException("An error occurred while expanding the ValueSet", e);
            }
            LOG.warn("An error occurred while expanding the ValueSet: {}", e.getMessage());
            return;
        }
        String[] codes = context.read("$.expansion.contains[*].code", String[].class);
        if (!Arrays.asList(codes).contains(codePhrase.getCodeString())) {
            ValidationException.raise(path, "CodePhrase codeString does not match any option, found: " + codePhrase.getCodeString(), "CODE_PHRASE_03");
        }
    }

    private boolean isCodeSystem(String referenceSetUri) {
        return StringUtils.startsWith(referenceSetUri, CODE_SYSTEM_PREFIX);
    }

    private boolean isValueSet(String referenceSetUri) {
        return StringUtils.startsWith(referenceSetUri, VALUE_SET_PREFIX);
    }

    private String buildUri(String referenceSetUri) {
        return baseUrl + (isCodeSystem(referenceSetUri) ? "/CodeSystem" : "/ValueSet" + "?url=" + extractUrl(referenceSetUri));
    }

    private String extractUrl(String referenceSetUri) {
        return StringUtils.substringAfter(referenceSetUri, "url=");
    }

    private DocumentContext internalGet(String uri) throws IOException {
        Request request = Request.Get(uri).addHeader(HttpHeaders.ACCEPT, "application/fhir+json");

        HttpResponse response = executor.execute(request).returnResponse();
        String responseBody = Optional.ofNullable(response.getEntity())
                .map(entity -> {
                    try {
                        return EntityUtils.toString(response.getEntity());
                    } catch (IOException e) {
                        return null;
                    }
                })
                .orElse("");

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            throw new ExternalTerminologyValidationException("Error response received from the terminology server. HTTP status: " + statusCode + ". Body: " + responseBody);
        }

        return JsonPath.parse(responseBody);
    }
}
