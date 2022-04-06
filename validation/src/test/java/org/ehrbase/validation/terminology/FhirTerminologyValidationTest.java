package org.ehrbase.validation.terminology;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.ehrbase.validation.terminology.FhirTerminologyValidation.ValueSetConverter;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.model.ValueSet.ValueSetExpansionComponent;
import org.hl7.fhir.r4.model.ValueSet.ValueSetExpansionContainsComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.nedap.archie.rm.datavalues.DvCodedText;

import ca.uhn.fhir.context.FhirContext;
import net.java.quickcheck.generator.PrimitiveGenerators;

class FhirTerminologyValidationTest {

  @Test
  void valueSetConverter() throws Exception {
    ValueSet values = anyValueSet();
    
    String json = FhirContext.forR4().newJsonParser().encodeResourceToString(values);
    DocumentContext ctx = JsonPath.parse(json);

    List<DvCodedText> dv = ValueSetConverter.convert(ctx);
    
    Assertions.assertEquals(values.getExpansion().getContains().size(), dv.size());
  }
  
  static ValueSet anyValueSet() {
    List<ValueSetExpansionContainsComponent> values = IntStream.range(0, 16)
      .mapToObj(i -> anyValueSetExpansionContainsComponent())
      .collect(Collectors.toList());
    
    ValueSetExpansionComponent ext = new ValueSetExpansionComponent();
      ext.setId(anyString());
      ext.setContains(values);
      
    ValueSet valueSet = new ValueSet();
      valueSet.setId(anyString());
      valueSet.setExpansion(ext);
      
    return valueSet;
  }
  
  static ValueSetExpansionContainsComponent anyValueSetExpansionContainsComponent() {
    ValueSetExpansionContainsComponent cmp = new ValueSetExpansionContainsComponent();
      cmp.setId(anyString());
      cmp.setCode(anyString());
      cmp.setSystem(anyString());
      cmp.setDisplay(anyString());
    return cmp;
  }
  
  static String anyString() {
    return PrimitiveGenerators.letterStrings(1, 16).next();
  }
}
