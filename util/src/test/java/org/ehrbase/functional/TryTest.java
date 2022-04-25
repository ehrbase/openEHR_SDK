package org.ehrbase.functional;

import java.util.ArrayList;
import java.util.List;

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
    
    Assertions.assertTrue(result*2 == success.map((i, e) -> i + i));
    
    List<Integer> list = new ArrayList<>();
    success.consume((i, e) -> list.add(i));
    Assertions.assertTrue(list.size() == 1);
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
    
    Assertions.assertEquals(exception, failure.map((i, e) -> e));
    
    List<Exception> list = new ArrayList<>();
    failure.consume((i, e) -> list.add(e));
    Assertions.assertTrue(list.size() == 1);
  }
}
