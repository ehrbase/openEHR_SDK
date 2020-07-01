package org.ehrbase.client.flattener;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.DvCount;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemExtractorTest {

    @Test
    public void testItemExtractor() {
        Composition composition = new Composition();
        EventContext eventContext = new EventContext();
        composition.setContext(eventContext);
        LocalDateTime localDateTime = LocalDateTime.of(2014, Month.APRIL, 20, 13, 40);
        DvDateTime endTime = new DvDateTime(localDateTime);
        composition.getContext().setEndTime(endTime);
        ItemExtractor cut = new ItemExtractor(composition, "/context/end_time|value");

        assertThat(cut.getChildName()).isEqualTo("value");
        assertThat(cut.getChild()).isEqualTo(localDateTime);
        assertThat(cut.getParent()).isEqualTo(endTime);

    }

    @Test
    public void testItemExtractorWithName() {
        Composition composition = new Composition();

        Observation observation1 = new Observation();
        observation1.setName(new DvText("Name1"));
        observation1.setArchetypeNodeId("openEHR-EHR-OBSERVATION.story.v1");

        Observation observation2 = new Observation();
        observation2.setName(new DvText("Name2"));
        observation2.setArchetypeNodeId("openEHR-EHR-OBSERVATION.story.v1");

        composition.setContent(List.of(observation1, observation2));


        ItemExtractor cut = new ItemExtractor(composition, "/content[openEHR-EHR-OBSERVATION.story.v1 and name/value='Name2']");

        assertThat(cut.getChild()).isNotNull();

        Observation actual = (Observation) cut.getChild();
        assertThat(actual.getNameAsString()).isEqualTo("Name2");


    }

    @Test
    public void testItemExtractorWithNameNotFound() {
        Composition composition = new Composition();

        Observation observation1 = new Observation();
        observation1.setName(new DvText("Name1"));
        observation1.setArchetypeNodeId("openEHR-EHR-OBSERVATION.story.v1");

        Observation observation2 = new Observation();
        observation2.setName(new DvText("Name2"));
        observation2.setArchetypeNodeId("openEHR-EHR-OBSERVATION.story.v1");

        composition.setContent(List.of(observation1, observation2));


        ItemExtractor cut = new ItemExtractor(composition, "/content[openEHR-EHR-OBSERVATION.story.v1 and name/value='Name3']");

        assertThat(cut.getChild()).isNull();


    }

    @Test
    public void testItemExtractorEmpty() {
        Composition composition = new Composition();

        ItemExtractor cut = new ItemExtractor(composition, "/context/end_time|value");

        assertThat(cut.getChildName()).isEqualTo("end_time");
        assertThat(cut.getChild()).isNull();
        assertThat(cut.getParent()).isNull();

    }

    @Test
    public void testItemExtractorOnlyAttribut() {
        DvCount dvCount = new DvCount();
        dvCount.setMagnitude(100L);
        ItemExtractor cut = new ItemExtractor(dvCount, "|magnitude");

        assertThat(cut.getChildName()).isEqualTo("magnitude");
        assertThat(cut.getChild()).isEqualTo(100L);
        assertThat(cut.getParent()).isEqualTo(dvCount);

    }

    @Test
    public void testItemExtractorWithoutAttribut() {
        Composition composition = new Composition();
        EventContext eventContext = new EventContext();
        composition.setContext(eventContext);
        LocalDateTime localDateTime = LocalDateTime.of(2014, Month.APRIL, 20, 13, 40);
        DvDateTime endTime = new DvDateTime(localDateTime);
        composition.getContext().setEndTime(endTime);
        ItemExtractor cut = new ItemExtractor(composition, "/context/end_time");

        assertThat(cut.getChildName()).isEqualTo("end_time");
        assertThat(cut.getChild()).isEqualTo(endTime);
        assertThat(cut.getParent()).isEqualTo(eventContext);

    }

    @Test
    public void testItemExtractorElement() {
        Observation observation = new Observation();
        ItemTree itemTree = new ItemTree();
        observation.setProtocol(itemTree);
        Element item = new Element();
        DvCodedText value = new DvCodedText();
        item.setValue(value);
        itemTree.addItem(item);
        ItemExtractor cut = new ItemExtractor(observation, "/protocol[at0020]/items");

        assertThat(cut.getChildName()).isEqualTo("items");
        assertThat(cut.getChild()).isEqualTo(value);
        assertThat(cut.getParent()).isEqualTo(itemTree);
    }


}