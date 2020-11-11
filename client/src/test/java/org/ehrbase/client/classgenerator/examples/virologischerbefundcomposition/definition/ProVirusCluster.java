package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
public class ProVirusCluster {
    @Path("/items[at0024]/value|value")
    private String virusValue;

    @Path("/items[at0003]/value|value")
    private String kommentarValue;

    @Path("/items[at0001]/value")
    @Choice
    private ProVirusNachweisChoice nachweis;

    @Path("/items[at0026]/value")
    private DvIdentifier zugehorigeLaborprobe;

    @Path("/items[at0027]/value|magnitude")
    private Long analyseergebnisReihenfolgeMagnitude;

    @Path("/items[at0014]")
    private List<Cluster> analyseergebnisDetails;

    public void setVirusValue(String virusValue) {
        this.virusValue = virusValue;
    }

    public String getVirusValue() {
        return this.virusValue;
    }

    public void setKommentarValue(String kommentarValue) {
        this.kommentarValue = kommentarValue;
    }

    public String getKommentarValue() {
        return this.kommentarValue;
    }

    public void setNachweis(ProVirusNachweisChoice nachweis) {
        this.nachweis = nachweis;
    }

    public ProVirusNachweisChoice getNachweis() {
        return this.nachweis;
    }

    public void setZugehorigeLaborprobe(DvIdentifier zugehorigeLaborprobe) {
        this.zugehorigeLaborprobe = zugehorigeLaborprobe;
    }

    public DvIdentifier getZugehorigeLaborprobe() {
        return this.zugehorigeLaborprobe;
    }

    public void setAnalyseergebnisReihenfolgeMagnitude(Long analyseergebnisReihenfolgeMagnitude) {
        this.analyseergebnisReihenfolgeMagnitude = analyseergebnisReihenfolgeMagnitude;
    }

    public Long getAnalyseergebnisReihenfolgeMagnitude() {
        return this.analyseergebnisReihenfolgeMagnitude;
    }

    public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
        this.analyseergebnisDetails = analyseergebnisDetails;
    }

    public List<Cluster> getAnalyseergebnisDetails() {
        return this.analyseergebnisDetails;
    }
}
