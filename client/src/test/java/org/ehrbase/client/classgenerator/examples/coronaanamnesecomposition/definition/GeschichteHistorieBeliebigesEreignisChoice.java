package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;

public interface GeschichteHistorieBeliebigesEreignisChoice {
  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  List<GeschichteHistorieGeschichteElement> getGeschichte();

  void setGeschichte(List<GeschichteHistorieGeschichteElement> geschichte);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  List<Cluster> getStrukturierteAngabe();

  void setStrukturierteAngabe(List<Cluster> strukturierteAngabe);
}
