/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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

import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Stefan Spiska
 */
public interface DirectoryCrudEndpoint {

    String FOLDER_DIVIDER = "/";

    ObjectVersionId createDirectory(Folder folder);

    ObjectVersionId updateDirectory(Folder folder);

    Optional<Folder> getDirectory();

    static Optional<Folder> find(Folder root, String path) {

        if (StringUtils.isBlank(path)) {
            return Optional.ofNullable(root);
        }
        String[] split = path.split(FOLDER_DIVIDER);
        Folder current = root;
        for (String folderName : split) {
            Optional<Folder> newFolder = Optional.of(current)
                    .map(Folder::getFolders)
                    .flatMap(l -> l.stream()
                            .filter(f -> folderName.equals(f.getName().getValue()))
                            .findAny());

            if (newFolder.isPresent()) {
                current = newFolder.get();
            } else {
                return Optional.empty();
            }
        }

        return Optional.ofNullable(current);
    }
}
