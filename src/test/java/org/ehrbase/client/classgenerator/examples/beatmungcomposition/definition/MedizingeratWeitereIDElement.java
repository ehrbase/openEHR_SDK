package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class MedizingeratWeitereIDElement {
  @Path("/value")
  private DvIdentifier value;

  public void setValue(DvIdentifier value) {
     this.value = value;
  }

  public DvIdentifier getValue() {
     return this.value ;
  }
}
