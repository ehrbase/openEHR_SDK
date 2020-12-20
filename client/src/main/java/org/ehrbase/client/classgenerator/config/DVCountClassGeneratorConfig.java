package org.ehrbase.client.classgenerator.config;

import com.nedap.archie.rm.datavalues.quantity.DvCount;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DVCountClassGeneratorConfig implements RmClassGeneratorConfig {
  private static final Set<String> FIELDS = Stream.of("magnitude").collect(Collectors.toSet());

  @Override
  public Class getAssociatedClass() {
    return DvCount.class;
  }

  @Override
  public Set<String> getExpandFields() {
    return FIELDS;
  }
}
