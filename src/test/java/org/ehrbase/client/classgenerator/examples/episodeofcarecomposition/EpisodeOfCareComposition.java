package org.ehrbase.client.classgenerator.examples.episodeofcarecomposition;

import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.*;
import org.ehrbase.client.classgenerator.examples.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;
import org.ehrbase.client.classgenerator.examples.shareddefinition.CategoryDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.versorgungsfall.v0")
@Template("EpisodeOfCare")
public class EpisodeOfCareComposition {
    @Id
    private VersionUid versionUid;

    @Path("/composer")
    private PartyProxy composer;

    @Path("/content[openEHR-EHR-ADMIN_ENTRY.episodeofcare.v0]")
    private List<EpisodeofcareAdminEntry> episodeofcare;

    @Path("/territory")
    private Territory territory;

    @Path("/name|value")
    private String value;

    @Path("/language")
    private Language language;

    @Path("/category|defining_code")
    private CategoryDefiningcode categoryDefiningcode;

    public VersionUid getVersionUid() {
        return this.versionUid;
    }

    public void setVersionUid(VersionUid versionUid) {
        this.versionUid = versionUid;
    }

    public void setComposer(PartyProxy composer) {
        this.composer = composer;
    }

    public PartyProxy getComposer() {
        return this.composer;
    }

    public void setEpisodeofcare(List<EpisodeofcareAdminEntry> episodeofcare) {
        this.episodeofcare = episodeofcare;
    }

    public List<EpisodeofcareAdminEntry> getEpisodeofcare() {
        return this.episodeofcare;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public Territory getTerritory() {
        return this.territory;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setCategoryDefiningcode(CategoryDefiningcode categoryDefiningcode) {
        this.categoryDefiningcode = categoryDefiningcode;
    }

    public CategoryDefiningcode getCategoryDefiningcode() {
        return this.categoryDefiningcode;
    }
}
