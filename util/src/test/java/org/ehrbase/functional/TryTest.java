package org.ehrbase.functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TryTest {

  @Test
  void success() throws Exception {
    Integer result = Integer.valueOf(123);
    Try<Integer, Exception> success = Try.success(result);
    
    Assertions.assertTrue(success.isSuccess());
    Assertions.assertFalse(success.isFailure());
    Assertions.assertEquals(result, success.getAsSuccess().get());
    Assertions.assertEquals(result, success.get());
    Assertions.assertEquals(result, success.getOrThrow());
    Assertions.assertThrows(UnsupportedOperationException.class, () -> success.getAsFailure());
  }
  
  @Test
  void filure() {
    RuntimeException exception = new RuntimeException();
    Try<Object, Exception> failure = Try.failure(exception);
    
    Assertions.assertFalse(failure.isSuccess());
    Assertions.assertTrue(failure.isFailure());
    Assertions.assertEquals(exception, failure.getAsFailure().get());
    Assertions.assertEquals(exception, failure.get());
    Assertions.assertThrows(RuntimeException.class, () -> failure.getOrThrow());
    Assertions.assertThrows(UnsupportedOperationException.class, () -> failure.getAsSuccess());
  }

}
