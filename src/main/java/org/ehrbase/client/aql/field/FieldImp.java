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

package org.ehrbase.client.aql.field;

import org.ehrbase.client.aql.containment.Containment;

public class FieldImp<T> implements SelectField<T> {

    private String name;
    private String path;
    private Class<T> clazz;
    private Class<?> entityClass;
    private Containment containment;

    public void setMultiValued(boolean multiValued) {
        this.multiValued = multiValued;
    }

    private boolean multiValued = false;

    protected FieldImp() {

    }

    protected FieldImp(Class<T> clazz) {
        this.clazz = clazz;
    }

    public FieldImp(Class<?> entityClass, String path, String name, Class<T> clazz) {
        this.name = name;
        this.path = path;
        this.clazz = clazz;
        this.entityClass = entityClass;
    }

    protected FieldImp(Class<?> entityClass, String path, String name, Class<T> clazz, boolean multiValued) {
        this.name = name;
        this.path = path;
        this.clazz = clazz;
        this.entityClass = entityClass;
        this.multiValued = multiValued;
    }


    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Class<T> getClazz() {
        return clazz;
    }

    @Override
    public boolean isMultiValued() {
        return multiValued;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }


    protected void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public void setContainment(Containment containment) {
        this.containment = containment;
    }

    @Override
    public String buildAQL() {
        return containment.getVariableName() + path.replace("|", "/");
    }

}
