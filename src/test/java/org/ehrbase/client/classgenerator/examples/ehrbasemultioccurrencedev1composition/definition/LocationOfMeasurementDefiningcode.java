package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LocationOfMeasurementDefiningcode implements EnumValueSet {
  VAGINA("Vagina", "Temperature is measured within the vagina.", "local", "at0051"),

  URINARYBLADDER("Urinary bladder", "Temperature is measured in the urinary bladder.", "local", "at0027"),

  INGUINALSKINCREASE("Inguinal skin crease", "Temperature is measured in the inguinal skin crease between the leg and abdominal wall.", "local", "at0055"),

  NASOPHARYNX("Nasopharynx", "Temperature is measured within the nasopharynx.", "local", "at0026"),

  EARCANAL("Ear canal", "Temperature is measured from within the external auditory canal.", "local", "at0023"),

  RECTUM("Rectum", "Temperature measured within the rectum.", "local", "at0025"),

  AXILLA("Axilla", "Temperature is measured from the skin of the axilla with the arm positioned down by the side.", "local", "at0024"),

  MOUTH("Mouth", "Temperature is measured within the mouth.", "local", "at0022"),

  TEMPLE("Temple", "Temperature is measured at the temple, over the superficial temporal artery.", "local", "at0060"),

  INTRAVASCULAR("Intravascular", "Temperature is measured within the vascular system.", "local", "at0028"),

  SKIN("Skin", "Temperature is measured from exposed skin.", "local", "at0043"),

  OESOPHAGUS("Oesophagus", "Temperatue is measured within the oesophagus.", "local", "at0054"),

  FOREHEAD("Forehead", "Temperature is measured on the forehead.", "local", "at0061");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LocationOfMeasurementDefiningcode(String value, String description, String terminologyId,
                                    String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public String getValue() {
    return this.value;
  }

  public String getDescription() {
    return this.description;
  }

  public String getTerminologyId() {
    return this.terminologyId;
  }

  public String getCode() {
    return this.code;
  }
}
