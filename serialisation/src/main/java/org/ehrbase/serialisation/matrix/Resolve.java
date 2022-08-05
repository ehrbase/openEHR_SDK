package org.ehrbase.serialisation.matrix;

import java.util.Objects;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class Resolve {

  private AqlPath pathFromRoot;
  private String archetypeId;

  private Integer count = 0;

  public Resolve() {
  }

  public Resolve(Resolve other) {
    this.pathFromRoot = AqlPath.parse(other.pathFromRoot.getPath());
    this.archetypeId = other.archetypeId;
  }



  public AqlPath getPathFromRoot() {
    return pathFromRoot;
  }

  public void setPathFromRoot(AqlPath pathFromRoot) {
    this.pathFromRoot = pathFromRoot;
  }

  public String getArchetypeId() {
    return archetypeId;
  }

  public void setArchetypeId(String archetypeId) {
    this.archetypeId = archetypeId;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Resolve resolve = (Resolve) o;
    return Objects.equals(pathFromRoot, resolve.pathFromRoot) &&
        Objects.equals(archetypeId, resolve.archetypeId) && Objects.equals(count, resolve.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pathFromRoot, archetypeId, count);
  }

  @Override
  public String toString() {
    return "Resolve{" +
        "pathFromRoot=" + pathFromRoot +
        ", archetypeId='" + archetypeId + '\'' +
        ", count=" + count +
        '}';
  }
}
