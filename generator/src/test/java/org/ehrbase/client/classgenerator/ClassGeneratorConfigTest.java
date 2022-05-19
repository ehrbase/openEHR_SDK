/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.client.classgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.util.Map;
import org.assertj.core.groups.Tuple;
import org.junit.Test;

public class ClassGeneratorConfigTest {

  @Test
  public void testParse() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    objectMapper.findAndRegisterModules();

    ClassGeneratorConfig actual =
        objectMapper.readValue(
            getClass().getResourceAsStream("/DefaultConfig.yaml"), ClassGeneratorConfig.class);

    assertThat(actual.getOptimizerSetting()).isEqualTo(OptimizerSetting.SECTION);
    assertThat(actual.isAddNullFlavor()).isTrue();
    assertThat(actual.getReplaceChars().entrySet())
        .extracting(Map.Entry::getKey, Map.Entry::getValue)
        .containsExactly(
            new Tuple('ä', "ae"),
            new Tuple('Ä', "Ae"),
            new Tuple('ö', "oe"),
            new Tuple('Ö', "OE"),
            new Tuple('ü', "ue"),
            new Tuple('Ü', "Ue"),
            new Tuple('ß', "ss"),
            new Tuple('æ', "ae"),
            new Tuple('ø', "oe"),
            new Tuple('å', "aa"));
  }

  @Test
  public void testParseLegacy() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    objectMapper.findAndRegisterModules();

    ClassGeneratorConfig actual =
        objectMapper.readValue(
            getClass().getResourceAsStream("/LegacyConfig.yaml"), ClassGeneratorConfig.class);

    assertThat(actual.getOptimizerSetting()).isEqualTo(OptimizerSetting.NONE);
    assertThat(actual.isAddNullFlavor()).isTrue();
    assertThat(actual.getReplaceChars().entrySet()).isEmpty();
  }
}
