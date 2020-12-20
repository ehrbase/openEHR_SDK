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

package org.ehrbase.client.openehrclient;

import java.util.Optional;
import java.util.UUID;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.exception.WrongStatusCodeException;

public interface CompositionEndpoint {

  /**
   * Save a Flat-Entity to remote systems.
   *
   * @param entity Flat-Entity to save. Has to be annotated with {@link Template}
   * @return CompositionId
   * @throws ClientException
   * @throws WrongStatusCodeException
   */
  <T> T mergeCompositionEntity(T entity);

  /**
   * Finds a Flat-Entity by
   *
   * @param compositionId CompositionId of the flat-Entity to retrieve.
   * @param clazz class of the flat-Entity to retrieve. Has to be annotated with {@link Template}
   * @return The Flat-Entity
   * @throws ClientException
   * @throws WrongStatusCodeException
   */
  <T> Optional<T> find(UUID compositionId, Class<T> clazz);
}
