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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.util.exception.SdkException;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

/** Provides Template which are saved as OPT files in the file system. */
public class FileBasedTemplateProvider implements TemplateProvider {

  public static final PathMatcher OPT_FILE_MATCHER =
      FileSystems.getDefault().getPathMatcher("glob:**.opt");
  private final Map<String, Path> pathMap = new HashMap<>();

  private final Path templateDirectory;

  /**
   * @param templateDirectory Path to folder with the OPT files. Every file with ending <code>*.opt
   *     </code> will be parsed.
   */
  public FileBasedTemplateProvider(Path templateDirectory) {
    this.templateDirectory = templateDirectory;

    snyc(templateDirectory);
  }

  private void snyc(Path templateDirectory) {
    try (Stream<Path> walk = Files.walk(templateDirectory)) {
      walk.filter(p -> p.toFile().isFile())
          .filter(OPT_FILE_MATCHER::matches)
          .filter(p -> !pathMap.containsValue(p))
          .forEach(p -> pathMap.put(extractTemplateId(p), p));

    } catch (IOException e) {
      throw new SdkException(e.getMessage(), e);
    }
  }

  private String extractTemplateId(Path path) {
    return readTemplate(path).getTemplateId().getValue();
  }

  private OPERATIONALTEMPLATE readTemplate(Path path) {
    try (InputStream in = new FileInputStream(path.toFile())) {
      org.openehr.schemas.v1.TemplateDocument document =
          org.openehr.schemas.v1.TemplateDocument.Factory.parse(in);
      return document.getTemplate();
    } catch (IOException | XmlException e) {
      throw new SdkException(e.getMessage(), e);
    }
  }

  @Override
  public Optional<OPERATIONALTEMPLATE> find(String templateId) {

    if (!pathMap.containsKey(templateId)) {
      snyc(templateDirectory);
    }

    return Optional.ofNullable(pathMap.get(templateId)).map(this::readTemplate);
  }
}
