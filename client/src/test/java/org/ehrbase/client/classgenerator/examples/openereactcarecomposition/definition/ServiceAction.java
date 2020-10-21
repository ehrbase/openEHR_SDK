package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.TransitionDefiningcode;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-ACTION.service.v0")
public class ServiceAction {
    @Path("/time|value")
    private TemporalAccessor timeValue;

    @Path("/protocol[at0015]/items[at0017]")
    private List<Cluster> requestor;

    @Path("/ism_transition[at0002]/careflow_step|defining_code")
    private ServicePlannedDefiningcode servicePlannedDefiningcode;

    @Path("/protocol[at0015]/items[at0019]")
    private List<Cluster> receiver;

    @Path("/language")
    private Language language;

    @Path("/ism_transition[at0002]/current_state|defining_code")
    private ServicePlannedDefiningcode2 servicePlannedDefiningcodeCurrentState;

    @Path("/ism_transition[at0002]/transition|defining_code")
    private TransitionDefiningcode transitionDefiningcode;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/description[at0001]/items[at0011]/value|defining_code")
    private ServiceNameDefiningcode serviceNameDefiningcode;

    @Path("/description[at0001]/items[at0027]")
    private List<Cluster> serviceDetail;

    @Path("/description[at0001]/items[at0029]")
    private List<Cluster> multimedia;

    @Path("/description[at0001]/items[at0013]/value|value")
    private String descriptionValue;

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setRequestor(List<Cluster> requestor) {
        this.requestor = requestor;
    }

    public List<Cluster> getRequestor() {
        return this.requestor;
    }

    public void setServicePlannedDefiningcode(ServicePlannedDefiningcode servicePlannedDefiningcode) {
        this.servicePlannedDefiningcode = servicePlannedDefiningcode;
    }

    public ServicePlannedDefiningcode getServicePlannedDefiningcode() {
        return this.servicePlannedDefiningcode;
    }

    public void setReceiver(List<Cluster> receiver) {
        this.receiver = receiver;
    }

    public List<Cluster> getReceiver() {
        return this.receiver;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setServicePlannedDefiningcodeCurrentState(
            ServicePlannedDefiningcode2 servicePlannedDefiningcodeCurrentState) {
        this.servicePlannedDefiningcodeCurrentState = servicePlannedDefiningcodeCurrentState;
    }

    public ServicePlannedDefiningcode2 getServicePlannedDefiningcodeCurrentState() {
        return this.servicePlannedDefiningcodeCurrentState;
    }

    public void setTransitionDefiningcode(TransitionDefiningcode transitionDefiningcode) {
        this.transitionDefiningcode = transitionDefiningcode;
    }

    public TransitionDefiningcode getTransitionDefiningcode() {
        return this.transitionDefiningcode;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setServiceNameDefiningcode(ServiceNameDefiningcode serviceNameDefiningcode) {
        this.serviceNameDefiningcode = serviceNameDefiningcode;
    }

    public ServiceNameDefiningcode getServiceNameDefiningcode() {
        return this.serviceNameDefiningcode;
    }

    public void setServiceDetail(List<Cluster> serviceDetail) {
        this.serviceDetail = serviceDetail;
    }

    public List<Cluster> getServiceDetail() {
        return this.serviceDetail;
    }

    public void setMultimedia(List<Cluster> multimedia) {
        this.multimedia = multimedia;
    }

    public List<Cluster> getMultimedia() {
        return this.multimedia;
    }

    public void setDescriptionValue(String descriptionValue) {
        this.descriptionValue = descriptionValue;
    }

    public String getDescriptionValue() {
        return this.descriptionValue;
    }
}
