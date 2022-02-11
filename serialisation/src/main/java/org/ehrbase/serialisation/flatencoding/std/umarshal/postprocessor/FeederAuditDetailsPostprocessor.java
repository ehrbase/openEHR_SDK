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

package org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor;

import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartyRelated;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.serialisation.exception.UnmarshalException;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import static org.ehrbase.serialisation.walker.FlatHelper.findOrBuildSubNode;

public class FeederAuditDetailsPostprocessor
    extends AbstractUnmarshalPostprocessor<FeederAuditDetails> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String currentTerm,
      FeederAuditDetails rmObject,
      Map<FlatPathDto, String> currentValues,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

    setParty(
        currentTerm,
        p -> rmObject.setLocation((PartyIdentified) p),
        currentValues,
        consumedPaths,
        context,
        "location",
        false);
    setParty(
        currentTerm, rmObject::setSubject, currentValues, consumedPaths, context, "subject", true);
    setParty(
        currentTerm,
        p -> rmObject.setProvider((PartyIdentified) p),
        currentValues,
        consumedPaths,
        context,
        "provider",
        false);
  }

  private void setParty(
      String currentTerm,
      Consumer<PartyProxy> partyConsumer,
      Map<FlatPathDto, String> currentValues,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context,
      String id,
      boolean allowPartySelf) {
    Map<FlatPathDto, String> values =
        FlatHelper.filter(currentValues, currentTerm + "/" + id, false);

    PartyProxy partyProxy;

    if (!values.isEmpty()) {

      if (FlatHelper.isExactlyPartyRelated(values, currentTerm + "/" + id, null)) {
        partyProxy = new PartyRelated();
      } else if (FlatHelper.isExactlyPartyIdentified(values, currentTerm + "/" + id, null)) {
        partyProxy = new PartyIdentified();
      } else if (allowPartySelf
          && FlatHelper.isExactlyPartySelf(values, currentTerm + "/" + id, null)) {
        partyProxy = new PartySelf();
      } else {
        throw new UnmarshalException(
            String.format(
                "Could not find concrete instance of Party proxy for %s/%s", currentTerm, id));
      }

      partyConsumer.accept(partyProxy);

      callUnmarshal(
          currentTerm,
          id,
          partyProxy,
          values,
          consumedPaths,
          context,
          findOrBuildSubNode(context, id));
      callPostProcess(
          currentTerm,
          id,
          partyProxy,
          values,
          consumedPaths,
          context,
          findOrBuildSubNode(context, id));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<FeederAuditDetails> getAssociatedClass() {
    return FeederAuditDetails.class;
  }
}
