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

package org.ehrbase.client.flattener;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;

public class Flattener {

  private ScanResult classGraph;

  private final TemplateProvider templateProvider;

  public Flattener(TemplateProvider templateProvider) {
    this.templateProvider = templateProvider;
  }

  private static <T> T createInstance(Class<T> aClass) {
    try {
      return aClass.getDeclaredConstructor().newInstance();
    } catch (InstantiationException
        | NoSuchMethodException
        | InvocationTargetException
        | IllegalAccessException e) {
      throw new ClientException(e.getMessage(), e);
    }
  }

  public <T> T flatten(RMObject locatable, Class<T> clazz) {
    try {
      classGraph =
          new ClassGraph()
              .enableClassInfo()
              .enableAnnotationInfo()
              .acceptPackages(StringUtils.removeEnd(clazz.getPackageName(), ".definition"))
              .scan();
      String templateId =
          classGraph
              .getClassesWithAnnotation(Template.class.getName())
              .loadClasses()
              .get(0)
              .getAnnotation(Template.class)
              .value();

      T dto = createInstance(clazz);
      String archetypeValue = clazz.getAnnotation(Archetype.class).value();
      WebTemplateNode root =
          templateProvider
              .buildIntrospect(templateId)
              .orElseThrow(
                  () -> new SdkException(String.format("Can not find Template: %s", templateId)))
              .getTree()
              .findMatching(n -> Objects.equals(n.getNodeId(), archetypeValue))
              .get(0);
      new DtoFromCompositionWalker()
          .walk(
              locatable,
              new DtoWithMatchingFields(
                  dto, DtoFromCompositionWalker.buildFieldByPathMap(dto.getClass())),
              root);
      if (locatable instanceof Composition && ((Composition) locatable).getUid() != null) {
        addVersion(dto, new VersionUid(((Composition) locatable).getUid().toString()));
      }
      return dto;
    } finally {
      classGraph.close();
    }
  }

  public static <T> void addVersion(T entity, VersionUid versionUid) {
    Optional<Field> idField =
        Arrays.stream(entity.getClass().getDeclaredFields())
            .filter(f -> f.isAnnotationPresent(Id.class))
            .findAny();
    if (idField.isPresent()) {
      try {
        PropertyDescriptor propertyDescriptor =
            new PropertyDescriptor(idField.get().getName(), entity.getClass());
        propertyDescriptor.getWriteMethod().invoke(entity, versionUid);
      } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
        throw new ClientException(e.getMessage(), e);
      }
    }
  }
}
