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

import static org.ehrbase.client.flattener.DtoToCompositionWalker.findEntity;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.support.identification.HierObjectId;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import org.ehrbase.building.OptSkeletonBuilder;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.normalizer.Normalizer;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

public class Unflattener {

  public static final Normalizer NORMALIZER = new Normalizer();
  public static final OptSkeletonBuilder OPT_SKELETON_BUILDER = new OptSkeletonBuilder();

  private final TemplateProvider templateProvider;

  public Unflattener(TemplateProvider templateProvider) {

    this.templateProvider = templateProvider;
  }

  public RMObject unflatten(Object dto) {
    Template template = dto.getClass().getAnnotation(Template.class);

    OPERATIONALTEMPLATE operationalTemplate =
        templateProvider
            .find(template.value())
            .orElseThrow(
                () -> new ClientException(String.format("Unknown Template %s", template.value())));
    Composition generate = (Composition) OPT_SKELETON_BUILDER.generate(operationalTemplate);

    new DtoToCompositionWalker()
        .walk(
            generate,
            findEntity(dto),
            templateProvider
                .buildIntrospect(template.value())
                .orElseThrow(
                    () ->
                        new SdkException(
                            String.format("Can not find Template: %s", template.value()))));
    Optional<VersionUid> versionUid = extractVersionUid(dto);
    if (versionUid.isPresent()) {
      generate.setUid(new HierObjectId(versionUid.get().toString()));
    }
    return NORMALIZER.normalize(generate);
  }

  static Optional<VersionUid> extractVersionUid(Object entity) {
    return Arrays.stream(entity.getClass().getDeclaredFields())
        .filter(f -> f.isAnnotationPresent(Id.class))
        .findAny()
        .map(
            idField -> {
              try {
                PropertyDescriptor propertyDescriptor =
                    new PropertyDescriptor(idField.getName(), entity.getClass());
                return (VersionUid) propertyDescriptor.getReadMethod().invoke(entity);
              } catch (IllegalAccessException
                  | InvocationTargetException
                  | IntrospectionException e) {
                throw new ClientException(e.getMessage(), e);
              }
            });
  }
}
