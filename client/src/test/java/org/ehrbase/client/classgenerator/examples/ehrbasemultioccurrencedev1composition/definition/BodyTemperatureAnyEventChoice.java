package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:51.167759200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
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
