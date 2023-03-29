/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.client.flattener;

import static org.ehrbase.client.flattener.DtoToCompositionWalker.findEntity;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import org.ehrbase.building.webtemplateskeletnbuilder.WebTemplateSkeletonBuilder;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;

public class Unflattener {

    private final TemplateProvider templateProvider;
    private final DefaultValuesProvider defaultValuesProvider;

    public Unflattener(TemplateProvider templateProvider, DefaultValuesProvider defaultValuesProvider) {

        this.templateProvider = templateProvider;
        this.defaultValuesProvider = defaultValuesProvider;
    }

    public Unflattener(TemplateProvider templateProvider) {

        this.defaultValuesProvider = o -> new DefaultValues();
        this.templateProvider = templateProvider;
    }

    public RMObject unflatten(Object dto) {
        Template template = dto.getClass().getAnnotation(Template.class);

        WebTemplate introspect = templateProvider
                .buildIntrospect(template.value())
                .orElseThrow(() -> new SdkException(String.format("Can not find Template: %s", template.value())));

        Composition generate = WebTemplateSkeletonBuilder.build(introspect, false);
        new DtoToCompositionWalker()
                .walk(generate, findEntity(dto), introspect, defaultValuesProvider.provide(dto), template.value());
        Optional<VersionUid> versionUid = extractVersionUid(dto);
        if (versionUid.isPresent()) {
            generate.setUid(new ObjectVersionId(versionUid.get().toString()));
        }
        return generate;
    }

    static Optional<VersionUid> extractVersionUid(Object entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findAny()
                .map(idField -> {
                    try {
                        PropertyDescriptor propertyDescriptor =
                                new PropertyDescriptor(idField.getName(), entity.getClass());
                        return (VersionUid) propertyDescriptor.getReadMethod().invoke(entity);
                    } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                });
    }
}
