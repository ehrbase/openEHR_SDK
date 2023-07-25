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
package org.ehrbase.openehr.sdk.webtemplate.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class NodeIdTest {

    @Test
    public void isArchetypeId() {
        NodeId nodeId1 = new NodeId("openEHR-EHR-SECTION.adhoc.v1");
        assertThat(nodeId1.isArchetypeId()).isTrue();
        NodeId nodeId2 = new NodeId("at0004");
        assertThat(nodeId2.isArchetypeId()).isFalse();
    }

    @Test
    public void testEquals() {
        NodeId nodeId1 = new NodeId("openEHR-EHR-SECTION.adhoc.v1");
        NodeId nodeId2 = new NodeId("at0004");
        NodeId nodeId3 = new NodeId("SECTION");

        assertThat(nodeId1.equals(nodeId1)).isTrue();
        assertThat(nodeId2.equals(nodeId2)).isTrue();
        assertThat(nodeId3.equals(nodeId3)).isTrue();

        assertThat(nodeId1.equals(nodeId2)).isFalse();
        assertThat(nodeId1.equals(nodeId3)).isFalse();
        assertThat(nodeId2.equals(nodeId3)).isFalse();
    }

    @Test
    public void testGetClassName() {
        NodeId nodeId1 = new NodeId("openEHR-EHR-SECTION.adhoc.v1");
        assertThat(nodeId1.getClassName()).isEqualTo("SECTION");

        NodeId nodeId2 = new NodeId("SECTION");
        assertThat(nodeId2.getClassName()).isEqualTo("SECTION");
    }
}
