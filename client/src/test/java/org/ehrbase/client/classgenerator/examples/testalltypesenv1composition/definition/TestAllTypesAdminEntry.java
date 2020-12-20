package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-ADMIN_ENTRY.test_all_types.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.822497300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class TestAllTypesAdminEntry implements EntryEntity {
  /** Path: Test all types/Test all types/section 2/Test all types/count 3 Description: * */
  @Path("/data[at0001]/item[at0002]/value|magnitude")
  private Long count3Magnitude;

  /** Path: Test all types/Test all types/section 2/Test all types/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Test all types/Test all types/section 2/Test all types/language */
  @Path("/language")
  private Language language;

  /** Path: Test all types/Test all types/section 2/Test all types/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setCount3Magnitude(Long count3Magnitude) {
    this.count3Magnitude = count3Magnitude;
  }

  public Long getCount3Magnitude() {
    return this.count3Magnitude;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
