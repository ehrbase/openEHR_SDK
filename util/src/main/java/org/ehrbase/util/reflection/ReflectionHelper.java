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

package org.ehrbase.util.reflection;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.ehrbase.util.exception.SdkException;

/** Helper class to find Configurations classes in the classpath. */
public class ReflectionHelper {

  private static Map<Class<?>, Map> cache = new HashMap<>();

  private ReflectionHelper() {}

  /**
   * Returns a Map containing all implementing classes of the interface {@code root} with key the
   * class they are associated to as subclass of {@link ClassDependent}
   *
   * @param root A Interface extending {@link ClassDependent }
   * @param <T>
   * @param <S>
   * @return
   */
  public static <T, S extends ClassDependent<T>> Map<Class<? extends T>, S> buildMap(
      Class<S> root) {

    Map<Class<? extends T>, S> classSMap = cache.get(root);

    if (classSMap == null) {
      classSMap = buildInternal(root);
      cache.put(root, classSMap);
    }
    return classSMap;
  }

  public static List<Class<?>> findAll(String packageName) {
    try (ScanResult result =
        new ClassGraph()
            .enableClassInfo()
            .enableAnnotationInfo()
            .acceptPackages(packageName)
            .scan()) {
      return result.getAllClasses().loadClasses();
    }
  }

  private static <T, S extends ClassDependent<T>> Map<Class<? extends T>, S> buildInternal(
      Class<?> root) {
    Map<Class<? extends T>, S> classSMap;
    try (ScanResult result =
        new ClassGraph()
            .enableClassInfo()
            .enableAnnotationInfo()
            .acceptPackages(root.getPackage().getName())
            .scan()) {

      classSMap =
          result.getClassesImplementing(root.getName()).stream()
              .filter(c -> !c.isAbstract())
              .map(
                  c -> {
                    try {
                      return c.loadClass().getConstructor().newInstance();
                    } catch (Exception e) {
                      throw new SdkException(e.getMessage(), e);
                    }
                  })
              .map(o -> (S) o)
              .collect(Collectors.toMap(S::getAssociatedClass, c -> c));
      cache.put(root, classSMap);
    }
    return classSMap;
  }
}
