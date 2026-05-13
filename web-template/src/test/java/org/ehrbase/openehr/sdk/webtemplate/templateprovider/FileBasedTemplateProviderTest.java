/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.webtemplate.templateprovider;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

class FileBasedTemplateProviderTest {

    @TempDir
    Path folder;

    static void writeTemplateFile(Path templateFolder, String templateId) throws IOException {
        TestDataTemplateProvider testDataTemplateProvider = new TestDataTemplateProvider();
        OPERATIONALTEMPLATE operationaltemplate =
                testDataTemplateProvider.find(templateId).get();
        XmlOptions opts = new XmlOptions();
        opts.setSaveSyntheticDocumentElement(new QName("http://schemas.openehr.org/v1", "template"));

        Path path = templateFolder.resolve(operationaltemplate.getTemplateId().getValue() + ".opt");
        Files.write(path, Collections.singletonList(operationaltemplate.xmlText(opts)), StandardCharsets.UTF_8);
    }

    @Test
    void find() throws IOException {

        Path templateFolder = Files.createDirectory(folder.resolve("template"));

        writeTemplateFile(templateFolder, "ehrbase_blood_pressure_simple.de.v0");

        FileBasedTemplateProvider cut = new FileBasedTemplateProvider(templateFolder);

        assertThat(cut.find("ehrbase_blood_pressure_simple.de.v0")).isPresent();

        assertThat(cut.find("test_all_types.en.v1")).isNotPresent();

        writeTemplateFile(templateFolder, "test_all_types.en.v1");
        assertThat(cut.find("test_all_types.en.v1")).isPresent();
    }
}
