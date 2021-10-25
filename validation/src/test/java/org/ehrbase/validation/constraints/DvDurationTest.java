/*
 * Copyright (c) 2019 Vitasystems GmbH and Hannover Medical School.
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

package org.ehrbase.validation.constraints;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.validation.constraints.wrappers.CArchetypeConstraint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openehr.schemas.v1.ARCHETYPECONSTRAINT;
import org.openehr.schemas.v1.CCOMPLEXOBJECT;

class DvDurationTest extends ConstraintTestBase {

  @Test
  void testConstraintValidation() throws XmlException, IOException {
    ARCHETYPECONSTRAINT archetypeconstraint = CCOMPLEXOBJECT.Factory.parse(
        new FileInputStream("./src/test/resources/constraints/dvduration.xml"));
    CArchetypeConstraint constraint = new CArchetypeConstraint(null, null);

    DvDuration duration = new DvDuration("P50D");
    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", duration, archetypeconstraint));
  }

  @Test
  void testConstraintValidationIncluded() throws XmlException, IOException {
    setUpContext("./src/test/resources/constraints/dvduration_range.xml");

    CArchetypeConstraint constraint = new CArchetypeConstraint(null, null);

    DvDuration d1 = new DvDuration("PT11H11M");
    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", d1, archetypeconstraint));

    DvDuration d2 = new DvDuration("P0D");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> constraint.validate("test", d2, archetypeconstraint));

    DvDuration d3 = new DvDuration("P50D");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> constraint.validate("test", d3, archetypeconstraint));
  }

  @Test
  void testConstraintValidationExcluded() throws XmlException, IOException {
    setUpContext("./src/test/resources/constraints/dvduration_excluded.xml");

    CArchetypeConstraint constraint = new CArchetypeConstraint(null, null);

    DvDuration d1 = new DvDuration("PT11H11M");
    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", d1, archetypeconstraint));

    DvDuration d2 = new DvDuration("P30D");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> constraint.validate("test", d2, archetypeconstraint));

    DvDuration d3 = new DvDuration("PT1S");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> constraint.validate("test", d3, archetypeconstraint));
  }

  @Test
  void testConstraintValidationLower() throws XmlException, IOException {
    setUpContext("./src/test/resources/constraints/dvduration_lower.xml");

    CArchetypeConstraint constraint = new CArchetypeConstraint(null, null);

    DvDuration d1 = new DvDuration("PT11H11M");
    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", d1, archetypeconstraint));

    DvDuration d2 = new DvDuration("P0D");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> constraint.validate("test", d2, archetypeconstraint));
  }

  @Test
  void testConstraintValidationUppder() throws XmlException, IOException {
    setUpContext("./src/test/resources/constraints/dvduration_upper.xml");

    CArchetypeConstraint constraint = new CArchetypeConstraint(null, null);

    DvDuration d1 = new DvDuration("P0D");
    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", d1, archetypeconstraint));

    DvDuration d2 = new DvDuration("P50D");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> constraint.validate("test", d2, archetypeconstraint));
  }
}
