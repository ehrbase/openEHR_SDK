/*
 * Copyright (c) 2020 vitasystems GmbH.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.walker;

import com.nedap.archie.rm.RMObject;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

public class Context<T> {

    private final Deque<WebTemplateNode> nodeDeque = new ArrayDeque<>();

    private final Deque<RMObject> rmObjectDeque = new ArrayDeque<>();

    private final Deque<T> objectDeque = new ArrayDeque<>();

    private final Map<NodeId, Integer> countMap = new HashMap<>();

    private DefaultValues defaultValues;

    private final FlatHelper<T> flatHelper = new FlatHelper<>();

    private String templateId;

    public Deque<WebTemplateNode> getNodeDeque() {
        return nodeDeque;
    }

    public Deque<RMObject> getRmObjectDeque() {
        return rmObjectDeque;
    }

    public Deque<T> getObjectDeque() {
        return objectDeque;
    }

    public Map<NodeId, Integer> getCountMap() {
        return countMap;
    }

    public DefaultValues getDefaultValues() {
        return defaultValues;
    }

    public void setDefaultValues(DefaultValues defaultValues) {
        this.defaultValues = defaultValues;
    }

    public FlatHelper<T> getFlatHelper() {
        return flatHelper;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
