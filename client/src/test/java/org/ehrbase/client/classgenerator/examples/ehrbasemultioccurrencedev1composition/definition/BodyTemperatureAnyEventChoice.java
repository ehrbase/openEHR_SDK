package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.019496200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public interface BodyTemperatureAnyEventChoice {
  Cluster getExertion();

  void setExertion(Cluster exertion);

  String getDescriptionOfThermalStressValue();

  void setDescriptionOfThermalStressValue(String descriptionOfThermalStressValue);

  BodyTemperatureBodyExposureChoice getBodyExposure();

  void setBodyExposure(BodyTemperatureBodyExposureChoice bodyExposure);

  Long getCurrentDayOfMenstrualCycleMagnitude();

  void setCurrentDayOfMenstrualCycleMagnitude(Long currentDayOfMenstrualCycleMagnitude);

  Double getTemperatureMagnitude();

  void setTemperatureMagnitude(Double temperatureMagnitude);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  String getTemperatureUnits();

  void setTemperatureUnits(String temperatureUnits);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  List<Cluster> getEnvironmentalConditions();

  void setEnvironmentalConditions(List<Cluster> environmentalConditions);
}
