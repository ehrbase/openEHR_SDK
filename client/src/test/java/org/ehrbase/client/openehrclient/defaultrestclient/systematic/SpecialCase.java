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

package org.ehrbase.client.openehrclient.defaultrestclient.systematic;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.serialisation.dbencoding.wrappers.json.I_DvTypeAdapter;

import java.net.URI;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Map;

import static org.ehrbase.client.openehrclient.defaultrestclient.systematic.CanonicalUtil.toMap;

public class SpecialCase {

    public Object transform(Object object){
        Object retObject = object;

        if (object instanceof PartySelf && (((PartySelf)object).getExternalRef() == null || ((PartySelf)object).getExternalRef().getId() == null) )
            retObject = "PARTY_SELF";
        else if (object instanceof RMObject) {
            retObject = toMap((RMObject) object);
            if (object instanceof TerminologyId) {
                //add the type as it is sometime implicit with archie but required for comparison
                ((Map<String, Object>) retObject).put(I_DvTypeAdapter.AT_TYPE, "TERMINOLOGY_ID");
            }
        }
        else  if (object instanceof Temporal || object instanceof Duration || object instanceof URI) {
            retObject = object.toString();
            if (object instanceof Temporal){
                //align the millisec delimiter
                if (retObject instanceof String && ((String) retObject).contains("."))
                    retObject = ((String)retObject).replace(".", ",");
            }
        }
        else if (object instanceof Integer)
            //this is a hack as a Long is transformed as Integer in the HTTP response?
            retObject = Integer.toUnsignedLong((Integer)object);
        else if (object instanceof String &&
                ((String)object).matches("^\\d{4}-\\d\\d-\\d\\dT\\d\\d:\\d\\d:\\d\\d(\\.\\d+)?(([+-]\\d\\d:\\d\\d)|Z)?$"))
            retObject = ((String)retObject).replace(".", ",");

        return retObject;
    }
}
