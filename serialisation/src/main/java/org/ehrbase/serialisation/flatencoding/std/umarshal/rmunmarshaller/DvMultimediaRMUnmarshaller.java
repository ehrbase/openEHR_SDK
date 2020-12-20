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

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvURI;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.support.identification.TerminologyId;
import java.net.URI;
import java.util.Map;
import org.ehrbase.serialisation.walker.Context;

public class DvMultimediaRMUnmarshaller extends AbstractRMUnmarshaller<DvMultimedia> {

  /** {@inheritDoc} */
  @Override
  public Class<DvMultimedia> getAssociatedClass() {
    return DvMultimedia.class;
  }

  /** {@inheritDoc} */
  @Override
  public void handle(
      String currentTerm,
      DvMultimedia rmObject,
      Map<String, String> currentValues,
      Context<Map<String, String>> context) {
    rmObject.setUri(new DvURI());
    rmObject.setMediaType(new CodePhrase());
    rmObject.getMediaType().setTerminologyId(new TerminologyId("IANA_media-types"));

    setValue(currentTerm, null, currentValues, rmObject.getUri()::setValue, URI.class);
    setValue(
        currentTerm,
        "mediatype",
        currentValues,
        rmObject.getMediaType()::setCodeString,
        String.class);
    setValue(currentTerm, "size", currentValues, rmObject::setSize, Integer.class);
  }
}
