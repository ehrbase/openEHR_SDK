/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.webtemplate;

import care.better.platform.web.template.converter.CompositionConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.serialisation.flatencoding.FlatJson;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.openehr.schemas.v1.TemplateDocument;

public class CompositionConverterImp implements CompositionConverter {

  public static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

  @Override
  public String convertRawToFlat(String template, String defaultLanguage, String rawComposition)
      throws Exception {
    Composition unmarshal = new CanonicalJson().unmarshal(rawComposition.replace("@class","_type"), Composition.class);

    return getFlatJson(template).marshal(unmarshal);
  }

  @Override
  public String convertRawToStructured(
      String template, String defaultLanguage, String rawComposition) throws Exception {
    throw  new UnsupportedOperationException();
  }

  @Override
  public String convertFlatToRaw(
      String template,
      String defaultTemplateLanguage,
      String flatComposition,
      Map<String, Object> compositionBuilderContext)
      throws Exception {
    FlatJson flatJson = getFlatJson(template);
    Map<String, Object> currentValues = new HashMap<>();
    for (Iterator<Map.Entry<String, JsonNode>> it =
            OBJECT_MAPPER.readTree(flatComposition).fields();
        it.hasNext(); ) {
      Map.Entry<String, JsonNode> e = it.next();
      currentValues.put(e.getKey(), e.getValue().asText());
    }

    compositionBuilderContext.forEach((k, v) -> currentValues.put(replace(k), v));
    Composition composition = flatJson.unmarshal(OBJECT_MAPPER.writeValueAsString(currentValues));
    return new CanonicalJson().marshal(composition).replace("_type","@class");
  }

  private FlatJson getFlatJson(String template) throws XmlException, IOException {
    TemplateDocument templateDocument =
        TemplateDocument.Factory.parse(IOUtils.toInputStream(template, StandardCharsets.UTF_8));

    FlatJson flatJson =
        new FlatJasonProvider(t -> Optional.ofNullable(templateDocument.getTemplate()))
            .buildFlatJson(
                FlatFormat.SIM_SDT, templateDocument.getTemplate().getTemplateId().getValue());
    return flatJson;
  }

  private String replace(String k) {
    if (k.equals("composerName")) {
      return "ctx/composer_name";
    }else    if (k.equals("start_time")) {
      return "ctx/time";
    }
    return "ctx/" + k;
  }

  @Override
  public String convertFlatToStructured(
      String template,
      String defaultLanguage,
      String flatComposition,
      Map<String, Object> compositionBuilderContext)
      throws Exception {
    throw  new UnsupportedOperationException();
  }

  @Override
  public String convertStructuredToRaw(
      String template,
      String defaultLanguage,
      String structuredComposition,
      Map<String, Object> compositionBuilderContext)
      throws Exception {
    throw  new UnsupportedOperationException();
  }

  @Override
  public String convertStructuredToFlat(
      String template,
      String defaultLanguage,
      String structuredComposition,
      Map<String, Object> compositionBuilderContext)
      throws Exception {
    throw  new UnsupportedOperationException();
  }

  @Override
  public String updateRawComposition(
      String template,
      String defaultLanguage,
      String rawComposition,
      Map<String, Object> compositionBuilderContext,
      Map<String, Object> deltaValues)
      throws Exception {
    throw  new UnsupportedOperationException();
  }
}
