package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class WeitereSymptomeSpezifischesSymptomAnzeichenCluster {
  @Path("/items[at0004]/value|value")
  private String bezeichnungDesSymptomsOderAnzeichensValue;

  @Path("/items[at0005]/value|defining_code")
  private VorhandenDefiningCode2 vorhandenDefiningCode;

  @Path("/items[at0026]")
  private List<Cluster> detaillierteAngabenZumSymptomAnzeichen;

  @Path("/items[at0035]/value|value")
  private String kommentarValue;

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
}
