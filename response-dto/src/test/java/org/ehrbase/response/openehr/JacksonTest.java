package org.ehrbase.response.openehr;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JacksonTest {

  private final ObjectMapper objectMapper;

  public JacksonTest() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  void testReadJson() throws IOException {
    QueryResponseData response = objectMapper.readValue(
        IOUtils.resourceToString("/query_response.json", UTF_8), QueryResponseData.class);

    MetaData meta = response.getMeta();
    Assertions.assertNotNull(response.getMeta());
    Assertions.assertEquals("... the URI for the executed AQL - used only for GET executions ...",
        meta.getHref());
    Assertions.assertEquals(
        "DIPS.OpenEhr.ResultSets.Serialization.Json.ResultSetJsonWriter (5.0.0.0)",
        meta.getGenerator());
    Assertions.assertEquals("1.0.0", meta.getSchemaVersion());
    Assertions.assertEquals("... the executed aql ...", meta.getExecutedAql());
  }

  @Test
  void testWriteJson() throws IOException {
    MetaData meta = new MetaData();
    meta.setHref(
        "https://rest.ehrscape.com/rest/openehr/v1/query/aql?q=select+c+from+composition+c");
    meta.setExecutedAql("select c from composition c");
    meta.setType("RESULTSET");
    QueryResponseData response = new QueryResponseData();
    response.setMeta(meta);

    String json = objectMapper.writeValueAsString(response);

    Assertions.assertTrue(json.contains(
        "https://rest.ehrscape.com/rest/openehr/v1/query/aql?q=select+c+from+composition+c"));
    Assertions.assertTrue(json.contains("select c from composition c"));
    Assertions.assertTrue(json.contains("RESULTSET"));
  }
}
