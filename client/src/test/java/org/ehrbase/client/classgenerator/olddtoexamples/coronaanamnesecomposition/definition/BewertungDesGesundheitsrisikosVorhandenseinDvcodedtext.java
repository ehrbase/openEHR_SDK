package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class BewertungDesGesundheitsrisikosVorhandenseinDvcodedtext
    implements BewertungDesGesundheitsrisikosVorhandenseinChoice {
  @Path("|defining_code")
  private VorhandenseinDefiningcode vorhandenseinDefiningcode;

  public void setVorhandenseinDefiningcode(VorhandenseinDefiningcode vorhandenseinDefiningcode) {
    this.vorhandenseinDefiningcode = vorhandenseinDefiningcode;
  }

  public VorhandenseinDefiningcode getVorhandenseinDefiningcode() {
    return this.vorhandenseinDefiningcode;
  }
}
