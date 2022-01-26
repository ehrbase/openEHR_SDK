/*
 * Modifications copyright (C) 2019 Christian Chevalley, Vitasystems GmbH and Hannover Medical School

 * This file is part of Project EHRbase

 * Copyright (c) 2015 Christian Chevalley
 * This file is part of Project Ethercis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.validation.constraints.hardwired;

import com.nedap.archie.rm.datastructures.History;
import org.ehrbase.validation.constraints.ConstraintMapper;
import org.ehrbase.validation.constraints.wrappers.ValidationException;
import org.ehrbase.validation.CompositionValidator;

/**
 * Validate an RM HISTORY node
 *
 * @link https://specifications.openehr.org/releases/RM/latest/data_structures.html#_history_t_class
 * <p>
 * Created by christian on 8/11/2016.
 * @see History
 * @deprecated as of release 1.7, in favor of {@link CompositionValidator}
 */
@Deprecated(since = "1.7")
public class CHistory extends StructureConstraint implements I_CHWConstraintValidate {


    public CHistory(ConstraintMapper constraintMapper) {
        super(constraintMapper);
    }

    @Override
    public void validate(String path, Object aValue) throws IllegalArgumentException {
        if (!(aValue instanceof History))
            ValidationException.raise(path, "Unexpected value type in History:" + aValue, "HIS01");

        History history = (History) aValue;
        Integer eventsOccurrences = history.getEvents().size();

        validate(path, eventsOccurrences);
    }
}
