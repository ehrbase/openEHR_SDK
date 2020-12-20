package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.049066600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public interface GeschichteHistorieBeliebigesEreignisChoice {
  List<Cluster> getStrukturierteAngabe();

  void setStrukturierteAngabe(List<Cluster> strukturierteAngabe);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  List<GeschichteHistorieGeschichteElement> getGeschichte();

  void setGeschichte(List<GeschichteHistorieGeschichteElement> geschichte);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);
}
