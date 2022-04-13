/*
 * Copyright (c) 2021 Vitasystems GmbH.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.validation.terminology;

import java.util.List;

import org.ehrbase.functional.Try;
import org.ehrbase.validation.ConstraintViolationException;

import com.nedap.archie.rm.datavalues.DvCodedText;

/**
 * This interface provides support for external terminology validation.
 */
public interface ExternalTerminologyValidation {
  boolean supports(TerminologyParam param);
  Try<Boolean,ConstraintViolationException> validate(TerminologyParam param);
  List<DvCodedText> expand(TerminologyParam param);
}
