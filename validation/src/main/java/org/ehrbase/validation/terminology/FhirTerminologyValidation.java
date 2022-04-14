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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
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
import org.ehrbase.functional.Try;
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
  
  static final String SUPPORTS_CODE_SYS_TEMPL = "%s/CodeSystem?url=%s";
  static final String SUPPORTS_VALUE_SET_TEMPL = "%s/ValueSet?url=%s";
  private static final String ERR_SUPPORTS = "An error occurred while checking if FHIR terminology server supports the referenceSetUri: %s";
  static final String VALIDATE_CODE_SYS_TEMPL = "%s/CodeSystem/$validate-code?%s";
  static final String VALIDATE_VALUE_SET_TEMPL = "%s/ValueSet/$validate-code?%s";
  static final String CODE_PHRASE_TEMPL = "code=%s&system=%s";
  private static final String ERR_EXPAND_VALUESET = "Error while expanding ValueSet[%s]";
  static final String EXPAND_VALUE_SET_TEMPL = "%s/ValueSet/$expand?%s";
  
  static String renderTempl(String templ, String...args) {
    return format(templ, args);
  }
  
  @SuppressWarnings({ "serial" })
  private final Set<String> acceptedFhirApis = new HashSet<>() {
    {
      add("//fhir.hl7.org");
      add("terminology://fhir.hl7.org");
      add("//hl7.org/fhir");
    }
    
    public String toString() {
      return this.stream().collect(Collectors.joining(", "));
    }
  };
  
  private static Function<CodePhrase,Optional<String>> codePhraseToString = cp -> {
    if(cp == null) return Optional.empty();
    return Optional.of(renderTempl(CODE_PHRASE_TEMPL, cp.getCodeString(), cp.getTerminologyId().getValue()));
  };  
  
  private boolean isValidTerminology(Optional<String> url) {
    if(url.isEmpty())
      return false;
    return acceptedFhirApis.stream()
      .filter(api -> url.get().startsWith(api))
      .map(api -> Boolean.TRUE)
      .findFirst()
      .orElse(Boolean.FALSE);
  }
  
  @Override
  public boolean supports(TerminologyParam param) {
    String url = null;
    Optional<String> urlParam = param.extractFromParameter(p -> Optional.ofNullable(extractUrl(p)));
    if(urlParam.isEmpty() || !isValidTerminology(param.getServiceApi()))
      return false;
    
    if(param.isUseValueSet())
      url = renderTempl(SUPPORTS_VALUE_SET_TEMPL, baseUrl, urlParam.get());
    else if(param.isUseCodeSystem())
      url = renderTempl(SUPPORTS_CODE_SYS_TEMPL, baseUrl, urlParam.get());
    else
      return false;
    
    try {
      return internalGet(url).read("$.total", int.class) > 0;
    } catch (IOException e) {
      if(failOnError)
        throw new ExternalTerminologyValidationException(format(ERR_SUPPORTS, url), e);
      LOG.warn("The following error occurred: {}", e.getMessage());
      return false;
    }
  }
  
  @Override
  public Try<Boolean,ConstraintViolationException> validate(TerminologyParam param) {
    //sanity checks
    if(param.getServiceApi().isEmpty() || !isValidTerminology(param.getServiceApi())) {
      return Try.failure(new ConstraintViolationException(List.of(
        new ConstraintViolation(MessageFormat.format("Service-Api {0} must be {1}", param.getServiceApi(), acceptedFhirApis.toString()))
      )));
    }
    
    if(param.extractFromParameter(p -> Optional.ofNullable(extractUrl(p))).isEmpty()) {
      return Try.failure(new ConstraintViolationException(List.of(
        new ConstraintViolation("Missing value-set url")
      )));
    }
    
    List<String> reqParam = new ArrayList<>();
    param.renderCodePhrase(codePhraseToString).ifPresent(r -> reqParam.add(r));
    param.getParameter().ifPresent(r -> reqParam.add(r));
    
    String urlParam = reqParam.stream().collect(Collectors.joining("&"));
    String url = null;
    
    if(param.isUseValueSet())
      url = renderTempl(VALIDATE_VALUE_SET_TEMPL, baseUrl, urlParam);
    else if(param.isUseCodeSystem())
      url = renderTempl(VALIDATE_CODE_SYS_TEMPL, baseUrl, urlParam);
    else
      return Try.failure(new ConstraintViolationException(List.of(
          new ConstraintViolation("Either CodeSet or ValueSet must be set"))));
    
    String _url = url;
    return validateDocumentContext(() -> internalGet(_url));
  }
  
  static String guaranteePrefix(String prefix, String str) {
    if(StringUtils.isEmpty(str)) return null;
    else if(str.contains(prefix))
      return str;
    else
      return prefix + str;
  }
  
  @Override
  public List<DvCodedText> expand(TerminologyParam param) {
    //sanity checks
    if(param.getServiceApi().isEmpty() || !isValidTerminology(param.getServiceApi())) {
      LOG.warn("Unsupported service-api: {}", param.getServiceApi());
      return Collections.emptyList();
    }
    
    Optional<String> urlParam = param.extractFromParameter(p -> Optional.ofNullable(guaranteePrefix("url=", p)));

    if(urlParam.isEmpty() || param.getServiceApi().isEmpty() || !isValidTerminology(param.getServiceApi())) {
      return Collections.emptyList();
    }

    try {
      DocumentContext jsonContext = internalGet(renderTempl(EXPAND_VALUE_SET_TEMPL, baseUrl, urlParam.get()));
      return ValueSetConverter.convert(jsonContext);
    } catch (Exception e) {
      if(failOnError)
        throw new ExternalTerminologyValidationException(format(ERR_EXPAND_VALUESET, e));
      LOG.warn(format(ERR_EXPAND_VALUESET, e.getMessage()));
      return Collections.emptyList();
    }
  }
  
  private Try<Boolean,ConstraintViolationException> validateDocumentContext(ExceptionalSupplier<DocumentContext,IOException> ctx) {
    try {
      DocumentContext docCtx = ctx.doGet();
      
      Boolean result = docCtx.read("$.parameter[0].valueBoolean", boolean.class);
      if(!result)
        return Try.failure(new ConstraintViolationException(List.of(new ConstraintViolation(docCtx.read("$.parameter[1].valueString", String.class)))));
      else
        return Try.success(Boolean.TRUE);
    } catch(IOException e) {
      if(failOnError)
        throw new ExternalTerminologyValidationException("An error occurred while validating the code in CodeSystem", e);
      LOG.warn("An error occurred while validating the code in CodeSystem: {}", e.getMessage());
      return Try.success(Boolean.FALSE);
    }
  }
  
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
}
