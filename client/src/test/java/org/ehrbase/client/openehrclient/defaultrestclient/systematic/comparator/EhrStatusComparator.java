/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.client.openehrclient.defaultrestclient.systematic.comparator;

import com.nedap.archie.rm.ehr.EhrStatus;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EhrStatusComparator extends CanonicalUtil {

    private static boolean compareUid = false; //uid may be build implicitely or passed explicitely

    public EhrStatusComparator setCompareUid(boolean compareUid) {
        this.compareUid = compareUid;
        return this;
    }

    public static void compare(Map<String, Object> actualEhrStatusMap, EhrStatus referenceEhrStatus){
        EhrStatus ehrStatus = (EhrStatus) toRmObject(actualEhrStatusMap, EhrStatus.class);
        compare(ehrStatus, referenceEhrStatus);
    }

    public static void compare(EhrStatus actualEhrStatus, EhrStatus referenceEhrStatus){
        assertThat(actualEhrStatus.getName()).isEqualTo(referenceEhrStatus.getName());
        assertThat(actualEhrStatus.getArchetypeNodeId()).isEqualTo(referenceEhrStatus.getArchetypeNodeId());

        if (compareUid)
            assertThat(actualEhrStatus.getUid()).isEqualTo(referenceEhrStatus.getUid());

        assertThat(actualEhrStatus.getLinks()).isEqualTo(referenceEhrStatus.getLinks());
        assertThat(actualEhrStatus.getArchetypeDetails()).isEqualTo(referenceEhrStatus.getArchetypeDetails());
        assertThat(actualEhrStatus.getSubject()).isEqualTo(referenceEhrStatus.getSubject());
        assertThat(actualEhrStatus.getFeederAudit()).isEqualTo(referenceEhrStatus.getFeederAudit());
        assertThat(actualEhrStatus.getName()).isEqualTo(referenceEhrStatus.getName());
        assertThat(actualEhrStatus.isQueryable()).isEqualTo(referenceEhrStatus.isQueryable());
        assertThat(actualEhrStatus.isModifiable()).isEqualTo(referenceEhrStatus.isModifiable());
        assertThat(actualEhrStatus.getOtherDetails()).isEqualTo(referenceEhrStatus.getOtherDetails());
    }
}
