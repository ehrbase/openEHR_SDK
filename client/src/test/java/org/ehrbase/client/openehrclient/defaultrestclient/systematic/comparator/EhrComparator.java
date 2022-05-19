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
package org.ehrbase.client.openehrclient.defaultrestclient.systematic.comparator;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.support.identification.HierObjectId;
import java.util.Map;
import java.util.UUID;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil;
import org.ehrbase.client.openehrclient.defaultrestclient.systematic.comparator.datetime.CompareCanonicalDvDateTime;

public class EhrComparator extends CanonicalUtil {

    private final EhrStatus referenceEhrStatus;
    private final UUID ehrUUID;
    private final DvDateTime transactionDateTime;

    public EhrComparator(EhrStatus referenceEhrStatus, UUID ehrUUID, DvDateTime transactionDateTime) {
        this.referenceEhrStatus = referenceEhrStatus;
        this.ehrUUID = ehrUUID;
        this.transactionDateTime = transactionDateTime;
    }

    public Object compare(Map<String, Object> actualEhrMap) {
        String type = (String) actualEhrMap.get("_type");
        HierObjectId ehrId =
                (HierObjectId) toRmObject((Map<String, Object>) actualEhrMap.get("ehr_id"), HierObjectId.class);
        EhrStatus ehrStatus =
                (EhrStatus) toRmObject((Map<String, Object>) actualEhrMap.get("ehr_status"), EhrStatus.class);
        HierObjectId systemId =
                (HierObjectId) toRmObject((Map<String, Object>) actualEhrMap.get("system_id"), HierObjectId.class);
        DvDateTime timeCreated =
                (DvDateTime) toRmObject((Map<String, Object>) actualEhrMap.get("time_created"), DvDateTime.class);

        // high level attributes
        assertThat(type).isEqualTo("EHR");
        new CompareCanonicalHierObjects(ehrId).isExpectedEqualToCanonicalUsing(ehrUUID.toString());
        new CompareCanonicalHierObjects(systemId).isExpectedEqualToCanonicalUsing("local.ehrbase.org");
        new CompareCanonicalDvDateTime(timeCreated)
                .setCompareDateOnly(true)
                .isExpectedEqualToCanonicalUsing(transactionDateTime);

        EhrStatusComparator.compare(ehrStatus, referenceEhrStatus);

        return null;
    }
}
