package org.ehrbase.validation.terminology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TerminologyParamTest {

  @Test
  void createTerminologyParam0() {
    TerminologyParam tp = TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem/$expand?url=http://hl7.org/fhir/observation-status");
    
    Assertions.assertEquals("//fhir.hl7.org", tp.getServiceApi().get());
    Assertions.assertTrue(tp.isUseCodeSystem());
    Assertions.assertEquals("$expand", tp.getOperation().get());
    Assertions.assertEquals("url=http://hl7.org/fhir/observation-status", tp.getParameter().get());
  }
  
  @Test
  void createTerminologyParam1() {
    TerminologyParam tp = TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem?url=http://hl7.org/fhir/observation-status");
    
    Assertions.assertEquals("//fhir.hl7.org", tp.getServiceApi().get());
    Assertions.assertTrue(tp.isUseCodeSystem());
    Assertions.assertTrue(tp.getOperation().isEmpty());
    Assertions.assertEquals("url=http://hl7.org/fhir/observation-status", tp.getParameter().get());
  }
  
  @Test
  void createTerminologyParam2() {
    TerminologyParam tp = TerminologyParam.ofFhir("//fhir.hl7.org/CodeSystem");
    
    Assertions.assertEquals("//fhir.hl7.org", tp.getServiceApi().get());
    Assertions.assertTrue(tp.isUseCodeSystem());
    Assertions.assertTrue(tp.getOperation().isEmpty());
    Assertions.assertTrue(tp.getParameter().isEmpty());
  }
}
