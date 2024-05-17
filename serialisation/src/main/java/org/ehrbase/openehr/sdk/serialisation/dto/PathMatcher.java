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
package org.ehrbase.openehr.sdk.serialisation.dto;

import java.util.Map;
import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PathMatcher {

    Logger logger = LoggerFactory.getLogger(getClass());

    AqlPath matchesPath(Context<?> context, WebTemplateNode child, Map.Entry<AqlPath, ?> e) {
        AqlPath aqlPath =
                child.getAqlPathDto().removeStart(context.getNodeDeque().peek().getAqlPathDto());
        if (e.getKey().startsWith(aqlPath)) {
            return remove(e, aqlPath, child);
        } else {
            AqlPath pathWithoutLastName = aqlPath.replaceLastNode(n -> n.withNameValue(null));
            if (e.getKey().startsWith(pathWithoutLastName)
                    && context.getNodeDeque().peek().getChildren().stream()
                                    .filter(n -> Objects.equals(n.getNodeId(), child.getNodeId()))
                                    .count()
                            == 1) {
                logger.warn("name/value not set in dto for {}", child.getAqlPathDto());
                return remove(e, pathWithoutLastName, child);
            } else {
                return null;
            }
        }
    }

    private AqlPath remove(Map.Entry<AqlPath, ?> e, AqlPath s, WebTemplateNode child) {
        if (child.getId().equals("ism_transition")) {
            AqlPath aqlPath = e.getKey().removeStart(s);
            // fix for old dto model
            if (!aqlPath.hasPath()) {
                return null;
            }
        }
        return e.getKey().removeStart(s);
    }
}
