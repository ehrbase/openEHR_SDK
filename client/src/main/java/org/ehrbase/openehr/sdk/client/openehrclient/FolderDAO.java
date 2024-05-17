/*
 * Copyright (c) 2019 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.client.openehrclient;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.util.List;
import java.util.Set;

public interface FolderDAO {

    String getName();

    void setName(String name);

    Set<String> listSubFolderNames();

    FolderDAO getSubFolder(String path);

    <T> T addCompositionEntity(T entity);

    ObjectVersionId addRaw(Composition composition);

    <T> List<T> find(Class<T> clazz);

    List<ObjectRef<? extends ObjectId>> getItems();

    Folder getRmFolder();

    void addItemToRmFolder(ObjectVersionId versionId);
}
