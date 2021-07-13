package org.ehrbase.aql.util;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AqlUtilTest {

  @Test
  public void removeParameter() {

    String aql =
        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where  e/ehr_id/value = $ehrid";

    String actual = AqlUtil.removeParameter(aql, "ehrid");

    assertThat(actual)
        .isEqualTo(
            "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]");
  }

  @Test
  public void removeParameterWithOr() {

    String aql =
        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where  e/ehr_id/value = $ehrid or e/ehr_id/value = $ehrid2";

    String actual = AqlUtil.removeParameter(aql, "ehrid");

    assertThat(actual)
        .isEqualTo(
            "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where e/ehr_id/value = $ehrid2");
  }

  @Test
  public void removeParameterWithTripleOr() {

    String aql =
        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where  e/ehr_id/value = $ehrid or e/ehr_id/value = $ehrid2 or e/ehr_id/value = $ehrid3";

    String actual = AqlUtil.removeParameter(aql, "ehrid");

    assertThat(actual)
        .isEqualTo(
            "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where (e/ehr_id/value = $ehrid2 or e/ehr_id/value = $ehrid3)");
  }

  @Test
  public void removeParameterMultiple() {

    String aql =
        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where e/ehr_id/value = $ehrid or e/ehr_id/value = $ehrid or e/ehr_id/value = $ehrid";

    String actual = AqlUtil.removeParameter(aql, "ehrid");

    assertThat(actual)
        .isEqualTo(
            "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]");
  }

  @Test
  public void removeParameterWithMixedOrAnd() {

    String aql =
        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where  (e/ehr_id/value = $ehrid or (e/ehr_id/value = $ehrid2 and e/ehr_id/value = $ehrid3))";

    String actual = AqlUtil.removeParameter(aql, "ehrid");

    assertThat(actual)
        .isEqualTo(
            "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where (e/ehr_id/value = $ehrid2 and e/ehr_id/value = $ehrid3)");
  }

  @Test
  public void removeParameterWithMixedAndOr() {

    String aql =
        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where  (e/ehr_id/value = $ehrid or (e/ehr_id/value = $ehrid2 and e/ehr_id/value = $ehrid3))";

    String actual = AqlUtil.removeParameter(aql, "ehrid2");

    assertThat(actual)
        .isEqualTo(
            "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where (e/ehr_id/value = $ehrid or e/ehr_id/value = $ehrid3)");
  }

  @Test
  public void removeParameterParameterNotfound() {

    String aql =
        "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where (e/ehr_id/value = $ehrid or (e/ehr_id/value = $ehrid2 and e/ehr_id/value = $ehrid3))";

    String actual = AqlUtil.removeParameter(aql, "ehrid9999");

    assertThat(actual).isEqualTo(aql);
  }

  @Test
  public void removeParameterNoWhere() {

    String aql =
        "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]";

    String actual = AqlUtil.removeParameter(aql, "ehrid9999");

    assertThat(actual).isEqualTo(aql);
  }
}
