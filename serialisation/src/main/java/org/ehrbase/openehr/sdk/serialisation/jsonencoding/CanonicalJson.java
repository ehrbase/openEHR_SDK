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
package org.ehrbase.openehr.sdk.serialisation.jsonencoding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nedap.archie.base.OpenEHRBase;
import com.nedap.archie.paths.PathSegment;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Archetyped;
import com.nedap.archie.rm.archetyped.Link;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.archetyped.Pathable;
import com.nedap.archie.rm.archetyped.TemplateId;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import com.nedap.archie.rm.support.identification.ArchetypeID;
import com.nedap.archie.rm.support.identification.UIDBasedId;
import com.nedap.archie.rminfo.ArchieAOMInfoLookup;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.ModelInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.ehrbase.openehr.sdk.serialisation.MarshalOption;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.exception.MarshalException;
import org.ehrbase.openehr.sdk.serialisation.exception.UnmarshalException;

// test CJOpenEHRTypeNaming
/**
 *  Reference-Model Data format <code>XML</code> marshaller/unmarshaller
 *
 * @link <a href="https://specifications.openehr.org/releases/ITS-XML/development">ITS-XML</a>
 */
public class CanonicalJson implements RMDataFormat {

    public static final ObjectMapper MARSHAL_OM =
            ArchieObjectMapperProvider.getObjectMapper().copy();

    static {
        // Configuration to ignore methods that are not part of the RM
        MARSHAL_OM
                .addMixIn(ArchetypeID.class, ObjectIdMixIn.class)
                .addMixIn(Locatable.class, LocatableMixIn.class)
                .addMixIn(Pathable.class, PathableMixIn.class)
                .addMixIn(UIDBasedId.class, UIDBasedIdMixIn.class);

        SimpleModule module = new SimpleModule();
        module.addSerializer(DvDateTime.class, new DateTimeSerializer());
        module.addSerializer(DvTime.class, new TimeSerializer());
        module.addSerializer(DvDate.class, new DateSerializer());
        module.addSerializer(byte[].class, new Base64Serializer());
        module.addDeserializer(DvDateTime.class, new DateTimeDeserializer());
        module.addDeserializer(DvTime.class, new TimeDeserializer());
        module.addDeserializer(DvDate.class, new DateDeserializer());
        module.addDeserializer(byte[].class, new Base64Deserializer());
        MARSHAL_OM.registerModule(module);

        // Global configuration to not include empty lists in the JSON
        MARSHAL_OM.setDefaultPropertyInclusion(JsonInclude.Value.construct(
                Include.CUSTOM,
                Include.CUSTOM,
                ExcludeEmptyCollectionsFilter.class,
                ExcludeEmptyCollectionsFilter.class));

        // Avoid _type for final classes / concrete attributes with known type
        TypeResolverBuilder<?> typeResolverBuilder = new CJArchieTypeResolverBuilder()
                .init(JsonTypeInfo.Id.NAME, new CJOpenEHRTypeNaming())
                .typeProperty("_type")
                .typeIdVisibility(true)
                .inclusion(JsonTypeInfo.As.PROPERTY);
        MARSHAL_OM.setDefaultTyping(typeResolverBuilder);
    }

    private static class ExcludeEmptyCollectionsFilter {
        @Override
        // Return true to exclude
        public boolean equals(Object o) {

            // Exclude Null
            if (o == null) {
                return true;
            }
            if (o instanceof Map<?, ?> map) {
                return map.isEmpty();
            }
            if (o instanceof Collection<?> collection) {
                return collection.isEmpty();
            }
            if (o instanceof Object[] objects) {
                return objects.length == 0;
            }
            // Include everything else
            return false;
        }
    }

    @Override
    public String marshalWithOptions(RMObject rmObject, Set<MarshalOption> options) {
        StringWriter stringWriter = new StringWriter();

        try {
            final PrettyPrinter prettyPrinter = options.contains(MarshalOption.PRETTY_PRINT)
                    ? MARSHAL_OM.getSerializationConfig().constructDefaultPrettyPrinter()
                    : null;

            MARSHAL_OM.writer(prettyPrinter).writeValue(stringWriter, rmObject);
        } catch (IOException e) {
            throw new MarshalException(e.getMessage(), e);
        }

        return stringWriter.toString();
    }

    @Override
    public <T extends RMObject> T unmarshal(String value, Class<T> clazz) {
        try {
            return MARSHAL_OM.readValue(value, clazz);
        } catch (IOException e) {
            throw new UnmarshalException(e.getMessage(), e);
        }
    }

    abstract class ObjectIdMixIn {
        ObjectIdMixIn(@JsonProperty String value) {}

        @JsonProperty("value")
        abstract String getValue();

        @JsonIgnore
        abstract String getFullId();

        @JsonIgnore
        abstract String getSemanticId();

        @JsonIgnore
        abstract String getQualifiedRmEntity();

        @JsonIgnore
        abstract String getDomainConcept();

        @JsonIgnore
        abstract String getRmOriginator();

        @JsonIgnore
        abstract String getRmName();

        @JsonIgnore
        abstract String getRmEntity();

        @JsonIgnore
        abstract String getSpecialisation();

        @JsonIgnore
        abstract String getVersionId();
    }

    abstract class UIDBasedIdMixIn {
        UIDBasedIdMixIn(@JsonProperty String value) {}

        @JsonProperty("value")
        abstract String getValue();

        @JsonIgnore
        abstract String getRoot();

        @JsonIgnore
        abstract String getExtension();
    }

    @JsonInclude(Include.NON_EMPTY)
    abstract class LocatableMixIn {
        LocatableMixIn(
                @JsonProperty UIDBasedId uid, @JsonProperty String archetypeNodeId, @JsonProperty List<Link> links) {}

        @JsonProperty("archetype_node_id")
        abstract String getArchetypeNodeId();

        @JsonProperty("uid")
        abstract UIDBasedId getUid();

        @JsonProperty("links")
        abstract List<Link> getLinks();
    }

    abstract class PathableMixIn {
        PathableMixIn() {}

        @JsonIgnore
        abstract String getPath();

        @JsonIgnore
        abstract List<PathSegment> getPathSegments();
    }

    // Type Resolver to avoid generting _type for final classes
    static class CJArchieTypeResolverBuilder extends ObjectMapper.DefaultTypeResolverBuilder {
        public CJArchieTypeResolverBuilder() {
            super(ObjectMapper.DefaultTyping.NON_FINAL);
        }

        @Override
        public boolean useForType(JavaType t) {
            return (OpenEHRBase.class.isAssignableFrom(t.getRawClass())
                    && !ArchetypeID.class.equals(t.getRawClass())
                    && !TemplateId.class.equals(t.getRawClass())
                    && !Archetyped.class.equals(t.getRawClass())
                    && !Link.class.equals(t.getRawClass())
                    &&
                    //                            !CodePhrase.class.equals(t.getRawClass()) &&
                    !History.class.equals(t.getRawClass())
            //                            !TerminologyId.class.equals(t.getRawClass())
            );
        }
    }

    // Test, inner class copied from com.nedap.archie.json.OpenEHRTypeNaming
    public static class CJOpenEHRTypeNaming extends ClassNameIdResolver {

        private ModelInfoLookup rmInfoLookup = ArchieRMInfoLookup.getInstance();
        private ModelInfoLookup aomInfoLookup = ArchieAOMInfoLookup.getInstance();

        public CJOpenEHRTypeNaming() {
            super(TypeFactory.defaultInstance().constructType(OpenEHRBase.class), TypeFactory.defaultInstance());
        }

        @Override
        public JsonTypeInfo.Id getMechanism() {
            return JsonTypeInfo.Id.NAME;
        }

        @Override
        public String idFromValue(Object value) {

            RMTypeInfo typeInfo = rmInfoLookup.getTypeInfo(value.getClass());
            if (typeInfo == null) {
                typeInfo = aomInfoLookup.getTypeInfo(value.getClass());
            }
            if (typeInfo != null) {
                // this case is faster and should always work. If for some reason it does not, the case
                // below works fine instead.
                return typeInfo.getRmName();
            } else {
                return rmInfoLookup.getNamingStrategy().getTypeName(value.getClass());
            }
        }

        @Override
        public JavaType typeFromId(DatabindContext context, String id) throws IOException {
            return _typeFromId(id, context);
        }

        @Override
        protected JavaType _typeFromId(String typeName, DatabindContext ctxt) throws IOException {
            Class result = rmInfoLookup.getClass(typeName);
            if (result == null) {
                // AOM class?
                result = aomInfoLookup.getClass(typeName);
            }
            if (result != null) {
                TypeFactory typeFactory = (ctxt == null) ? _typeFactory : ctxt.getTypeFactory();
                return typeFactory.constructSpecializedType(_baseType, result);
            }
            return super._typeFromId(typeName, ctxt);
        }
    }
}
