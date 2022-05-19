package org.ehrbase.serialisation.walker;

import org.ehrbase.webtemplate.model.WebTemplateNode;

import java.util.Objects;

public class NodeId {

  private final String aql;
  private final String name;
  private final String rmClass;

  public NodeId(WebTemplateNode node) {
    this.aql = node.getAqlPath(true);
    this.name = node.getName();
    this.rmClass = node.getRmType();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NodeId nodeId = (NodeId) o;
    return Objects.equals(aql, nodeId.aql)
        && Objects.equals(name, nodeId.name)
        && Objects.equals(rmClass, nodeId.rmClass);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aql, name, rmClass);
  }
}
