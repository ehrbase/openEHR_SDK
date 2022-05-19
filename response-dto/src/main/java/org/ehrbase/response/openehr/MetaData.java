package org.ehrbase.response.openehr;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public class MetaData {

  @JsonProperty(value = "_href")
  private String href;

  @JsonProperty(value = "_type")
  private String type;

  @JsonProperty(value = "_schema_version")
  private String schemaVersion;

  @JsonProperty(value = "_created")
  private OffsetDateTime created;

  @JsonProperty(value = "_generator")
  private String generator;

  @JsonProperty(value = "_executed_aql")
  private String executedAql;

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSchemaVersion() {
    return schemaVersion;
  }

  public void setSchemaVersion(String schemaVersion) {
    this.schemaVersion = schemaVersion;
  }

  public OffsetDateTime getCreated() {
    return created;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public String getGenerator() {
    return generator;
  }

  public void setGenerator(String generator) {
    this.generator = generator;
  }

  public String getExecutedAql() {
    return executedAql;
  }

  public void setExecutedAql(String executedAql) {
    this.executedAql = executedAql;
  }
}
