package org.ehrbase.response.openehr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.changecontrol.OriginalVersion;
import com.nedap.archie.rm.generic.AuditDetails;
import com.nedap.archie.rm.support.identification.HierObjectId;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(
        name = "CONTRIBUTION",
        propOrder = {"uid", "versions", "audit"}
)
@XmlAccessorType(XmlAccessType.FIELD)
public class ContributionCreateDto extends RMObject {

    private HierObjectId uid;

    @JsonProperty(value = "versions")
    @JacksonXmlElementWrapper(localName = "versions")
    @JacksonXmlProperty(localName = "version")
    private List<OriginalVersion<?>> versions = new ArrayList();

    private AuditDetails audit;

    public ContributionCreateDto() {}

    public ContributionCreateDto(HierObjectId uid, List<OriginalVersion<?>> versions, AuditDetails audit) {
        this.uid = uid;
        this.versions = versions;
        this.audit = audit;
    }

    public HierObjectId getUid() {
        return this.uid;
    }

    public void setUid(HierObjectId uid) {
        this.uid = uid;
    }

    public List<OriginalVersion<?>> getVersions() {
        return this.versions;
    }

    public void setVersions(List<OriginalVersion<?>> versions) {
        this.versions = versions;
    }

    public AuditDetails getAudit() {
        return this.audit;
    }

    public void setAudit(AuditDetails audit) {
        this.audit = audit;
    }
}
