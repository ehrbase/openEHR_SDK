/*
 * Copyright (c) 2022. vitasystems GmbH and Hannover Medical School.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.openehr.sdk.examplegenerator;

import com.nedap.archie.rm.RMObject;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.model.WebTemplateNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ContextUtil {

    private ContextUtil() {
        //NOOP
    }

    public static WebTemplateNode peekNode(Context<?> context) {
        return context.getNodeDeque().peek();
    }

    public static WebTemplateNode peekNode(Context<?> context, int index) {
        return peek(context.getNodeDeque(), index);
    }

    public static <T> T peekObject(Context<T> context) {
        return context.getObjectDeque().peek();
    }

    public static <T> T peekObject(Context<T> context, int index) {
        return peek(context.getObjectDeque(), index);
    }
    public static RMObject peekRmObject(Context<?> context) {
        return context.getRmObjectDeque().peek();
    }

    public static RMObject peekRmObject(Context<?> context, int index) {
        return peek(context.getRmObjectDeque(), index);
    }

    private static <U> U peek(Deque<U> deque, int index) {
        if (index == 0) {
            return deque.peek();
        }

        int size = deque.size();
        int i = size - index;
        if (size <= index) {
            return null;
        }
        if (i + i == size) {
            return deque.peekLast();
        }
        var queue = new ArrayDeque<>(deque);

        return queue.stream().skip(index).findFirst().orElse(null);
    }
}
