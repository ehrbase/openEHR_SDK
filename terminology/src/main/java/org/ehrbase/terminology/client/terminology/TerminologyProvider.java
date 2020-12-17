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

package org.ehrbase.terminology.client.terminology;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.support.identification.TerminologyId;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.terminology.openehr.implementation.LocalizedTerminologies;
import org.ehrbase.util.exception.SdkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminologyProvider {
  public static final String OPENEHR = "openehr";
  private static final LocalizedTerminologies LOCALIZED_TERMINOLOGIES;
  private static final Logger LOGGER = LoggerFactory.getLogger(TerminologyProvider.class);

  static {
    try {
      LOCALIZED_TERMINOLOGIES = new LocalizedTerminologies();
    } catch (Exception e) {
      throw new SdkException(e.getMessage(), e);
    }
  }

  public static ValueSet findOpenEhrValueSet(String id, String group) {
    try {
      if (StringUtils.isNotBlank(group)) {
        return new ValueSet(
            id,
            group,
            LOCALIZED_TERMINOLOGIES.getDefault().terminology(id).codesForGroupId(group).stream()
                .map((CodePhrase cp) -> convert(id, cp))
                .collect(Collectors.toSet()));
      } else {
        return new ValueSet(
            id,
            "all",
            LOCALIZED_TERMINOLOGIES.getDefault().codeSet(id).allCodes().stream()
                .map((CodePhrase cp) -> convert(id, cp))
                .collect(Collectors.toSet()));
      }
    } catch (RuntimeException e) {
      LOGGER.warn("Unknown  group {} in Terminology {}", group, id);
      return ValueSet.EMPTY_VALUE_SET;
    }
  }

  public static ValueSet findOpenEhrValueSet(String id, String[] values) {
    try {
      if (ArrayUtils.isNotEmpty(values)) {
        return new ValueSet(
            id,
            "local",
            Arrays.stream(values)
                .map(v -> new CodePhrase(new TerminologyId(id), v))
                .map((CodePhrase cp) -> convert(id, cp))
                .collect(Collectors.toSet()));
      } else {
        return new ValueSet(
            id,
            "all",
            LOCALIZED_TERMINOLOGIES.getDefault().codeSet(id).allCodes().stream()
                .map((CodePhrase cp) -> convert(id, cp))
                .collect(Collectors.toSet()));
      }
    } catch (RuntimeException e) {
      LOGGER.warn("Unknown  value {} in Terminology {}", values, id);
      return ValueSet.EMPTY_VALUE_SET;
    }
  }

  private static TermDefinition convert(String id, CodePhrase codePhrase) {
    String value;
    try {
      value =
          LOCALIZED_TERMINOLOGIES
              .getDefault()
              .terminology(id)
              .rubricForCode(codePhrase.getCodeString(), "en");
    } catch (RuntimeException e) {
      value = codePhrase.getCodeString();
    }
    return new TermDefinition(codePhrase.getCodeString(), value, value);
  }
}
