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

package org.ehrbase.serialisation.flatencoding;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.exception.MarshalException;
import org.ehrbase.serialisation.flatencoding.std.marshal.FlatJsonMarshaller;
import org.ehrbase.serialisation.flatencoding.std.umarshal.FlatJsonUnmarshaller;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.filter.Filter;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

public class FlatJson implements RMDataFormat {

  private final OPERATIONALTEMPLATE operationaltemplate;
  private final WebTemplate templateIntrospect;
  private final FlatJsonMarshaller flatJsonMarshaller;
  private final FlatJsonUnmarshaller flatJsonUnmarshaller;

  FlatJson(FlatJasonProvider flatJasonProvider, String templateId) {

    operationaltemplate =
        flatJasonProvider
            .getTemplateProvider()
            .find(templateId)
            .orElseThrow(
                () -> new SdkException(String.format("Template %s not found", templateId)));

    flatJsonUnmarshaller = new FlatJsonUnmarshaller();
    templateIntrospect =
        flatJasonProvider
            .getTemplateProvider()
            .buildIntrospect(templateId)
            .map(t -> new Filter().filter(t))
            .orElseThrow(
                () -> new SdkException(String.format("Template %s not found", templateId)));
    flatJsonMarshaller = new FlatJsonMarshaller();
  }

  @Override
  public String marshal(RMObject rmObject) {
    if (rmObject instanceof Composition) {
      return flatJsonMarshaller.toFlatJson((Composition) rmObject, templateIntrospect);
    } else {
      throw new MarshalException(
          String.format(
              "Class %s not supported in flat format", rmObject.getClass().getSimpleName()));
    }
  }

  @Override
  public <T extends RMObject> T unmarshal(String value, Class<T> clazz) {
    if (clazz.isAssignableFrom(Composition.class)) {
      return (T) unmarshal(value);
    } else {
      throw new SdkException(
          String.format("Class %s not supported in flat format", clazz.getSimpleName()));
    }
  }

  public Composition unmarshal(String value) {
    return flatJsonUnmarshaller.unmarshal(value, templateIntrospect, operationaltemplate);
  }
}
