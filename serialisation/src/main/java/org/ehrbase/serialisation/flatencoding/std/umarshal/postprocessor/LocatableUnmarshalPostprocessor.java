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

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.support.identification.HierObjectId;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.FeederAuditRMUnmarshaller;
import org.ehrbase.serialisation.walker.RMHelper;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ehrbase.serialisation.walker.FlatHelper.consumeAllMatching;
import static org.ehrbase.serialisation.walker.FlatHelper.extractMultiValued;
import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class LocatableUnmarshalPostprocessor extends AbstractUnmarshalPostprocessor<Locatable> {

  private static final FeederAuditRMUnmarshaller FEEDER_AUDIT_RM_UNMARSHALLER =
      new FeederAuditRMUnmarshaller();

  /** {@inheritDoc} Unmarshalls {@link Composition#setUid} */
  @Override
  public void process(
      String term, Locatable rmObject, Map<FlatPathDto, String> values, Set<String> consumedPaths) {

    setValue(
        term + PATH_DIVIDER + "_uid",
        null,
        values,
        s -> rmObject.setUid(new HierObjectId(s)),
        String.class,
        consumedPaths);

    Map<Integer, Map<String, String>> links =
        extractMultiValued(term + PATH_DIVIDER + "_link", values);

    if (rmObject.getLinks() == null) {
      rmObject.setLinks(new ArrayList<>());
    }

    rmObject
        .getLinks()
        .addAll(
            links.values().stream().map(DefaultValues::createLink).collect(Collectors.toList()));

    consumeAllMatching(term + PATH_DIVIDER + "_link", values, consumedPaths);

    if (RMHelper.isEmpty(rmObject.getFeederAudit())) {

      rmObject.setFeederAudit(new FeederAudit());
      FEEDER_AUDIT_RM_UNMARSHALLER.handle(
          term + "/_feeder_audit", rmObject.getFeederAudit(), values, null, consumedPaths);

      if (RMHelper.isEmpty(rmObject.getFeederAudit())) {
        rmObject.setFeederAudit(null);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<Locatable> getAssociatedClass() {
    return Locatable.class;
  }
}
