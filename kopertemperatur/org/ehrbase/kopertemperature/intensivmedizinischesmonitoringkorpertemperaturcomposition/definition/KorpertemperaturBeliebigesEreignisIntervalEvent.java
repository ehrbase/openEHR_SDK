package org.ehrbase.kopertemperature.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.kopertemperature.shareddefinition.MathFunctionDefiningcode;

@Entity
@OptionFor("INTERVAL_EVENT")
public class KorpertemperaturBeliebigesEreignisIntervalEvent implements KorpertemperaturBeliebigesEreignisChoice {
  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0001]/items[at0004]/value|magnitude")
  private Double temperaturMagnitude;

  @Path("/data[at0001]/items[at0004]/value|units")
  private String temperaturUnits;

  @Path("/data[at0001]/items[at0063]/value|value")
  private String kommentarValue;

  @Path("/state[at0029]/items[at0041]/value|value")
  private String beschreibungDerWarmebelastungValue;

  @Path("/state[at0029]/items[at0057]")
  private Cluster betatigung;

  @Path("/state[at0029]/items[at0065]/value|magnitude")
  private Long aktuellerTagDesMenstruationszyklusMagnitude;

  @Path("/state[at0029]/items[at0056]")
  private List<Cluster> umweltbedingungen;

  @Path("/width|value")
  private TemporalAmount widthValue;

  @Path("/state[at0029]/items[at0030]/value")
  @Choice
  private KorpertemperaturKorperexpositionChoiceStatus korperexposition;

  @Path("/math_function|defining_code")
  private MathFunctionDefiningcode mathFunctionDefiningcode;

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setTemperaturMagnitude(Double temperaturMagnitude) {
     this.temperaturMagnitude = temperaturMagnitude;
  }

  public Double getTemperaturMagnitude() {
     return this.temperaturMagnitude ;
  }

  public void setTemperaturUnits(String temperaturUnits) {
     this.temperaturUnits = temperaturUnits;
  }

  public String getTemperaturUnits() {
     return this.temperaturUnits ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setBeschreibungDerWarmebelastungValue(String beschreibungDerWarmebelastungValue) {
     this.beschreibungDerWarmebelastungValue = beschreibungDerWarmebelastungValue;
  }

  public String getBeschreibungDerWarmebelastungValue() {
     return this.beschreibungDerWarmebelastungValue ;
  }

  public void setBetatigung(Cluster betatigung) {
     this.betatigung = betatigung;
  }

  public Cluster getBetatigung() {
     return this.betatigung ;
  }

  public void setAktuellerTagDesMenstruationszyklusMagnitude(
      Long aktuellerTagDesMenstruationszyklusMagnitude) {
     this.aktuellerTagDesMenstruationszyklusMagnitude = aktuellerTagDesMenstruationszyklusMagnitude;
  }

  public Long getAktuellerTagDesMenstruationszyklusMagnitude() {
     return this.aktuellerTagDesMenstruationszyklusMagnitude ;
  }

  public void setUmweltbedingungen(List<Cluster> umweltbedingungen) {
     this.umweltbedingungen = umweltbedingungen;
  }

  public List<Cluster> getUmweltbedingungen() {
     return this.umweltbedingungen ;
  }

  public void setWidthValue(TemporalAmount widthValue) {
     this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
     return this.widthValue ;
  }

  public void setKorperexposition(KorpertemperaturKorperexpositionChoiceStatus korperexposition) {
     this.korperexposition = korperexposition;
  }

  public KorpertemperaturKorperexpositionChoiceStatus getKorperexposition() {
     return this.korperexposition ;
  }

  public void setMathFunctionDefiningcode(MathFunctionDefiningcode mathFunctionDefiningcode) {
     this.mathFunctionDefiningcode = mathFunctionDefiningcode;
  }

  public MathFunctionDefiningcode getMathFunctionDefiningcode() {
     return this.mathFunctionDefiningcode ;
  }
}
