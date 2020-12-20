package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.symptom_sign_screening.v0")
public class AndereAktuelleErkrankungenObservation {
  @Path("/language")
  private Language language;

  @Path("/protocol[at0007]/items[at0021]")
  private List<Cluster> erweiterung;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String bezeichnungDesSymptomsOderAnzeichensValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code")
  private VorhandenDefiningcode vorhandenDefiningcode;

  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]")
  private List<Cluster> anzeichen;

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
    this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
    return this.erweiterung;
  }

  public void setBezeichnungDesSymptomsOderAnzeichensValue(
      String bezeichnungDesSymptomsOderAnzeichensValue) {
    this.bezeichnungDesSymptomsOderAnzeichensValue = bezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getBezeichnungDesSymptomsOderAnzeichensValue() {
    return this.bezeichnungDesSymptomsOderAnzeichensValue;
  }

  public void setVorhandenDefiningcode(VorhandenDefiningcode vorhandenDefiningcode) {
    this.vorhandenDefiningcode = vorhandenDefiningcode;
  }

  public VorhandenDefiningcode getVorhandenDefiningcode() {
    return this.vorhandenDefiningcode;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
  }

  public void setOriginValue(TemporalAccessor originValue) {
    this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
    return this.originValue;
  }

  public void setAnzeichen(List<Cluster> anzeichen) {
    this.anzeichen = anzeichen;
  }

  public List<Cluster> getAnzeichen() {
    return this.anzeichen;
  }
}
