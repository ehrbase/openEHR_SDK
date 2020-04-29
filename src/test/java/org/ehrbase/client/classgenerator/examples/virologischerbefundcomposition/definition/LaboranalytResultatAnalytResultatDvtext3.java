package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class LaboranalytResultatAnalytResultatDvtext3 implements LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase40f33492 {
  @Path("|value")
  private String analytResultatValue;

  public void setAnalytResultatValue(String analytResultatValue) {
     this.analytResultatValue = analytResultatValue;
  }

  public String getAnalytResultatValue() {
     return this.analytResultatValue ;
  }
}
