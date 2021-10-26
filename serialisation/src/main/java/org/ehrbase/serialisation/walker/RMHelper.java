/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.serialisation.walker;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class RMHelper {

  private RMHelper() {
    // NOP
  }

  public static boolean isEmpty(Object rmObject) {
    if (rmObject == null) {
      return true;
    }

    if (rmObject instanceof Collection) {
      return ((Collection<?>) rmObject).isEmpty()
          || ((Collection<?>) rmObject).stream().allMatch(RMHelper::isEmpty);
    }

    if (rmObject instanceof Participation) {
      return ((Participation) rmObject).getPerformer() == null;
    }

    if (rmObject instanceof CodePhrase) {
      return StringUtils.isBlank(((CodePhrase) rmObject).getCodeString());
    }

    if (rmObject instanceof DvCodedText) {
      return isEmpty(((DvCodedText) rmObject).getDefiningCode());
    }

    if (rmObject instanceof DvText) {
      return ((DvText) rmObject).getValue() == null;
    }

    if (rmObject instanceof PartyIdentified) {
      return ((PartyIdentified) rmObject).getName() == null
          && CollectionUtils.isEmpty(((PartyIdentified) rmObject).getIdentifiers());
    }

    if (rmObject instanceof DvDateTime) {
      return ((DvDateTime) rmObject).getValue() == null;
    }

    if (rmObject instanceof DvParsable) {
      return ((DvParsable) rmObject).getValue() == null
          && ((DvParsable) rmObject).getFormalism() == null;
    }

    if (rmObject instanceof FeederAudit) {
      return isEmpty(((FeederAudit) rmObject).getOriginalContent());
    }
    if (rmObject instanceof FeederAuditDetails) {
      return StringUtils.isEmpty(((FeederAuditDetails) rmObject).getSystemId());
    }
    return false;
  }
}
