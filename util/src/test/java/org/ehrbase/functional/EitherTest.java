package org.ehrbase.functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EitherTest {

  @Test
  void left() throws Exception {
    Integer result = Integer.valueOf(123);
    Either<Integer, Exception> left = Either.left(result);
    
    Assertions.assertTrue(left.isLeft());
    Assertions.assertFalse(left.isRight());
    Assertions.assertEquals(result, left.get());
    Assertions.assertEquals(result, left.getAsLeft());
    Assertions.assertThrows(UnsupportedOperationException.class, () -> left.getAsRight());
  }
  
  @Test
  void failure() {
    RuntimeException exception = new RuntimeException();
    Either<Object, Exception> right = Try.right(exception);
    
    Assertions.assertFalse(right.isLeft());
    Assertions.assertTrue(right.isRight());
    Assertions.assertEquals(exception, right.get());
    Assertions.assertEquals(exception, right.getAsRight());
    Assertions.assertThrows(UnsupportedOperationException.class, () -> right.getAsLeft());
  }
}
