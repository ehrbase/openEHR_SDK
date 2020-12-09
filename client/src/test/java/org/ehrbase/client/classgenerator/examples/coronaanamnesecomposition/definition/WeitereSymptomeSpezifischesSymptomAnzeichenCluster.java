package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:52.591761100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
public class WeitereSymptomeSpezifischesSymptomAnzeichenCluster implements LocatableEntity {
  /**
   * Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Bezeichnung des Symptoms oder Anzeichens.
   */
  @Path("/items[at0004]/value|value")
  private String bezeichnungDesSymptomsOderAnzeichensValue;

  /**
   * Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Vorhanden?
   */
  @Path("/items[at0005]/value|defining_code")
  private VorhandenDefiningCode2 vorhandenDefiningCode;

  /**
   * Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Detaillierte Angaben zum Symptom/Anzeichen
   */
  @Path("/items[at0026]")
  private List<Cluster> detaillierteAngabenZumSymptomAnzeichen;

  /**
   * Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/Kommentar
   */
  @Path("/items[at0035]/value|value")
  private String kommentarValue;

  /**
   * Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBezeichnungDesSymptomsOderAnzeichensValue(
      String bezeichnungDesSymptomsOderAnzeichensValue) {
     this.bezeichnungDesSymptomsOderAnzeichensValue = bezeichnungDesSymptomsOderAnzeichensValue;
  }

  public String getBezeichnungDesSymptomsOderAnzeichensValue() {
     return this.bezeichnungDesSymptomsOderAnzeichensValue ;
  }

  public void setVorhandenDefiningCode(VorhandenDefiningCode2 vorhandenDefiningCode) {
     this.vorhandenDefiningCode = vorhandenDefiningCode;
  }

  public VorhandenDefiningCode2 getVorhandenDefiningCode() {
     return this.vorhandenDefiningCode ;
  }

  public void setDetaillierteAngabenZumSymptomAnzeichen(
      List<Cluster> detaillierteAngabenZumSymptomAnzeichen) {
     this.detaillierteAngabenZumSymptomAnzeichen = detaillierteAngabenZumSymptomAnzeichen;
  }

  public List<Cluster> getDetaillierteAngabenZumSymptomAnzeichen() {
     return this.detaillierteAngabenZumSymptomAnzeichen ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
