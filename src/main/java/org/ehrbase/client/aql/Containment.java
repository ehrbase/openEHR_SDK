/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.aql;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.exception.ClientException;

public class Containment implements ContainmentExpression {

    private final Class<? extends Locatable> type;
    private final String archetype;
    private EntityQuery<?> query;
    private ContainmentExpression child;


    public Containment(String archetype) {
        this.archetype = archetype;
        String typeName = StringUtils.substringBetween(archetype, "openEHR-EHR-", ".");
        if (StringUtils.isBlank(typeName)) {
            typeName = archetype;
        }
        switch (typeName) {
            case "COMPOSITION":
                type = Composition.class;
                break;
            case "OBSERVATION":
                type = Observation.class;
                break;
            case "EHR":
                type = null;
                break;
            default:
                throw new ClientException(String.format("Unknown type %s", typeName));
        }
    }

    @Override
    public String buildAQL() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(type.getSimpleName().toUpperCase())
                .append(" ")
                .append(getVariableName())
                .append("[").append(archetype).append("]");
        if (child != null) {
            sb
                    .append("contains ")
                    .append(child.buildAQL());
        }
        return sb.toString();
    }

    String getVariableName() {
        return query.buildVariabelName(this);
    }


    @Override
    public void bindQuery(EntityQuery<?> query) {
        this.query = query;
        if (child != null) {
            child.bindQuery(query);
        }
    }

    public <T> SelectField<T> bindField(EntityField<T> entityField) {

        FieldImp<T> f = (FieldImp<T>) entityField;
        f.setContainment(this);
        return f;
    }


    protected Class<? extends Locatable> getType() {
        return type;
    }

    public ContainmentExpression getChild() {
        return child;
    }

    public void setChild(ContainmentExpression child) {
        this.child = child;
    }
}
