package org.ehrbase.serialisation.attributes;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.TAG_CLASS;

import com.nedap.archie.rm.generic.Participation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.ehrbase.serialisation.dbencoding.CompositionSerializer;
import org.ehrbase.serialisation.dbencoding.PathMap;
import org.ehrbase.serialisation.dbencoding.SimpleClassName;

/** populate the attributes for RM OtherParticipations in CareEntry */
public class OtherParticipationAttributes {

  private final List<Participation> participationList;
  private final CompositionSerializer compositionSerializer;

  public OtherParticipationAttributes(
      List<Participation> participationList, CompositionSerializer compositionSerializer) {
    this.participationList = participationList;
    this.compositionSerializer = compositionSerializer;
  }

  public List<Map<String, Object>> toMap() {
    List<Map<String, Object>> participations = new ArrayList<>();
    for (Participation participation : participationList) {
      Map<String, Object> valuemap = PathMap.getInstance();
      valuemap.put(TAG_CLASS, new SimpleClassName(participation).toString());
      valuemap.put("function", participation.getFunction());
      valuemap.put("mode", participation.getMode());
      valuemap.put("time", participation.getTime());
      valuemap.put(
          "performer",
          new SubjectAttributes(participation.getPerformer(), compositionSerializer).toMap());
      participations.add(valuemap);
    }

    return participations;
  }
}
