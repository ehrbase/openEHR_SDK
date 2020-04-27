package laborbefund.laborbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class LaboranalytResultatAnalytResultatDvtext2 implements LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase1df98368 {
  @Path("|value")
  private String analytResultatValue;

  public void setAnalytResultatValue(String analytResultatValue) {
     this.analytResultatValue = analytResultatValue;
  }

  public String getAnalytResultatValue() {
     return this.analytResultatValue ;
  }
}
