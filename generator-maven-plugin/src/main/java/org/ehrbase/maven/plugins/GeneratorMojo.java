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

package org.ehrbase.maven.plugins;

import com.squareup.javapoet.JavaFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.classgenerator.ClassGenerator;
import org.ehrbase.client.classgenerator.ClassGeneratorConfig;
import org.ehrbase.client.classgenerator.ClassGeneratorResult;
import org.ehrbase.client.classgenerator.FieldGenerator;
import org.ehrbase.client.classgenerator.OptimizerSetting;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

/**
 * @author jbellmann
 */
@Mojo(name="codegen", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GeneratorMojo implements org.apache.maven.plugin.Mojo {

  private Log log;

  private static final Map<String, String> DEFAULT_REPLACE_CHARS = Map.of(
      "ä", "ae",
      "Ä", "Ae",
      "ö", "oe",
      "Ö", "Oe",
      "ü", "ue",
      "Ü", "ue",
      "ß", "ss",
      "æ", "ae",
      "ø", "oe",
      "å","aa"
  );

  @Parameter(required = true, defaultValue = "${project.build.directory}/generated-sources/ehrbase-generator")
  private File outputDirectory;

  @Parameter(defaultValue = "SECTION")
  private OptimizerSetting optimizerSetting = OptimizerSetting.SECTION;

  @Parameter(defaultValue = "true")
  private boolean addNullFlavor = true;

  @Parameter(defaultValue = "false")
  private boolean choicesForSingleEvent = false;

  @Parameter(required = true)
  private List<String> templateFiles;

  @Parameter(required = true)
  private String packageName;

  @Parameter
  private Map<String,String> replaceChars;

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {

    if(templateFiles == null || templateFiles.isEmpty()) {
      throw new MojoExecutionException("No template files configured.");
    }

    try {
      ClassGeneratorConfig generatorConfig = new ClassGeneratorConfig();
      generatorConfig.setAddNullFlavor(addNullFlavor);
      generatorConfig.setGenerateChoicesForSingleEvent(choicesForSingleEvent);
      generatorConfig.setOptimizerSetting(optimizerSetting);

      if(replaceChars != null) {
        replaceChars.forEach(
            (k,v) -> generatorConfig.getReplaceChars().putIfAbsent(k.charAt(0), v)
        );
      } else {
        DEFAULT_REPLACE_CHARS.forEach(
            (k,v) -> generatorConfig.getReplaceChars().putIfAbsent(k.charAt(0), v)
        );
      }

      ClassGenerator generator = new ClassGenerator(generatorConfig);

      templateFiles.forEach(templateFile -> {
        log.info("Generate code for template file : " + templateFile);
        OPERATIONALTEMPLATE template = null;
        try {
          template = TemplateDocument.Factory.parse(new File(templateFile)).getTemplate();
        } catch (XmlException e) {
          new MojoExecutionException("Unable to parse template file: " + templateFile, e);
        } catch (IOException e) {
          new MojoExecutionException("Unable to parse template file: " + templateFile, e);
        }

        WebTemplate webTemplate = new OPTParser(template).parse();
        ClassGeneratorResult generate = generator.generate(packageName, webTemplate);

        Path fsRoot = outputDirectory.toPath();

        try {
          List<JavaFile> generateFiles = generate.writeFiles(fsRoot);
          FieldGenerator fieldGenerator = new FieldGenerator();
          fieldGenerator.generate(generateFiles).writeFiles(fsRoot);
        } catch (IOException e) {
          new MojoExecutionException("Unable to write generated code for template file: " + templateFile, e);
        }
      });


    } catch (Exception e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }
  }

  @Override
  public void setLog(Log log) {
    this.log = log;
  }

  @Override
  public Log getLog() {
    return this.log;
  }
}
