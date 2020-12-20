package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class SmicsErgebnisObservationContainment extends Containment {
  public SelectAqlField<SmicsErgebnisObservation> SMICS_ERGEBNIS_OBSERVATION =
      new AqlFieldImp<SmicsErgebnisObservation>(
          SmicsErgebnisObservation.class,
          "",
          "SmicsErgebnisObservation",
          SmicsErgebnisObservation.class,
          this);

  public SelectAqlField<SmicsErgebniskategorieDefiningCode> SMICS_ERGEBNISKATEGORIE_DEFINING_CODE =
      new AqlFieldImp<SmicsErgebniskategorieDefiningCode>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code",
          "smicsErgebniskategorieDefiningCode",
          SmicsErgebniskategorieDefiningCode.class,
          this);

  public SelectAqlField<Boolean> MULTISPEZIES_VALUE =
      new AqlFieldImp<Boolean>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|value",
          "multispeziesValue",
          Boolean.class,
          this);

  public SelectAqlField<TemporalAccessor> BEGINN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value|value",
          "beginnValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> BEGINN_DER_UNTERSUCHUNG_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|value",
          "beginnDerUntersuchungValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ENDE_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|value",
          "endeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAmount> DAUER_VALUE =
      new AqlFieldImp<TemporalAmount>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0009]/value|value",
          "dauerValue",
          TemporalAmount.class,
          this);

  public ListSelectAqlField<StandortCluster> STANDORT =
      new ListAqlFieldImp<StandortCluster>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.location.v1]",
          "standort",
          StandortCluster.class,
          this);

  public SelectAqlField<String> BAUM_KOMMENTAR_VALUE =
      new AqlFieldImp<String>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value|value",
          "baumKommentarValue",
          String.class,
          this);

  public SelectAqlField<String> UBERTRAGUNGSWEG_VALUE =
      new AqlFieldImp<String>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0014]/value|value",
          "ubertragungswegValue",
          String.class,
          this);

  public SelectAqlField<String> TRANSMISSIONSWEG_VALUE =
      new AqlFieldImp<String>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0013]/value|value",
          "transmissionswegValue",
          String.class,
          this);

  public SelectAqlField<String> ART_DER_UBERTRAGUNG_KOMMENTAR_VALUE =
      new AqlFieldImp<String>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0012]/items[at0038]/value|value",
          "artDerUbertragungKommentarValue",
          String.class,
          this);

  public SelectAqlField<ErregernameDefiningCode> ERREGERNAME_DEFINING_CODE =
      new AqlFieldImp<ErregernameDefiningCode>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0016]/value|defining_code",
          "erregernameDefiningCode",
          ErregernameDefiningCode.class,
          this);

  public ListSelectAqlField<SmicsErgebnisAnzahlDerErregernachweiseElement>
      ANZAHL_DER_ERREGERNACHWEISE =
          new ListAqlFieldImp<SmicsErgebnisAnzahlDerErregernachweiseElement>(
              SmicsErgebnisObservation.class,
              "/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0037]",
              "anzahlDerErregernachweise",
              SmicsErgebnisAnzahlDerErregernachweiseElement.class,
              this);

  public SelectAqlField<ErregerdetailsCluster> ERREGERDETAILS =
      new AqlFieldImp<ErregerdetailsCluster>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[openEHR-EHR-CLUSTER.erregerdetails.v1]",
          "erregerdetails",
          ErregerdetailsCluster.class,
          this);

  public SelectAqlField<ErregertypisierungCluster> ERREGERTYPISIERUNG =
      new AqlFieldImp<ErregertypisierungCluster>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[openEHR-EHR-CLUSTER.molekulare_typisierung.v0]",
          "erregertypisierung",
          ErregertypisierungCluster.class,
          this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DES_ERSTEN_ERREGERNACHWEISES_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0021]/value|value",
          "zeitpunktDesErstenErregernachweisesValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DES_LETZTEN_ERREGERNACHWEISES_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/data[at0003]/items[at0015]/items[at0020]/value|value",
          "zeitpunktDesLetztenErregernachweisesValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/events[at0002]/time|value",
          "timeValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE =
      new AqlFieldImp<TemporalAccessor>(
          SmicsErgebnisObservation.class,
          "/data[at0001]/origin|value",
          "originValue",
          TemporalAccessor.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          SmicsErgebnisObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          SmicsErgebnisObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          SmicsErgebnisObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private SmicsErgebnisObservationContainment() {
    super("openEHR-EHR-OBSERVATION.smics_befund.v1");
  }

  public static SmicsErgebnisObservationContainment getInstance() {
    return new SmicsErgebnisObservationContainment();
  }
}
