package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-19T11:36:01.876797+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class LaborergebnisDetailsDerTestanforderungCluster implements LocatableEntity {
  /**
   * Path: Laborbefund/Laborergebnis/Details der Testanforderung/Auftrags-ID (Einsender)
   * Description: Lokale Auftrags-ID des anfordernden/einsendenden Systems.
   * Comment: Äquivalent zur "HL7 Placer Order Identifier".
   */
  @Path("/items[at0062 and name/value='Auftrags-ID (Einsender)']/value")
  private DvIdentifier auftragsIdEinsender;

  /**
   * Path: Laborbefund/Laborergebnis/Tree/Details der Testanforderung/Auftrags-ID (Einsender)/null_flavour
   */
  @Path("/items[at0062 and name/value='Auftrags-ID (Einsender)']/null_flavour|defining_code")
  private NullFlavour auftragsIdEinsenderNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Details der Testanforderung/Auftrags-ID (Empfänger)
   * Description: Lokale Auftrags-ID, die vom auftragsempfangendem System, gewöhnlich dem Laborinformationssystem (LIS) zugewiesen wird.
   * Comment: Die Vergabe einer solchen ID ermöglicht das Nachverfolgen des Auftragsstatus und das Verlinken der Ergebnisse zum Auftrag. Es erlaubt auch das Verwalten von weiteren Erkundigungen und Nachfragen und ist äquivalent zum "HL7 Filler Order Identifier".
   */
  @Path("/items[at0063]/value")
  private DvIdentifier auftragsIdEmpfaenger;

  /**
   * Path: Laborbefund/Laborergebnis/Tree/Details der Testanforderung/Auftrags-ID (Empfänger)/null_flavour
   */
  @Path("/items[at0063]/null_flavour|defining_code")
  private NullFlavour auftragsIdEmpfaengerNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Details der Testanforderung/Einsender
   * Description: Details über den Kliniker oder die Abteilung, die das Labortestergebnis angefordert hat.
   */
  @Path("/items[at0090]")
  private Cluster einsender;

  /**
   * Path: Laborbefund/Laborergebnis/Details der Testanforderung/Verteilerliste
   * Description: Details über weitere Kliniker oder Organisationen, die eine Kopie der Analyseergebnisse benötigen.
   * Comment: Die "Verteilerliste" dient nur zu Informationszwecken. Der Hauptempfänger des Berichts ist die Person, die dazu bestimmt ist, auf die Information zu reagieren.
   */
  @Path("/items[at0035]")
  private List<Cluster> verteilerliste;

  /**
   * Path: Laborbefund/Laborergebnis/Details der Testanforderung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAuftragsIdEinsender(DvIdentifier auftragsIdEinsender) {
     this.auftragsIdEinsender = auftragsIdEinsender;
  }

  public DvIdentifier getAuftragsIdEinsender() {
     return this.auftragsIdEinsender ;
  }

  public void setAuftragsIdEinsenderNullFlavourDefiningCode(
      NullFlavour auftragsIdEinsenderNullFlavourDefiningCode) {
     this.auftragsIdEinsenderNullFlavourDefiningCode = auftragsIdEinsenderNullFlavourDefiningCode;
  }

  public NullFlavour getAuftragsIdEinsenderNullFlavourDefiningCode() {
     return this.auftragsIdEinsenderNullFlavourDefiningCode ;
  }

  public void setAuftragsIdEmpfaenger(DvIdentifier auftragsIdEmpfaenger) {
     this.auftragsIdEmpfaenger = auftragsIdEmpfaenger;
  }

  public DvIdentifier getAuftragsIdEmpfaenger() {
     return this.auftragsIdEmpfaenger ;
  }

  public void setAuftragsIdEmpfaengerNullFlavourDefiningCode(
      NullFlavour auftragsIdEmpfaengerNullFlavourDefiningCode) {
     this.auftragsIdEmpfaengerNullFlavourDefiningCode = auftragsIdEmpfaengerNullFlavourDefiningCode;
  }

  public NullFlavour getAuftragsIdEmpfaengerNullFlavourDefiningCode() {
     return this.auftragsIdEmpfaengerNullFlavourDefiningCode ;
  }

  public void setEinsender(Cluster einsender) {
     this.einsender = einsender;
  }

  public Cluster getEinsender() {
     return this.einsender ;
  }

  public void setVerteilerliste(List<Cluster> verteilerliste) {
     this.verteilerliste = verteilerliste;
  }

  public List<Cluster> getVerteilerliste() {
     return this.verteilerliste ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
