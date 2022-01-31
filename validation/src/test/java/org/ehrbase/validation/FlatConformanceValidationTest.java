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

package org.ehrbase.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.validation.webtemplate.TestDataTemplateProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class FlatConformanceValidationTest {

  private final CompositionValidator validator = new CompositionValidator();

  public static final TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();

  @Test
  void roundTripEhrbaseConformanceDataTypesDvText() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_TEXT;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvCodedTextAsDvText() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_CODED_TEXT_AS_DV_TEXT;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvCodedText() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_CODED_TEXT;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvQuantity() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_QUANTITY;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvProportion() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_PROPORTION;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvCount() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_COUNT;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvDateTime() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvDate() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvTime() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_TIME;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvOrdinal() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_ORDINAL;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvBoolean() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_BOOLEAN;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvIdentifier() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_IDENTIFIER;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvUri() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_URI;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvEhrUri() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_EHR_URI;
    check(testData);
  }


  @Test
  void roundTripEhrbaseConformanceDataTypesDvParsable() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_PARSABLE;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceDataTypesDvMultimedia() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_MULTIMEDIA;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceElementNullFlavor() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_ELEMENT_NULL_FLAVOR;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceElementFeederAudit() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_ELEMENT_FEEDER_AUDIT;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceCluster() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_CLUSTER;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceComposition() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_COMPOSITION;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformancePointEvent() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_POINT_EVENT;
    check(testData);
  }

  @Test
  void roundTripEhrbaseConformanceIntervalEvent() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_INTERVAL_EVENT;
    check(testData);
  }


  @Test
  @Disabled(value = "see https://github.com/openEHR/archie/issues/379")
  void roundTripEhrbaseConformanceDataTypesIntervalDvDuration() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DURATION;
    check(testData);
  }


  @Test
  void roundTripEhrbaseConformanceDataTypesIntervalDvQuantity() throws IOException {
    var testData = CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_INTERVAL_DV_QUANTITY;
    check(testData);
  }

  private void check(CompositionTestDataSimSDTJson testData) throws IOException {
    String templateId = testData.getTemplate().getTemplateId();

    RMDataFormat cut = new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.SIM_SDT,
        templateId);

    String flat = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    var existingTemplate = templateProvider.find(templateId);
    assertTrue(existingTemplate.isPresent());

    var result = validator.validate(unmarshal, existingTemplate.get());
    assertTrue(result.isEmpty());
  }
}
