package org.ehrbase.client.classgenerator.examples.testalltypesenv1;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.ehrbase.client.annotations.*;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SettingDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1.definition.ContextCodedTextDefiningcode;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1.definition.TestAllTypes;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1.definition.TestAllTypes2;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1.definition.TestAllTypes7;
import org.ehrbase.client.openehrclient.VersionUid;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.test_all_types.v1")
@Template("test_all_types.en.v1")
public class TestAllTypesEnV1 {
    @Id
    private VersionUid versionUid;

    @Path("/content[openEHR-EHR-EVALUATION.test_all_types.v1]")
    private List<TestAllTypes> testAllTypes;

    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    @Path("/content[openEHR-EHR-SECTION.test_all_types.v1]")
    private List<TestAllTypes2> testAllTypes2;

    @Path("/language")
    private Language language;

    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    @Path("/composer|external_ref")
    private PartyRef composerExternalref;

    @Path("/context/setting|defining_code")
    private SettingDefiningcode settingDefiningcode;

    @Path("/territory")
    private Territory territory;

    @Path("/context/other_context[at0004]/item[at0005]/value|defining_code")
    private ContextCodedTextDefiningcode contextCodedTextDefiningcode;

    @Path("/content[openEHR-EHR-OBSERVATION.test_all_types.v1]")
    private List<TestAllTypes7> testAllTypes3;

    @Path("/context/location")
    private String location;

    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    public VersionUid getVersionUid() {
        return this.versionUid;
    }

    public void setVersionUid(VersionUid versionUid) {
        this.versionUid = versionUid;
    }

    public void setTestAllTypes(List<TestAllTypes> testAllTypes) {
        this.testAllTypes = testAllTypes;
    }

    public List<TestAllTypes> getTestAllTypes() {
        return this.testAllTypes;
    }

    public void setEndTimeValue(TemporalAccessor endTimeValue) {
        this.endTimeValue = endTimeValue;
    }

    public TemporalAccessor getEndTimeValue() {
        return this.endTimeValue;
    }

    public void setTestAllTypes2(List<TestAllTypes2> testAllTypes2) {
        this.testAllTypes2 = testAllTypes2;
    }

    public List<TestAllTypes2> getTestAllTypes2() {
        return this.testAllTypes2;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setHealthCareFacility(PartyIdentified healthCareFacility) {
        this.healthCareFacility = healthCareFacility;
    }

    public PartyIdentified getHealthCareFacility() {
        return this.healthCareFacility;
    }

    public void setComposerExternalref(PartyRef composerExternalref) {
        this.composerExternalref = composerExternalref;
    }

    public PartyRef getComposerExternalref() {
        return this.composerExternalref;
    }

    public void setSettingDefiningcode(SettingDefiningcode settingDefiningcode) {
        this.settingDefiningcode = settingDefiningcode;
    }

    public SettingDefiningcode getSettingDefiningcode() {
        return this.settingDefiningcode;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public Territory getTerritory() {
        return this.territory;
    }

    public void setContextCodedTextDefiningcode(
            ContextCodedTextDefiningcode contextCodedTextDefiningcode) {
        this.contextCodedTextDefiningcode = contextCodedTextDefiningcode;
    }

    public ContextCodedTextDefiningcode getContextCodedTextDefiningcode() {
        return this.contextCodedTextDefiningcode;
    }

    public void setTestAllTypes3(List<TestAllTypes7> testAllTypes3) {
        this.testAllTypes3 = testAllTypes3;
    }

    public List<TestAllTypes7> getTestAllTypes3() {
        return this.testAllTypes3;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setStartTimeValue(TemporalAccessor startTimeValue) {
        this.startTimeValue = startTimeValue;
    }

    public TemporalAccessor getStartTimeValue() {
        return this.startTimeValue;
    }
}
