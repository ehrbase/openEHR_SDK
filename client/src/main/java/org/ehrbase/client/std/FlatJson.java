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

package org.ehrbase.client.std;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.client.std.marshal.FlatJsonMarshaller;
import org.ehrbase.client.std.umarshal.FlatJsonUnmarshaller;
import org.ehrbase.serialisation.RMDataFormat;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

public class FlatJson implements RMDataFormat {

    private final OPERATIONALTEMPLATE operationaltemplate;
    private final TemplateIntrospect templateIntrospect;
    private final FlatJsonMarshaller flatJsonMarshaller;
    private final FlatJsonUnmarshaller flatJsonUnmarshaller;

    FlatJson(FlatJasonProvider flatJasonProvider, String templateId) {

        operationaltemplate = flatJasonProvider.getTemplateProvider().find(templateId).orElseThrow(() -> new ClientException(String.format("Template %s not found", templateId)));

        flatJsonUnmarshaller = new FlatJsonUnmarshaller();
        templateIntrospect = flatJasonProvider.getTemplateProvider().buildIntrospect(templateId).orElseThrow(() -> new ClientException(String.format("Template %s not found", templateId)));
        flatJsonMarshaller = new FlatJsonMarshaller(templateIntrospect);

    }

    @Override
    public String marshal(RMObject rmObject) {
        if (rmObject instanceof Composition) {
            return flatJsonMarshaller.toFlatJson((Composition) rmObject);
        } else {
            throw new ClientException(String.format("Class %s not supported in flat format", rmObject.getClass().getSimpleName()));
        }
    }

    @Override
    public <T extends RMObject> T unmarshal(String value, Class<T> clazz) {
        if (clazz.isAssignableFrom(Composition.class)) {
            return (T) unmarshal(value);
        } else {
            throw new ClientException(String.format("Class %s not supported in flat format", clazz.getSimpleName()));
        }
    }

    public Composition unmarshal(String value) {
        return flatJsonUnmarshaller.unmarshal(value, templateIntrospect, operationaltemplate);
    }
}
