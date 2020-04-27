package laborbefund.laborbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_IDENTIFIER")
public class LaboranalytResultatProbeDvidentifier implements LaboranalytResultatProbeChoice {
  @Path("")
  private DvIdentifier probe;

  public void setProbe(DvIdentifier probe) {
     this.probe = probe;
  }

  public DvIdentifier getProbe() {
     return this.probe ;
  }
}
