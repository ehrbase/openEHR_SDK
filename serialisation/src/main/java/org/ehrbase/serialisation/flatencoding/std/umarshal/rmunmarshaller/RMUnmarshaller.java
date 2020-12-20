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

package org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller;

import com.nedap.archie.rm.RMObject;
import java.util.Map;
import java.util.Set;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.util.reflection.ClassDependent;

/** Defines how terminal RMObjects will be unmarshalled to flat json */
public interface RMUnmarshaller<T extends RMObject> extends ClassDependent<T> {
  /**
   * Puts the {@code currentValues} into the {@code rmObject}
   *
   * @param currentTerm
   * @param rmObject
   * @param currentValues
   * @param context
   */
  void handle(
      String currentTerm,
      T rmObject,
      Map<String, String> currentValues,
      Context<Map<String, String>> context);

  /** @return The parts consumed by this RMUnmarshaller */
  Set<String> getConsumedPaths();
}
