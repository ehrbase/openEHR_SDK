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

    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", new DvDuration("PT10H10M"), archetypeconstraint));
    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", new DvDuration("P1Y"), archetypeconstraint));
  }

  @Test
  void testConstraintValidationIncluded() throws XmlException, IOException {
    setUpContext("./src/test/resources/constraints/dvduration_range.xml");

    CArchetypeConstraint constraint = new CArchetypeConstraint(null, null);

    DvDuration d1 = new DvDuration("PT11H11M");
    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", d1, archetypeconstraint));

    DvDuration d2 = new DvDuration("PT0S");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> constraint.validate("test", d2, archetypeconstraint));

    DvDuration d3 = new DvDuration("PT36H");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> constraint.validate("test", d3, archetypeconstraint));
  }

  @Test
  void testConstraintValidationRange() throws XmlException, IOException {
    setUpContext("./src/test/resources/constraints/dvduration_range2.xml");

    CArchetypeConstraint constraint = new CArchetypeConstraint(null, null);

    DvDuration d1 = new DvDuration("P67Y");
    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", d1, archetypeconstraint));
  }

  @Test
  void testConstraintValidationExcluded() throws XmlException, IOException {
    setUpContext("./src/test/resources/constraints/dvduration_excluded.xml");

    CArchetypeConstraint constraint = new CArchetypeConstraint(null, null);

    DvDuration d1 = new DvDuration("PT11H11M");
    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", d1, archetypeconstraint));

    DvDuration d2 = new DvDuration("PT0H");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> constraint.validate("test", d2, archetypeconstraint));

    DvDuration d3 = new DvDuration("PT24H");
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

    DvDuration d2 = new DvDuration("PT0S");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> constraint.validate("test", d2, archetypeconstraint));

    DvDuration d3 = new DvDuration("PT1S");
    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", d1, archetypeconstraint));
  }

  @Test
  void testConstraintValidationUpper() throws XmlException, IOException {
    setUpContext("./src/test/resources/constraints/dvduration_upper.xml");

    CArchetypeConstraint constraint = new CArchetypeConstraint(null, null);

    DvDuration d1 = new DvDuration("PT24H");
    Assertions.assertDoesNotThrow(
        () -> constraint.validate("test", d1, archetypeconstraint));

    DvDuration d2 = new DvDuration("PT744H");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> constraint.validate("test", d2, archetypeconstraint));
  }
}
