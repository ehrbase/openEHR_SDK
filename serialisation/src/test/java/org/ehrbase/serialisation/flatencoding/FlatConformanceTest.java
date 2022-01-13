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
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import com.nedap.archie.rm.support.identification.TerminologyId;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidator;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.validation.Validator;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Period;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.serialisation.flatencoding.std.marshal.FlatJsonMarshallerTest.compere;

public class FlatConformanceTest {

  public static final TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvText() throws IOException {

    CompositionTestDataSimSDTJson testData =
        CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_TEXT;

    check(testData, new String[] {}, new String[] {}, new String[]{});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvCodedTextAsDvText() throws IOException {

    CompositionTestDataSimSDTJson testData =
        CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_CODED_TEXT_AS_DV_TEXT;

    check(testData, new String[] {}, new String[] {}, new String[]{});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvCodedText() throws IOException {

    CompositionTestDataSimSDTJson testData =
        CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_CODED_TEXT;

    check(testData, new String[] {}, new String[] {}, new String[]{});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvQuantity() throws IOException {

    CompositionTestDataSimSDTJson testData =
        CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_QUANTITY;

    check(testData, new String[] {}, new String[] {}, new String[]{});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvProportion() throws IOException {

    CompositionTestDataSimSDTJson testData =
            CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_PROPORTION;

    check(testData, new String[] {}, new String[] {}, new String[]{});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvCount() throws IOException {

    CompositionTestDataSimSDTJson testData =
            CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_COUNT;

    check(testData, new String[] {}, new String[] {}, new String[]{});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesDvDateTime() throws IOException {

    CompositionTestDataSimSDTJson testData =
            CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE_TIME;

    check(testData, new String[] {}, new String[] {}, new String[]{});
  }
  @Test
  public void roundTripEhrbaseConformanceDataTypesDvDate() throws IOException {

    CompositionTestDataSimSDTJson testData =
            CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DATE;

    check(testData, new String[] {}, new String[] {}, new String[]{});
  }
  @Test
  public void roundTripEhrbaseConformanceDataTypesDvTime() throws IOException {

    CompositionTestDataSimSDTJson testData =
            CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_TIME;

    check(testData, new String[] {}, new String[] {}, new String[]{});
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesIntervalDvDuration() throws IOException {

    CompositionTestDataSimSDTJson testData =
            CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_DV_DURATION;
// see https://github.com/openEHR/archie/issues/379
    check(testData, new String[] {}, new String[] {}, new String[]{"/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/data[at0001]/events[at0002, 1]/data[at0003]/items[at0018, 1]/value","/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/data[at0001]/events[at0002, 1]/data[at0003]/items[at0018, 1]/value/other_reference_ranges[1]/range/interval","/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/data[at0001]/events[at0002, 1]/data[at0003]/items[at0018, 1]/value/normal_range/interval"});
  }

  @Test
  @Ignore("see https://github.com/openEHR/archie/issues/379")
  public void validateDvDuration(){

    DvDuration dvDuration = new DvDuration(Period.of(10,5,5));
    dvDuration.setNormalStatus(new CodePhrase(new TerminologyId("openehr_normal_statuses"),"N"));

    dvDuration.setNormalRange(new DvInterval<>());
    dvDuration.getNormalRange().setLower(new DvDuration(Period.of(10,5,5)));
    dvDuration.getNormalRange().setUpper(new DvDuration(Period.of(10,6,5)));

    RMObjectValidator rmObjectValidator =
            new RMObjectValidator(ArchieRMInfoLookup.getInstance(), s -> null);

    assertThat(rmObjectValidator.validate(dvDuration)).isEmpty();
  }

  @Test
  public void roundTripEhrbaseConformanceDataTypesIntervalDvQuantity() throws IOException {

    CompositionTestDataSimSDTJson testData =
            CompositionTestDataSimSDTJson.EHRBASE_CONFORMANCE_DATA_TYPES_INTERVAL_DV_QUANTITY;

    check(testData, new String[] {}, new String[] {}, new String[]{});
  }

  private void check(
          CompositionTestDataSimSDTJson testData, String[] expectedMissing, String[] expectedExtra, String[] expectedValidationErrorPath)
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

    softAssertions.assertThat(rmObjectValidator.validate(unmarshal)).filteredOn(d -> !ArrayUtils.contains(expectedValidationErrorPath, d.getPath())).isEmpty();

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
