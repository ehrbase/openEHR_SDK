/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.matrixencoding;

import static org.ehrbase.util.rmconstants.RmConstants.DV_TEXT;

import org.ehrbase.util.rmconstants.RmConstants;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * @author Stefan Spiska
 */
class MatrixUtil {

    private MatrixUtil() {
        // util class
    }

    static void addMissingChildren(WebTemplateNode child) {
        // Webtemplate is missing "link" for Element thus we add it here
        if (child.getRmType().equals(RmConstants.ELEMENT)) {

            WebTemplateNode links = new WebTemplateNode();

            links.setRmType("LINK");
            links.setAqlPath(child.getAqlPathDto().addEnd("/links"));
            links.setId("links");
            child.getChildren().add(links);
            child.setMax(1);
            child.setMin(0);
        }
        // Webtemplate is missing "reason" for ISM_TRANSITION thus we add it here
        if (child.getRmType().equals(RmConstants.ISM_TRANSITION)) {

            WebTemplateNode reason = new WebTemplateNode();

            reason.setRmType(DV_TEXT);
            reason.setAqlPath(child.getAqlPathDto().addEnd("/reason"));
            reason.setId("reason");
            child.getChildren().add(reason);
            child.setMax(1);
            child.setMin(0);
        }
    }
}
