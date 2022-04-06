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
package org.ehrbase.validation.terminology;

import static java.lang.String.format;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.ehrbase.functional.ExceptionalSupplier;
import org.ehrbase.validation.ConstraintViolation;
import org.ehrbase.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.HttpHeaders;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;

import net.minidev.json.JSONArray;

/**
 * {@link ExternalTerminologyValidation} that supports FHIR terminology validation.
 */
public class FhirTerminologyValidation implements ExternalTerminologyValidation {

  private static final String TERMINOLOGY_PREFIX = "terminology:";

  private static final String FHIR_CODE_SYSTEM = "//fhir.hl7.org/CodeSystem";

  private static final String FHIR_VALUE_SET = "//fhir.hl7.org/ValueSet/$expand";

  private static final Logger LOG = LoggerFactory.getLogger(FhirTerminologyValidation.class);

  private final String baseUrl;

  private final boolean failOnError;

  private final Executor executor;

  public FhirTerminologyValidation(String baseUrl) {
    this(baseUrl, true);
  }

  public FhirTerminologyValidation(String baseUrl, boolean failOnError) {
    this(baseUrl, failOnError, HttpClients.createDefault());
  }

  public FhirTerminologyValidation(String baseUrl, boolean failOnError, HttpClient httpClient) {
    this.baseUrl = baseUrl;
    this.failOnError = failOnError;
    executor = Executor.newInstance(httpClient);
  }

  /**
   * @see ExternalTerminologyValidation#supports(String)
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
        throw new ExternalTerminologyValidationException(
            "An error occurred while checking if FHIR terminology server " +
                "supports the referenceSetUri: " + referenceSetUri, e);
      }
      LOG.warn("The following error occurred: {}", e.getMessage());
      return false;
    }
    int total = context.read("$.total", int.class);
    return total > 0;
  }

  private static final String ERR_TERMINOLOGY_MISSMATCH = "The terminology %s must be %s";
  
  /**
   * @see ExternalTerminologyValidation#validate(String, String, CodePhrase)
   */
  @Override
  public void validate(String path, String referenceSetUri, CodePhrase codePhrase) {
    String url = extractUrl(referenceSetUri);
    
    if(!StringUtils.equals(url, codePhrase.getTerminologyId().getValue()))
      throw new ConstraintViolationException(List.of(
        new ConstraintViolation(path, format(ERR_TERMINOLOGY_MISSMATCH, codePhrase.getTerminologyId().getValue(), url))
      ));
    
    if(isCodeSystem(referenceSetUri))
      validateByCodeSys(path, url, codePhrase);
    else if(isValueSet(referenceSetUri))
      validateByValueSet(path, url, codePhrase);
  }

  
//-------------------------------------------
  static abstract class ValueSetConverter {
    private static final String CONTAINS = "$['expansion']['contains'][*]";
    private static final String SYS = "system";
    private static final String CODE = "code";
    private static final String DISP = "display";
    
    @SuppressWarnings("unchecked")
    static List<DvCodedText> convert(DocumentContext ctx) throws Exception {
      JSONArray read = ctx.read(CONTAINS);
      return read.stream()
        .map(e -> (Map<String,String>) e)
        .map(m -> new DvCodedText(m.get(DISP), new CodePhrase(new TerminologyId(m.get(SYS)), m.get(CODE))))
        .collect(Collectors.toList());
    }
  }
  
  private static final String ERR_EXPAND_VALUESET = "Error while expanding ValueSet[%s]";
  
  @Override
  public List<DvCodedText> expand(String path, String url) {
    try {
      DocumentContext jsonContext = internalGet(baseUrl + "/ValueSet/$expand?url=" + url);
      return ValueSetConverter.convert(jsonContext);
    } catch (Exception e) {
      if(failOnError)
        throw new ExternalTerminologyValidationException(format(ERR_EXPAND_VALUESET, e));
      LOG.warn(format(ERR_EXPAND_VALUESET, e.getMessage()));
      return Collections.emptyList();
    }
  }  
//-------------------------------------------  
  
  /**
   * Validates that the code comes from the CodeSystem using <code>validate-code</code> method.
   *
   * @param url        the URL of the CodeSystem
   * @param codePhrase the concept code
   */
  private void validateByCodeSys(String path, String url, CodePhrase codePhrase) {
    validateDocumentContext(path, () -> internalGet(baseUrl + "/CodeSystem/$validate-code?url=" + url + "&code=" + codePhrase.getCodeString()));
  }

  private void validateDocumentContext(String path, ExceptionalSupplier<DocumentContext,IOException> ctx) {
    try {
      DocumentContext context = ctx.doGet();
      if(!context.read("$.parameter[0].valueBoolean", boolean.class)) {
        ConstraintViolation constraintViolation = new ConstraintViolation(
          path,
          context.read("$.parameter[1].valueString", String.class));
        throw new ConstraintViolationException(List.of(constraintViolation));
      }
    } catch(IOException e) {
      if (failOnError)
        throw new ExternalTerminologyValidationException("An error occurred while validating the code in CodeSystem", e);
      LOG.warn("An error occurred while validating the code in CodeSystem: {}", e.getMessage());
      return;
    }
  }
  
  private static final String VALIDATE_BY_VALUESET_TEMPL = "%s/ValueSet/$validate-code?url=%s&code=%s&system=%s";
  
  private void validateByValueSet(String path, String url, CodePhrase codePhrase) {
    validateDocumentContext(path, () ->
      internalGet(format(VALIDATE_BY_VALUESET_TEMPL, baseUrl, url, codePhrase.getCodeString(), codePhrase.getTerminologyId().getValue())));
  }
  
  private boolean isCodeSystem(String referenceSetUri) {
    return StringUtils.startsWithAny(referenceSetUri, TERMINOLOGY_PREFIX + FHIR_CODE_SYSTEM,
        FHIR_CODE_SYSTEM);
  }

  private boolean isValueSet(String referenceSetUri) {
    return StringUtils.startsWithAny(referenceSetUri, TERMINOLOGY_PREFIX + FHIR_VALUE_SET,
        FHIR_VALUE_SET);
  }

  private String buildUri(String referenceSetUri) {
    return baseUrl + (isCodeSystem(referenceSetUri) ? "/CodeSystem"
        : "/ValueSet" + "?url=" + extractUrl(referenceSetUri));
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
      throw new ExternalTerminologyValidationException(
          "Error response received from the terminology server. HTTP status: " + statusCode
              + ". Body: " + responseBody);
    }

    return JsonPath.parse(responseBody);
  }
}
