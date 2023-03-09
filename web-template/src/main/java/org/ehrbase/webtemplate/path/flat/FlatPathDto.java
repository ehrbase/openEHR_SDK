/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.webtemplate.path.flat;

import java.util.AbstractMap;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.UnaryOperator;
import org.apache.commons.lang3.ObjectUtils;

public final class FlatPathDto {

    private final String name;
    private final FlatPathDto child;
    private final String attributeName;
    private final Integer count;

    public FlatPathDto(CharSequence path) {
        this(FlatPathParser.parse(path));
    }

    private FlatPathDto(FlatPathDto flatPathDto) {
        this.name = flatPathDto.getName();
        this.child = flatPathDto.getChild();
        this.attributeName = flatPathDto.getAttributeName();
        this.count = flatPathDto.getCount();
    }

    public FlatPathDto(String name, FlatPathDto child, Integer count, String attributeName) {
        this.name = name;
        this.child = child;
        this.count = count;
        this.attributeName = attributeName;
    }

    public String getName() {
        return name;
    }

    public FlatPathDto getChild() {
        return child;
    }

    /**
     * Clones this node and appends the child
     *
     * @param child
     * @return
     */
    public FlatPathDto nodeWithChild(FlatPathDto child) {
        return new FlatPathDto(name, child, count, attributeName);
    }

    /**
     * Appends the child to a clone of this path
     * @param child
     * @return
     */
    public FlatPathDto pathWithChild(FlatPathDto child) {
        return replaceEnd(this, n -> n.nodeWithChild(child));
    }

    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Clones this node and sets the attributeName property
     *
     * @param attributeName
     * @return
     */
    public FlatPathDto nodeWithAttributeName(String attributeName) {
        if (child == null) {
            throw new IllegalStateException("attributeName can only be set for leaf nodes");
        }
        return new FlatPathDto(name, child, count, attributeName);
    }

    /**
     * Clones the whole path and sets the attributeName property of the trailing node
     * @param attributeName
     * @return
     */
    public FlatPathDto pathWithAttributeName(String attributeName) {
        return replaceEnd(this, n -> n.nodeWithAttributeName(attributeName));
    }

    public Integer getCount() {
        return count;
    }

    /**
     * Clones this node and sets the count property
     *
     * @param count
     * @return
     */
    public FlatPathDto nodeWithCount(Integer count) {
        return new FlatPathDto(name, child, count, attributeName);
    }

    /**
     * Clones the whole path and sets the count property of the trailing node
     * @param count
     * @return
     */
    public FlatPathDto pathWithCount(Integer count) {
        return replaceEnd(this, n -> n.nodeWithCount(count));
    }

    public String format() {
        StringBuilder sb = new StringBuilder();
        appendFormat(sb);
        return sb.toString();
    }

    private void appendFormat(StringBuilder sb) {

        sb.append(name);

        if (count != null) {
            sb.append(':').append(count);
        }

        if (attributeName != null) {
            sb.append('|').append(attributeName);
        }
        if (child != null) {
            sb.append('/');
            child.appendFormat(sb);
        }
    }

    public FlatPathDto getLast() {
        FlatPathDto path = this;
        while (path.getChild() != null) {
            path = path.getChild();
        }
        return path;
    }

    public static FlatPathDto removeEnd(FlatPathDto path, FlatPathDto remove) {

        Deque<FlatPathDto> nodes = new LinkedList<>();
        for (FlatPathDto n = path; n != null; n = n.getChild()) {
            nodes.add(n);
        }

        Deque removeNodes = new LinkedList();
        for (FlatPathDto n = remove; n != null; n = n.getChild()) {
            removeNodes.add(n);
        }

        if (nodes.size() < removeNodes.size()) {
            return null;
        }

        // check + skip tail
        Iterator<FlatPathDto> nIt = nodes.descendingIterator();
        Iterator<FlatPathDto> rIt = removeNodes.descendingIterator();
        while (rIt.hasNext()) {
            FlatPathDto nNode = nIt.next();
            FlatPathDto rNode = rIt.next();
            if (!isNodeEqual(nNode, rNode)) {
                return null;
            }
        }

        // construct result
        FlatPathDto newPath = null;
        while (nIt.hasNext()) {
            FlatPathDto nNode = nIt.next();
            newPath = new FlatPathDto(nNode.name, newPath, nNode.count, nNode.attributeName);
        }
        return newPath;
    }

    public static FlatPathDto replaceEnd(FlatPathDto path, UnaryOperator<FlatPathDto> replacement) {

        if (path == null) {
            return null;
        }

        Deque<FlatPathDto> nodes = new LinkedList<>();
        for (FlatPathDto n = path; n != null; n = n.getChild()) {
            nodes.add(n);
        }

        Iterator<FlatPathDto> nIt = nodes.descendingIterator();

        FlatPathDto node = replacement.apply(nIt.next());

        while (nIt.hasNext()) {
            node = nIt.next().nodeWithChild(node);
        }
        return node;
    }

    public static boolean isNodeEqual(FlatPathDto me, FlatPathDto other) {
        return isNodeEqual(me, other, false, false);
    }

    public static boolean isNodeEqual(FlatPathDto me, FlatPathDto other, boolean ignoreCount, boolean ignoreAttribute) {

        if (!Objects.equals(me.getName(), other.getName())) {
            return false;
        }

        if (!ignoreCount
                && !Objects.equals(me.getCount(), other.getCount())
                && !(me.getCount() == null && Objects.equals(other.getCount(), 0))
                && !(Objects.equals(me.getCount(), 0) && other.getCount() == null)) {
            return false;
        }

        if (!ignoreAttribute && !Objects.equals(me.getAttributeName(), other.getAttributeName())) {
            return false;
        }
        return true;
    }

    public static FlatPathDto removeStart(FlatPathDto path, FlatPathDto remove) {
        FlatPathDto other = remove;
        FlatPathDto me = path;
        do {

            if (!isNodeEqual(me, other)) break;
            other = other.getChild();
            me = me.child;
        } while (other != null && me != null);

        if (other == null) {
            return me;
        } else {
            return path;
        }
    }

    public static FlatPathDto addEnd(FlatPathDto path, FlatPathDto add) {
        if (add == null) {
            return path;
        }
        Deque nodes = new LinkedList();
        for (FlatPathDto n = path; n != null; n = n.getChild()) {
            nodes.add(n);
        }
        FlatPathDto child = add;
        Iterator<FlatPathDto> it = nodes.descendingIterator();
        while (it.hasNext()) {
            child = it.next().nodeWithChild(child);
        }
        return child;
    }

    @Override
    public String toString() {
        return format();
    }

    public boolean startsWith(CharSequence otherPath) {
        return startsWith(FlatPathParser.parse(otherPath));
    }

    public boolean startsWith(FlatPathDto other) {
        FlatPathDto me = this;
        do {
            boolean ignoreCount = other.getChild() == null && other.count == null;
            boolean ignoreAttribute = other.getAttributeName() == null;

            boolean nodeEqual = isNodeEqual(me, other, ignoreCount, ignoreAttribute);
            if (!nodeEqual) break;

            other = other.getChild();
            me = me.child;

        } while (other != null && me != null);

        return other == null;
    }

    /**
     *
     * For {@code count} 0 and null are considered equal.
     *
     * @param otherPath
     * @return
     */
    public boolean isEqualTo(CharSequence otherPath) {
        return isEqualTo(this, FlatPathParser.parse(otherPath), false, false);
    }

    public boolean isEqualTo(FlatPathDto otherPath) {
        return isEqualTo(this, otherPath, false, false);
    }

    public boolean isEqualTo(FlatPathDto otherPath, boolean ignoreCount, boolean ignoreAttribute) {
        return isEqualTo(this, otherPath, ignoreCount, ignoreAttribute);
    }

    public static boolean isEqualTo(FlatPathDto me, FlatPathDto other, boolean ignoreCount, boolean ignoreAttribute) {
        if (me == other) {
            return true;
        }
        if (ObjectUtils.anyNull(me, other)) {
            return false;
        }
        if (!Objects.equals(me.getName(), other.getName())) {
            return false;
        }
        if (!ignoreAttribute && !Objects.equals(me.getAttributeName(), other.getAttributeName())) {
            return false;
        }

        if (!ignoreCount
                && Optional.ofNullable(me.getCount()).orElse(0).intValue()
                        != Optional.ofNullable(other.getCount()).orElse(0).intValue()) {
            return false;
        }

        return isEqualTo(me.getChild(), other.getChild(), ignoreCount, ignoreAttribute);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatPathDto that = (FlatPathDto) o;
        return Objects.equals(name, that.name)
                && Objects.equals(child, that.child)
                && Objects.equals(attributeName, that.attributeName)
                && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, child, attributeName, count);
    }

    public static <T> Map.Entry<FlatPathDto, T> get(Map<FlatPathDto, T> map, String otherPath) {
        FlatPathDto other = FlatPathParser.parse(otherPath);

        return map.entrySet().stream()
                .filter(d -> d.getKey().isEqualTo(other, false, false))
                .findAny()
                .orElseGet(() -> new AbstractMap.SimpleEntry<>(null, null));
    }
}
