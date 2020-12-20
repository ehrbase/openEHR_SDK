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

package org.ehrbase.webtemplate.templateprovider;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlOptions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

public class FileBasedTemplateProviderTest {

  @Rule public TemporaryFolder folder = new TemporaryFolder();

  public static void writeTemplateFile(File templateFolder, String templateId) throws IOException {
    TestDataTemplateProvider testDataTemplateProvider = new TestDataTemplateProvider();
    OPERATIONALTEMPLATE operationaltemplate = testDataTemplateProvider.find(templateId).get();
    XmlOptions opts = new XmlOptions();
    opts.setSaveSyntheticDocumentElement(new QName("http://schemas.openehr.org/v1", "template"));

    Path path =
        Paths.get(
            templateFolder.getPath(), operationaltemplate.getTemplateId().getValue() + ".opt");
    Files.write(
        path, Collections.singletonList(operationaltemplate.xmlText(opts)), StandardCharsets.UTF_8);
  }

  @Test
  public void find() throws IOException {

    File templateFolder = folder.newFolder("template");

    writeTemplateFile(templateFolder, "ehrbase_blood_pressure_simple.de.v0");

    FileBasedTemplateProvider cut = new FileBasedTemplateProvider(templateFolder.toPath());

    assertTrue(cut.find("ehrbase_blood_pressure_simple.de.v0").isPresent());

    assertFalse(cut.find("test_all_types.en.v1").isPresent());

    writeTemplateFile(templateFolder, "test_all_types.en.v1");
    assertTrue(cut.find("test_all_types.en.v1").isPresent());
  }
}
