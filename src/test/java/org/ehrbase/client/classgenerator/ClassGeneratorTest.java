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

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;

public class ClassGeneratorTest {

    @Test
    public void testGenerate() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream()).getTemplate();
        ClassGenerator cut = new ClassGenerator();
        TypeSpec generate = cut.generate(template);
        JavaFile javaFile = JavaFile.builder("org.ehrbase.client.classgenerator", generate)
                .build();
        StringWriter stringWriter = new StringWriter();
        javaFile.writeTo(stringWriter);
        String actual = stringWriter.toString();
        System.out.println(actual);
        assertTrue(StringUtils.isNotBlank(actual));
    }

    @Test
    public void testGenerateMultiOccurrence() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.MULTI_OCCURRENCE.getStream()).getTemplate();
        ClassGenerator cut = new ClassGenerator();
        TypeSpec generate = cut.generate(template);
        JavaFile javaFile = JavaFile.builder("org.ehrbase.client.classgenerator", generate)
                .build();

        // javaFile.writeTo(Paths.get(".", "src/test/java/"));
        StringWriter stringWriter = new StringWriter();
        javaFile.writeTo(stringWriter);
        String actual = stringWriter.toString();
        System.out.println(actual);
        assertTrue(StringUtils.isNotBlank(actual));

    }


}