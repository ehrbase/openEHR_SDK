/*
 * Copyright (c) 2022 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.generator.commons.aql.funtion;

import java.util.Collections;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

/**
 * @author Stefan Spiska
 */
public interface Function {

    List<SelectAqlField<?>> getParameters();

    static AbstractFunction<Integer> count(SelectAqlField<?> field, String as) {

        return AbstractFunction.create(Collections.singletonList(field), AQLFunction.COUNT, as, Integer.class);
    }

    static AbstractFunction<Integer> count(SelectAqlField<?> field) {

        return count(field, null);
    }

    static AbstractFunction<Integer> max(SelectAqlField<?> field, String as) {

        return AbstractFunction.create(Collections.singletonList(field), AQLFunction.MAX, as, Integer.class);
    }

    static AbstractFunction<Integer> max(SelectAqlField<?> field) {

        return max(field, null);
    }

    static AbstractFunction<Integer> min(SelectAqlField<?> field, String as) {

        return AbstractFunction.create(Collections.singletonList(field), AQLFunction.MAX, as, Integer.class);
    }

    static AbstractFunction<Integer> min(SelectAqlField<?> field) {

        return min(field, null);
    }

    static AbstractFunction<Integer> avg(SelectAqlField<?> field, String as) {

        return AbstractFunction.create(Collections.singletonList(field), AQLFunction.AVG, as, Integer.class);
    }

    static AbstractFunction<Integer> avg(SelectAqlField<?> field) {

        return avg(field, null);
    }
}
