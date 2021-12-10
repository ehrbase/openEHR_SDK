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

import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.PartyIdentified;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;

public class FeederAuditDetailsRMUnmarshaller extends AbstractRMUnmarshaller<FeederAuditDetails> {

  private static final PartyIdentifiedRMUnmarshaller PARTY_IDENTIFIED_RM_UNMARSHALLER =
      new PartyIdentifiedRMUnmarshaller();

  /** {@inheritDoc} */
  @Override
  public Class<FeederAuditDetails> getAssociatedClass() {
    return FeederAuditDetails.class;
  }

  /** {@inheritDoc} */
  @Override
  public void handle(
      String currentTerm,
      FeederAuditDetails rmObject,
      Map<FlatPathDto, String> currentValues,
      Context<Map<FlatPathDto, String>> context,
      Set<String> consumedPaths) {

    setValue(
        currentTerm,
        "time",
        currentValues,
        s -> {
          if (s != null) {
            rmObject.setTime(new DvDateTime(s));
          }
        },
        String.class,
        consumedPaths);

    Map<FlatPathDto, String> locationValues =
        FlatHelper.filter(currentValues, currentTerm + "/location", false);

    if (!locationValues.isEmpty()) {
      rmObject.setLocation(new PartyIdentified());
      PARTY_IDENTIFIED_RM_UNMARSHALLER.handle(
          currentTerm + "/location", rmObject.getLocation(), locationValues, null, consumedPaths);
    }

    Map<FlatPathDto, String> providerValues =
        FlatHelper.filter(currentValues, currentTerm + "/provider", false);

    if (!providerValues.isEmpty()) {
      rmObject.setProvider(new PartyIdentified());
      PARTY_IDENTIFIED_RM_UNMARSHALLER.handle(
          currentTerm + "/provider", rmObject.getProvider(), providerValues, null, consumedPaths);
    }

    setValue(
        currentTerm,
        "system_id",
        currentValues,
        rmObject::setSystemId,
        String.class,
        consumedPaths);
    setValue(
        currentTerm,
        "version_id",
        currentValues,
        rmObject::setVersionId,
        String.class,
        consumedPaths);
  }
}
