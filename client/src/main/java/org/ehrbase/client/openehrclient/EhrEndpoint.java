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

import com.nedap.archie.rm.ehr.EhrStatus;
import java.util.Optional;
import java.util.UUID;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.exception.WrongStatusCodeException;

public interface EhrEndpoint {

  /**
   * Create a new EHR.
   *
   * @return ehrID
   * @throws ClientException
   * @throws WrongStatusCodeException
   */
  UUID createEhr();

  /**
   * Create a new EHR with the given EHR_STATUS.
   *
   * @param ehrStatus EHR_STATUS object to create the EHR with.
   * @return ehrID
   * @throws ClientException
   * @throws WrongStatusCodeException
   */
  UUID createEhr(EhrStatus ehrStatus);

  /**
   * Get the EhrStatus for {@code ehrId}.
   *
   * @param ehrId Id of the ehr from which to return the status.
   * @return {@link EhrStatus}
   * @throws ClientException
   * @throws WrongStatusCodeException
   */
  Optional<EhrStatus> getEhrStatus(UUID ehrId);

  /**
   * Updates the status of the ehr with {@code ehrId} to {@code ehrStatus}
   *
   * @param ehrId EhrId of the ehr which will be updated
   * @param ehrStatus new ehrStatus
   * @throws ClientException
   * @throws WrongStatusCodeException
   */
  void updateEhrStatus(UUID ehrId, EhrStatus ehrStatus);
}
