package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:52.563762+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
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
