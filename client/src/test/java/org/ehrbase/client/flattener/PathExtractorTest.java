package org.ehrbase.client.flattener;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        PathExtractor cut = new PathExtractor("/context/other_context[at0001]/items[at0006]/items[openEHR-EHR-CLUSTER.sample_device.v1]");
        assertThat(cut.getAttributeName()).isNull();
        assertThat(cut.getChildName()).isEqualTo("items");
        assertThat(cut.getChildPath()).isEqualTo("/context/other_context[at0001]/items[at0006]/items[openEHR-EHR-CLUSTER.sample_device.v1]");
        assertThat(cut.getParentPath()).isEqualTo("/context/other_context[at0001]/items[at0006]");
    }

    @Test
    public void testPathExtractor() {
        PathExtractor cut = new PathExtractor("/context/end_time");
        assertThat(cut.getAttributeName()).isNull();
        assertThat(cut.getChildName()).isEqualTo("end_time");
        assertThat(cut.getChildPath()).isEqualTo("/context/end_time");
        assertThat(cut.getParentPath()).isEqualTo("/context");
    }

}