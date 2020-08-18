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

package org.ehrbase.client.reflection;

import org.ehrbase.client.exception.ClientException;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Helper class to find Configurations classes in the classpath.
 */
public class ReflectionHelper {

    private ReflectionHelper() {
    }

    /**
     * Returns a Map containing all implementing classes of the interface {@code root} with key the class they are associated to as subclass of {@link ClassDependent}
     *
     * @param root A Interface extending {@link ClassDependent }
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S extends ClassDependent<T>> Map<Class<? extends T>, S> buildMap(Class<S> root) {
        Reflections reflections = new Reflections(root.getPackage().getName());
        Set<Class<? extends S>> configs = reflections.getSubTypesOf(root);

        return configs.stream()
                .filter(c -> !Modifier.isAbstract(c.getModifiers()))
                .map(c -> {
                    try {
                        return c.getConstructor().newInstance();
                    } catch (Exception e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                }).collect(Collectors.toMap(S::getAssociatedClass, c -> c));

    }
}
