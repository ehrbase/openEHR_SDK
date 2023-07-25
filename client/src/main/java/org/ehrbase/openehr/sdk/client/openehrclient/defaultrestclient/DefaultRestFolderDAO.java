/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.support.identification.ObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.client.openehrclient.FolderDAO;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Template;
import org.ehrbase.openehr.sdk.generator.commons.aql.condition.Condition;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.EhrFields;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.NativeSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.query.EntityQuery;
import org.ehrbase.openehr.sdk.generator.commons.aql.query.Query;
import org.ehrbase.openehr.sdk.generator.commons.aql.record.Record1;
import org.ehrbase.openehr.sdk.util.exception.ClientException;

public class DefaultRestFolderDAO implements FolderDAO {

    private final DefaultRestDirectoryEndpoint directoryEndpoint;
    private final String path;

    public DefaultRestFolderDAO(DefaultRestDirectoryEndpoint directoryEndpoint, String path) {
        this.directoryEndpoint = directoryEndpoint;
        this.path = path;
    }

    @Override
    public String getName() {
        directoryEndpoint.syncFromDb();
        return getRmFolder().getName().getValue();
    }

    @Override
    public void setName(String name) {
        directoryEndpoint.syncFromDb();
        getRmFolder().setName(new DvText(name));
        directoryEndpoint.saveToDb();
    }

    @Override
    public Set<String> listSubFolderNames() {
        directoryEndpoint.syncFromDb();
        return Optional.of(getRmFolder()).stream()
                .map(Folder::getFolders)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(Folder::getName)
                .map(DvText::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public Folder getRmFolder() {
        return directoryEndpoint.find(path);
    }

    @Override
    public FolderDAO getSubFolder(String path) {
        DefaultRestFolderDAO folderDAO = new DefaultRestFolderDAO(
                directoryEndpoint,
                Stream.of(this.path, path).filter(StringUtils::isNotBlank).collect(Collectors.joining("//")));
        folderDAO.sync();
        return folderDAO;
    }

    @Override
    public <T> T addCompositionEntity(T entity) {
        T updatedEntity = directoryEndpoint.getCompositionEndpoint().mergeCompositionEntity(entity);
        var versionId = DefaultRestCompositionEndpoint.extractVersionUid(updatedEntity)
                .orElseThrow(() -> new ClientException(String.format("No Id Element for %s", entity.getClass())));
        addToFolder(versionId);
        return updatedEntity;
    }

    @Override
    public ObjectVersionId addRaw(Composition composition) {
        ObjectVersionId versionId = directoryEndpoint.getCompositionEndpoint().mergeRaw(composition);
        addToFolder(versionId);
        return versionId;
    }

    @Override
    public <T> List<T> find(Class<T> clazz) {

        Containment compositionContainment = new Containment("COMPOSITION");

        EntityQuery<Record1<T>> query = Query.buildEntityQuery(
                compositionContainment, new NativeSelectAqlField<>(compositionContainment, "", clazz));

        query.where(Condition.equal(EhrFields.EHR_ID(), directoryEndpoint.getEhrId())
                .and(Condition.equal(
                        new NativeSelectAqlField<>(compositionContainment, "/template_id", String.class),
                        extractTemplateId(clazz)))
                .and(Condition.matches(
                        new NativeSelectAqlField<>(compositionContainment, "/uid/value", String.class),
                        getRmFolder().getItems().stream()
                                .map(ObjectRef::getId)
                                .map(Object::toString)
                                .toArray(String[]::new))));

        List<Record1<T>> execute =
                directoryEndpoint.getDefaultRestClient().aqlEndpoint().execute(query);

        return execute.stream().map(Record1::value1).collect(Collectors.toList());
    }

    /**
     * Returns the items stored in the current folder
     * @return The items of the current folder.
     */
    @Override
    public List<ObjectRef<? extends ObjectId>> getItems() {
        return getRmFolder().getItems();
    }

    void sync() {
        getRmFolder();
        directoryEndpoint.saveToDb();
    }

    private String extractTemplateId(Class clazz) {
        Template annotation = (Template) clazz.getAnnotation(Template.class);
        return annotation.value();
    }

    private void addToFolder(ObjectVersionId versionId) {
        addItemToFolder(versionId);
    }

    private void addItemToFolder(ObjectVersionId versionId) {
        Folder folder = getRmFolder();
        if (folder.getItems() == null) {
            folder.setItems(new ArrayList<>());
        }
        folder.getItems()
                .add(new ObjectRef<>(
                        new ObjectVersionId(versionId.getObjectId().getValue()),
                        versionId.getCreatingSystemId().getValue(),
                        "VERSIONED_COMPOSITION"));
        directoryEndpoint.saveToDb();
    }

    @Override
    public void addItemToRmFolder(ObjectVersionId versionId) {
        addItemToFolder(versionId);
    }
}
