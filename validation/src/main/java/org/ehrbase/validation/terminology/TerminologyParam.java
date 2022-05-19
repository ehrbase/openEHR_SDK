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

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.nedap.archie.rm.datatypes.CodePhrase;

public class TerminologyParam {
  static final Pattern PATTERN = Pattern.compile("(?<api>//[^/]*)/(?<type>CodeSystem|ValueSet)(?:/)?(?<op>\\$expand|\\$validate-code)?(?<param>\\?.*)?");
  /**
   * @param url fhir url of format:
   * </br> 
   * {@code //<some char>/<"CodeSystem" or "ValueSet">?<req-parameter>}
   * </br>
   * {@code //<some char>/<"CodeSystem" or "ValueSet">/<"$expand" or "$validate-code">?<req-parameter>}
   */
  public static TerminologyParam ofFhir(String url) {
    if(url == null)
      return new TerminologyParam();
    
    Matcher matcher = PATTERN.matcher(url);
    if(!matcher.matches())
      return new TerminologyParam();
    
    String api = matcher.group("api");
    String type = matcher.group("type");
    String op = matcher.group("op");
    String param = matcher.group("param");
    
    if(api == null)
      throw new RuntimeException("Missing service-api");
    
    TerminologyParam tp = new TerminologyParam(api);
    if("codesystem".equalsIgnoreCase(type)) tp.useCodeSystem();
    if("valueset".equalsIgnoreCase(type)) tp.useValueSet();
    tp.setOperation(op);
    tp.setParameter(param != null ? param.substring(1) : null);
    
    return tp;
  }
  
  public static TerminologyParam ofServiceApi(String api) {
    return new TerminologyParam(api);
  }

//  private String aqlPath;
  private String serviceApi;//should be checked in teh impl.
  private String operation;
  private boolean useValueSet = true;
  private boolean useCodeSystem = false;
  private String parameter;
  private CodePhrase codePhrase = null;

  private TerminologyParam() { }
  
  private TerminologyParam(String serviceApi) {
    this.serviceApi = serviceApi;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TerminologyParam that = (TerminologyParam) o;

    return new EqualsBuilder()
//      .append(aqlPath, that.aqlPath)
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
//      .append(aqlPath)
      .append(serviceApi)
      .append(operation)
      .append(useValueSet)
      .append(useCodeSystem)
      .append(parameter)
      .append(codePhrase)
      .hashCode();
  }  
  
//  public void setAqlPath(String path) {
//    this.aqlPath = path;
//  }
  
//  public String getAqlPath() {
//    return aqlPath;
//  }
  
  public void useValueSet() {
    useValueSet = true;
    useCodeSystem = false;
  }
  
  public void useCodeSystem() {
    useCodeSystem = true;
    useValueSet = false;
  }
  
  public Optional<String> getServiceApi() { return Optional.ofNullable(serviceApi); }
  
  public void setOperation(String op) {
    this.operation = op;
  }
  public Optional<String> getOperation() {
    return Optional.ofNullable(operation);
  }
  
  
  public Optional<CodePhrase> getCodePhrase() { return Optional.ofNullable(codePhrase); }
  public void setCodePhrase(CodePhrase cp) { this.codePhrase = cp; }
  public Optional<String> renderCodePhrase(Function<CodePhrase,Optional<String>> renderer) {
    return renderer.apply(codePhrase);
  }
  
  public boolean isUseValueSet() { return useValueSet; }
  public boolean isUseCodeSystem() { return useCodeSystem; }
  
  public void setParameter(String param) {
    this.parameter = param;
  }
  public Optional<String> getParameter() { return Optional.ofNullable(parameter); }
  public Optional<String> extractFromParameter(Function<String,Optional<String>> extractor) {
    return extractor.apply(parameter);
  }
}
