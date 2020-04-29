package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AddressClusterContainment extends Containment {
  public SelectAqlField<AddressCluster> ADDRESS_CLUSTER = new AqlFieldImp<AddressCluster>(AddressCluster.class, "", "AddressCluster", AddressCluster.class, this);

  public SelectAqlField<String> CITY_VALUE = new AqlFieldImp<String>(AddressCluster.class, "/items[at0012]/value|value", "cityValue", String.class, this);

  public SelectAqlField<String> COUNTRY_VALUE = new AqlFieldImp<String>(AddressCluster.class, "/items[at0015]/value|value", "countryValue", String.class, this);

  private AddressClusterContainment() {
    super("openEHR-EHR-CLUSTER.address_cc.v0");
  }

  public static AddressClusterContainment getInstance() {
    return new AddressClusterContainment();
  }
}
