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
