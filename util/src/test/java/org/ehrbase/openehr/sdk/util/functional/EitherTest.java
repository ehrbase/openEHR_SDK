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
package org.ehrbase.openehr.sdk.util.functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EitherTest {

    @Test
    void left() throws Exception {
        Integer result = Integer.valueOf(123);
        Either<Integer, Exception> left = Either.left(result);

        Assertions.assertTrue(left.isLeft());
        Assertions.assertFalse(left.isRight());
        Assertions.assertEquals(result, left.get());
        Assertions.assertEquals(result, left.getAsLeft());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> left.getAsRight());
    }

    @Test
    void failure() {
        RuntimeException exception = new RuntimeException();
        Either<Object, Exception> right = Try.right(exception);

        Assertions.assertFalse(right.isLeft());
        Assertions.assertTrue(right.isRight());
        Assertions.assertEquals(exception, right.get());
        Assertions.assertEquals(exception, right.getAsRight());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> right.getAsLeft());
    }
}
