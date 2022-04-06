package org.ehrbase.functional;

import java.util.function.Supplier;

public interface ExceptionalSupplier<T, E extends Exception> extends Supplier<T> {
  T doGet() throws E;
  
  default T get() {
    try {
      return doGet();
    } catch(Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
