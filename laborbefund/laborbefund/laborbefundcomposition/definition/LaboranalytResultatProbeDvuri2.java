package laborbefund.laborbefundcomposition.definition;

import java.net.URI;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_URI")
public class LaboranalytResultatProbeDvuri2 implements LaboranalytResultatProbeChoiceOrgEhrbaseEhrEncodeWrappersSnakecase21ca139c {
  @Path("|value")
  private URI probeValue;

  public void setProbeValue(URI probeValue) {
     this.probeValue = probeValue;
  }

  public URI getProbeValue() {
     return this.probeValue ;
  }
}
