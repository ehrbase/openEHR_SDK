package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ServiceRequestReasonForRequestElement {
  @Path("/value")
  private DvCodedText value;

  public void setValue(DvCodedText value) {
     this.value = value;
  }

  public DvCodedText getValue() {
     return this.value ;
  }
}
