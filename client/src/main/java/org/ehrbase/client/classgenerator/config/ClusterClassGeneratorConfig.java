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

package org.ehrbase.client.classgenerator.config;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.Collections;
import java.util.Set;

public class ClusterClassGeneratorConfig implements RmClassGeneratorConfig {

  @Override
  public Class getAssociatedClass() {
    return Cluster.class;
  }

  @Override
  public Set<String> getExpandFields() {
    return Collections.emptySet();
  }
}
