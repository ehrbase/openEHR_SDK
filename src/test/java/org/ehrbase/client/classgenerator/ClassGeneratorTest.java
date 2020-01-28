/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.classgenerator;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.groups.Tuple;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class ClassGeneratorTest {

    @Test
    public void testGenerate() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream()).getTemplate();
        ClassGenerator cut = new ClassGenerator();
        ClassGeneratorResult generate = cut.generate("org.ehrbase.client.classgenerator.examples", template);


        List<FieldSpec> fieldSpecs = generate.getClasses().values().stream()
                .flatMap(Collection::stream)
                .filter(t -> !t.kind.equals(TypeSpec.Kind.ENUM))
                .map(t -> t.fieldSpecs).flatMap(List::stream).collect(Collectors.toList());


        assertThat(fieldSpecs)
                .extracting(f -> f.name, f -> f.type.toString())
                .containsExactlyInAnyOrder(
                        new Tuple("device", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
                        new Tuple("language", "org.ehrbase.client.classgenerator.examples.shareddefinition.Language"),
                        new Tuple("levelOfExertion", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
                        new Tuple("commentValue", "java.lang.String"),
                        new Tuple("cuffSizeDefiningcode", "org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.CuffSizeDefiningcode"),
                        new Tuple("korotkoffSoundsDefiningcode", "org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.KorotkoffSoundsDefiningcode"),
                        new Tuple("systolicMagnitude", "java.lang.Double"),
                        new Tuple("systolicUnits", "java.lang.String"),
                        new Tuple("diastolicMagnitude", "java.lang.Double"),
                        new Tuple("diastolicUnits", "java.lang.String"),
                        new Tuple("positionDefiningcode", "org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.PositionDefiningcode"),
                        new Tuple("tiltMagnitude", "java.lang.Double"),
                        new Tuple("tiltUnits", "java.lang.String"),
                        new Tuple("meanArterialPressureMagnitude", "java.lang.Double"),
                        new Tuple("meanArterialPressureUnits", "java.lang.String"),
                        new Tuple("timeValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("subjectExternalref", "com.nedap.archie.rm.support.identification.PartyRef"),
                        new Tuple("originValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("pulsePressureMagnitude", "java.lang.Double"),
                        new Tuple("pulsePressureUnits", "java.lang.String"),
                        new Tuple("locationOfMeasurementDefiningcode", "org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.LocationOfMeasurementDefiningcode"),
                        new Tuple("modelValue", "java.lang.String"),
                        new Tuple("serialNumberValue", "java.lang.String"),
                        new Tuple("dateLastServicedValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("dateLastCalibrationValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("nameValue", "java.lang.String"),
                        new Tuple("descriptionValue", "java.lang.String"),
                        new Tuple("components", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
                        new Tuple("servicedByValue", "java.lang.String"),
                        new Tuple("manufacturerValue", "java.lang.String"),
                        new Tuple("endTimeValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("language", "org.ehrbase.client.classgenerator.examples.shareddefinition.Language"),
                        new Tuple("healthCareFacility", "com.nedap.archie.rm.generic.PartyIdentified"),
                        new Tuple("composerExternalref", "com.nedap.archie.rm.support.identification.PartyRef"),
                        new Tuple("settingDefiningcode", "org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode"),
                        new Tuple("territory", "org.ehrbase.client.classgenerator.examples.shareddefinition.Territory"),
                        new Tuple("bloodPressureTrainingSample", "java.util.List<org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.BloodPressureTrainingSample>"),
                        new Tuple("location", "java.lang.String"),
                        new Tuple("deviceDetailsTrainingSample", "java.util.List<org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0.definition.DeviceDetailsTrainingSample>"),
                        new Tuple("startTimeValue", "java.time.temporal.TemporalAccessor")
                );


        //generate.createFiles(Paths.get(".", "src/test/java/"));


    }
/*
    @Test
    public void testGenerateMultiOccurrence() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.MULTI_OCCURRENCE.getStream()).getTemplate();
        ClassGenerator cut = new ClassGenerator();
        TypeSpec generate = cut.generate(template);


        List<FieldSpec> fieldSpecs = generate.typeSpecs.stream().map(t -> t.fieldSpecs).flatMap(List::stream).collect(Collectors.toList());
        fieldSpecs.addAll(generate.fieldSpecs);
        assertThat(fieldSpecs)
                .extracting(f -> f.name, f -> f.type.toString())
                .containsExactlyInAnyOrder(
                        new Tuple("extension", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
                        new Tuple("language", "com.nedap.archie.rm.datatypes.CodePhrase"),
                        new Tuple("structuredMeasurementLocation", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>"),
                        new Tuple("originValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("device", "com.nedap.archie.rm.datastructures.Cluster"),
                        new Tuple("subjectExternalref", "com.nedap.archie.rm.support.identification.PartyRef"),
                        new Tuple("locationOfMeasurement", "ProtocolLocationOfMeasurementChoice"),
                        new Tuple("history", "java.util.List<BodyTemperatureHistory>"),
                        new Tuple("endTimeValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("language", "com.nedap.archie.rm.datatypes.CodePhrase"),
                        new Tuple("healthCareFacility", "com.nedap.archie.rm.generic.PartyIdentified"),
                        new Tuple("composerExternalref", "com.nedap.archie.rm.support.identification.PartyRef"),
                        new Tuple("settingDefiningcode", "com.nedap.archie.rm.datatypes.CodePhrase"),
                        new Tuple("territory", "com.nedap.archie.rm.datatypes.CodePhrase"),
                        new Tuple("bodyTemperature", "java.util.List<BodyTemperature>"),
                        new Tuple("location", "java.lang.String"),
                        new Tuple("startTimeValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("extension", "java.util.List<com.nedap.archie.rm.datastructures.Cluster>")
                );


        JavaFile javaFile = JavaFile.builder("org.ehrbase.client.classgenerator", generate)
                .build();

        //  javaFile.writeTo(Paths.get(".", "src/test/java/"));

        StringWriter stringWriter = new StringWriter();
        javaFile.writeTo(stringWriter);
        String actual = stringWriter.toString();
        System.out.println(actual);
        assertTrue(StringUtils.isNotBlank(actual));

    }

    @Test
    public void testGenerateAllTypes() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.ALL_TYPES.getStream()).getTemplate();
        ClassGenerator cut = new ClassGenerator();
        TypeSpec generate = cut.generate(template);

        List<FieldSpec> fieldSpecs = generate.typeSpecs.stream().map(t -> t.fieldSpecs).flatMap(List::stream).collect(Collectors.toList());
        fieldSpecs.addAll(generate.fieldSpecs);

        assertThat(fieldSpecs)
                .extracting(f -> f.name, f -> f.type.toString())
                .containsExactlyInAnyOrder(
                        new Tuple("intervalQuantity", "com.nedap.archie.rm.datavalues.quantity.DvInterval"),
                        new Tuple("text2Value", "java.lang.String"),
                        new Tuple("intervalCount", "com.nedap.archie.rm.datavalues.quantity.DvInterval"),
                        new Tuple("intervalDatetime", "com.nedap.archie.rm.datavalues.quantity.DvInterval"),
                        new Tuple("uriValue", "java.net.URI"),
                        new Tuple("language", "com.nedap.archie.rm.datatypes.CodePhrase"),
                        new Tuple("language", "com.nedap.archie.rm.datatypes.CodePhrase"),
                        new Tuple("choice", "ArbolChoiceChoice"),
                        new Tuple("subjectExternalref", "com.nedap.archie.rm.support.identification.PartyRef"),
                        new Tuple("testAllTypes", "java.util.List<TestAllTypes3>"),
                        new Tuple("testAllTypes2", "java.util.List<TestAllTypes5>"),
                        new Tuple("testAllTypes3", "java.util.List<TestAllTypes6>"),
                        new Tuple("value", "java.lang.String"),
                        new Tuple("description", "java.lang.String"),
                        new Tuple("terminologyId", "java.lang.String"),
                        new Tuple("code", "java.lang.String"),
                        new Tuple("multimediaAny", "com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia"),
                        new Tuple("timeValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("parsableAny", "com.nedap.archie.rm.datavalues.encapsulated.DvParsable"),
                        new Tuple("identifier", "com.nedap.archie.rm.datavalues.DvIdentifier"),
                        new Tuple("textValue", "java.lang.String"),
                        new Tuple("codedTextDefiningcode", "com.nedap.archie.rm.datatypes.CodePhrase"),
                        new Tuple("proportionAny", "com.nedap.archie.rm.datavalues.quantity.DvProportion"),
                        new Tuple("timeValue2", "java.time.temporal.TemporalAccessor"),
                        new Tuple("codedTextTerminologyDefiningcode", "com.nedap.archie.rm.datatypes.CodePhrase"),
                        new Tuple("quantityMagnitude", "java.lang.Double"),
                        new Tuple("quantityUnits", "java.lang.String"),
                        new Tuple("countMagnitude", "java.lang.Long"),
                        new Tuple("dateValue", "java.time.temporal.Temporal"),
                        new Tuple("subjectExternalref", "com.nedap.archie.rm.support.identification.PartyRef"),
                        new Tuple("originValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("durationAnyValue", "java.time.temporal.TemporalAmount"),
                        new Tuple("ordinal", "com.nedap.archie.rm.datavalues.quantity.DvOrdinal"),
                        new Tuple("booleanValue", "java.lang.Boolean"),
                        new Tuple("datetimeValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("datetimeAnyValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("testAllTypes", "java.util.List<TestAllTypes>"),
                        new Tuple("endTimeValue", "java.time.temporal.TemporalAccessor"),
                        new Tuple("testAllTypes2", "java.util.List<TestAllTypes2>"),
                        new Tuple("language", "com.nedap.archie.rm.datatypes.CodePhrase"),
                        new Tuple("healthCareFacility", "com.nedap.archie.rm.generic.PartyIdentified"),
                        new Tuple("composerExternalref", "com.nedap.archie.rm.support.identification.PartyRef"),
                        new Tuple("settingDefiningcode", "com.nedap.archie.rm.datatypes.CodePhrase"),
                        new Tuple("territory", "com.nedap.archie.rm.datatypes.CodePhrase"),
                        new Tuple("contextCodedTextDefiningcode", "ContextCodedTextDefiningcode"),
                        new Tuple("testAllTypes3", "java.util.List<TestAllTypes7>"),
                        new Tuple("location", "java.lang.String"),
                        new Tuple("startTimeValue", "java.time.temporal.TemporalAccessor")
                );


        JavaFile javaFile = JavaFile.builder("org.ehrbase.client.classgenerator", generate)
                .build();


        //   javaFile.writeTo(Paths.get(".", "src/test/java/"));

        StringWriter stringWriter = new StringWriter();
        javaFile.writeTo(stringWriter);
        String actual = stringWriter.toString();
        System.out.println(actual);
        assertTrue(StringUtils.isNotBlank(actual));

    }

*/
}