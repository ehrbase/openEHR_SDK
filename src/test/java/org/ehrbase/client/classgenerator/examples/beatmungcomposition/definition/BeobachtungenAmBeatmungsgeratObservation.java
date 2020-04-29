package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.ventilator_vital_signs.v0")
public class BeobachtungenAmBeatmungsgeratObservation {
  @Path("/protocol[at0012]/items[openEHR-EHR-CLUSTER.device.v1]")
  private MedizingeratCluster medizingerat;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.ventilator_settings2.v0]")
  private MesswerteCluster messwerte;

  @Path("/data[at0001]/events[at0002]/state[at0010]/items[openEHR-EHR-CLUSTER.ventilator_settings2.v0]")
  private MesswerteClusterBeatmungsgeratEinstellungen messwerteBeatmungsgeratEinstellungen;

  public void setMedizingerat(MedizingeratCluster medizingerat) {
     this.medizingerat = medizingerat;
  }

  public MedizingeratCluster getMedizingerat() {
     return this.medizingerat ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setMesswerte(MesswerteCluster messwerte) {
     this.messwerte = messwerte;
  }

  public MesswerteCluster getMesswerte() {
     return this.messwerte ;
  }

  public void setMesswerteBeatmungsgeratEinstellungen(
      MesswerteClusterBeatmungsgeratEinstellungen messwerteBeatmungsgeratEinstellungen) {
     this.messwerteBeatmungsgeratEinstellungen = messwerteBeatmungsgeratEinstellungen;
  }

  public MesswerteClusterBeatmungsgeratEinstellungen getMesswerteBeatmungsgeratEinstellungen() {
     return this.messwerteBeatmungsgeratEinstellungen ;
  }
}
