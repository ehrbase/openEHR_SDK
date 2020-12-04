package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class StoryHistorySoftSignsElement {
  /**
   * open_eREACT-Care/Situation/Story/History/Any event/Soft signs
   */
  @Path("/value|value")
  private String value;

  public void setValue(String value) {
     this.value = value;
  }

  public String getValue() {
     return this.value ;
  }
}
