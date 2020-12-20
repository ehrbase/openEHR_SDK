package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("POINT_EVENT")
public class GeschichteHistorieBeliebigesEreignisPointEvent
    implements GeschichteHistorieBeliebigesEreignisChoice {
  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0003]/items[at0004]")
  private List<GeschichteHistorieGeschichteElement> geschichte;

  @Path("/data[at0003]/items[at0006]")
  private List<Cluster> strukturierteAngabe;

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setGeschichte(List<GeschichteHistorieGeschichteElement> geschichte) {
    this.geschichte = geschichte;
  }

  public List<GeschichteHistorieGeschichteElement> getGeschichte() {
    return this.geschichte;
  }

  public void setStrukturierteAngabe(List<Cluster> strukturierteAngabe) {
    this.strukturierteAngabe = strukturierteAngabe;
  }

  public List<Cluster> getStrukturierteAngabe() {
    return this.strukturierteAngabe;
  }
}
