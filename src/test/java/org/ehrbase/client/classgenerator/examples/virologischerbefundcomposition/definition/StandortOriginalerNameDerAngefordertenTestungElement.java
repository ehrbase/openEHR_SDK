package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import java.lang.String;

import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class StandortOriginalerNameDerAngefordertenTestungElement {
  @Path("/value|value")
  private String value;

  public void setValue(String value) {
     this.value = value;
  }

  public String getValue() {
     return this.value ;
  }

    @Path("/name")
    private DvText name;

    public DvText getName() {
        return name;
    }

    public void setName(DvText name) {
        this.name = name;
    }
}
