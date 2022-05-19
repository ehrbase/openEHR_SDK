package org.ehrbase.validation.webtemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvState;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.junit.jupiter.api.Test;

/**
 *
 */
class DvStateValidatorTest extends AbstractRMObjectValidatorTest {

  private final DvStateValidator validator = new DvStateValidator();

  @Test
  void testValidate() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_state.json");

    var dvState = new DvState();
    dvState.setValue(new DvCodedText("Test", new CodePhrase(new TerminologyId("local"), "at0010")));

    var result = validator.validate(dvState, node);
    assertTrue(result.isEmpty());
  }

  @Test
  void testValidate_List() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_state_codedtext.json");

    var dvState = new DvState();
    dvState.setValue(new DvCodedText("First", new CodePhrase(new TerminologyId("local"), "at0028")));
    var result = validator.validate(dvState, node);
    assertTrue(result.isEmpty());

    dvState.setValue(new DvCodedText("Test", new CodePhrase(new TerminologyId("local"), "at0010")));
    result = validator.validate(dvState, node);
    assertEquals(1, result.size());

  }
}
