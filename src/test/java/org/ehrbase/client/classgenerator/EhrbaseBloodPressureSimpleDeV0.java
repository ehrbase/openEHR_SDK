package org.ehrbase.client.classgenerator;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.sample_encounter.v1")
@Template("ehrbase_blood_pressure_simple.de.v0")
public class EhrbaseBloodPressureSimpleDeV0 {
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

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

  @Path("/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]")
  private List<BloodPressureTrainingSample> bloodPressureTrainingSample;

  @Path("/context/location")
  private String location;

  @Path("/context/other_context[at0001]/items[at0006]/items[openEHR-EHR-CLUSTER.sample_device.v1]")
  private List<DeviceDetailsTrainingSample> deviceDetailsTrainingSample;

  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
    this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
    return this.endTimeValue;
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

  public void setBloodPressureTrainingSample(
          List<BloodPressureTrainingSample> bloodPressureTrainingSample) {
    this.bloodPressureTrainingSample = bloodPressureTrainingSample;
  }

  public List<BloodPressureTrainingSample> getBloodPressureTrainingSample() {
    return this.bloodPressureTrainingSample;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return this.location;
  }

  public void setDeviceDetailsTrainingSample(
          List<DeviceDetailsTrainingSample> deviceDetailsTrainingSample) {
    this.deviceDetailsTrainingSample = deviceDetailsTrainingSample;
  }

  public List<DeviceDetailsTrainingSample> getDeviceDetailsTrainingSample() {
    return this.deviceDetailsTrainingSample;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
    this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
    return this.startTimeValue;
  }

  public enum Language implements EnumValueSet {
    FR("fr", "fr", "ISO_639-1", "fr"),

    ARAE("ar-ae", "ar-ae", "ISO_639-1", "ar-ae"),

    ARSY("ar-sy", "ar-sy", "ISO_639-1", "ar-sy"),

    AA("aa", "aa", "ISO_639-1", "aa"),

    HU("hu", "hu", "ISO_639-1", "hu"),

    ENIE("en-ie", "en-ie", "ISO_639-1", "en-ie"),

    SR("sr", "sr", "ISO_639-1", "sr"),

    AF("af", "af", "ISO_639-1", "af"),

    ARIQ("ar-iq", "ar-iq", "ISO_639-1", "ar-iq"),

    SL("sl", "sl", "ISO_639-1", "sl"),

    ID("id", "id", "ISO_639-1", "id"),

    ROMO("ro-mo", "ro-mo", "ISO_639-1", "ro-mo"),

    ESHN("es-hn", "es-hn", "ISO_639-1", "es-hn"),

    PTBR("pt-br", "pt-br", "ISO_639-1", "pt-br"),

    ZHHK("zh-hk", "zh-hk", "ISO_639-1", "zh-hk"),

    ENAU("en-au", "en-au", "ISO_639-1", "en-au"),

    ENNZ("en-nz", "en-nz", "ISO_639-1", "en-nz"),

    CYAR("cy-ar", "cy-ar", "ISO_639-1", "cy-ar"),

    ARSA("ar-sa", "ar-sa", "ISO_639-1", "ar-sa"),

    ARLY("ar-ly", "ar-ly", "ISO_639-1", "ar-ly"),

    FRBE("fr-be", "fr-be", "ISO_639-1", "fr-be"),

    AREG("ar-eg", "ar-eg", "ISO_639-1", "ar-eg"),

    CS("cs", "cs", "ISO_639-1", "cs"),

    RUMO("ru-mo", "ru-mo", "ISO_639-1", "ru-mo"),

    ESEC("es-ec", "es-ec", "ISO_639-1", "es-ec"),

    IS("is", "is", "ISO_639-1", "is"),

    ARTN("ar-tn", "ar-tn", "ISO_639-1", "ar-tn"),

    TN("tn", "tn", "ISO_639-1", "tn"),

    ZHCN("zh-cn", "zh-cn", "ISO_639-1", "zh-cn"),

    BE("be", "be", "ISO_639-1", "be"),

    NB("nb", "nb", "ISO_639-1", "nb"),

    ARBH("ar-bh", "ar-bh", "ISO_639-1", "ar-bh"),

    NL("nl", "nl", "ISO_639-1", "nl"),

    ENJM("en-jm", "en-jm", "ISO_639-1", "en-jm"),

    ESCR("es-cr", "es-cr", "ISO_639-1", "es-cr"),

    SZ("sz", "sz", "ISO_639-1", "sz"),

    GDIE("gd-ie", "gd-ie", "ISO_639-1", "gd-ie"),

    FRLU("fr-lu", "fr-lu", "ISO_639-1", "fr-lu"),

    ARMA("ar-ma", "ar-ma", "ISO_639-1", "ar-ma"),

    NLBE("nl-be", "nl-be", "ISO_639-1", "nl-be"),

    HI("hi", "hi", "ISO_639-1", "hi"),

    ZH("zh", "zh", "ISO_639-1", "zh"),

    TH("th", "th", "ISO_639-1", "th"),

    JI("ji", "ji", "ISO_639-1", "ji"),

    ESPY("es-py", "es-py", "ISO_639-1", "es-py"),

    RU("ru", "ru", "ISO_639-1", "ru"),

    ARYE("ar-ye", "ar-ye", "ISO_639-1", "ar-ye"),

    ESVE("es-ve", "es-ve", "ISO_639-1", "es-ve"),

    DE("de", "de", "ISO_639-1", "de"),

    ESBO("es-bo", "es-bo", "ISO_639-1", "es-bo"),

    DELU("de-lu", "de-lu", "ISO_639-1", "de-lu"),

    SVFI("sv-fi", "sv-fi", "ISO_639-1", "sv-fi"),

    SB("sb", "sb", "ISO_639-1", "sb"),

    ARQA("ar-qa", "ar-qa", "ISO_639-1", "ar-qa"),

    ARDZ("ar-dz", "ar-dz", "ISO_639-1", "ar-dz"),

    ESPR("es-pr", "es-pr", "ISO_639-1", "es-pr"),

    RM("rm", "rm", "ISO_639-1", "rm"),

    ARKW("ar-kw", "ar-kw", "ISO_639-1", "ar-kw"),

    ESMX("es-mx", "es-mx", "ISO_639-1", "es-mx"),

    ESPA("es-pa", "es-pa", "ISO_639-1", "es-pa"),

    VE("ve", "ve", "ISO_639-1", "ve"),

    KM("km", "km", "ISO_639-1", "km"),

    ET("et", "et", "ISO_639-1", "et"),

    ITCH("it-ch", "it-ch", "ISO_639-1", "it-ch"),

    ZHTW("zh-tw", "zh-tw", "ISO_639-1", "zh-tw"),

    DA("da", "da", "ISO_639-1", "da"),

    IT("it", "it", "ISO_639-1", "it"),

    NN("nn", "nn", "ISO_639-1", "nn"),

    ESNI("es-ni", "es-ni", "ISO_639-1", "es-ni"),

    FA("fa", "fa", "ISO_639-1", "fa"),

    EL("el", "el", "ISO_639-1", "el"),

    ARLB("ar-lb", "ar-lb", "ISO_639-1", "ar-lb"),

    HE("he", "he", "ISO_639-1", "he"),

    ESSV("es-sv", "es-sv", "ISO_639-1", "es-sv"),

    CY("cy", "cy", "ISO_639-1", "cy"),

    ENBZ("en-bz", "en-bz", "ISO_639-1", "en-bz"),

    EU("eu", "eu", "ISO_639-1", "eu"),

    ENCA("en-ca", "en-ca", "ISO_639-1", "en-ca"),

    AZ("az", "az", "ISO_639-1", "az"),

    SK("sk", "sk", "ISO_639-1", "sk"),

    ESCL("es-cl", "es-cl", "ISO_639-1", "es-cl"),

    HR("hr", "hr", "ISO_639-1", "hr"),

    ENZA("en-za", "en-za", "ISO_639-1", "en-za"),

    SQ("sq", "sq", "ISO_639-1", "sq"),

    DELI("de-li", "de-li", "ISO_639-1", "de-li"),

    MK("mk", "mk", "ISO_639-1", "mk"),

    ESGT("es-gt", "es-gt", "ISO_639-1", "es-gt"),

    PT("pt", "pt", "ISO_639-1", "pt"),

    SI("si", "si", "ISO_639-1", "si"),

    DEAT("de-at", "de-at", "ISO_639-1", "de-at"),

    XH("xh", "xh", "ISO_639-1", "xh"),

    LT("lt", "lt", "ISO_639-1", "lt"),

    ESDO("es-do", "es-do", "ISO_639-1", "es-do"),

    DECH("de-ch", "de-ch", "ISO_639-1", "de-ch"),

    TR("tr", "tr", "ISO_639-1", "tr"),

    ARJO("ar-jo", "ar-jo", "ISO_639-1", "ar-jo"),

    PTPT("pt-pt", "pt-pt", "ISO_639-1", "pt-pt"),

    JA("ja", "ja", "ISO_639-1", "ja"),

    FRCH("fr-ch", "fr-ch", "ISO_639-1", "fr-ch"),

    SX("sx", "sx", "ISO_639-1", "sx"),

    ENTT("en-tt", "en-tt", "ISO_639-1", "en-tt"),

    ES("es", "es", "ISO_639-1", "es"),

    FI("fi", "fi", "ISO_639-1", "fi"),

    ESAR("es-ar", "es-ar", "ISO_639-1", "es-ar"),

    KK("kk", "kk", "ISO_639-1", "kk"),

    ENGB("en-gb", "en-gb", "ISO_639-1", "en-gb"),

    BG("bg", "bg", "ISO_639-1", "bg"),

    PL("pl", "pl", "ISO_639-1", "pl"),

    SV("sv", "sv", "ISO_639-1", "sv"),

    CA("ca", "ca", "ISO_639-1", "ca"),

    EN("en", "en", "ISO_639-1", "en"),

    KO("ko", "ko", "ISO_639-1", "ko"),

    AROM("ar-om", "ar-om", "ISO_639-1", "ar-om"),

    GD("gd", "gd", "ISO_639-1", "gd"),

    RO("ro", "ro", "ISO_639-1", "ro"),

    ZU("zu", "zu", "ISO_639-1", "zu"),

    ESCO("es-co", "es-co", "ISO_639-1", "es-co"),

    VI("vi", "vi", "ISO_639-1", "vi"),

    ESPE("es-pe", "es-pe", "ISO_639-1", "es-pe"),

    TS("ts", "ts", "ISO_639-1", "ts"),

    CYGB("cy-gb", "cy-gb", "ISO_639-1", "cy-gb"),

    LV("lv", "lv", "ISO_639-1", "lv"),

    UR("ur", "ur", "ISO_639-1", "ur"),

    ZHSG("zh-sg", "zh-sg", "ISO_639-1", "zh-sg"),

    ESUY("es-uy", "es-uy", "ISO_639-1", "es-uy"),

    MT("mt", "mt", "ISO_639-1", "mt"),

    ENUS("en-us", "en-us", "ISO_639-1", "en-us"),

    FRCA("fr-ca", "fr-ca", "ISO_639-1", "fr-ca"),

    UK("uk", "uk", "ISO_639-1", "uk"),

    FO("fo", "fo", "ISO_639-1", "fo");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    Language(String value, String description, String terminologyId, String code) {
      this.value = value;
      this.description = description;
      this.terminologyId = terminologyId;
      this.code = code;
    }

    public String getValue() {
      return this.value;
    }

    public String getDescription() {
      return this.description;
    }

    public String getTerminologyId() {
      return this.terminologyId;
    }

    public String getCode() {
      return this.code;
    }
  }

  public enum SettingDefiningcode implements EnumValueSet {
    PRIMARYNURSINGCARE("primary nursing care", "primary nursing care", "openehr", "229"),

    OTHERCARE("other care", "other care", "openehr", "238"),

    DENTALCARE("dental care", "dental care", "openehr", "236"),

    SECONDARYNURSINGCARE("secondary nursing care", "secondary nursing care", "openehr", "233"),

    NURSINGHOMECARE("nursing home care", "nursing home care", "openehr", "237"),

    EMERGENCYCARE("emergency care", "emergency care", "openehr", "227"),

    HOME("home", "home", "openehr", "225"),

    COMPLEMENTARYHEALTHCARE("complementary health care", "complementary health care", "openehr", "235"),

    SECONDARYALLIEDHEALTHCARE("secondary allied health care", "secondary allied health care", "openehr", "234"),

    PRIMARYMEDICALCARE("primary medical care", "primary medical care", "openehr", "228"),

    MIDWIFERYCARE("midwifery care", "midwifery care", "openehr", "231"),

    SECONDARYMEDICALCARE("secondary medical care", "secondary medical care", "openehr", "232"),

    PRIMARYALLIEDHEALTHCARE("primary allied health care", "primary allied health care", "openehr", "230");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    SettingDefiningcode(String value, String description, String terminologyId, String code) {
      this.value = value;
      this.description = description;
      this.terminologyId = terminologyId;
      this.code = code;
    }

    public String getValue() {
      return this.value;
    }

    public String getDescription() {
      return this.description;
    }

    public String getTerminologyId() {
      return this.terminologyId;
    }

    public String getCode() {
      return this.code;
    }
  }

  public enum Territory implements EnumValueSet {
    MU("MU", "MU", "ISO_3166-1", "MU"),

    AL("AL", "AL", "ISO_3166-1", "AL"),

    JM("JM", "JM", "ISO_3166-1", "JM"),

    BF("BF", "BF", "ISO_3166-1", "BF"),

    LR("LR", "LR", "ISO_3166-1", "LR"),

    AG("AG", "AG", "ISO_3166-1", "AG"),

    LA("LA", "LA", "ISO_3166-1", "LA"),

    AZ("AZ", "AZ", "ISO_3166-1", "AZ"),

    PT("PT", "PT", "ISO_3166-1", "PT"),

    DZ("DZ", "DZ", "ISO_3166-1", "DZ"),

    MP("MP", "MP", "ISO_3166-1", "MP"),

    AS("AS", "AS", "ISO_3166-1", "AS"),

    BW("BW", "BW", "ISO_3166-1", "BW"),

    TM("TM", "TM", "ISO_3166-1", "TM"),

    MZ("MZ", "MZ", "ISO_3166-1", "MZ"),

    LS("LS", "LS", "ISO_3166-1", "LS"),

    IM("IM", "IM", "ISO_3166-1", "IM"),

    GH("GH", "GH", "ISO_3166-1", "GH"),

    ME("ME", "ME", "ISO_3166-1", "ME"),

    GL("GL", "GL", "ISO_3166-1", "GL"),

    DE("DE", "DE", "ISO_3166-1", "DE"),

    HM("HM", "HM", "ISO_3166-1", "HM"),

    GM("GM", "GM", "ISO_3166-1", "GM"),

    KW("KW", "KW", "ISO_3166-1", "KW"),

    NG("NG", "NG", "ISO_3166-1", "NG"),

    FI("FI", "FI", "ISO_3166-1", "FI"),

    OM("OM", "OM", "ISO_3166-1", "OM"),

    CV("CV", "CV", "ISO_3166-1", "CV"),

    YE("YE", "YE", "ISO_3166-1", "YE"),

    SK("SK", "SK", "ISO_3166-1", "SK"),

    CG("CG", "CG", "ISO_3166-1", "CG"),

    RW("RW", "RW", "ISO_3166-1", "RW"),

    UG("UG", "UG", "ISO_3166-1", "UG"),

    NC("NC", "NC", "ISO_3166-1", "NC"),

    MH("MH", "MH", "ISO_3166-1", "MH"),

    BT("BT", "BT", "ISO_3166-1", "BT"),

    NR("NR", "NR", "ISO_3166-1", "NR"),

    NZ("NZ", "NZ", "ISO_3166-1", "NZ"),

    BY("BY", "BY", "ISO_3166-1", "BY"),

    TC("TC", "TC", "ISO_3166-1", "TC"),

    GG("GG", "GG", "ISO_3166-1", "GG"),

    TD("TD", "TD", "ISO_3166-1", "TD"),

    UM("UM", "UM", "ISO_3166-1", "UM"),

    ZA("ZA", "ZA", "ISO_3166-1", "ZA"),

    FM("FM", "FM", "ISO_3166-1", "FM"),

    UZ("UZ", "UZ", "ISO_3166-1", "UZ"),

    TV("TV", "TV", "ISO_3166-1", "TV"),

    CL("CL", "CL", "ISO_3166-1", "CL"),

    ZM("ZM", "ZM", "ISO_3166-1", "ZM"),

    JP("JP", "JP", "ISO_3166-1", "JP"),

    JO("JO", "JO", "ISO_3166-1", "JO"),

    CK("CK", "CK", "ISO_3166-1", "CK"),

    SG("SG", "SG", "ISO_3166-1", "SG"),

    FK("FK", "FK", "ISO_3166-1", "FK"),

    PL("PL", "PL", "ISO_3166-1", "PL"),

    MA("MA", "MA", "ISO_3166-1", "MA"),

    RO("RO", "RO", "ISO_3166-1", "RO"),

    HR("HR", "HR", "ISO_3166-1", "HR"),

    ZW("ZW", "ZW", "ISO_3166-1", "ZW"),

    CU("CU", "CU", "ISO_3166-1", "CU"),

    GP("GP", "GP", "ISO_3166-1", "GP"),

    NO("NO", "NO", "ISO_3166-1", "NO"),

    PN("PN", "PN", "ISO_3166-1", "PN"),

    MQ("MQ", "MQ", "ISO_3166-1", "MQ"),

    SL("SL", "SL", "ISO_3166-1", "SL"),

    BI("BI", "BI", "ISO_3166-1", "BI"),

    PG("PG", "PG", "ISO_3166-1", "PG"),

    JE("JE", "JE", "ISO_3166-1", "JE"),

    SM("SM", "SM", "ISO_3166-1", "SM"),

    IL("IL", "IL", "ISO_3166-1", "IL"),

    BE("BE", "BE", "ISO_3166-1", "BE"),

    EH("EH", "EH", "ISO_3166-1", "EH"),

    MY("MY", "MY", "ISO_3166-1", "MY"),

    HN("HN", "HN", "ISO_3166-1", "HN"),

    AT("AT", "AT", "ISO_3166-1", "AT"),

    MT("MT", "MT", "ISO_3166-1", "MT"),

    GW("GW", "GW", "ISO_3166-1", "GW"),

    EG("EG", "EG", "ISO_3166-1", "EG"),

    LK("LK", "LK", "ISO_3166-1", "LK"),

    TR("TR", "TR", "ISO_3166-1", "TR"),

    GA("GA", "GA", "ISO_3166-1", "GA"),

    BM("BM", "BM", "ISO_3166-1", "BM"),

    HT("HT", "HT", "ISO_3166-1", "HT"),

    DK("DK", "DK", "ISO_3166-1", "DK"),

    LV("LV", "LV", "ISO_3166-1", "LV"),

    AM("AM", "AM", "ISO_3166-1", "AM"),

    VU("VU", "VU", "ISO_3166-1", "VU"),

    BA("BA", "BA", "ISO_3166-1", "BA"),

    GR("GR", "GR", "ISO_3166-1", "GR"),

    CO("CO", "CO", "ISO_3166-1", "CO"),

    BB("BB", "BB", "ISO_3166-1", "BB"),

    AO("AO", "AO", "ISO_3166-1", "AO"),

    BG("BG", "BG", "ISO_3166-1", "BG"),

    GE("GE", "GE", "ISO_3166-1", "GE"),

    PS("PS", "PS", "ISO_3166-1", "PS"),

    VC("VC", "VC", "ISO_3166-1", "VC"),

    NU("NU", "NU", "ISO_3166-1", "NU"),

    MM("MM", "MM", "ISO_3166-1", "MM"),

    ID("ID", "ID", "ISO_3166-1", "ID"),

    SN("SN", "SN", "ISO_3166-1", "SN"),

    AE("AE", "AE", "ISO_3166-1", "AE"),

    VE("VE", "VE", "ISO_3166-1", "VE"),

    LU("LU", "LU", "ISO_3166-1", "LU"),

    TZ("TZ", "TZ", "ISO_3166-1", "TZ"),

    RS("RS", "RS", "ISO_3166-1", "RS"),

    IR("IR", "IR", "ISO_3166-1", "IR"),

    KG("KG", "KG", "ISO_3166-1", "KG"),

    CR("CR", "CR", "ISO_3166-1", "CR"),

    TH("TH", "TH", "ISO_3166-1", "TH"),

    AF("AF", "AF", "ISO_3166-1", "AF"),

    UY("UY", "UY", "ISO_3166-1", "UY"),

    GD("GD", "GD", "ISO_3166-1", "GD"),

    PK("PK", "PK", "ISO_3166-1", "PK"),

    TF("TF", "TF", "ISO_3166-1", "TF"),

    DM("DM", "DM", "ISO_3166-1", "DM"),

    HK("HK", "HK", "ISO_3166-1", "HK"),

    NF("NF", "NF", "ISO_3166-1", "NF"),

    BL("BL", "BL", "ISO_3166-1", "BL"),

    RU("RU", "RU", "ISO_3166-1", "RU"),

    WF("WF", "WF", "ISO_3166-1", "WF"),

    GQ("GQ", "GQ", "ISO_3166-1", "GQ"),

    TT("TT", "TT", "ISO_3166-1", "TT"),

    LY("LY", "LY", "ISO_3166-1", "LY"),

    GN("GN", "GN", "ISO_3166-1", "GN"),

    BS("BS", "BS", "ISO_3166-1", "BS"),

    BJ("BJ", "BJ", "ISO_3166-1", "BJ"),

    WS("WS", "WS", "ISO_3166-1", "WS"),

    AQ("AQ", "AQ", "ISO_3166-1", "AQ"),

    MO("MO", "MO", "ISO_3166-1", "MO"),

    GI("GI", "GI", "ISO_3166-1", "GI"),

    VN("VN", "VN", "ISO_3166-1", "VN"),

    BZ("BZ", "BZ", "ISO_3166-1", "BZ"),

    IE("IE", "IE", "ISO_3166-1", "IE"),

    AR("AR", "AR", "ISO_3166-1", "AR"),

    CY("CY", "CY", "ISO_3166-1", "CY"),

    IO("IO", "IO", "ISO_3166-1", "IO"),

    SR("SR", "SR", "ISO_3166-1", "SR"),

    AX("AX", "AX", "ISO_3166-1", "AX"),

    KM("KM", "KM", "ISO_3166-1", "KM"),

    GB("GB", "GB", "ISO_3166-1", "GB"),

    PW("PW", "PW", "ISO_3166-1", "PW"),

    SA("SA", "SA", "ISO_3166-1", "SA"),

    SJ("SJ", "SJ", "ISO_3166-1", "SJ"),

    KR("KR", "KR", "ISO_3166-1", "KR"),

    SB("SB", "SB", "ISO_3166-1", "SB"),

    MG("MG", "MG", "ISO_3166-1", "MG"),

    SI("SI", "SI", "ISO_3166-1", "SI"),

    CD("CD", "CD", "ISO_3166-1", "CD"),

    AN("AN", "AN", "ISO_3166-1", "AN"),

    SD("SD", "SD", "ISO_3166-1", "SD"),

    KP("KP", "KP", "ISO_3166-1", "KP"),

    GY("GY", "GY", "ISO_3166-1", "GY"),

    GT("GT", "GT", "ISO_3166-1", "GT"),

    AD("AD", "AD", "ISO_3166-1", "AD"),

    DJ("DJ", "DJ", "ISO_3166-1", "DJ"),

    FR("FR", "FR", "ISO_3166-1", "FR"),

    CF("CF", "CF", "ISO_3166-1", "CF"),

    MK("MK", "MK", "ISO_3166-1", "MK"),

    CA("CA", "CA", "ISO_3166-1", "CA"),

    GU("GU", "GU", "ISO_3166-1", "GU"),

    BR("BR", "BR", "ISO_3166-1", "BR"),

    SY("SY", "SY", "ISO_3166-1", "SY"),

    IQ("IQ", "IQ", "ISO_3166-1", "IQ"),

    ES("ES", "ES", "ISO_3166-1", "ES"),

    KI("KI", "KI", "ISO_3166-1", "KI"),

    CH("CH", "CH", "ISO_3166-1", "CH"),

    MD("MD", "MD", "ISO_3166-1", "MD"),

    AI("AI", "AI", "ISO_3166-1", "AI"),

    MV("MV", "MV", "ISO_3166-1", "MV"),

    BN("BN", "BN", "ISO_3166-1", "BN"),

    HU("HU", "HU", "ISO_3166-1", "HU"),

    VG("VG", "VG", "ISO_3166-1", "VG"),

    VI("VI", "VI", "ISO_3166-1", "VI"),

    UA("UA", "UA", "ISO_3166-1", "UA"),

    TK("TK", "TK", "ISO_3166-1", "TK"),

    ST("ST", "ST", "ISO_3166-1", "ST"),

    LB("LB", "LB", "ISO_3166-1", "LB"),

    CX("CX", "CX", "ISO_3166-1", "CX"),

    TO("TO", "TO", "ISO_3166-1", "TO"),

    IS("IS", "IS", "ISO_3166-1", "IS"),

    KN("KN", "KN", "ISO_3166-1", "KN"),

    AW("AW", "AW", "ISO_3166-1", "AW"),

    KE("KE", "KE", "ISO_3166-1", "KE"),

    MS("MS", "MS", "ISO_3166-1", "MS"),

    ET("ET", "ET", "ISO_3166-1", "ET"),

    DO("DO", "DO", "ISO_3166-1", "DO"),

    PM("PM", "PM", "ISO_3166-1", "PM"),

    IT("IT", "IT", "ISO_3166-1", "IT"),

    TJ("TJ", "TJ", "ISO_3166-1", "TJ"),

    QA("QA", "QA", "ISO_3166-1", "QA"),

    SE("SE", "SE", "ISO_3166-1", "SE"),

    MC("MC", "MC", "ISO_3166-1", "MC"),

    US("US", "US", "ISO_3166-1", "US"),

    NP("NP", "NP", "ISO_3166-1", "NP"),

    BD("BD", "BD", "ISO_3166-1", "BD"),

    TN("TN", "TN", "ISO_3166-1", "TN"),

    TW("TW", "TW", "ISO_3166-1", "TW"),

    LI("LI", "LI", "ISO_3166-1", "LI"),

    MX("MX", "MX", "ISO_3166-1", "MX"),

    YT("YT", "YT", "ISO_3166-1", "YT"),

    CI("CI", "CI", "ISO_3166-1", "CI"),

    KZ("KZ", "KZ", "ISO_3166-1", "KZ"),

    PE("PE", "PE", "ISO_3166-1", "PE"),

    CZ("CZ", "CZ", "ISO_3166-1", "CZ"),

    NI("NI", "NI", "ISO_3166-1", "NI"),

    SO("SO", "SO", "ISO_3166-1", "SO"),

    AU("AU", "AU", "ISO_3166-1", "AU"),

    RE("RE", "RE", "ISO_3166-1", "RE"),

    NA("NA", "NA", "ISO_3166-1", "NA"),

    ER("ER", "ER", "ISO_3166-1", "ER"),

    FJ("FJ", "FJ", "ISO_3166-1", "FJ"),

    TL("TL", "TL", "ISO_3166-1", "TL"),

    KY("KY", "KY", "ISO_3166-1", "KY"),

    EE("EE", "EE", "ISO_3166-1", "EE"),

    KH("KH", "KH", "ISO_3166-1", "KH"),

    EC("EC", "EC", "ISO_3166-1", "EC"),

    PA("PA", "PA", "ISO_3166-1", "PA"),

    PY("PY", "PY", "ISO_3166-1", "PY"),

    SH("SH", "SH", "ISO_3166-1", "SH"),

    CN("CN", "CN", "ISO_3166-1", "CN"),

    PH("PH", "PH", "ISO_3166-1", "PH"),

    MN("MN", "MN", "ISO_3166-1", "MN"),

    BV("BV", "BV", "ISO_3166-1", "BV"),

    NL("NL", "NL", "ISO_3166-1", "NL"),

    PR("PR", "PR", "ISO_3166-1", "PR"),

    TG("TG", "TG", "ISO_3166-1", "TG"),

    GS("GS", "GS", "ISO_3166-1", "GS"),

    SC("SC", "SC", "ISO_3166-1", "SC"),

    BO("BO", "BO", "ISO_3166-1", "BO"),

    NE("NE", "NE", "ISO_3166-1", "NE"),

    CM("CM", "CM", "ISO_3166-1", "CM"),

    PF("PF", "PF", "ISO_3166-1", "PF"),

    FO("FO", "FO", "ISO_3166-1", "FO"),

    MR("MR", "MR", "ISO_3166-1", "MR"),

    MF("MF", "MF", "ISO_3166-1", "MF"),

    SV("SV", "SV", "ISO_3166-1", "SV"),

    MW("MW", "MW", "ISO_3166-1", "MW"),

    BH("BH", "BH", "ISO_3166-1", "BH"),

    LT("LT", "LT", "ISO_3166-1", "LT"),

    VA("VA", "VA", "ISO_3166-1", "VA"),

    SZ("SZ", "SZ", "ISO_3166-1", "SZ"),

    CC("CC", "CC", "ISO_3166-1", "CC"),

    IN("IN", "IN", "ISO_3166-1", "IN"),

    LC("LC", "LC", "ISO_3166-1", "LC"),

    GF("GF", "GF", "ISO_3166-1", "GF"),

    ML("ML", "ML", "ISO_3166-1", "ML");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    Territory(String value, String description, String terminologyId, String code) {
      this.value = value;
      this.description = description;
      this.terminologyId = terminologyId;
      this.code = code;
    }

    public String getValue() {
      return this.value;
    }

    public String getDescription() {
      return this.description;
    }

    public String getTerminologyId() {
      return this.terminologyId;
    }

    public String getCode() {
      return this.code;
    }
  }

  @Entity
  @Archetype("openEHR-EHR-OBSERVATION.sample_blood_pressure.v1")
  public static class BloodPressureTrainingSample {
    @Path("/protocol[at0011]/items[at1025]")
    private List<Cluster> device;

    @Path("/language")
    private Language language;

    @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1030]")
    private List<Cluster> levelOfExertion;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0033]/value|value")
    private String commentValue;

    @Path("/protocol[at0011]/items[at0013]/value|defining_code")
    private CuffSizeDefiningcode cuffSizeDefiningcode;

    @Path("/protocol[at0011]/items[at1010]/value|defining_code")
    private KorotkoffSoundsDefiningcode korotkoffSoundsDefiningcode;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
    private Double systolicMagnitude;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
    private String systolicUnits;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|magnitude")
    private Double diastolicMagnitude;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|units")
    private String diastolicUnits;

    @Path("/data[at0001]/events[at0002]/state[at0007]/items[at0008]/value|defining_code")
    private PositionDefiningcode positionDefiningcode;

    @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|magnitude")
    private Double tiltMagnitude;

    @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|units")
    private String tiltUnits;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|magnitude")
    private Double meanArterialPressureMagnitude;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|units")
    private String meanArterialPressureUnits;

    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    @Path("/subject|external_ref")
    private PartyRef subjectExternalref;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|magnitude")
    private Double pulsePressureMagnitude;

    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|units")
    private String pulsePressureUnits;

    @Path("/protocol[at0011]/items[at0014]/value|defining_code")
    private LocationOfMeasurementDefiningcode locationOfMeasurementDefiningcode;

    public void setDevice(List<Cluster> device) {
      this.device = device;
    }

    public List<Cluster> getDevice() {
      return this.device;
    }

    public void setLanguage(Language language) {
      this.language = language;
    }

    public Language getLanguage() {
      return this.language;
    }

    public void setLevelOfExertion(List<Cluster> levelOfExertion) {
      this.levelOfExertion = levelOfExertion;
    }

    public List<Cluster> getLevelOfExertion() {
      return this.levelOfExertion;
    }

    public void setCommentValue(String commentValue) {
      this.commentValue = commentValue;
    }

    public String getCommentValue() {
      return this.commentValue;
    }

    public void setCuffSizeDefiningcode(CuffSizeDefiningcode cuffSizeDefiningcode) {
      this.cuffSizeDefiningcode = cuffSizeDefiningcode;
    }

    public CuffSizeDefiningcode getCuffSizeDefiningcode() {
      return this.cuffSizeDefiningcode;
    }

    public void setKorotkoffSoundsDefiningcode(
            KorotkoffSoundsDefiningcode korotkoffSoundsDefiningcode) {
      this.korotkoffSoundsDefiningcode = korotkoffSoundsDefiningcode;
    }

    public KorotkoffSoundsDefiningcode getKorotkoffSoundsDefiningcode() {
      return this.korotkoffSoundsDefiningcode;
    }

    public void setSystolicMagnitude(Double systolicMagnitude) {
      this.systolicMagnitude = systolicMagnitude;
    }

    public Double getSystolicMagnitude() {
      return this.systolicMagnitude;
    }

    public void setSystolicUnits(String systolicUnits) {
      this.systolicUnits = systolicUnits;
    }

    public String getSystolicUnits() {
      return this.systolicUnits;
    }

    public void setDiastolicMagnitude(Double diastolicMagnitude) {
      this.diastolicMagnitude = diastolicMagnitude;
    }

    public Double getDiastolicMagnitude() {
      return this.diastolicMagnitude;
    }

    public void setDiastolicUnits(String diastolicUnits) {
      this.diastolicUnits = diastolicUnits;
    }

    public String getDiastolicUnits() {
      return this.diastolicUnits;
    }

    public void setPositionDefiningcode(PositionDefiningcode positionDefiningcode) {
      this.positionDefiningcode = positionDefiningcode;
    }

    public PositionDefiningcode getPositionDefiningcode() {
      return this.positionDefiningcode;
    }

    public void setTiltMagnitude(Double tiltMagnitude) {
      this.tiltMagnitude = tiltMagnitude;
    }

    public Double getTiltMagnitude() {
      return this.tiltMagnitude;
    }

    public void setTiltUnits(String tiltUnits) {
      this.tiltUnits = tiltUnits;
    }

    public String getTiltUnits() {
      return this.tiltUnits;
    }

    public void setMeanArterialPressureMagnitude(Double meanArterialPressureMagnitude) {
      this.meanArterialPressureMagnitude = meanArterialPressureMagnitude;
    }

    public Double getMeanArterialPressureMagnitude() {
      return this.meanArterialPressureMagnitude;
    }

    public void setMeanArterialPressureUnits(String meanArterialPressureUnits) {
      this.meanArterialPressureUnits = meanArterialPressureUnits;
    }

    public String getMeanArterialPressureUnits() {
      return this.meanArterialPressureUnits;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
      this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
      return this.timeValue;
    }

    public void setSubjectExternalref(PartyRef subjectExternalref) {
      this.subjectExternalref = subjectExternalref;
    }

    public PartyRef getSubjectExternalref() {
      return this.subjectExternalref;
    }

    public void setOriginValue(TemporalAccessor originValue) {
      this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
      return this.originValue;
    }

    public void setPulsePressureMagnitude(Double pulsePressureMagnitude) {
      this.pulsePressureMagnitude = pulsePressureMagnitude;
    }

    public Double getPulsePressureMagnitude() {
      return this.pulsePressureMagnitude;
    }

    public void setPulsePressureUnits(String pulsePressureUnits) {
      this.pulsePressureUnits = pulsePressureUnits;
    }

    public String getPulsePressureUnits() {
      return this.pulsePressureUnits;
    }

    public void setLocationOfMeasurementDefiningcode(
            LocationOfMeasurementDefiningcode locationOfMeasurementDefiningcode) {
      this.locationOfMeasurementDefiningcode = locationOfMeasurementDefiningcode;
    }

    public LocationOfMeasurementDefiningcode getLocationOfMeasurementDefiningcode() {
      return this.locationOfMeasurementDefiningcode;
    }

    public enum Language implements EnumValueSet {
      ARJO("ar-jo", "ar-jo", "ISO_639-1", "ar-jo"),

      TS("ts", "ts", "ISO_639-1", "ts"),

      AREG("ar-eg", "ar-eg", "ISO_639-1", "ar-eg"),

      ITCH("it-ch", "it-ch", "ISO_639-1", "it-ch"),

      ARSA("ar-sa", "ar-sa", "ISO_639-1", "ar-sa"),

      RO("ro", "ro", "ISO_639-1", "ro"),

      DEAT("de-at", "de-at", "ISO_639-1", "de-at"),

      ENBZ("en-bz", "en-bz", "ISO_639-1", "en-bz"),

      ZU("zu", "zu", "ISO_639-1", "zu"),

      AROM("ar-om", "ar-om", "ISO_639-1", "ar-om"),

      ESCL("es-cl", "es-cl", "ISO_639-1", "es-cl"),

      ENZA("en-za", "en-za", "ISO_639-1", "en-za"),

      ENNZ("en-nz", "en-nz", "ISO_639-1", "en-nz"),

      ROMO("ro-mo", "ro-mo", "ISO_639-1", "ro-mo"),

      FRBE("fr-be", "fr-be", "ISO_639-1", "fr-be"),

      SVFI("sv-fi", "sv-fi", "ISO_639-1", "sv-fi"),

      HU("hu", "hu", "ISO_639-1", "hu"),

      DECH("de-ch", "de-ch", "ISO_639-1", "de-ch"),

      NN("nn", "nn", "ISO_639-1", "nn"),

      ARQA("ar-qa", "ar-qa", "ISO_639-1", "ar-qa"),

      ARYE("ar-ye", "ar-ye", "ISO_639-1", "ar-ye"),

      CY("cy", "cy", "ISO_639-1", "cy"),

      BG("bg", "bg", "ISO_639-1", "bg"),

      ZHSG("zh-sg", "zh-sg", "ISO_639-1", "zh-sg"),

      DELI("de-li", "de-li", "ISO_639-1", "de-li"),

      ARDZ("ar-dz", "ar-dz", "ISO_639-1", "ar-dz"),

      FRLU("fr-lu", "fr-lu", "ISO_639-1", "fr-lu"),

      ESDO("es-do", "es-do", "ISO_639-1", "es-do"),

      HR("hr", "hr", "ISO_639-1", "hr"),

      ESUY("es-uy", "es-uy", "ISO_639-1", "es-uy"),

      ESCO("es-co", "es-co", "ISO_639-1", "es-co"),

      ENTT("en-tt", "en-tt", "ISO_639-1", "en-tt"),

      ENAU("en-au", "en-au", "ISO_639-1", "en-au"),

      SX("sx", "sx", "ISO_639-1", "sx"),

      SL("sl", "sl", "ISO_639-1", "sl"),

      FI("fi", "fi", "ISO_639-1", "fi"),

      CS("cs", "cs", "ISO_639-1", "cs"),

      PTPT("pt-pt", "pt-pt", "ISO_639-1", "pt-pt"),

      NLBE("nl-be", "nl-be", "ISO_639-1", "nl-be"),

      ARSY("ar-sy", "ar-sy", "ISO_639-1", "ar-sy"),

      EL("el", "el", "ISO_639-1", "el"),

      VI("vi", "vi", "ISO_639-1", "vi"),

      ESMX("es-mx", "es-mx", "ISO_639-1", "es-mx"),

      FRCH("fr-ch", "fr-ch", "ISO_639-1", "fr-ch"),

      LV("lv", "lv", "ISO_639-1", "lv"),

      LT("lt", "lt", "ISO_639-1", "lt"),

      ENIE("en-ie", "en-ie", "ISO_639-1", "en-ie"),

      CYAR("cy-ar", "cy-ar", "ISO_639-1", "cy-ar"),

      IS("is", "is", "ISO_639-1", "is"),

      JI("ji", "ji", "ISO_639-1", "ji"),

      DELU("de-lu", "de-lu", "ISO_639-1", "de-lu"),

      FO("fo", "fo", "ISO_639-1", "fo"),

      EU("eu", "eu", "ISO_639-1", "eu"),

      ESPY("es-py", "es-py", "ISO_639-1", "es-py"),

      SK("sk", "sk", "ISO_639-1", "sk"),

      ESHN("es-hn", "es-hn", "ISO_639-1", "es-hn"),

      ENCA("en-ca", "en-ca", "ISO_639-1", "en-ca"),

      KO("ko", "ko", "ISO_639-1", "ko"),

      ESPR("es-pr", "es-pr", "ISO_639-1", "es-pr"),

      ZHHK("zh-hk", "zh-hk", "ISO_639-1", "zh-hk"),

      ARIQ("ar-iq", "ar-iq", "ISO_639-1", "ar-iq"),

      VE("ve", "ve", "ISO_639-1", "ve"),

      HE("he", "he", "ISO_639-1", "he"),

      ESGT("es-gt", "es-gt", "ISO_639-1", "es-gt"),

      TH("th", "th", "ISO_639-1", "th"),

      ET("et", "et", "ISO_639-1", "et"),

      ENJM("en-jm", "en-jm", "ISO_639-1", "en-jm"),

      RM("rm", "rm", "ISO_639-1", "rm"),

      SQ("sq", "sq", "ISO_639-1", "sq"),

      ENGB("en-gb", "en-gb", "ISO_639-1", "en-gb"),

      AF("af", "af", "ISO_639-1", "af"),

      ESCR("es-cr", "es-cr", "ISO_639-1", "es-cr"),

      ESNI("es-ni", "es-ni", "ISO_639-1", "es-ni"),

      ARLB("ar-lb", "ar-lb", "ISO_639-1", "ar-lb"),

      FRCA("fr-ca", "fr-ca", "ISO_639-1", "fr-ca"),

      RU("ru", "ru", "ISO_639-1", "ru"),

      KK("kk", "kk", "ISO_639-1", "kk"),

      ESVE("es-ve", "es-ve", "ISO_639-1", "es-ve"),

      GD("gd", "gd", "ISO_639-1", "gd"),

      AA("aa", "aa", "ISO_639-1", "aa"),

      ZH("zh", "zh", "ISO_639-1", "zh"),

      TR("tr", "tr", "ISO_639-1", "tr"),

      ESBO("es-bo", "es-bo", "ISO_639-1", "es-bo"),

      ESPA("es-pa", "es-pa", "ISO_639-1", "es-pa"),

      SV("sv", "sv", "ISO_639-1", "sv"),

      ARMA("ar-ma", "ar-ma", "ISO_639-1", "ar-ma"),

      TN("tn", "tn", "ISO_639-1", "tn"),

      ARAE("ar-ae", "ar-ae", "ISO_639-1", "ar-ae"),

      SR("sr", "sr", "ISO_639-1", "sr"),

      DA("da", "da", "ISO_639-1", "da"),

      ESEC("es-ec", "es-ec", "ISO_639-1", "es-ec"),

      PTBR("pt-br", "pt-br", "ISO_639-1", "pt-br"),

      BE("be", "be", "ISO_639-1", "be"),

      PL("pl", "pl", "ISO_639-1", "pl"),

      JA("ja", "ja", "ISO_639-1", "ja"),

      SI("si", "si", "ISO_639-1", "si"),

      UK("uk", "uk", "ISO_639-1", "uk"),

      DE("de", "de", "ISO_639-1", "de"),

      ZHTW("zh-tw", "zh-tw", "ISO_639-1", "zh-tw"),

      EN("en", "en", "ISO_639-1", "en"),

      FR("fr", "fr", "ISO_639-1", "fr"),

      UR("ur", "ur", "ISO_639-1", "ur"),

      HI("hi", "hi", "ISO_639-1", "hi"),

      ES("es", "es", "ISO_639-1", "es"),

      GDIE("gd-ie", "gd-ie", "ISO_639-1", "gd-ie"),

      ARKW("ar-kw", "ar-kw", "ISO_639-1", "ar-kw"),

      RUMO("ru-mo", "ru-mo", "ISO_639-1", "ru-mo"),

      PT("pt", "pt", "ISO_639-1", "pt"),

      ID("id", "id", "ISO_639-1", "id"),

      CYGB("cy-gb", "cy-gb", "ISO_639-1", "cy-gb"),

      ESSV("es-sv", "es-sv", "ISO_639-1", "es-sv"),

      ARBH("ar-bh", "ar-bh", "ISO_639-1", "ar-bh"),

      XH("xh", "xh", "ISO_639-1", "xh"),

      ZHCN("zh-cn", "zh-cn", "ISO_639-1", "zh-cn"),

      ENUS("en-us", "en-us", "ISO_639-1", "en-us"),

      KM("km", "km", "ISO_639-1", "km"),

      SB("sb", "sb", "ISO_639-1", "sb"),

      MT("mt", "mt", "ISO_639-1", "mt"),

      ESPE("es-pe", "es-pe", "ISO_639-1", "es-pe"),

      ESAR("es-ar", "es-ar", "ISO_639-1", "es-ar"),

      SZ("sz", "sz", "ISO_639-1", "sz"),

      AZ("az", "az", "ISO_639-1", "az"),

      ARLY("ar-ly", "ar-ly", "ISO_639-1", "ar-ly"),

      FA("fa", "fa", "ISO_639-1", "fa"),

      IT("it", "it", "ISO_639-1", "it"),

      NB("nb", "nb", "ISO_639-1", "nb"),

      CA("ca", "ca", "ISO_639-1", "ca"),

      ARTN("ar-tn", "ar-tn", "ISO_639-1", "ar-tn"),

      MK("mk", "mk", "ISO_639-1", "mk"),

      NL("nl", "nl", "ISO_639-1", "nl");

      private String value;

      private String description;

      private String terminologyId;

      private String code;

      Language(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
      }

      public String getValue() {
        return this.value;
      }

      public String getDescription() {
        return this.description;
      }

      public String getTerminologyId() {
        return this.terminologyId;
      }

      public String getCode() {
        return this.code;
      }
    }

    public enum CuffSizeDefiningcode implements EnumValueSet {
      ADULTTHIGH("Adult Thigh", "A cuff used for an adult thigh - bladder approx 20cm x 42 cm", "local", "at1008"),

      LARGEADULT("Large Adult", "A cuff for adults with larger arms - bladder approx 16cm x 38cm", "local", "at0016"),

      NEONATAL("Neonatal", "A cuff used for a new born - bladder approx 3cm x 6cm", "local", "at1009"),

      INFANT("Infant", "A cuff used for infants - bladder approx 5cm x 15cm", "local", "at1018"),

      ADULT("Adult", "A cuff that is standard for an adult - bladder approx 13cm x 30cm", "local", "at0015"),

      SMALLADULT("Small Adult", "A cuff used for a small adult - bladder approx 10cm x 24 cm", "local", "at1019"),

      CHILD("Paediatric/Child", "A cuff that is appropriate for a child or thin arm - bladder approx 8cm x 21 cm", "local", "at0017");

      private String value;

      private String description;

      private String terminologyId;

      private String code;

      CuffSizeDefiningcode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
      }

      public String getValue() {
        return this.value;
      }

      public String getDescription() {
        return this.description;
      }

      public String getTerminologyId() {
        return this.terminologyId;
      }

      public String getCode() {
        return this.code;
      }
    }

    public enum KorotkoffSoundsDefiningcode implements EnumValueSet {
      FOURTHSOUND("Fourth sound", "The fourth Korotkoff sound is identified as an abrupt muffling of sounds", "local", "at1011"),

      FIFTHSOUND("Fifth sound", "The fifth Korotkoff sound is identified by absence of sounds as the cuff pressure drops below the diastolic blood pressure", "local", "at1012");

      private String value;

      private String description;

      private String terminologyId;

      private String code;

      KorotkoffSoundsDefiningcode(String value, String description, String terminologyId,
                                  String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
      }

      public String getValue() {
        return this.value;
      }

      public String getDescription() {
        return this.description;
      }

      public String getTerminologyId() {
        return this.terminologyId;
      }

      public String getCode() {
        return this.code;
      }
    }

    public enum PositionDefiningcode implements EnumValueSet {
      STANDING("Standing", "Standing at the time of blood pressure measurement", "local", "at1000"),

      LYING("Lying", "Lying flat at the time of blood pressure measurement", "local", "at1003"),

      TRENDELENBURG("Trendelenburg", "Lying flat on the back (supine position) with the feet higher than the head at the time of blood pressure measurement", "local", "at1013"),

      RECLINING("Reclining", "Reclining at the time of blood pressure measurement", "local", "at1002"),

      SITTING("Sitting", "Sitting (for example on bed or chair) at the time of blood pressure measurement", "local", "at1001"),

      LEFTLATERAL("Left Lateral", "Lying on the left side at the time of blood pressure measurement", "local", "at1014");

      private String value;

      private String description;

      private String terminologyId;

      private String code;

      PositionDefiningcode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
      }

      public String getValue() {
        return this.value;
      }

      public String getDescription() {
        return this.description;
      }

      public String getTerminologyId() {
        return this.terminologyId;
      }

      public String getCode() {
        return this.code;
      }
    }

    public enum LocationOfMeasurementDefiningcode implements EnumValueSet {
      RIGHTARM("Right arm", "The right arm of the person", "local", "at0025"),

      LEFTTHIGH("Left thigh", "The left thigh of the person", "local", "at0028"),

      RIGHTTHIGH("Right thigh", "The right thigh of the person", "local", "at0027"),

      LEFTWRIST("Left wrist", "The left wrist of the person", "local", "at1021"),

      LEFTARM("Left arm", "The left arm of the person", "local", "at0026"),

      FINGER("Finger", "A finger of the person", "local", "at1026"),

      INTRAARTERIAL("Intra-arterial", "Blood pressure monitored via an intra-arterial line", "local", "at0032"),

      RIGHTANKLE("Right ankle", "The right ankle of the person", "local", "at1031"),

      RIGHTWRIST("Right wrist", "The right wrist of the person", "local", "at1020"),

      LEFTANKLE("Left ankle", "The left ankle of the person", "local", "at1032");

      private String value;

      private String description;

      private String terminologyId;

      private String code;

      LocationOfMeasurementDefiningcode(String value, String description, String terminologyId,
                                        String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
      }

      public String getValue() {
        return this.value;
      }

      public String getDescription() {
        return this.description;
      }

      public String getTerminologyId() {
        return this.terminologyId;
      }

      public String getCode() {
        return this.code;
      }
    }
  }

  @Entity
  @Archetype("openEHR-EHR-CLUSTER.sample_device.v1")
  public static class DeviceDetailsTrainingSample {
    @Path("/items[at0004]/items[at0005]/value|value")
    private String modelValue;

    @Path("/items[at0004]/items[at0006]/value|value")
    private String serialNumberValue;

    @Path("/items[at0008]/items[at0009]/value|value")
    private TemporalAccessor dateLastServicedValue;

    @Path("/items[at0008]/items[at0010]/value|value")
    private TemporalAccessor dateLastCalibrationValue;

    @Path("/items[at0001]/value|value")
    private String nameValue;

    @Path("/items[at0002]/value|value")
    private String descriptionValue;

    @Path("/items[at0007]/items")
    private List<Cluster> components;

    @Path("/items[at0008]/items[at0011]/value|value")
    private String servicedByValue;

    @Path("/items[at0004]/items[at0003]/value|value")
    private String manufacturerValue;

    public void setModelValue(String modelValue) {
      this.modelValue = modelValue;
    }

    public String getModelValue() {
      return this.modelValue;
    }

    public void setSerialNumberValue(String serialNumberValue) {
      this.serialNumberValue = serialNumberValue;
    }

    public String getSerialNumberValue() {
      return this.serialNumberValue;
    }

    public void setDateLastServicedValue(TemporalAccessor dateLastServicedValue) {
      this.dateLastServicedValue = dateLastServicedValue;
    }

    public TemporalAccessor getDateLastServicedValue() {
      return this.dateLastServicedValue;
    }

    public void setDateLastCalibrationValue(TemporalAccessor dateLastCalibrationValue) {
      this.dateLastCalibrationValue = dateLastCalibrationValue;
    }

    public TemporalAccessor getDateLastCalibrationValue() {
      return this.dateLastCalibrationValue;
    }

    public void setNameValue(String nameValue) {
      this.nameValue = nameValue;
    }

    public String getNameValue() {
      return this.nameValue;
    }

    public void setDescriptionValue(String descriptionValue) {
      this.descriptionValue = descriptionValue;
    }

    public String getDescriptionValue() {
      return this.descriptionValue;
    }

    public void setComponents(List<Cluster> components) {
      this.components = components;
    }

    public List<Cluster> getComponents() {
      return this.components;
    }

    public void setServicedByValue(String servicedByValue) {
      this.servicedByValue = servicedByValue;
    }

    public String getServicedByValue() {
      return this.servicedByValue;
    }

    public void setManufacturerValue(String manufacturerValue) {
      this.manufacturerValue = manufacturerValue;
    }

    public String getManufacturerValue() {
      return this.manufacturerValue;
    }
  }
}
