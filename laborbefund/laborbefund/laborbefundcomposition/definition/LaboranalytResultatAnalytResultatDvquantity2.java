package laborbefund.laborbefundcomposition.definition;

import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_QUANTITY")
public class LaboranalytResultatAnalytResultatDvquantity2 implements LaboranalytResultatAnalytResultatChoiceOrgEhrbaseEhrEncodeWrappersSnakecase1df98368 {
  @Path("|magnitude")
  private Double analytResultatMagnitude;

  @Path("|units")
  private String analytResultatUnits;

  public void setAnalytResultatMagnitude(Double analytResultatMagnitude) {
     this.analytResultatMagnitude = analytResultatMagnitude;
  }

  public Double getAnalytResultatMagnitude() {
     return this.analytResultatMagnitude ;
  }

  public void setAnalytResultatUnits(String analytResultatUnits) {
     this.analytResultatUnits = analytResultatUnits;
  }

  public String getAnalytResultatUnits() {
     return this.analytResultatUnits ;
  }
}
