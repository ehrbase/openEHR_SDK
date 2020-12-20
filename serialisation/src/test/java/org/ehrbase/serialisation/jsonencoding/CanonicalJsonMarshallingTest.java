package org.ehrbase.serialisation.jsonencoding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import org.junit.Test;

public class CanonicalJsonMarshallingTest {

  @Test
  public void UnmarshalMultimedia() throws IOException {

    String value =
        new String(Files.readAllBytes(Paths.get("src/test/resources/sample_data/multimedia.json")));

    CanonicalJson cut = new CanonicalJson();
    DvMultimedia dvMultimedia = cut.unmarshal(value, DvMultimedia.class);

    assertNotNull(dvMultimedia);
  }

  @Test
  public void UnmarshalMultimediaElement() throws IOException {

    String value =
        new String(
            Files.readAllBytes(
                Paths.get("src/test/resources/sample_data/element_multimedia.json")));

    CanonicalJson cut = new CanonicalJson();
    Element element = cut.unmarshal(value, Element.class);

    assertNotNull(element);
  }

  @Test
  public void UnmarshalItemTree() throws IOException {

    String value =
        new String(
            Files.readAllBytes(
                Paths.get("src/test/resources/sample_data/item_tree_with_multimedia.json")));

    CanonicalJson cut = new CanonicalJson();
    ItemTree itemTree = cut.unmarshal(value, ItemTree.class);

    assertNotNull(itemTree);
  }

  @Test
  public void UnmarshalPartialDate() throws IOException {

    String value =
        new String(
            Files.readAllBytes(Paths.get("src/test/resources/sample_data/partialdvdate.json")));

    CanonicalJson cut = new CanonicalJson();
    DvDate dvDate = cut.unmarshal(value, DvDate.class);

    assertNotNull(dvDate);

    assertEquals("2020-08", dvDate.getValue().toString());
  }

  @Test
  public void MarshalDuration() {
    DvDuration duration = new DvDuration(Duration.ofDays(30L));
    CanonicalJson cut = new CanonicalJson();
    String actual = cut.marshal(duration);
    assertThat(actual)
        .isEqualToIgnoringWhitespace(
            "{" + "  \"_type\" : \"DV_DURATION\"," + "  \"value\" : \"PT720H\"" + "}");
  }

  @Test
  public void UnmarshalPartialDateTime() throws IOException {

    String value =
        new String(
            Files.readAllBytes(Paths.get("src/test/resources/sample_data/partialdvdatetime.json")));

    CanonicalJson cut = new CanonicalJson();
    DvDateTime dvDateTime = cut.unmarshal(value, DvDateTime.class);

    assertNotNull(dvDateTime);

    // NB. partial time (e.g. '10') is defaulted to '10:00' due to Java API handling of time values
    assertEquals("2020-08-01T10:00", dvDateTime.getValue().toString());
  }
}
