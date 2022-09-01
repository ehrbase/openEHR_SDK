package org.ehrbase.response.openehr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;

import java.util.List;
import java.util.Map;

@JacksonXmlRootElement
public class StoredQueryResponseData {

    @JsonProperty
    private String name;

    @JsonProperty
    private String type;

    @JsonProperty
    private String version;

    @JsonProperty
    private DvDateTime saved;

    @JsonProperty(value = "q")
    private String aqlQuery;

    //TODO: the answer is returning colums & rows which is not valid according to spec
    @JsonProperty(value = "columns")
    private List<Map<String, String>> columns;

    //TODO: the answer is returning colums & rows which is not valid according to spec
    @JsonProperty(value = "rows")
    private List<List<Object>> rows;

    public String getName() { return name; }

    public String getVersion() { return version; }

    public String getAqlQuery() { return aqlQuery; }
}
