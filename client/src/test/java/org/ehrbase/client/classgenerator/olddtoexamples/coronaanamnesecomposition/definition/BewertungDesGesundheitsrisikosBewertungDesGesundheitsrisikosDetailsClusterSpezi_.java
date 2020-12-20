package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("CLUSTER")
public class BewertungDesGesundheitsrisikosBewertungDesGesundheitsrisikosDetailsClusterSpezi_
    implements BewertungDesGesundheitsrisikosDetailsChoice {
  @Path("")
  private List<Cluster> details;

  public void setDetails(List<Cluster> details) {
    this.details = details;
  }

  public List<Cluster> getDetails() {
    return this.details;
  }
}
