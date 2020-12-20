/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.serialisation.attributes;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

import com.nedap.archie.rm.composition.Entry;
import java.util.Map;
import org.ehrbase.serialisation.dbencoding.CompositionSerializer;
import org.ehrbase.serialisation.dbencoding.ItemStack;

/**
 * populate the attributes for RM Entry
 * https://specifications.openehr.org/releases/UML/latest/#Architecture___18_1_83e026d_1433773264997_829602_8426
 */
public abstract class EntryAttributes extends LocatableAttributes {

  public EntryAttributes(
      CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
    super(compositionSerializer, itemStack, map);
  }

  protected Map<String, Object> toMap(Entry entry) {
    // add complementary attributes

    if (entry.getSubject() != null) {
      map.put(
          TAG_SUBJECT, new SubjectAttributes(entry.getSubject(), compositionSerializer).toMap());
    }
    if (entry.getLanguage() != null) {
      map.put(TAG_LANGUAGE, entry.getLanguage());
    }
    if (entry.getProvider() != null) {
      map.put(
          TAG_PROVIDER, new SubjectAttributes(entry.getProvider(), compositionSerializer).toMap());
    }
    if (entry.getEncoding() != null) {
      map.put(TAG_ENCODING, entry.getEncoding());
    }

    if (entry.getWorkflowId() != null)
      map = toMap(TAG_WORKFLOW_ID, entry.getWorkflowId(), entry.getName());

    if (entry.getOtherParticipations() != null && !entry.getOtherParticipations().isEmpty()) {
      map.put(
          TAG_OTHER_PARTICIPATIONS,
          new OtherParticipationAttributes(entry.getOtherParticipations(), compositionSerializer)
              .toMap());
    }

    map = super.toMap(entry);

    return map;
  }
}
