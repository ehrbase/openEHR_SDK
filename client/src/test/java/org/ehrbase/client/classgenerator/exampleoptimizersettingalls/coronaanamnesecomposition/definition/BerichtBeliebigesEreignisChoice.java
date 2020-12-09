package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:53.136288100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public interface BerichtBeliebigesEreignisChoice {
  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  List<BerichtSpezifischesSymptomAnzeichenCluster> getSpezifischesSymptomAnzeichen();

  void setSpezifischesSymptomAnzeichen(
      List<BerichtSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen);
}
