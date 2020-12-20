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

import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestEhrEndpoint.EHR_PATH;

import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.directory.Folder;
import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.FolderDAO;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.response.openehr.DirectoryResponseData;

public class DefaultRestDirectoryEndpoint {

  static final String FOLDER_DIVIDER = "/";
  private static final String DIRECTORY_PATH = "/directory/";
  private final DefaultRestClient defaultRestClient;
  private final UUID ehrId;
  private VersionUid rootVersion;
  private Folder root;

  DefaultRestDirectoryEndpoint(DefaultRestClient defaultRestClient, UUID ehrId) {
    this.defaultRestClient = defaultRestClient;
    this.ehrId = ehrId;
    syncFromDb();
  }

  synchronized void syncFromDb() {
    if (root == null) {
      createRoot();
    }
    Optional<DirectoryResponseData> directoryResponseData =
        defaultRestClient.httpGet(resolve(""), DirectoryResponseData.class);
    copyToFolder(root, directoryResponseData.orElseThrow());
  }

  synchronized void saveToDb() {
    rootVersion = defaultRestClient.httpPut(resolve(""), root, rootVersion);
    syncFromDb();
  }

  synchronized Folder find(String path) {
    if (StringUtils.isBlank(path)) {
      return root;
    }
    String[] split = path.split(FOLDER_DIVIDER);
    Folder current = root;
    for (String folderName : split) {
      Folder newFolder =
          Optional.ofNullable(current)
              .map(Folder::getFolders)
              .flatMap(
                  l -> l.stream().filter(f -> folderName.equals(f.getName().getValue())).findAny())
              .orElse(null);
      if (newFolder == null) {
        newFolder = new Folder();
        newFolder.setArchetypeNodeId("openEHR-EHR-FOLDER.generic.v1");
        newFolder.setName(new DvText(folderName));
        if (current.getFolders() == null) {
          current.setFolders(new ArrayList<>());
        }
        current.addFolder(newFolder);
      }
      current = newFolder;
    }

    return current;
  }

  private void copyToFolder(Folder folder, DirectoryResponseData responseData) {
    folder.setUid(responseData.getUid());
    folder.setName(responseData.getName());
    folder.setDetails(responseData.getDetails());
    folder.setFolders(responseData.getFolders());
    folder.setItems(responseData.getItems());
  }

  private void createRoot() {
    root = new Folder();
    root.setName(new DvText("root"));
    root.setArchetypeNodeId("openEHR-EHR-FOLDER.generic.v1");
    VersionUid versionUid = defaultRestClient.httpPost(resolve(""), root);
    rootVersion = versionUid;
  }

  private URI resolve(String subPath) {
    if (StringUtils.isBlank(subPath)) {
      return defaultRestClient
          .getConfig()
          .getBaseUri()
          .resolve(EHR_PATH + ehrId.toString() + DIRECTORY_PATH);
    } else {
      return defaultRestClient
          .getConfig()
          .getBaseUri()
          .resolve(EHR_PATH + ehrId.toString() + DIRECTORY_PATH + subPath);
    }
  }

  public FolderDAO getFolder(String path) {
    DefaultRestFolderDAO folderDAO = new DefaultRestFolderDAO(this, path);
    folderDAO.sync();
    return folderDAO;
  }

  CompositionEndpoint getCompositionEndpoint() {
    return defaultRestClient.compositionEndpoint(ehrId);
  }

  DefaultRestClient getDefaultRestClient() {
    return defaultRestClient;
  }

  UUID getEhrId() {
    return ehrId;
  }
}
