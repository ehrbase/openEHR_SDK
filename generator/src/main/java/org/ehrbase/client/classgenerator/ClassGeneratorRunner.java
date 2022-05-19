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

package org.ehrbase.client.classgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.squareup.javapoet.JavaFile;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

public class ClassGeneratorRunner {
  private static final Options OPTIONS = new Options();

  public static void main(String[] args) throws IOException, XmlException {

    OPTIONS.addOption("opt", true, "path to opt file");
    OPTIONS.addOption("package", true, "package name");
    OPTIONS.addOption("out", true, "path to output directory");
    OPTIONS.addOption("h", false, "show help");
    OPTIONS.addOption("config", true, "optional Path to config file");

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = null;
    try {
      cmd = parser.parse(OPTIONS, args);
    } catch (ParseException e) {
      System.out.println(String.format("Error parsing commandline %s", e.getMessage()));
      showHelp();
    }

    if (cmd.hasOption("h")) {
      showHelp();
    }

    if (!cmd.hasOption("opt") || !cmd.hasOption("package") || !cmd.hasOption("out")) {
      showHelp();
    }

    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(Paths.get(cmd.getOptionValue("opt")).toFile()).getTemplate();
    ClassGenerator cut = new ClassGenerator(getClassGeneratorConfig(cmd));
    ClassGeneratorResult generate =
        cut.generate(cmd.getOptionValue("package"), new OPTParser(template).parse());

    java.nio.file.Path fsRoot = Paths.get(cmd.getOptionValue("out"));

    List<JavaFile> generateFiles = generate.writeFiles(fsRoot);
    FieldGenerator fieldGenerator = new FieldGenerator();
    fieldGenerator.generate(generateFiles).writeFiles(fsRoot);
  }

  private static ClassGeneratorConfig getClassGeneratorConfig(CommandLine cmd) throws IOException {
    final InputStream configFile;
    if (cmd.hasOption("config")) {
      configFile = new FileInputStream(cmd.getOptionValue("config"));
    } else {
      configFile = ClassGeneratorRunner.class.getResourceAsStream("/DefaultConfig.yaml");
    }

    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    objectMapper.findAndRegisterModules();

    ClassGeneratorConfig config = objectMapper.readValue(configFile, ClassGeneratorConfig.class);
    return config;
  }

  private static void showHelp() {

    HelpFormatter formatter = new HelpFormatter();

    formatter.printHelp("java -jar client-library.jar ", OPTIONS);

    System.exit(0);
  }
}
