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

package org.ehrbase.serialisation.flatencoding.std.marshal.config;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvURI;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import org.ehrbase.serialisation.walker.Context;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DvMultimediaConfig extends AbstractsStdConfig<DvMultimedia> {

  /** {@inheritDoc} */
  @Override
  public Class<DvMultimedia> getAssociatedClass() {
    return DvMultimedia.class;
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> buildChildValues(
      String currentTerm, DvMultimedia rmObject, Context<Map<String, Object>> context) {
    Map<String, Object> result = new HashMap<>();

    addValue(
        result,
        currentTerm,
        null,
        Optional.of(rmObject).map(DvMultimedia::getUri).map(DvURI::getValue).orElse(null));

    addValue(
        result,
        currentTerm,
        "mediatype",
        Optional.of(rmObject)
            .map(DvMultimedia::getMediaType)
            .map(CodePhrase::getCodeString)
            .orElse(null));

    addValue(
            result,
            currentTerm,
            "compression_algorithm",
            Optional.of(rmObject)
                    .map(DvMultimedia::getCompressionAlgorithm)
                    .map(CodePhrase::getCodeString)
                    .orElse(null));

    addValue(
            result,
            currentTerm,
            "integrity_check_algorithm",
            Optional.of(rmObject)
                    .map(DvMultimedia::getIntegrityCheckAlgorithm)
                    .map(CodePhrase::getCodeString)
                    .orElse(null));

    addValue(
            result,
            currentTerm,
            "integrity_check",
            Optional.of(rmObject)
                    .map(DvMultimedia::getIntegrityCheck)
                    .map(b -> new String(b, StandardCharsets.UTF_8))
                    .orElse(null));

    addValue(
            result,
            currentTerm,
            "data",
            Optional.of(rmObject)
                    .map(DvMultimedia::getData)
                    .map(b -> new String(b, StandardCharsets.UTF_8))
                    .orElse(null));

    addValue(result, currentTerm, "size", rmObject.getSize());
    addValue(result, currentTerm, "alternatetext", rmObject.getAlternateText());
    return result;
  }
}
