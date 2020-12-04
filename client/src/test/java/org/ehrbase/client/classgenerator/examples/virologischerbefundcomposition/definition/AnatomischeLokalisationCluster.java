package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.anatomical_location.v1")
public class AnatomischeLokalisationCluster {
  /**
   * Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/Name der Körperstelle
   */
  @Path("/items[at0001]/value|value")
  private String nameDerKorperstelleValue;

  /**
   * Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/Alternative Struktur
   */
  @Path("/items[at0053]")
  private List<Cluster> alternativeStruktur;

  /**
   * Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/Multimediale Darstellung
   */
  @Path("/items[at0054]")
  private List<Cluster> multimedialeDarstellung;

  /**
   * Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameDerKorperstelleValue(String nameDerKorperstelleValue) {
     this.nameDerKorperstelleValue = nameDerKorperstelleValue;
  }

  public String getNameDerKorperstelleValue() {
     return this.nameDerKorperstelleValue ;
  }

  public void setAlternativeStruktur(List<Cluster> alternativeStruktur) {
     this.alternativeStruktur = alternativeStruktur;
  }

  public List<Cluster> getAlternativeStruktur() {
     return this.alternativeStruktur ;
  }

  public void setMultimedialeDarstellung(List<Cluster> multimedialeDarstellung) {
     this.multimedialeDarstellung = multimedialeDarstellung;
  }

  public List<Cluster> getMultimedialeDarstellung() {
     return this.multimedialeDarstellung ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
