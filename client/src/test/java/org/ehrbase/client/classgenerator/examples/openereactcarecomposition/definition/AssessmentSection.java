package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class AssessmentSection {
  /**
   * open_eREACT-Care/Assessment/DENWIS
   */
  @Path("/items[openEHR-EHR-OBSERVATION.denwis.v0]")
  private DenwisObservation denwis;

  /**
   * open_eREACT-Care/Assessment/Sepsis
   */
  @Path("/items[openEHR-EHR-SECTION.adhoc.v1 and name/value='Sepsis']")
  private SepsisSection sepsis;

  /**
   * open_eREACT-Care/Assessment/Covid
   */
  @Path("/items[openEHR-EHR-SECTION.adhoc.v1 and name/value='Covid']")
  private CovidSection covid;

  /**
   * open_eREACT-Care/Assessment/NEWS2
   */
  @Path("/items[openEHR-EHR-SECTION.adhoc.v1 and name/value='NEWS2']")
  private News2Section news2;

  public void setDenwis(DenwisObservation denwis) {
     this.denwis = denwis;
  }

  public DenwisObservation getDenwis() {
     return this.denwis ;
  }

  public void setSepsis(SepsisSection sepsis) {
     this.sepsis = sepsis;
  }

  public SepsisSection getSepsis() {
     return this.sepsis ;
  }

  public void setCovid(CovidSection covid) {
     this.covid = covid;
  }

  public CovidSection getCovid() {
     return this.covid ;
  }

  public void setNews2(News2Section news2) {
     this.news2 = news2;
  }

  public News2Section getNews2() {
     return this.news2 ;
  }
}
