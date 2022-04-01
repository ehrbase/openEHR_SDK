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

package org.ehrbase.openehr.sdk.examplegenerator;

import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

class RmTypesInspection {

    @Test
    void testInspect() {
        try (PrintStream out = output("rmtypes_inspect.txt")) {
            ArchieRMInfoLookup.getInstance().getAllTypes()
                    .forEach(e -> {

                        Class<?> jType = e.getJavaClass();
                        if (Modifier.isAbstract(jType.getModifiers())) {
                            out.print("ABSTRACT ");
                        }
                        out.println(e.getRmName());
                        out.println(jType.toGenericString());
                        e.getAttributes().keySet().forEach(v -> out.println("\t" + v));
                    });
        }
    }

    @Test
    void testInspectTypeHierarchy() {
        try (PrintStream out = output("type_hierarch.txt")) {
            MultiValuedMap<Class<?>, Class<?>> hierarchy = new HashSetValuedHashMap<>();
            ArchieRMInfoLookup rmInfoLookup = ArchieRMInfoLookup.getInstance();

            rmInfoLookup.getAllTypes()
                    .forEach(e -> {
                        var t = e.getJavaClass();
                        for (; ; ) {
                            var p = t.getSuperclass();
                            if (p != null) {
                                hierarchy.put(p, t);
                                t = p;
                            } else {
                                break;
                            }
                        }
                    });

            printChildren(out, rmInfoLookup, hierarchy, Object.class, 0);
        }
    }

    private PrintStream output(String name) {
        try {
            return new PrintStream(Files.newOutputStream(Path.of(name)), false, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void printChildren(PrintStream out, ArchieRMInfoLookup rmInfoLookup, MultiValuedMap<Class<?>, Class<?>> hierarchy, Class<?> n, int r) {
        if (r > 0) {
            out.print(r);
            for (int i = 1; i < r; i++) {
                out.print(' ');
                out.print('|');
            }
            out.print('-');
            out.print(n.getSimpleName());
            RMTypeInfo typeInfo = rmInfoLookup.getTypeInfo(n);
            if (typeInfo != null) {
                out.print(' ');
                out.print(typeInfo.getRmName());

            }

            out.println();
        }
        hierarchy.get(n).stream().sorted(Comparator.comparing(c -> hierarchy.get(c).size())).forEach(c -> printChildren(out, rmInfoLookup, hierarchy, c, r+1));
    }

    @Test
    void testInspectTypeDiagram() {
        try (PrintStream out = output("rmtype_diagram.mermaid")) {
            ArchieRMInfoLookup rmInfoLookup = ArchieRMInfoLookup.getInstance();

            out.println("classDiagram");

            RMTypeInfo root = null;//rmInfoLookup.getTypeInfo("DATA_VALUE");
            List<RMTypeInfo> types = rmInfoLookup.getAllTypes().stream()
                    .filter(t -> root == null || t.isDescendantOf(root)).collect(Collectors.toList());
            types.stream()
                    .forEach(t -> {
                        out.println("class " + t.getRmName());
                        if (Modifier.isAbstract(t.getJavaClass().getModifiers())) {
                            out.println("<<abstract>> " + t.getRmName());
                        }
                        t.getAttributes().forEach((a, at) -> {
                            out.print(t.getRmName() + " : +");
                            out.print(at.getType().getSimpleName());
                            if (at.isMultipleValued()) {
                                out.print("~" + at.getTypeNameInCollection() + "~");
                            }
                            out.println(" " + a);
                        });

                    });

            types.stream()
                    .forEach(t -> {
                        t.getDirectParentClasses().forEach(p -> {
                            out.println(p.getRmName() + " <|-- " + t.getRmName());
                        });
                    });
        }
    }

    @Test
    void testInspectCompositionDiagram() {

        try (PrintStream out = output("composition_diagram.mermaid")) {

            ArchieRMInfoLookup rmInfoLookup = ArchieRMInfoLookup.getInstance();

            out.println("classDiagram");

            Set<RMTypeInfo> handled = new HashSet<>();
            Deque<RMTypeInfo> todo = new LinkedList<>();

            Set<RMTypeInfo> todoDecendents = new LinkedHashSet<>();

            RMTypeInfo observation = rmInfoLookup.getTypeInfo("OBSERVATION");
            todo.push(observation);

            Boolean includeSubclasses = true;

            RMTypeInfo dataValue = rmInfoLookup.getTypeInfo("DATA_VALUE");

            while (!todo.isEmpty()) {

                RMTypeInfo typeInfo = todo.pop();
                if (handled.add(typeInfo)) {

                    if (!Boolean.FALSE.equals(includeSubclasses)) {
                        todoDecendents.add(typeInfo);
                        todoDecendents.addAll(typeInfo.getAllDescendantClasses());

                        if (Boolean.TRUE.equals(includeSubclasses)) {
                            typeInfo.getDirectDescendantClasses().stream()
                                    .filter(t -> !handled.contains(t)).forEach(todo::push);
                        }
                    }

                    out.println("class " + typeInfo.getRmName());


                    typeInfo.getAttributes().values().stream()
                            .forEach(a -> {
                                if (a.isComputed()) {
                                    return;
                                }
                                if (a.getRmName().equals("parent")) {
                                    //skip parent function of Pathable
                                    return;
                                }

                                Optional.of(a.getType()).map(rmInfoLookup::getTypeInfo)
                                        .or(() -> Optional.of(a.getTypeInCollection()).map(rmInfoLookup::getTypeInfo))
                                        .ifPresentOrElse(destType -> {

                                            if (destType.isDescendantOrEqual(dataValue)) {
                                                out.print(typeInfo.getRmName() + " : +");
                                                out.print(destType.getRmName());
                                                if (a.isMultipleValued()) {
                                                    out.print("~" + a.getTypeNameInCollection() + "~");
                                                }
                                                out.println(" " + a.getRmName());
                                            } else {
                                                out.println(typeInfo.getRmName() + " --> " + destType.getRmName() + " : " + a.getRmName());
                                                if (!handled.contains(destType)) {
                                                    todo.push(destType);
                                                }
                                            }
                                        }, () -> {
                                            out.print(typeInfo.getRmName() + " : +");
                                            out.print(a.getType().getSimpleName());
                                            out.println(" " + a.getRmName());
                                        });
                            });
                }
            }

            todoDecendents.forEach(s -> {
                s.getDirectDescendantClasses().forEach(d -> {
                    out.println(s.getRmName() + " <|-- " + d.getRmName());
                });

            });
        }
    }

}