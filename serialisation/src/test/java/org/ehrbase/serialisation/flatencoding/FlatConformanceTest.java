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

package org.ehrbase.serialisation.flatencoding;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidator;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.validation.Validator;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.ehrbase.serialisation.flatencoding.std.marshal.FlatJsonMarshallerTest.compere;

public class FlatConformanceTest {

  public static final TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvText() throws IOException {

    CompositionTestDataSimSDTJson testData =
        CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_TEXT;

    check(testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvCodedTextAsDvText() throws IOException {

    CompositionTestDataSimSDTJson testData =
        CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_CODED_TEXT_AS_DV_TEXT;

    check(testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvCodedText() throws IOException {

    CompositionTestDataSimSDTJson testData =
        CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_CODED_TEXT;

    check(testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvQuantity() throws IOException {

    CompositionTestDataSimSDTJson testData =
        CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_QUANTITY;

    check(testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvProportion() throws IOException {

    CompositionTestDataSimSDTJson testData =
            CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_PROPORTION;

    check(testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvCount() throws IOException {

    CompositionTestDataSimSDTJson testData =
            CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_COUNT;

    check(testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesIntervalDvQuantity() throws IOException {

    CompositionTestDataSimSDTJson testData =
            CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_INTERVAL_DV_QUANTITY;

    check(testData, new String[] {}, new String[] {});
  }

  private void check(
      CompositionTestDataSimSDTJson testData, String[] expectedMissing, String[] expectedExtra)
      throws IOException {

    String templateId = testData.getTemplate().getTemplateId();

    RMDataFormat cut =
        new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.SIM_SDT, templateId);

    String flat = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    SoftAssertions softAssertions = new SoftAssertions();

    softAssertions.assertThat(unmarshal).isNotNull();

    try {
      new Validator(templateProvider.find(templateId).get()).check(unmarshal);
    } catch (Exception e) {
      softAssertions.fail(e.getMessage());
    }

    RMObjectValidator rmObjectValidator =
        new RMObjectValidator(ArchieRMInfoLookup.getInstance(), s -> null);

    softAssertions.assertThat(rmObjectValidator.validate(unmarshal)).containsExactlyInAnyOrder();

    String actual = cut.marshal(unmarshal);

    String expected = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder(expectedMissing);

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra"))
        .containsExactlyInAnyOrder(expectedExtra);

    softAssertions.assertAll();
  }
}
