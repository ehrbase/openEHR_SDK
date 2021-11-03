package org.ehrbase.serialisation.walker;

import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.*;
import java.util.stream.Collectors;

import static org.ehrbase.util.rmconstants.RmConstants.*;

public class FlatHelper<T> {

  private Map<String, Map<String, Integer>> pathCountMap = new HashMap<>();

  public String buildNamePath(Context<T> context, boolean addCount) {
    StringBuilder sb = new StringBuilder();
    StringBuilder fullPath = new StringBuilder();
    WebTemplateNode node = null;
    for (Iterator<WebTemplateNode> iterator = context.getNodeDeque().descendingIterator();
        iterator.hasNext(); ) {
      WebTemplateNode parent = node;
      node = iterator.next();
      boolean skip = skip(node, parent);
      fullPath.append(node.getId());

      if (!skip) {
        String key = sb + "/" + node.getId(false);
        if (!pathCountMap.containsKey(key)) {
          pathCountMap.put(key, new HashMap<>());
        }

        if (!pathCountMap.get(key).containsKey(fullPath.toString())) {
          pathCountMap
              .get(key)
              .put(
                  fullPath.toString(),
                  pathCountMap.get(key).values().stream()
                      .max(Comparator.naturalOrder())
                      .map(i -> i + 1)
                      .orElse(1));
        }

        Integer integer =
            Optional.ofNullable(pathCountMap.get(key))
                .map(m -> m.get(fullPath.toString()))
                .orElse(1);
        if (integer != 1) {
          sb.append(node.getId(false) + integer);
        } else {
          sb.append(node.getId(false));
        }
      }
      if (parent != null && ELEMENT.equals(parent.getRmType()) && skip(parent, null)) {
        addCount(context, addCount, sb, parent, skip);
      } else {
        addCount(context, addCount, sb, node, skip);
      }
      if (!skip && iterator.hasNext()) {
        sb.append("/");
      }

      if (!pathCountMap.containsKey(sb.toString())) {
        pathCountMap.put(sb.toString(), new HashMap<>());
      }

      if (!pathCountMap.get(sb.toString()).containsKey(fullPath.toString())) {
        pathCountMap
            .get(sb.toString())
            .put(
                fullPath.toString(),
                pathCountMap.get(sb.toString()).values().stream()
                    .max(Comparator.naturalOrder())
                    .map(i -> i + 1)
                    .orElse(1));
      }
    }
    return StringUtils.removeEnd(sb.toString(), "/");
  }

  private void addCount(
      Context<T> context, boolean addCount, StringBuilder sb, WebTemplateNode node, boolean skip) {
    if (!skip
        && node.getMax() != 1
        && context.getCountMap().containsKey(new NodeId(node))
        && (addCount || context.getCountMap().get(new NodeId(node)) != 0)) {
      sb.append(":").append(context.getCountMap().get(new NodeId(node)));
    }
  }

  public boolean skip(Context<T> context) {
    WebTemplateNode node = context.getNodeDeque().poll();
    WebTemplateNode parent = context.getNodeDeque().peek();
    context.getNodeDeque().push(node);
    boolean skip = skip(node, parent);
    return skip;
  }

  public boolean skip(WebTemplateNode node, WebTemplateNode parent) {

    if (node.isArchetypeSlot()) {
      return true;
    }
    if (parent != null && isNonMandatoryRmAttribute(node, parent)) {
      return true;
    }

    if (parent != null
        && parent.getRmType().equals(ISM_TRANSITION)
        && !parent.getId().equals("ism_transition")) {
      return true;
    }

    if (List.of("HISTORY", "ITEM_TREE", "ITEM_LIST", "ITEM_SINGLE", "ITEM_TABLE", "ITEM_STRUCTURE")
        .contains(node.getRmType())) {
      return true;
    } else if (parent != null && isEvent(node)) {
      return parent.getChildren().stream().filter(this::isEvent).count() == 1 && node.getMax() == 1;
    } else if (node.getRmType().equals(ELEMENT)) {
      List<String> trueChildren =
          node.getChildren().stream()
              .filter(n -> !List.of("null_flavour", "feeder_audit").contains(n.getName()))
              .map(WebTemplateNode::getRmType)
              .collect(Collectors.toList());
      return node.getChildren().stream().anyMatch(n -> n.getId().equals(node.getId()))
          || (trueChildren.size() == 2
              && trueChildren.containsAll(List.of(DV_TEXT, DV_CODED_TEXT)));
    } else if (node.getRmType().equals(CODE_PHRASE) && parent != null) {
      return parent.getRmType().equals(DV_CODED_TEXT);
    } else if (node.getRmType().equals(ISM_TRANSITION)) {
      return !node.getId().equals("ism_transition");
    }
    return false;
  }

  public boolean isEvent(WebTemplateNode node) {
    RMTypeInfo typeInfo = Walker.ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
    return typeInfo != null && Event.class.isAssignableFrom(typeInfo.getJavaClass());
  }

  public boolean isNonMandatoryRmAttribute(WebTemplateNode node, WebTemplateNode parent) {
    RMTypeInfo typeInfo = Walker.ARCHIE_RM_INFO_LOOKUP.getTypeInfo(parent.getRmType());
    boolean nonMandatoryRmAttribute =
        typeInfo.getAttributes().containsKey(node.getName()) && node.getMin() == 0;
    boolean mandatoryNotInWebTemplate =
        List.of(
                "name",
                "archetype_node_id",
                "origin",
                "media_type",
                "upper_included",
                "lower_included",
                "upper_unbounded",
                "lower_unbounded")
            .contains(node.getName());
    boolean nonMandatoryInWebTemplate =
        typeInfo.getRmName().equals("ACTIVITY") && node.getName().equals("timing")
            || typeInfo.getRmName().equals("INSTRUCTION") && node.getName().equals("expiry_time")
            || typeInfo.getRmName().equals("INTERVAL_EVENT") && node.getName().equals("width")
            || typeInfo.getRmName().equals("INTERVAL_EVENT")
                && node.getName().equals("math_function")
            || typeInfo.getRmName().equals(ISM_TRANSITION) && node.getName().equals("transition");

    return (nonMandatoryRmAttribute || mandatoryNotInWebTemplate) && !nonMandatoryInWebTemplate;
  }

  public static void consumeAllMatching(
      String term, Map<FlatPathDto, String> values, Set<String> consumedPaths) {
    consumedPaths.addAll(
        values.keySet().stream()
            .filter(s -> s.startsWith(term))
            .map(FlatPathDto::format)
            .collect(Collectors.toSet()));
  }

  /**
   * extract multi valued sub-values like
   * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:0|id": "id1",
   * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:0|type": "PERSON",
   * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:0|assigner": "assigner1",
   * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:0|issuer": "issuer1",
   * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:1|id": "id2",
   * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:1|type": "PERSON",
   * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:1|assigner": "assigner2",
   * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:1|issuer": "issuer2",
   *
   * @param term
   * @param values
   * @return
   */
  public static Map<Integer, Map<String, String>> extractMultiValued(
      String term, Map<FlatPathDto, String> values) {
    return values.entrySet().stream()
        .filter(s -> s.getKey().startsWith(term))
        .collect(
            Collectors.groupingBy(
                e -> Optional.ofNullable(e.getKey().getLast().getCount()).orElse(0),
                Collectors.toMap(
                    e1 -> Optional.ofNullable(e1.getKey().getLast().getAttributeName()).orElse(""),
                    stringStringEntry -> StringUtils.unwrap(stringStringEntry.getValue(), '"'))));
  }

  public static Map<FlatPathDto, String> filter(Map<FlatPathDto, String> values, String path) {

    return values.entrySet().stream()
        .filter(e -> e.getKey().startsWith(path))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  public static <E extends EnumValueSet> E findEnumValueOrThrow(String value, Class<E> clazz) {

    return Arrays.stream(clazz.getEnumConstants())
        .filter(e -> e.getCode().equals(value) || e.getValue().equals(value))
        .findAny()
        .orElseThrow(
            () ->
                new SdkException(
                    String.format(
                        "Unknown Value %s in terminology %s",
                        value, clazz.getEnumConstants()[0].getTerminologyId())));
  }
}
