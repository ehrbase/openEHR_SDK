package org.ehrbase.validation.terminology;

import java.util.ArrayList;
import java.util.IllegalFormatException;
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
  public void guaranteePrefix() {
    Assertions.assertEquals("url=ABC", FhirTerminologyValidation.guaranteePrefix("url=", "url=ABC"));
    Assertions.assertEquals("url=ABC", FhirTerminologyValidation.guaranteePrefix("url=", "ABC"));
    Assertions.assertEquals(null, FhirTerminologyValidation.guaranteePrefix("url=", ""));
    Assertions.assertEquals("xyz=XYZ&url=ABC", FhirTerminologyValidation.guaranteePrefix("url=", "xyz=XYZ&url=ABC"));
  }
  
  @Test
  public void renderTempl() {
    String ref = String.format(FhirTerminologyValidation.SUPPORTS_CODE_SYS_TEMPL, "abc", "123");
    String render1 = FhirTerminologyValidation.renderTempl(FhirTerminologyValidation.SUPPORTS_CODE_SYS_TEMPL, "abc", "123");
    Assertions.assertEquals(ref, render1);
    
    String render2 = FhirTerminologyValidation.renderTempl(FhirTerminologyValidation.SUPPORTS_CODE_SYS_TEMPL, "abc", "123", "xyz");
    Assertions.assertEquals(ref, render2);
    Assertions.assertThrows(IllegalFormatException.class, () -> FhirTerminologyValidation.renderTempl(FhirTerminologyValidation.SUPPORTS_CODE_SYS_TEMPL, "abc"));
  }
  
  @Test
  void valueSetConverter() throws Exception {
    ValueSet values = anyValueSet();
    
    String json = FhirContext.forR4().newJsonParser().encodeResourceToString(values);
    DocumentContext ctx = JsonPath.parse(json);

    List<DvCodedText> dv = ValueSetConverter.convert(ctx);
    
    Assertions.assertEquals(values.getExpansion().getContains().size(), dv.size());
  }
  
  @Test
  void stringjoin() {
    List<String> params = new ArrayList<>();
    String reqParam = params.stream().collect(Collectors.joining("&"));
    Assertions.assertTrue("".equals(reqParam));
    
    params = List.of("a");
    reqParam = params.stream().collect(Collectors.joining("&"));
    Assertions.assertTrue("a".equals(reqParam));
    
    params = List.of("a", "b");
    reqParam = params.stream().collect(Collectors.joining("&"));
    Assertions.assertTrue("a&b".equals(reqParam));
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
