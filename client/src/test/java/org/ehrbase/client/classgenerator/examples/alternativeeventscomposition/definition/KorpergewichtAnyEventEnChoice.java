package org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:50.075758800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public interface KorpergewichtAnyEventEnChoice {
  Double getGewichtMagnitude();

  void setGewichtMagnitude(Double gewichtMagnitude);

  String getGewichtUnits();

  void setGewichtUnits(String gewichtUnits);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  List<KorpergewichtConfoundingFactorsEnElement> getConfoundingFactorsEn();

  void setConfoundingFactorsEn(List<KorpergewichtConfoundingFactorsEnElement> confoundingFactorsEn);

  String getCommentEnValue();

  void setCommentEnValue(String commentEnValue);

  StateOfDressEnDefiningCode getStateOfDressEnDefiningCode();

  void setStateOfDressEnDefiningCode(StateOfDressEnDefiningCode stateOfDressEnDefiningCode);
}
