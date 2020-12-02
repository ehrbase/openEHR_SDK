package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.pulse.v1")
public class PulseObservation {
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004 and name/value='Pulse Rate']/value|magnitude")
  private Double pulseRateMagnitude;

  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004 and name/value='Pulse Rate']/value|units")
  private String pulseRateUnits;

  @Path("/data[at0002]/events[at0003]/state[at0012]/items[at1017]")
  private List<Cluster> exertion;

  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  @Path("/protocol[at0010]/items[at1013]")
  private Cluster device;

  @Path("/protocol[at0010]/items[at1056]")
  private List<Cluster> extension;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setPulseRateMagnitude(Double pulseRateMagnitude) {
     this.pulseRateMagnitude = pulseRateMagnitude;
  }

  public Double getPulseRateMagnitude() {
     return this.pulseRateMagnitude ;
  }

  public void setPulseRateUnits(String pulseRateUnits) {
     this.pulseRateUnits = pulseRateUnits;
  }

  public String getPulseRateUnits() {
     return this.pulseRateUnits ;
  }

  public void setExertion(List<Cluster> exertion) {
     this.exertion = exertion;
  }

  public List<Cluster> getExertion() {
     return this.exertion ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setDevice(Cluster device) {
     this.device = device;
  }

  public Cluster getDevice() {
     return this.device ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }
}
