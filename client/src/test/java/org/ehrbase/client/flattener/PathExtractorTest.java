package org.ehrbase.client.flattener;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PathExtractorTest {

  @Test
  public void testPathExtractorWithAttribut() {
    PathExtractor cut = new PathExtractor("/context/end_time|value");
    assertThat(cut.getAttributeName()).isEqualTo("value");
    assertThat(cut.getChildName()).isEqualTo("end_time");
    assertThat(cut.getChildPath()).isEqualTo("/context/end_time");
    assertThat(cut.getParentPath()).isEqualTo("/context");
  }

  @Test
  public void testPathExtractorOnlyAttribut() {
    PathExtractor cut = new PathExtractor("|value");
    assertThat(cut.getAttributeName()).isEqualTo("value");
    assertThat(cut.getChildName()).isEqualTo("");
    assertThat(cut.getChildPath()).isEqualTo("");
    assertThat(cut.getParentPath()).isEqualTo("/");
  }

  @Test
  public void testPathExtractorWithNodeId() {
    PathExtractor cut =
        new PathExtractor(
            "/context/other_context[at0001]/items[at0006]/items[openEHR-EHR-CLUSTER.sample_device.v1]");
    assertThat(cut.getAttributeName()).isNull();
    assertThat(cut.getChildName()).isEqualTo("items");
    assertThat(cut.getChildPath())
        .isEqualTo(
            "/context/other_context[at0001]/items[at0006]/items[openEHR-EHR-CLUSTER.sample_device.v1]");
    assertThat(cut.getParentPath()).isEqualTo("/context/other_context[at0001]/items[at0006]");
  }

  @Test
  public void testPathExtractorWithName() {
    PathExtractor cut =
        new PathExtractor(
            "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']");
    assertThat(cut.getAttributeName()).isNull();
    assertThat(cut.getChildName()).isEqualTo("content");
    assertThat(cut.getChildPath())
        .isEqualTo("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']");
    assertThat(cut.getParentPath()).isEqualTo("/");
  }

  @Test
  public void testPathExtractor() {
    PathExtractor cut = new PathExtractor("/context/end_time");
    assertThat(cut.getAttributeName()).isNull();
    assertThat(cut.getChildName()).isEqualTo("end_time");
    assertThat(cut.getChildPath()).isEqualTo("/context/end_time");
    assertThat(cut.getParentPath()).isEqualTo("/context");
  }

  @Test
  public void testPathExtractorWithAttributeAndAtCode() {
    PathExtractor cut =
        new PathExtractor("/data[at0001]/events[at0002]/data[at0003]/items[at0004]|magnitude");
    assertThat(cut.getAttributeName()).isEqualTo("magnitude");
    assertThat(cut.getChildName()).isEqualTo("items");
    assertThat(cut.getChildPath())
        .isEqualTo("/data[at0001]/events[at0002]/data[at0003]/items[at0004]");
    assertThat(cut.getParentPath()).isEqualTo("/data[at0001]/events[at0002]/data[at0003]");
  }
}
