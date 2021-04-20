package org.ehrbase.client.classgenerator.examples.minimalevaluationenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-EVALUATION.minimal.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-22T13:27:16.936346+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class MinimalEvaluation implements EntryEntity {
  /**
   * Path: Minimal/Minimal/quantity
   * Description: *
   */
  @Path("/data[at0001]/items[at0002]/value|magnitude")
  private Double quantityMagnitude;

  /**
   * Path: Minimal/Minimal/quantity
   * Description: *
   */
  @Path("/data[at0001]/items[at0002]/value|units")
  private String quantityUnits;

  /**
   * Path: Minimal/Minimal/Arbol/quantity/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour quantityNullFlavourDefiningCode;

  /**
   * Path: Minimal/Minimal/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Minimal/Minimal/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Minimal/Minimal/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setQuantityMagnitude(Double quantityMagnitude) {
     this.quantityMagnitude = quantityMagnitude;
  }

  public Double getQuantityMagnitude() {
     return this.quantityMagnitude ;
  }

  public void setQuantityUnits(String quantityUnits) {
     this.quantityUnits = quantityUnits;
  }

  public String getQuantityUnits() {
     return this.quantityUnits ;
  }

  public void setQuantityNullFlavourDefiningCode(NullFlavour quantityNullFlavourDefiningCode) {
     this.quantityNullFlavourDefiningCode = quantityNullFlavourDefiningCode;
  }

  public NullFlavour getQuantityNullFlavourDefiningCode() {
     return this.quantityNullFlavourDefiningCode ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
