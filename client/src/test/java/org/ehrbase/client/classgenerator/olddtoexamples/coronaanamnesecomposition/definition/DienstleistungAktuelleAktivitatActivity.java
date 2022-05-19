/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemStructure;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DienstleistungAktuelleAktivitatActivity {
    @Path("/description[at0009]/items[at0149]")
    private List<Cluster> unterstutzendeInformationen;

    @Path("/description[at0009]/items[at0116]")
    private List<Cluster> anforderungenAnPatienten;

    @Path("/description[at0009]/items[at0121]/value|value")
    private String nameDerDienstleistungValue;

    @Path("/description[at0009]/items[at0132]")
    private List<Cluster> spezifischeDetails;

    @Path("/description[at0009]/items[at0151]")
    private List<Cluster> komplexeZeitplanung;

    @Path("/description[at0009]/items[at0062]")
    private List<DienstleistungGrundFurDieAnforderungElement> grundFurDieAnforderung;

    @Path("/description")
    private ItemStructure description;

    public void setUnterstutzendeInformationen(List<Cluster> unterstutzendeInformationen) {
        this.unterstutzendeInformationen = unterstutzendeInformationen;
    }

    public List<Cluster> getUnterstutzendeInformationen() {
        return this.unterstutzendeInformationen;
    }

    public void setAnforderungenAnPatienten(List<Cluster> anforderungenAnPatienten) {
        this.anforderungenAnPatienten = anforderungenAnPatienten;
    }

    public List<Cluster> getAnforderungenAnPatienten() {
        return this.anforderungenAnPatienten;
    }

    public void setNameDerDienstleistungValue(String nameDerDienstleistungValue) {
        this.nameDerDienstleistungValue = nameDerDienstleistungValue;
    }

    public String getNameDerDienstleistungValue() {
        return this.nameDerDienstleistungValue;
    }

    public void setSpezifischeDetails(List<Cluster> spezifischeDetails) {
        this.spezifischeDetails = spezifischeDetails;
    }

    public List<Cluster> getSpezifischeDetails() {
        return this.spezifischeDetails;
    }

    public void setKomplexeZeitplanung(List<Cluster> komplexeZeitplanung) {
        this.komplexeZeitplanung = komplexeZeitplanung;
    }

    public List<Cluster> getKomplexeZeitplanung() {
        return this.komplexeZeitplanung;
    }

    public void setGrundFurDieAnforderung(List<DienstleistungGrundFurDieAnforderungElement> grundFurDieAnforderung) {
        this.grundFurDieAnforderung = grundFurDieAnforderung;
    }

    public List<DienstleistungGrundFurDieAnforderungElement> getGrundFurDieAnforderung() {
        return this.grundFurDieAnforderung;
    }

    public void setDescription(ItemStructure description) {
        this.description = description;
    }

    public ItemStructure getDescription() {
        return this.description;
    }
}
