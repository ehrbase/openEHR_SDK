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
        return directoryEndpoint.find(path).getName().getValue();
    }

    @Override
    public void setName(String name) {
        directoryEndpoint.syncFromDb();
        directoryEndpoint.find(path).setName(new DvText(name));
        directoryEndpoint.saveToDb();
    }

    @Override
    public FolderDAO getSubFolder(String path) {
        return new DefaultRestFolderDAO(directoryEndpoint, this.path + "\\" + path);
    }

}
