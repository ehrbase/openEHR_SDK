package org.ehrbase.serialisation.dbencoding.rmobject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.archetyped.Link;
import java.util.List;
import org.ehrbase.serialisation.dbencoding.EncodeUtilArchie;

/**
 * Encode/decode a Links object as a json structure. Should be used to support FeederAudit at DB
 * level (f.e. Composition Entry)
 */
public class LinksEncoding extends RMObjectEncoding {

  public String toDB(List<Link> linkList) {
    GsonBuilder builder = EncodeUtilArchie.getGsonBuilderInstance();
    Gson gson = builder.setPrettyPrinting().create();
    return gson.toJson(linkList);
  }

  // TODO: finalize implementation whenever required
  public FeederAudit fromDB(String dbJonRepresentation) {
    throw new IllegalStateException("not implemented");
  }
}
