package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-19T11:36:01.864553+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class LaborbereichCluster implements LocatableEntity {
  /**
   * Path: Laborbefund/Laborergebnis/Laborbereich/Standorttyp
   * Description: Kategorisierung des Standorttyps, z.B. Klinik, zu Hause.
   */
  @Path("/items[at0040]/value|value")
  private String standorttypValue;

  /**
   * Path: Laborbefund/Laborergebnis/Tree/Laborbereich/Standorttyp/null_flavour
   */
  @Path("/items[at0040]/null_flavour|defining_code")
  private NullFlavour standorttypNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Laborbereich/Laborbereich
   * Description: Das Feld enthält die Freitextbeschreibung des Standorts, z.B. Throax-, Herz- und Gefäßchirurgie.
   */
  @Path("/items[at0046 and name/value='Laborbereich']/value|value")
  private String laborbereichValue;

  /**
   * Path: Laborbefund/Laborergebnis/Tree/Laborbereich/Laborbereich/null_flavour
   */
  @Path("/items[at0046 and name/value='Laborbereich']/null_flavour|defining_code")
  private NullFlavour laborbereichNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Laborbereich/Kode des Laborbereichs
   * Description: Kodierung des Standortes, z.B. der Fachabteilungsschlüssel (z. B. 2000 Thoraxchirurgie).
   */
  @Path("/items[at0048 and name/value='Kode des Laborbereichs']/value|value")
  private String kodeDesLaborbereichsValue;

  /**
   * Path: Laborbefund/Laborergebnis/Tree/Laborbereich/Kode des Laborbereichs/null_flavour
   */
  @Path("/items[at0048 and name/value='Kode des Laborbereichs']/null_flavour|defining_code")
  private NullFlavour kodeDesLaborbereichsNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Laborbereich/Details
   * Description: Für die Erfassung weiterer Angaben über das Bett oder der Adresse. Verwenden Sie dazu den Archetyp CLUSTER.device oder CLUSTER.address.
   */
  @Path("/items[at0047]")
  private List<Cluster> details;

  /**
   * Path: Laborbefund/Laborergebnis/Laborbereich/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStandorttypValue(String standorttypValue) {
     this.standorttypValue = standorttypValue;
  }

  public String getStandorttypValue() {
     return this.standorttypValue ;
  }

  public void setStandorttypNullFlavourDefiningCode(
      NullFlavour standorttypNullFlavourDefiningCode) {
     this.standorttypNullFlavourDefiningCode = standorttypNullFlavourDefiningCode;
  }

  public NullFlavour getStandorttypNullFlavourDefiningCode() {
     return this.standorttypNullFlavourDefiningCode ;
  }

  public void setLaborbereichValue(String laborbereichValue) {
     this.laborbereichValue = laborbereichValue;
  }

  public String getLaborbereichValue() {
     return this.laborbereichValue ;
  }

  public void setLaborbereichNullFlavourDefiningCode(
      NullFlavour laborbereichNullFlavourDefiningCode) {
     this.laborbereichNullFlavourDefiningCode = laborbereichNullFlavourDefiningCode;
  }

  public NullFlavour getLaborbereichNullFlavourDefiningCode() {
     return this.laborbereichNullFlavourDefiningCode ;
  }

  public void setKodeDesLaborbereichsValue(String kodeDesLaborbereichsValue) {
     this.kodeDesLaborbereichsValue = kodeDesLaborbereichsValue;
  }

  public String getKodeDesLaborbereichsValue() {
     return this.kodeDesLaborbereichsValue ;
  }

  public void setKodeDesLaborbereichsNullFlavourDefiningCode(
      NullFlavour kodeDesLaborbereichsNullFlavourDefiningCode) {
     this.kodeDesLaborbereichsNullFlavourDefiningCode = kodeDesLaborbereichsNullFlavourDefiningCode;
  }

  public NullFlavour getKodeDesLaborbereichsNullFlavourDefiningCode() {
     return this.kodeDesLaborbereichsNullFlavourDefiningCode ;
  }

  public void setDetails(List<Cluster> details) {
     this.details = details;
  }

  public List<Cluster> getDetails() {
     return this.details ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
