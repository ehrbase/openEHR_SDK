package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;

public interface BodyTemperatureAnyEventChoice {
  Double getTemperatureMagnitude();

  void setTemperatureMagnitude(Double temperatureMagnitude);

  Cluster getExertion();

  void setExertion(Cluster exertion);

  String getDescriptionOfThermalStressValue();

  void setDescriptionOfThermalStressValue(String descriptionOfThermalStressValue);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  Long getCurrentDayOfMenstrualCycleMagnitude();

  void setCurrentDayOfMenstrualCycleMagnitude(Long currentDayOfMenstrualCycleMagnitude);

  List<Cluster> getEnvironmentalConditions();

  void setEnvironmentalConditions(List<Cluster> environmentalConditions);

  BodyTemperatureBodyExposureChoice getBodyExposure();

  void setBodyExposure(BodyTemperatureBodyExposureChoice bodyExposure);

  String getTemperatureUnits();

  void setTemperatureUnits(String temperatureUnits);
}
