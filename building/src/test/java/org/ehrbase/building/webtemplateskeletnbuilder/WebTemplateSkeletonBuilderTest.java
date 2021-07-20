package org.ehrbase.building.webtemplateskeletnbuilder;

import com.nedap.archie.rm.composition.Composition;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class WebTemplateSkeletonBuilderTest {

    @Test
    public void build() throws XmlException, IOException {

        org.openehr.schemas.v1.TemplateDocument document = org.openehr.schemas.v1.TemplateDocument.Factory.parse(OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream());
        OPERATIONALTEMPLATE operationaltemplate = document.getTemplate();

        WebTemplate webTemplate = new OPTParser(operationaltemplate).parse();


        Composition generate =  WebTemplateSkeletonBuilder.build(webTemplate,true);
        assertThat(generate).isNotNull();
        assertThat(generate.itemAtPath("/composer")).isNotNull();
        assertThat(generate.itemAtPath("/context/end_time")).isNotNull();
        assertThat(generate.itemAtPath("/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value")).isNotNull();

    }
}