package org.ehrbase.serialisation.matrix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Stefan Spiska
 */
public class Index {

private final Map<Integer,Integer> indexMap = new LinkedHashMap<>();
private Integer index=0;

  public Index() {
  }

  public Index(Index other) {
    this.index = other.index;
    indexMap.putAll(other.indexMap);
  }

  public void incrementIndex(){
    index++;

  }

  public void setRepetition(Integer repetition){

    indexMap.put(index, repetition);
  }

  List<Integer> getRepetitions(){

    return new ArrayList<>(indexMap.values());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Index index1 = (Index) o;
    return Objects.equals(indexMap, index1.indexMap) && Objects.equals(index, index1.index);
  }

  @Override
  public int hashCode() {
    return Objects.hash(indexMap, index);
  }

  @Override
  public String toString() {
    return "Index{" +
        "indexMap=" + indexMap +
        ", index=" + index +
        '}';
  }
}
