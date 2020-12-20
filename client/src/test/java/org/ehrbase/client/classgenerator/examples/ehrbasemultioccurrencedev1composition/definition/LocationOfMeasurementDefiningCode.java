package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LocationOfMeasurementDefiningCode implements EnumValueSet {
  TEMPLE(
      "Temple",
      "Temperature is measured at the temple, over the superficial temporal\n"
          + "                        artery.\n"
          + "                    ",
      "local",
      "at0060"),

  INTRAVASCULAR(
      "Intravascular", "Temperature is measured within the vascular system.", "local", "at0028"),

  URINARY_BLADDER(
      "Urinary bladder", "Temperature is measured in the urinary bladder.", "local", "at0027"),

  NASOPHARYNX("Nasopharynx", "Temperature is measured within the nasopharynx.", "local", "at0026"),

  VAGINA("Vagina", "Temperature is measured within the vagina.", "local", "at0051"),

  RECTUM("Rectum", "Temperature measured within the rectum.", "local", "at0025"),

  SKIN("Skin", "Temperature is measured from exposed skin.", "local", "at0043"),

  MOUTH("Mouth", "Temperature is measured within the mouth.", "local", "at0022"),

  AXILLA(
      "Axilla",
      "Temperature is measured from the skin of the axilla with the arm positioned\n"
          + "                        down by the side.\n"
          + "                    ",
      "local",
      "at0024"),

  OESOPHAGUS("Oesophagus", "Temperatue is measured within the oesophagus.", "local", "at0054"),

  INGUINAL_SKIN_CREASE(
      "Inguinal skin crease",
      "Temperature is measured in the inguinal skin crease between the leg and\n"
          + "                        abdominal wall.\n"
          + "                    ",
      "local",
      "at0055"),

  FOREHEAD("Forehead", "Temperature is measured on the forehead.", "local", "at0061"),

  EAR_CANAL(
      "Ear canal",
      "Temperature is measured from within the external auditory canal.",
      "local",
      "at0023");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LocationOfMeasurementDefiningCode(
      String value, String description, String terminologyId, String code) {
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
