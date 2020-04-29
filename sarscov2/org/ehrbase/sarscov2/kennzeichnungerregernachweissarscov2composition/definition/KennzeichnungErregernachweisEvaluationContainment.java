package org.ehrbase.sarscov2.kennzeichnungerregernachweissarscov2composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.sarscov2.shareddefinition.ErregernameDefiningcode;
import org.ehrbase.sarscov2.shareddefinition.Language;

public class KennzeichnungErregernachweisEvaluationContainment extends Containment {
  public SelectAqlField<KennzeichnungErregernachweisEvaluation> KENNZEICHNUNG_ERREGERNACHWEIS_EVALUATION = new AqlFieldImp<KennzeichnungErregernachweisEvaluation>(KennzeichnungErregernachweisEvaluation.class, "", "KennzeichnungErregernachweisEvaluation", KennzeichnungErregernachweisEvaluation.class, this);

  public SelectAqlField<Boolean> ERREGERNACHWEIS_VALUE = new AqlFieldImp<Boolean>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0005]/value|value", "erregernachweisValue", Boolean.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KennzeichnungErregernachweisEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(KennzeichnungErregernachweisEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(KennzeichnungErregernachweisEvaluation.class, "/protocol[at0003]/items[at0007]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DER_KENNZEICHNUNG_VALUE = new AqlFieldImp<TemporalAccessor>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0015]/value|value", "zeitpunktDerKennzeichnungValue", TemporalAccessor.class, this);

  public SelectAqlField<ErregernameDefiningcode> ERREGERNAME_DEFININGCODE = new AqlFieldImp<ErregernameDefiningcode>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0012]/value|defining_code", "erregernameDefiningcode", ErregernameDefiningcode.class, this);

  public SelectAqlField<String> HINWEISTEXT_VALUE = new AqlFieldImp<String>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0013]/value|value", "hinweistextValue", String.class, this);

  public SelectAqlField<Boolean> ERREGERNACHWEIS_IN_DER_KLINIK_VALUE = new AqlFieldImp<Boolean>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0011]/value|value", "erregernachweisInDerKlinikValue", Boolean.class, this);

  public SelectAqlField<TemporalAccessor> ZULETZT_AKTUALISIERT_VALUE = new AqlFieldImp<TemporalAccessor>(KennzeichnungErregernachweisEvaluation.class, "/protocol[at0003]/items[at0004]/value|value", "zuletztAktualisiertValue", TemporalAccessor.class, this);

  private KennzeichnungErregernachweisEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.flag_pathogen.v0");
  }

  public static KennzeichnungErregernachweisEvaluationContainment getInstance() {
    return new KennzeichnungErregernachweisEvaluationContainment();
  }
}
