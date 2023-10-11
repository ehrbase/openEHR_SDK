/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal.config.DefaultStdConfig;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal.config.StdConfig;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal.postprocessor.MarshalPostprocessor;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal.StdToCompositionWalker;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.FromCompositionWalker;
import org.ehrbase.openehr.sdk.util.reflection.ReflectionHelper;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.ehrbase.openehr.sdk.webtemplate.util.WebTemplateUtils;

public class StdFromCompositionWalker extends FromCompositionWalker<Map<String, Object>> {

    private static final Map<Class<? extends RMObject>, StdConfig> configMap =
            ReflectionHelper.buildMap(StdConfig.class);
    private static final Map<Class<? extends RMObject>, MarshalPostprocessor> POSTPROCESSOR_MAP =
            ReflectionHelper.buildMap(MarshalPostprocessor.class);
    public static final DefaultStdConfig DEFAULT_STD_CONFIG = new DefaultStdConfig();
    public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    @Override
    protected Map<String, Object> extract(
            Context<Map<String, Object>> context, WebTemplateNode child, boolean isChoice, Integer i) {

        return context.getObjectDeque().peek();
    }

    @Override
    protected void preHandle(Context<Map<String, Object>> context) {

        // Handle if at a End-Node
        if (!visitChildren(context.getNodeDeque().peek())
                && !context.getFlatHelper().skip(context)) {
            RMObject currentObject = context.getRmObjectDeque().peek();

            StdConfig stdConfig = findStdConfig(currentObject.getClass());

            context.getObjectDeque()
                    .peek()
                    .putAll(stdConfig.buildChildValues(
                            context.getFlatHelper().buildNamePath(context, true), currentObject, context));
        }
    }

    public static <T extends RMObject> StdConfig<T> findStdConfig(Class<T> aClass) {
        return configMap.getOrDefault(aClass, DEFAULT_STD_CONFIG);
    }

    @Override
    protected void postHandle(Context<Map<String, Object>> context) {

        Class<? extends RMObject> aClass = context.getRmObjectDeque().peek().getClass();

        List<? extends MarshalPostprocessor<? extends RMObject>> postprocessor = findPostprocessors(aClass);
        WebTemplateNode currentNode = context.getNodeDeque().poll();
        WebTemplateNode parentNode = context.getNodeDeque().peek();
        context.getNodeDeque().push(currentNode);

        if (parentNode == null || isNameAttribute(currentNode, parentNode)) {
            postprocessor.forEach(p -> ((MarshalPostprocessor) p)
                    .process(
                            StdToCompositionWalker.buildNamePathWithElementHandling(context),
                            context.getRmObjectDeque().peek(),
                            context.getObjectDeque().peek(),
                            context));
        }
    }

    private boolean isNameAttribute(WebTemplateNode currentNode, WebTemplateNode parentNode) {
        return !(currentNode != null && currentNode.getId().equals("name")) && isLocatable(parentNode);
    }

    private boolean isLocatable(WebTemplateNode parent) {
        return Locatable.class.isAssignableFrom(
                ARCHIE_RM_INFO_LOOKUP.getTypeInfo(parent.getRmType()).getJavaClass());
    }

    public static <T extends RMObject> List<MarshalPostprocessor<T>> findPostprocessors(Class<T> aClass) {
        List<MarshalPostprocessor<T>> postprocessor = new ArrayList<>();

        Class<?> currentClass = aClass;

        while (currentClass != null) {
            if (POSTPROCESSOR_MAP.containsKey(currentClass)) {
                postprocessor.add(POSTPROCESSOR_MAP.get(currentClass));
            }

            currentClass = currentClass.getSuperclass();
        }

        Collections.reverse(postprocessor);
        return postprocessor;
    }

    @Override
    protected void handleInheritance(WebTemplateNode currentNode) {
        if (currentNode.getRmType().equals(RmConstants.ELEMENT)
                && WebTemplateUtils.isChoiceDvCodedTextAndDvText(currentNode)) {
            StdToCompositionWalker.handleDVTextInternal(currentNode);
        } else {
            super.handleInheritance(currentNode);
        }
    }
}
