package org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.time.temporal.TemporalAccessor;
import java.util.List;

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
