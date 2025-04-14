/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.dto.path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.ehrbase.openehr.sdk.aql.dto.operand.StringPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPath.PathNode;
import org.ehrbase.openehr.sdk.aql.dto.path.ComparisonOperatorPredicate.PredicateComparisonOperator;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.junit.jupiter.api.Test;

class AqlObjectPathTest {

    @Test
    void roundTrip() {
        String path = "content[name/value='1234']/data[name[value=\"1234\"]/value=1234]/events[at001]";
        assertEquals(path.replace('"', '\''), AqlObjectPath.parse(path).render());
    }

    @Test
    void deepClone() {
        String pathStr = "content[name/value='1234']/data[name[value='1234']/value=1234]/events[at001]";
        AqlObjectPath aqlObjectPath = AqlObjectPath.parse(pathStr);
        AqlObjectPath clone = aqlObjectPath.clone();

        assertEquals(aqlObjectPath, clone);
        assertEquals(pathStr, clone.render());

        PathNode firstClonedNode = clone.getPathNodes().get(0);
        firstClonedNode
                .getPredicateOrOperands()
                .get(0)
                .add(AqlObjectPathUtil.NAME_CODE_STRING, PredicateComparisonOperator.EQ, new StringPrimitive("foo"));

        assertNotEquals(aqlObjectPath, clone);
        assertNotEquals(pathStr, clone.render());

        assertEquals(pathStr, aqlObjectPath.render());
    }

    @Test
    void aqlPathRoundtrip() {
        String path = "content[name/value='1234']/data[at001]/events[at001,'1234']";

        AqlObjectPath aop = AqlObjectPath.parse(path);
        AqlPath ap = AqlPath.parse(path);

        assertEquals(aop, AqlObjectPath.fromAqlPath(ap));
        assertEquals(ap, AqlObjectPath.toAqlPath(aop));
    }

    @Test
    void endsWith() {

        String path1 = "content[name/value='1234']/data/events[at0001,'1234']";
        String path2 = "data/events[at0001,'1234']";

        AqlObjectPath aop1 = AqlObjectPath.parse(path1);
        AqlObjectPath aop2 = AqlObjectPath.parse(path2);

        assertThat(aop1.endsWith(aop1)).isTrue();
        assertThat(aop1.endsWith(aop2)).isTrue();
        assertThat(aop2.endsWith(aop1)).isFalse();

        assertThat(aop1.endsWith(null)).isTrue();
    }
}
