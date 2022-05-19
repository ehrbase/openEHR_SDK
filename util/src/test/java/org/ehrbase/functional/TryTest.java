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
package org.ehrbase.functional;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TryTest {

    @Test
    void success() throws Exception {
        Integer result = Integer.valueOf(123);
        Try<Integer, Exception> success = Try.success(result);

        Assertions.assertTrue(success.isSuccess());
        Assertions.assertFalse(success.isFailure());
        Assertions.assertEquals(result, success.getAsSuccess().get());
        Assertions.assertEquals(result, success.get());
        Assertions.assertEquals(result, success.getOrThrow());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> success.getAsFailure());

        Assertions.assertTrue(result * 2 == success.map((i, e) -> i + i));

        List<Integer> list = new ArrayList<>();
        success.consume((i, e) -> list.add(i));
        Assertions.assertTrue(list.size() == 1);
    }

    @Test
    void failure() {
        RuntimeException exception = new RuntimeException();
        Try<Object, Exception> failure = Try.failure(exception);

        Assertions.assertFalse(failure.isSuccess());
        Assertions.assertTrue(failure.isFailure());
        Assertions.assertEquals(exception, failure.getAsFailure().get());
        Assertions.assertEquals(exception, failure.get());
        Assertions.assertThrows(RuntimeException.class, () -> failure.getOrThrow());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> failure.getAsSuccess());

        Assertions.assertEquals(exception, failure.map((i, e) -> e));

        List<Exception> list = new ArrayList<>();
        failure.consume((i, e) -> list.add(e));
        Assertions.assertTrue(list.size() == 1);
    }
}
