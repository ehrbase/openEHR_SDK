package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datatypes.CodePhrase;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ServiceRequestReasonForRequestElement {
  @Path("/value|defining_code")
  private CodePhrase value;

  public void setValue(CodePhrase value) {
     this.value = value;
  }

  public CodePhrase getValue() {
     return this.value ;
  }
}
