package org.ehrbase.validation.terminology;

import static org.junit.Assert.assertEquals;

import com.nedap.archie.rm.datatypes.CodePhrase;
import org.junit.Test;

public class ClassBindingTest {

  @Test
  public void bindingTest() {
    assertEquals(
        CodePhrase.class, new org.ehrbase.validation.terminology.validator.CodePhrase().rmClass());
  }
}
