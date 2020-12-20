/*
 *
 *  *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.client.openehrclient.defaultrestclient;

import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.support.identification.ObjectRef;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.aql.condition.Condition;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.EhrFields;
import org.ehrbase.client.aql.field.NativeSelectAqlField;
import org.ehrbase.client.aql.query.EntityQuery;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.openehrclient.FolderDAO;

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
    return getFolder().getName().getValue();
  }

  @Override
  public void setName(String name) {
    directoryEndpoint.syncFromDb();
    getFolder().setName(new DvText(name));
    directoryEndpoint.saveToDb();
  }

  @Override
  public Set<String> listSubFolderNames() {
    directoryEndpoint.syncFromDb();
    return Optional.of(getFolder()).stream()
        .map(Folder::getFolders)
        .filter(Objects::nonNull)
        .flatMap(List::stream)
        .map(Folder::getName)
        .map(DvText::getValue)
        .collect(Collectors.toSet());
  }

  private Folder getFolder() {
    return directoryEndpoint.find(path);
  }

  @Override
  public FolderDAO getSubFolder(String path) {
    DefaultRestFolderDAO folderDAO =
        new DefaultRestFolderDAO(
            directoryEndpoint,
            Stream.of(this.path, path)
                .filter(s -> StringUtils.isNotBlank(s))
                .collect(Collectors.joining("//")));
    folderDAO.sync();
    return folderDAO;
  }

  @Override
  public <T> T addCompositionEntity(T entity) {
    T updatedEntity = directoryEndpoint.getCompositionEndpoint().mergeCompositionEntity(entity);
    UUID uuid =
        DefaultRestCompositionEndpoint.extractVersionUid(updatedEntity)
            .orElseThrow(
                () -> new ClientException(String.format("No Id Element for %s", entity.getClass())))
            .getUuid();
    Folder folder = getFolder();
    if (folder.getItems() == null) {
      folder.setItems(new ArrayList<>());
    }
    folder
        .getItems()
        .add(
            new ObjectRef(
                new ObjectVersionId(uuid.toString()), "dffddfd", "VERSIONED_COMPOSITION"));
    directoryEndpoint.saveToDb();
    return updatedEntity;
  }

  @Override
  public <T> List<T> find(Class<T> clazz) {

    Containment compositionContainment = new Containment("COMPOSITION");

    EntityQuery<Record1<T>> query =
        Query.buildEntityQuery(
            compositionContainment, new NativeSelectAqlField<>(compositionContainment, "", clazz));

    query.where(
        Condition.equal(EhrFields.EHR_ID(), directoryEndpoint.getEhrId())
            .and(
                Condition.equal(
                    new NativeSelectAqlField<>(
                        compositionContainment, "/template_id", String.class),
                    extractTemplateId(clazz)))
            .and(
                Condition.matches(
                    new NativeSelectAqlField<>(compositionContainment, "/uid/value", String.class),
                    getFolder().getItems().stream()
                        .map(ObjectRef::getId)
                        .map(Object::toString)
                        .toArray(String[]::new))));

    List<Record1<T>> execute =
        directoryEndpoint.getDefaultRestClient().aqlEndpoint().execute(query);

    return execute.stream().map(Record1::value1).collect(Collectors.toList());
  }

  void sync() {
    getFolder();
    directoryEndpoint.saveToDb();
  }

  private String extractTemplateId(Class clazz) {
    Template annotation = (Template) clazz.getAnnotation(Template.class);
    return annotation.value();
  }
}
