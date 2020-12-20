package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.IntervalEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.363034500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("INTERVAL_EVENT")
public class GeschichteHistorieBeliebigesEreignisIntervalEvent
    implements IntervalEventEntity, GeschichteHistorieBeliebigesEreignisChoice {
  /**
   * Path: Bericht/Geschichte/Historie/Beliebiges Ereignis/Geschichte Description: Beschreibung der
   * Geschichte oder der klinischen Vorgeschichte für das Fachgebiet der Pflege.
   */
  @Path("/data[at0003]/items[at0004]")
  private List<GeschichteHistorieGeschichteElement> geschichte;

  /**
   * Path: Bericht/Geschichte/Historie/Beliebiges Ereignis/Strukturierte Angabe Description:
   * Strukturierte Angaben über die Geschichte der Person oder des Patienten. Comment: Zum Beispiel:
   * ein spezifisches Symptom wie Übelkeit oder Schmerzen; ein Ereignis wie ein Sturz vom Fahrrad;
   * oder ein Anliegen wie der Wunsch, mit dem Tabakkonsum aufzuhören.
   */
  @Path("/data[at0003]/items[at0006]")
  private List<Cluster> strukturierteAngabe;

  /** Path: Bericht/Geschichte/Historie/Beliebiges Ereignis/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: Bericht/Geschichte/Historie/Beliebiges Ereignis/time */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /** Path: Bericht/Geschichte/Historie/Beliebiges Ereignis/width */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /** Path: Bericht/Geschichte/Historie/Beliebiges Ereignis/math_function */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  /** Path: Bericht/Geschichte/Historie/Beliebiges Ereignis/sample_count */
  @Path("/sample_count")
  private Long sampleCount;

  public void setGeschichte(List<GeschichteHistorieGeschichteElement> geschichte) {
    this.geschichte = geschichte;
  }

  public List<GeschichteHistorieGeschichteElement> getGeschichte() {
    return this.geschichte;
  }

  public void setStrukturierteAngabe(List<Cluster> strukturierteAngabe) {
    this.strukturierteAngabe = strukturierteAngabe;
  }

  public List<Cluster> getStrukturierteAngabe() {
    return this.strukturierteAngabe;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setWidthValue(TemporalAmount widthValue) {
    this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
    return this.widthValue;
  }

  public void setMathFunctionDefiningCode(MathFunction mathFunctionDefiningCode) {
    this.mathFunctionDefiningCode = mathFunctionDefiningCode;
  }

  public MathFunction getMathFunctionDefiningCode() {
    return this.mathFunctionDefiningCode;
  }

  public void setSampleCount(Long sampleCount) {
    this.sampleCount = sampleCount;
  }

  public Long getSampleCount() {
    return this.sampleCount;
  }
}
