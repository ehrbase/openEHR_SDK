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

import com.squareup.javapoet.TypeSpec;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.ehrbase.terminology.client.terminology.ValueSet;
import org.ehrbase.webtemplate.model.FilteredWebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;

class ClassGeneratorContext {
  final MultiValuedMap<String, TypeSpec> classes = new ArrayListValuedHashMap<>();
  final Deque<WebTemplateNode> nodeDeque = new ArrayDeque<>();
  final Deque<WebTemplateNode> unFilteredNodeDeque = new ArrayDeque<>();
  final Map<WebTemplateNode, TypeSpec> currentTypeSpec = new HashMap<>();
  String currentMainClass;
  final Deque<String> currentArchetypeName = new ArrayDeque<>();
  final Map<String, Integer> currentClassNameMap = new HashMap<>();
  String currentPackageName;
  final Map<ValueSet, TypeSpec> currentEnums = new HashMap<>();
  final Deque<Map<String, Integer>> currentFieldNameMap = new ArrayDeque<>();
  FilteredWebTemplate webTemplate;
}
