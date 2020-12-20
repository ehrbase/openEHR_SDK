/*
 *
 *  *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class ClassGeneratorResult {
  private final MultiValuedMap<String, TypeSpec> classes = new ArrayListValuedHashMap<>();

  public void addClass(String path, TypeSpec typeSpec) {
    classes.put(path, typeSpec);
  }

  public Map<String, Collection<TypeSpec>> getClasses() {
    return classes.asMap();
  }

  public List<JavaFile> writeFiles(Path root) throws IOException {
    List<JavaFile> javaFiles = createFiles();

    for (JavaFile j : javaFiles) {
      j.writeTo(root);
    }
    return javaFiles;
  }

  public List<JavaFile> createFiles() {
    List<JavaFile> files = new ArrayList<>();

    for (Map.Entry<String, Collection<TypeSpec>> entry : classes.asMap().entrySet()) {
      String s = entry.getKey();
      Collection<TypeSpec> typeSpecs = entry.getValue();
      List<JavaFile> filesInternal = createFilesInternal(s, typeSpecs);
      files.addAll(filesInternal);
    }
    return files;
  }

  private List<JavaFile> createFilesInternal(String packageName, Collection<TypeSpec> typeSpecs) {
    List<JavaFile> files = new ArrayList<>();
    for (TypeSpec t : typeSpecs) {

      JavaFile javaFile = JavaFile.builder(packageName, t).build();
      files.add(javaFile);
    }
    return files;
  }
}
