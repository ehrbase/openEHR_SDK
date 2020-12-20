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

package org.ehrbase.serialisation.flatencoding;

import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;

/**
 * Used to build the {@link org.ehrbase.serialisation.RMDataFormat} for flat jason for a template.
 */
public class FlatJasonProvider {

  private final TemplateProvider templateProvider;

  public FlatJasonProvider(TemplateProvider templateProvider) {
    this.templateProvider = templateProvider;
  }

  /**
   * Builds a {@link FlatJson} for the template with {@code templateId} and {@link FlatFormat}
   * {@code format}
   *
   * @param format
   * @param templateId
   * @return
   */
  public FlatJson buildFlatJson(FlatFormat format, String templateId) {

    switch (format) {
      case SIM_SDT:
        return new FlatJson(this, templateId);
      default:
        throw new SdkException(String.format("Format %s not supported", format));
    }
  }

  TemplateProvider getTemplateProvider() {
    return templateProvider;
  }
}
