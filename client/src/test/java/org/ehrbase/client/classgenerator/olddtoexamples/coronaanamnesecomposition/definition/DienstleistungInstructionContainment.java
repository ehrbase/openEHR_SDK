package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.olddtoexamples.shareddefinition.Language;

public class DienstleistungInstructionContainment extends Containment {
  public SelectAqlField<DienstleistungInstruction> DIENSTLEISTUNG_INSTRUCTION =
      new AqlFieldImp<DienstleistungInstruction>(
          DienstleistungInstruction.class,
          "",
          "DienstleistungInstruction",
          DienstleistungInstruction.class,
          this);

  public SelectAqlField<String> NARRATIVE_VALUE =
      new AqlFieldImp<String>(
          DienstleistungInstruction.class,
          "/narrative|value",
          "narrativeValue",
          String.class,
          this);

  public SelectAqlField<Language> LANGUAGE =
      new AqlFieldImp<Language>(
          DienstleistungInstruction.class, "/language", "language", Language.class, this);

  public SelectAqlField<Cluster> EMPFANGER =
      new AqlFieldImp<Cluster>(
          DienstleistungInstruction.class,
          "/protocol[at0008]/items[at0142]",
          "empfanger",
          Cluster.class,
          this);

  public ListSelectAqlField<Cluster> ERWEITERUNG =
      new ListAqlFieldImp<Cluster>(
          DienstleistungInstruction.class,
          "/protocol[at0008]/items[at0112]",
          "erweiterung",
          Cluster.class,
          this);

  public SelectAqlField<Cluster> EINSENDER =
      new AqlFieldImp<Cluster>(
          DienstleistungInstruction.class,
          "/protocol[at0008]/items[at0141]",
          "einsender",
          Cluster.class,
          this);

  public SelectAqlField<PartyProxy> SUBJECT =
      new AqlFieldImp<PartyProxy>(
          DienstleistungInstruction.class, "/subject", "subject", PartyProxy.class, this);

  public ListSelectAqlField<DienstleistungAktuelleAktivitatActivity> AKTUELLE_AKTIVITAT =
      new ListAqlFieldImp<DienstleistungAktuelleAktivitatActivity>(
          DienstleistungInstruction.class,
          "/activities[at0001]",
          "aktuelleAktivitat",
          DienstleistungAktuelleAktivitatActivity.class,
          this);

  public ListSelectAqlField<Cluster> VERTEILERLISTE =
      new ListAqlFieldImp<Cluster>(
          DienstleistungInstruction.class,
          "/protocol[at0008]/items[at0128]",
          "verteilerliste",
          Cluster.class,
          this);

  private DienstleistungInstructionContainment() {
    super("openEHR-EHR-INSTRUCTION.service_request.v1");
  }

  public static DienstleistungInstructionContainment getInstance() {
    return new DienstleistungInstructionContainment();
  }
}
