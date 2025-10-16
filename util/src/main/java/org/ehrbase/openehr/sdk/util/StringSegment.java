/*
 * Copyright (c) 2025 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.util;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

public final class StringSegment implements CharSequence {

    private final String str;
    private final int start;
    private final int end;

    private StringSegment(String str, int start, int end) {
        if (start < 0 || end < 0 || start > end || end > str.length()) {
            throw new StringIndexOutOfBoundsException();
        }
        this.str = str;
        this.start = start;
        this.end = end;
    }

    public static CharSequence subSequence(String str, int start, int end) {
        return new StringSegment(str, start, end);
    }

    public static CharSequence wrap(String str) {
        return new StringSegment(str, 0, str.length());
    }

    @Override
    public int length() {
        return end - start;
    }

    @Override
    public char charAt(int index) {
        return str.charAt(start + index);
    }

    @Override
    public StringSegment subSequence(int start, int end) {
        int newStart = this.start + start;
        int newEnd = this.start + end;
        return new StringSegment(str, newStart, newEnd);
    }

    @Override
    public int hashCode() {
        int h = 1;
        for (int i = start; i < end; i++) {
            h = 31 * h + (int) str.charAt(i);
        }
        return h;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StringSegment seg) || length() != seg.length()) {
            return false;
        }
        if (start == seg.start && str == seg.str) {
            return true;
        }
        for (int i = 0; i < length(); i++) {
            if (charAt(i) != seg.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return str.substring(start, end);
    }

    public Reader reader() {
        return new StringSegmentReader();
    }

    final class StringSegmentReader extends Reader {

        private int next;
        private int mark;
        boolean closed = false;

        public StringSegmentReader() {
            this.next = start;
            this.mark = start;
        }

        private void ensureOpen() throws IOException {
            if (closed) throw new IOException("Stream closed");
        }

        @Override
        public int read() throws IOException {
            ensureOpen();
            if (next >= end) return -1;
            return str.charAt(next++);
        }

        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            ensureOpen();
            Objects.checkFromIndexSize(off, len, cbuf.length);
            if (len == 0) {
                return 0;
            }
            if (next >= end) return -1;
            int n = Math.min(end - next, len);
            str.getChars(next, next + n, cbuf, off);
            next += n;
            return n;
        }

        @Override
        public long skip(long n) throws IOException {
            ensureOpen();
            if (next >= end) return 0;
            // Bound skip by beginning and end of the source
            long r = Math.min((long) end - next, n);
            r = Math.max(-next, r);
            next += (int) r;
            return r;
        }

        @Override
        public boolean ready() throws IOException {
            ensureOpen();
            return true;
        }

        @Override
        public boolean markSupported() {
            return true;
        }

        @Override
        public void mark(int readAheadLimit) throws IOException {
            if (readAheadLimit < 0) {
                throw new IllegalArgumentException("Read-ahead limit < 0");
            }
            ensureOpen();
            mark = next;
        }

        @Override
        public void reset() throws IOException {
            ensureOpen();
            next = mark;
        }

        @Override
        public void close() {
            closed = true;
        }
    }
}
