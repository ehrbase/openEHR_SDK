package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.symptom_sign_screening.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.458033600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class ChronischeErkrankungenObservation implements EntryEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Chronische Erkrankungen/Beliebiges Ereignis/Spezifisches
   * Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens. Description: Name des Symptoms oder
   * Anzeichens, das geprüft wird.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String bezeichnungDesSymptomsOderAnzeichensValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Chronische Erkrankungen/Beliebiges Ereignis/Spezifisches
   * Symptom/Anzeichen/Vorhanden? Description: Das Symptom oder Anzeichen ist vorhanden.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value|defining_code")
  private VorhandenDefiningCode vorhandenDefiningCode;

  /**
   * Path: Bericht/Allgemeine Angaben/Chronische Erkrankungen/Beliebiges Ereignis/Spezifisches
   * Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen Description: Zusätzliche
   * strukturierte Informationen zu einem bestimmten Symptom oder Anzeichen.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0026]")
  private List<Cluster> detaillierteAngabenZumSymptomAnzeichen;

  /** Path: Bericht/Allgemeine Angaben/Chronische Erkrankungen/Beliebiges Ereignis/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Path: Bericht/Allgemeine Angaben/Chronische Erkrankungen/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Chronische Erkrankungen/Erweiterung Description: Zusätzliche
   * Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere
   * Referenzmodelle/Formalismen. Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche
   * Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0007]/items[at0021]")
  private List<Cluster> erweiterung;

  /** Path: Bericht/Allgemeine Angaben/Chronische Erkrankungen/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Bericht/Allgemeine Angaben/Chronische Erkrankungen/language */
  @Path("/language")
  private Language language;

  /** Path: Bericht/Allgemeine Angaben/Chronische Erkrankungen/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBezeichnungDesSymptomsOderAnzeichensValue(
      String bezeichnungDesSymptomsOderAnzeichensValue) {
    this.bezeichnungDesSymptomsOderAnzeichensValue = bezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getBezeichnungDesSymptomsOderAnzeichensValue() {
    return this.bezeichnungDesSymptomsOderAnzeichensValue;
  }

  public void setVorhandenDefiningCode(VorhandenDefiningCode vorhandenDefiningCode) {
    this.vorhandenDefiningCode = vorhandenDefiningCode;
  }

  public VorhandenDefiningCode getVorhandenDefiningCode() {
    return this.vorhandenDefiningCode;
  }

  public void setDetaillierteAngabenZumSymptomAnzeichen(
      List<Cluster> detaillierteAngabenZumSymptomAnzeichen) {
    this.detaillierteAngabenZumSymptomAnzeichen = detaillierteAngabenZumSymptomAnzeichen;
  }

  public List<Cluster> getDetaillierteAngabenZumSymptomAnzeichen() {
    return this.detaillierteAngabenZumSymptomAnzeichen;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setOriginValue(TemporalAccessor originValue) {
    this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
    return this.originValue;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
    this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
    return this.erweiterung;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
