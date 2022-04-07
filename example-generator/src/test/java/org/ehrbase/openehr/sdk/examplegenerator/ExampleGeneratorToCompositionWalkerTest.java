/*
 * Copyright (c) 2022. vitasystems GmbH and Hannover Medical School.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.openehr.sdk.examplegenerator;

import com.nedap.archie.rm.composition.Composition;
import org.ehrbase.building.webtemplateskeletnbuilder.WebTemplateSkeletonBuilder;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.validation.CompositionValidator;
import org.ehrbase.validation.ConstraintViolation;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ExampleGeneratorToCompositionWalkerTest {

    @Test
    void testConformanceTemplate() throws IOException {
        ExampleGeneratorToCompositionWalker cut = new ExampleGeneratorToCompositionWalker();

        Path outPath = Path.of("target", "compositions", "conformance.json");
        Files.createDirectories(outPath.getParent());
        writeAndValidateComposition(cut, outPath, OperationalTemplateTestData.CONFORMANCE);
    }

    private static Map<OperationalTemplateTestData, Integer> EXPECTED_CONSTRAINT_VIOLATIONS = Map.of(
            // missing values for  Observation results
            OperationalTemplateTestData.EHRN_VITAL_SIGNS_TEST, 1,
            // existence 1..1 EVALUATION ACTION
            OperationalTemplateTestData.IDCR_PROBLEM_LIST, 13,
            // null_flavor support
            OperationalTemplateTestData.SM_I_C_S_BEFUND, 1,
            // null_flavor support
            OperationalTemplateTestData.INITIAL_ASSESSMENT, 3
    );

    @ParameterizedTest
    @EnumSource(value = OperationalTemplateTestData.class, names = {
            // unknown constraint name DV_DATE_TIME
            "RIPPLE_CONFORMANCE_TEST",
            // NPE NodeId.<init>
            "BLOOD_PRESSURE_SIMPLE",
            // DV_CODED_TEXT|name missing
            "WORD_WITH_AND",
            // deserialization: could no consume Parts
            "IDCR_PROBLEM_LIST",
            // deserialization: could no consume Parts
            "EPISODE_OF_CARE",
            // deserialization: could no consume Parts
            "MINIMAL_EVALUATION",
            // deserialization: could no consume Parts
            "MINIMAL_INSTRUCTION",
            // deserialization: could no consume Parts
            "MINIMAL_ACTION_2",
            // deserialization: could no consume Parts
            "MINIMAL_ACTION",
            // serialization: Unknown Ordinal with code kg
            "NESTED",
            // deserialization: could no consume Parts
            "SECTION_CARDINALITY"
    }, mode = EnumSource.Mode.EXCLUDE)
    void testWalkAll(OperationalTemplateTestData template) throws IOException {
        ExampleGeneratorToCompositionWalker cut = new ExampleGeneratorToCompositionWalker();

        Path outDir = Path.of("target", "compositions");
        Files.createDirectories(outDir);
        Path outPath = outDir.resolve(template.name() + ".json");

        writeAndValidateComposition(cut, outPath, template);
    }

    private void writeAndValidateComposition(ExampleGeneratorToCompositionWalker cut, Path outPath, OperationalTemplateTestData template) {
        String templateId = template.getTemplateId();
        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();

        WebTemplate webTemplate = templateProvider.buildIntrospect(templateId).get();
        //showNodesWithOpenehrTerminology(webTemplate.getTree());

        Composition composition = WebTemplateSkeletonBuilder.build(webTemplate, false);

        ExampleGeneratorConfig object = new ExampleGeneratorConfig();

        DefaultValues defaultValues = new DefaultValues();
        defaultValues.addDefaultValue(DefaultValuePath.TIME, OffsetDateTime.now());
        defaultValues.addDefaultValue(DefaultValuePath.LANGUAGE, Language.DE);
        defaultValues.addDefaultValue(DefaultValuePath.TERRITORY, Territory.DE);
        defaultValues.addDefaultValue(DefaultValuePath.SETTING, Setting.DENTAL_CARE);
        defaultValues.addDefaultValue(DefaultValuePath.COMPOSER_NAME,"Max Mustermann");



        cut.walk(composition, object, webTemplate, defaultValues, templateId);

        appendToFile(outPath, new CanonicalJson().marshal(composition));

        CompositionValidator compositionValidator = new CompositionValidator();
        int expectedConstraintValidations = EXPECTED_CONSTRAINT_VIOLATIONS.getOrDefault(template, 0);

        {
            List<ConstraintViolation> violations = compositionValidator.validate(composition, webTemplate);

            violations.stream().collect(Collectors.groupingBy(v -> v.getMessage())).entrySet()
                    .stream()
                    .sorted(Comparator.comparing(e -> e.getKey()))
                    .forEach(e -> {
                        System.out.println(e.getValue().size() + "x " + e.getKey());
                        e.getValue().stream().map(v -> v.getAqlPath()).forEach(a -> System.out.println("\t" + a));
                    });

            assertThat(violations).withFailMessage("%d / %d Constraint Violations", violations.size(), expectedConstraintValidations).size().isEqualTo(expectedConstraintValidations);
        }

        // re-validate after serialization roundtrip
        {
            RMDataFormat format = new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.SIM_SDT, templateId);
            Composition reloadedComposition = format.unmarshal(format.marshal(composition));

            List<ConstraintViolation> violations = compositionValidator.validate(reloadedComposition, webTemplate);
            violations.stream().collect(Collectors.groupingBy(v -> v.getMessage())).entrySet()
                    .stream()
                    .sorted(Comparator.comparing(e -> e.getKey()))
                    .forEach(e -> {
                        System.out.println(e.getValue().size() + "x " + e.getKey());
                        e.getValue().stream().map(v -> v.getAqlPath()).forEach(a -> System.out.println("\t" + a));
                    });

            assertThat(violations).withFailMessage("After reload: %d / %d Constraint Violations", violations.size(), expectedConstraintValidations).size().isEqualTo(expectedConstraintValidations);
        }
    }

    private static void showNodesWithOpenehrTerminology(WebTemplateNode node) {
        if (node.getInputs() != null) {
            node.getInputs().stream()
                    .filter(in -> "openehr".equals(in.getTerminology()))
                    .forEach(in -> System.out.println(node.getRmType() + " " + node.getAqlPath() + "|" + in.getSuffix() + " " + in.getType()));
        }
        node.getChildren()
                .stream()
                .forEach(c -> showNodesWithOpenehrTerminology(c));
    }

    private static void appendToFile(Path outPath, String data) {
        try {
            Files.writeString(outPath,
                    data, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }
}