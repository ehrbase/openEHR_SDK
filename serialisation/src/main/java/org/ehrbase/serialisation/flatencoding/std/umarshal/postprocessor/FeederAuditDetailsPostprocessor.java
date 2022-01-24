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
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.PartyIdentified;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;

public class FeederAuditDetailsPostprocessor extends AbstractUnmarshalPostprocessor<FeederAuditDetails> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      FeederAuditDetails rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

      Map<FlatPathDto, String> locationValues = FlatHelper.filter(values, term + "/_location", false);
      if(!locationValues.isEmpty() ){
          rmObject.setLocation(new PartyIdentified());
        handleRmAttribute(term,rmObject.getLocation(),locationValues,consumedPaths,context,"location" );

      }

      Map<FlatPathDto, String> subjectValues = FlatHelper.filter(values, term + "/_subject", false);
      if(!subjectValues.isEmpty() ){
          rmObject.setSubject(new PartyIdentified());
          handleRmAttribute(term,rmObject.getSubject(),subjectValues,consumedPaths,context,"subject" );

      }

      Map<FlatPathDto, String> providerValues = FlatHelper.filter(values, term + "/_provider", false);
      if(!providerValues.isEmpty() ){
          rmObject.setProvider(new PartyIdentified());
          handleRmAttribute(term,rmObject.getProvider(),providerValues,consumedPaths,context,"provider" );

      }

      Map<FlatPathDto, String> timeValues = FlatHelper.filter(values, term + "/_time", false);
      if(!timeValues.isEmpty() ){
          rmObject.setTime(new DvDateTime( ));
          handleRmAttribute(term,rmObject.getTime(),timeValues,consumedPaths,context,"time" );

      }

  }

  /** {@inheritDoc} */
  @Override
  public Class<FeederAuditDetails> getAssociatedClass() {
    return FeederAuditDetails.class;
  }
}
