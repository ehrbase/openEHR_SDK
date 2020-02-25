package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.datatypes.CodePhrase;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-ADMIN_ENTRY.test_all_types.v1")
public class TestAllTypesAdminEntry {
    @Path("/language")
    private CodePhrase language;

    @Path("/data[at0001]/item[at0002]/value|magnitude")
    private Long count3Magnitude;

    public void setLanguage(CodePhrase language) {
        this.language = language;
    }

    public CodePhrase getLanguage() {
        return this.language;
    }

    public void setCount3Magnitude(Long count3Magnitude) {
        this.count3Magnitude = count3Magnitude;
    }

    public Long getCount3Magnitude() {
        return this.count3Magnitude;
    }
}
