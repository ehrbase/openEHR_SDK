/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import java.util.Set;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.serialisation.xmlencoding.CanonicalXML;
import org.ehrbase.openehr.sdk.webtemplate.templateprovider.TemplateProvider;

/**
 * Reference-Model Data format marshaller/unmarshaller
 */
public interface RMDataFormat {

    /**
     * Factory method to create a <code>XML</code> based marshaller/unmarshaller.
     *
     * @link <a href="https://specifications.openehr.org/releases/ITS-XML/development">ITS-XML</a>
     */
    static RMDataFormat canonicalXML() {
        return new CanonicalXML();
    }

    /**
     * Factory method to create a <code>JSON</code> based marshaller/unmarshaller.
     *
     * @link <a href="https://specifications.openehr.org/releases/ITS-JSON/development">ITS-JSON</a>
     */
    static RMDataFormat canonicalJSON() {
        return new CanonicalJson();
    }

    /**
     * Factory method to create a <code>JSON</code> based <code>Structured Simplified Data Template</code> marshaller/unmarshaller.
     *
     * @link <a href="https://specifications.openehr.org/releases/ITS-REST/latest/simplified_data_template.html">Simplified Data Template (SDT)</a>
     */
    static RMDataFormat sdtStructuredJSON(TemplateProvider templateProvider, String templateId) {
        return new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.STRUCTURED, templateId);
    }

    /**
     * Factory method to create a <code>JSON</code> based <code>Flat Simplified Data Template</code> marshaller/unmarshaller.
     *
     * @link <a href="https://specifications.openehr.org/releases/ITS-REST/latest/simplified_data_template.html">Simplified Data Template (SDT)</a>
     */
    static RMDataFormat sdtFlatJSON(TemplateProvider templateProvider, String templateId) {
        return new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.SIM_SDT, templateId);
    }

    /**
     * Marshals the given {@link RMObject} into a string representation using default {@link MarshalOption}s.
     *
     * @param rmObject to marshal
     * @return rmObjectStringValue
     */
    default String marshal(RMObject rmObject) {
        // use PRETTY_PRINT to have the same behavior same as versions <= 2.24.0
        return marshalWithOptions(rmObject, MarshalOption.PRETTY_PRINT);
    }

    /**
     * Convenient variadic alternative for {@link #marshalWithOptions(RMObject, Set)}.
     *
     * @see #marshalWithOptions(RMObject, Set)
     */
    default String marshalWithOptions(RMObject rmObject, MarshalOption... options) {
        return marshalWithOptions(rmObject, Set.of(options));
    }

    /**
     * Marshals the given {@link RMObject} into a string representation using the provided {@link MarshalOption}s.
     *
     * @param rmObject to marshal
     * @param options to use while marshalling
     * @return rmObjectStringValue
     */
    String marshalWithOptions(RMObject rmObject, Set<MarshalOption> options);

    /**
     * Unmarshalls the given {@link RMObject} string value
     *
     * @param value marshalled {@link RMObject}
     * @param clazz target {@link RMObject} type
     * @return rmObject
     * @param <T> of the {@link RMObject} to unmarshall into
     */
    <T extends RMObject> T unmarshal(String value, Class<T> clazz);

    /**
     * Convenient usage of {@link #unmarshal(String, Class)} that unmarshall into a {@link Composition}.
     *
     * @param value marshalled {@link Composition}
     * @return composition unmarshalled {@link Composition} of the given value
     */
    default Composition unmarshal(String value) {
        return unmarshal(value, Composition.class);
    }
}
