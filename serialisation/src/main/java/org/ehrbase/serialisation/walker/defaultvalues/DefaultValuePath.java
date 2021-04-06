/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.serialisation.walker.defaultvalues;

import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

public class DefaultValuePath<T> {

  public static final DefaultValuePath<Language> LANGUAGE =
      new DefaultValuePath<>("language", Language.class);
  public static final DefaultValuePath<Territory> TERRITORY =
      new DefaultValuePath<>("territory", Territory.class);
  public static final DefaultValuePath<String> COMPOSER_NAME =
      new DefaultValuePath<>("composer_name", String.class);
  public static final DefaultValuePath<String> COMPOSER_ID =
      new DefaultValuePath<>("composer_id", String.class);
  public static final DefaultValuePath<String> ID_NAMESPACE =
      new DefaultValuePath<>("id_namespace", String.class);
  public static final DefaultValuePath<String> ID_SCHEME =
      new DefaultValuePath<>("id_scheme", String.class);
  public static final DefaultValuePath<Boolean> COMPOSER_SELF =
      new DefaultValuePath<>("composer_self", Boolean.class);

  private final String path;
  private final Class<T> type;

  private DefaultValuePath(String path, Class<T> type) {
    this.path = path;
    this.type = type;
  }

  public String getPath() {
    return path;
  }

  public Class<?> getType() {
    return type;
  }
}
