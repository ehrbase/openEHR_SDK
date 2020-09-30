package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-EVALUATION.clinical_synopsis.v1")
public class CovidNotesEvaluation {
    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/items[at0002]/value|value")
    private String synopsisValue;

    @Path("/language")
    private Language language;

    @Path("/protocol[at0003]/items[at0004]")
    private List<Cluster> extension;

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setSynopsisValue(String synopsisValue) {
        this.synopsisValue = synopsisValue;
    }

    public String getSynopsisValue() {
        return this.synopsisValue;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setExtension(List<Cluster> extension) {
        this.extension = extension;
    }

    public List<Cluster> getExtension() {
        return this.extension;
    }
}
