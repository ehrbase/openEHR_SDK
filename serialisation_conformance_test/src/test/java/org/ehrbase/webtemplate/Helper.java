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

package org.ehrbase.webtemplate;

import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class Helper {
  private Helper() {}

  public static RMDataFormat getFlatJson(String template, FlatFormat flatFormat)
      throws XmlException, IOException {
    TemplateDocument templateDocument =
        TemplateDocument.Factory.parse(IOUtils.toInputStream(template, StandardCharsets.UTF_8));

    RMDataFormat flatJson =
        new FlatJasonProvider(t -> Optional.ofNullable(templateDocument.getTemplate()))
            .buildFlatJson(flatFormat, templateDocument.getTemplate().getTemplateId().getValue());
    return flatJson;
  }
}
