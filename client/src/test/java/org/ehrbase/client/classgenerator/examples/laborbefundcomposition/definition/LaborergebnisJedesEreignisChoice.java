package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-19T11:36:02.018906+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public interface LaborergebnisJedesEreignisChoice {
  List<Cluster> getStrukturierteTestdiagnostik();

  void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik);

  GesamtUntersuchungsstatusDefiningCode getGesamtUntersuchungsstatusDefiningCode();

  void setGesamtUntersuchungsstatusDefiningCode(
      GesamtUntersuchungsstatusDefiningCode gesamtUntersuchungsstatusDefiningCode);

  NullFlavour getInterpretationNullFlavourDefiningCode();

  void setInterpretationNullFlavourDefiningCode(NullFlavour interpretationNullFlavourDefiningCode);

  NullFlavour getGesamtUntersuchungsstatusNullFlavourDefiningCode();

  void setGesamtUntersuchungsstatusNullFlavourDefiningCode(
      NullFlavour gesamtUntersuchungsstatusNullFlavourDefiningCode);

  List<LaboranalytResultatCluster> getLaboranalytResultat();

  void setLaboranalytResultat(List<LaboranalytResultatCluster> laboranalytResultat);

  String getInterpretationValue();

  void setInterpretationValue(String interpretationValue);

  NullFlavour getAnforderungNullFlavourDefiningCode();

  void setAnforderungNullFlavourDefiningCode(NullFlavour anforderungNullFlavourDefiningCode);

  NullFlavour getKommentarNullFlavourDefiningCode();

  void setKommentarNullFlavourDefiningCode(NullFlavour kommentarNullFlavourDefiningCode);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  String getAnforderungValue();

  void setAnforderungValue(String anforderungValue);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  List<ProbeCluster> getProbe();

  void setProbe(List<ProbeCluster> probe);

  List<Cluster> getMultimediaDarstellung();

  void setMultimediaDarstellung(List<Cluster> multimediaDarstellung);

  List<Cluster> getStrukturierteErfassungDerStoerfaktoren();

  void setStrukturierteErfassungDerStoerfaktoren(
      List<Cluster> strukturierteErfassungDerStoerfaktoren);

  String getKommentarValue();

  void setKommentarValue(String kommentarValue);
}
