package org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.115491200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public interface KorpergewichtAnyEventEnChoice {
  List<KorpergewichtConfoundingFactorsEnElement> getConfoundingFactorsEn();

  void setConfoundingFactorsEn(List<KorpergewichtConfoundingFactorsEnElement> confoundingFactorsEn);

  String getCommentEnValue();

  void setCommentEnValue(String commentEnValue);

  String getGewichtUnits();

  void setGewichtUnits(String gewichtUnits);

  StateOfDressEnDefiningCode getStateOfDressEnDefiningCode();

  void setStateOfDressEnDefiningCode(StateOfDressEnDefiningCode stateOfDressEnDefiningCode);

  Double getGewichtMagnitude();

  void setGewichtMagnitude(Double gewichtMagnitude);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);
}
