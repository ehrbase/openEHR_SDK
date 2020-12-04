package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Long;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-ADMIN_ENTRY.test_all_types.v1")
public class TestAllTypesAdminEntry {
  /**
   * Test all types/Test all types/section 2/Test all types/count 3
   */
  @Path("/data[at0001]/item[at0002]/value|magnitude")
  private Long count3Magnitude;

  /**
   * Test all types/Test all types/section 2/Test all types/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Test all types/Test all types/section 2/Test all types/language
   */
  @Path("/language")
  private Language language;

  public void setCount3Magnitude(Long count3Magnitude) {
     this.count3Magnitude = count3Magnitude;
  }

  public Long getCount3Magnitude() {
     return this.count3Magnitude ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }
}
