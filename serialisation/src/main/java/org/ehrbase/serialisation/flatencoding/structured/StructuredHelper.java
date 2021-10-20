/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.serialisation.flatencoding.structured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.*;
import java.util.stream.Collectors;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class StructuredHelper {
  private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

  private StructuredHelper() {
    // NOP
  }

  public static String convertStructuredToFlat(String structuredString) {

    try {
        JsonNode jsonNode = OBJECT_MAPPER.readTree(structuredString);
        Map<String, JsonNode> convert = convertStructuredToFlat("", jsonNode);

        return OBJECT_MAPPER.writeValueAsString(convert.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().asText())));
    } catch (JsonProcessingException e) {

     throw  new SdkException(e.getMessage(),e);
    }

  }

  public static String convertFlatToStructured(String flatString){


      try {

          JsonNode jsonNode = null;
          jsonNode = OBJECT_MAPPER.readTree(flatString);

          Map<FlatPathDto, JsonNode> flatMap = new HashMap<>();
          for (Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields(); it.hasNext(); ) {
              Map.Entry<String, JsonNode> next = it.next();

              flatMap.put(new FlatPathDto(next.getKey()),next.getValue());

          }
          Map<String,Object> structuredMap = convertFlatToStructured(flatMap);

          Map.Entry<String, Object> root = structuredMap.entrySet().stream().findAny().orElseThrow();

          structuredMap.replace(root.getKey(),((List)root.getValue()).get(0));

          return OBJECT_MAPPER.writeValueAsString(structuredMap);
      } catch (JsonProcessingException e) {

          throw  new SdkException(e.getMessage(),e);
      }

  }

   private static Map<String,Object> convertFlatToStructured( Map<FlatPathDto, JsonNode> flatMap){

       Map<FlatPathDto, Map<FlatPathDto, JsonNode>> subMap = flatMap.entrySet().stream().collect(Collectors.groupingBy(e -> {
           FlatPathDto startFlatPathDto = new FlatPathDto();
           startFlatPathDto.setName(e.getKey().getName());
           startFlatPathDto.setCount(e.getKey().getCount());
           return startFlatPathDto;
       }, Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

       Map<String,Object> structured = new HashMap<>();

       subMap.forEach(
               (k,v) ->{

                   if(!structured.containsKey(k.getName())){
                       structured.put(k.getName(), new ArrayList<>());
                   }

                   List<Object> values = (List<Object>) structured.get(k.getName());


                   Map<String, Object> stringObjectMap = convertFlatToStructured(v.entrySet().stream().filter(e -> e.getKey().getChild() != null).collect(Collectors.toMap(e -> FlatPathDto.removeStart(e.getKey(), new FlatPathDto(k)), Map.Entry::getValue)));

                   if (!stringObjectMap.isEmpty()){
                       values.add(stringObjectMap);
                   }


                   Map<String, Object> collect = v.entrySet().stream().filter(e -> e.getKey().getChild() == null).collect(Collectors.toMap(e -> Optional.ofNullable(e.getKey().getAttributeName()).map(s -> "|"+s).orElse(""), Map.Entry::getValue));

                   if (collect.size() == 1 && collect.containsKey("")){
                       values.add(collect.entrySet().stream().findAny().map(Map.Entry::getValue).orElse(""));
                   }else if (!collect.isEmpty()){
                       values.add(collect);
                   }
               }
       );



       return structured;
   }

  private static Map<String, JsonNode> convertStructuredToFlat(String path, JsonNode jsonNode) {
    if (jsonNode instanceof ValueNode) {

      return Map.of(path, jsonNode);
    } else if (jsonNode instanceof ObjectNode) {
      Map<String, JsonNode> map = new HashMap<>();
      for (Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields(); it.hasNext(); ) {
        Map.Entry<String, JsonNode> field = it.next();
         final String newPath ;
          if(StringUtils.startsWith(field.getKey(),"|") || StringUtils.isBlank(path)){
               newPath = path + field.getKey();
          }else {
              newPath = path +PATH_DIVIDER + field.getKey();
          }
          map.putAll(convertStructuredToFlat(newPath  , field.getValue()));
      }
      return map;
    } else if (jsonNode instanceof ArrayNode) {
        Map<String, JsonNode> map = new HashMap<>();

      for (int i = 0; i < jsonNode.size(); i++){
          JsonNode child = jsonNode.get(i);
          final String newPath ;
          if (i >0 ){
               newPath = path + ":" + i;
          }else{
              newPath = path;
          }
          map.putAll(convertStructuredToFlat(newPath,child));
      }

        return map;
    }

    throw new SdkException(
        String.format("Unknown Structure %s", jsonNode.getClass().getSimpleName()));
  }
}
