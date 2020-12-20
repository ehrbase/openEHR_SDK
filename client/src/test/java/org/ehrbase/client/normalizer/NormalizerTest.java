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

package org.ehrbase.client.normalizer;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.building.OptSkeletonBuilder;
import org.ehrbase.normalizer.Normalizer;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

public class NormalizerTest {

  @Test
  public void normalize() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.ALL_TYPES.getStream())
            .getTemplate();
    OptSkeletonBuilder skeletonBuilder = new OptSkeletonBuilder();
    RMObject rmObject = skeletonBuilder.generate(template);

    Normalizer cut = new Normalizer();
    Composition actual = (Composition) cut.normalize(rmObject);
    assertThat(actual).isNotNull();
    assertThat(actual.getContent()).size().isEqualTo(0);
  }
}
