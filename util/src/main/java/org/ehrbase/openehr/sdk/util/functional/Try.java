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

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public abstract class Try<S, E extends Exception> extends Either<S, E> {
    public static <S1, E1 extends Exception> Success<S1, E1> success(S1 result) {
        return new Success<>(result);
    }

    public static <S1, E1 extends Exception> Failure<S1, E1> failure(E1 failure) {
        return new Failure<>(failure);
    }

    protected Try(Object result) {
        super(result);
    }

    public boolean isLeft() {
        return isSuccess();
    }

    public boolean isRight() {
        return isFailure();
    }

    public abstract boolean isSuccess();

    public abstract boolean isFailure();

    public abstract Object get();

    public abstract S getOrThrow() throws E;

    public abstract Success<S, E> getAsSuccess();

    public abstract Failure<S, E> getAsFailure();

    /**
     * Convenience method to transform the internal state
     * @param map function which maps the internal state to the specified type
     * The function must be null safe in both arguments
     * @return the mapped internal state
     */
    public abstract <T> T map(BiFunction<S, E, T> map);

    /**
     * Convenience method to consume the internal state
     * @param con consumer which consumes the internal state
     * The consumer must be null safe in both arguments
     */
    public abstract void consume(BiConsumer<S, E> con);

    public static class Success<S0, E0 extends Exception> extends Try<S0, E0> {
        private Success(S0 success) {
            super(success);
        }

        public boolean isSuccess() {
            return true;
        }

        public boolean isFailure() {
            return false;
        }

        @SuppressWarnings("unchecked")
        public S0 get() {
            return (S0) super.value;
        }

        public S0 getOrThrow() throws E0 {
            return get();
        }

        public Success<S0, E0> getAsSuccess() {
            return this;
        }

        public Failure<S0, E0> getAsFailure() {
            throw new UnsupportedOperationException();
        }

        /**
         * @see Try#map(BiFunction)
         */
        public <T> T map(BiFunction<S0, E0, T> map) {
            return map.apply(get(), null);
        }

        /**
         * @see Try#consume(BiConsumer)
         */
        public void consume(BiConsumer<S0, E0> con) {
            con.accept(get(), null);
        }

        public S0 getAsLeft() {
            return this.get();
        }

        public E0 getAsRight() {
            throw new UnsupportedOperationException();
        }
    }

    public static class Failure<S0, E0 extends Exception> extends Try<S0, E0> {
        private Failure(E0 failure) {
            super(failure);
        }

        public boolean isSuccess() {
            return false;
        }

        public boolean isFailure() {
            return true;
        }

        @SuppressWarnings("unchecked")
        public E0 get() {
            return (E0) super.value;
        }

        public S0 getOrThrow() throws E0 {
            throw get();
        }

        public Success<S0, E0> getAsSuccess() {
            throw new UnsupportedOperationException();
        }

        public Failure<S0, E0> getAsFailure() {
            return this;
        }

        /**
         * @see Try#map(BiFunction)
         */
        public <T> T map(BiFunction<S0, E0, T> map) {
            return map.apply(null, get());
        }

        /**
         * @see Try#consume(BiConsumer)
         */
        public void consume(BiConsumer<S0, E0> con) {
            con.accept(null, get());
        }

        public S0 getAsLeft() {
            throw new UnsupportedOperationException();
        }

        public E0 getAsRight() {
            return this.get();
        }
    }
}
