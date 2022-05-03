package org.ehrbase.functional;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public abstract class Either<L,R> {
  public static <L1,R1> Left<L1,R1> left(L1 value) {
    return new Left<>(value);
  }
  
  public static <L1,R1> Right<L1,R1> right(R1 value) {
    return new Right<>(value);
  }
  
  protected final Object value;
  
  protected Either(Object value) {
    this.value = value;
  }
  
  public abstract boolean isLeft();
  public abstract boolean isRight();
  public abstract Object get();
  public abstract L getAsLeft();
  public abstract R getAsRight();
  
  
  /**
   * Convenience method to transform the internal state
   * @param map function which maps the internal state to the specified type
   * The function must be null safe in both arguments
   * @return the mapped internal state
   */
  public abstract <T> T map(BiFunction<L,R,T> map);

  /**
   * Convenience method to consume the internal state
   * @param con consumer which consumes the internal state
   * The consumer must be null safe in both arguments
   */
  public abstract void consume(BiConsumer<L,R> con);
  
  public static class Left<L,R> extends Either<L,R> {
    private Left(L value) {
      super(value);
    }
    
    public boolean isLeft() {
      return true;
    }

    public boolean isRight() {
      return false;
    }

    @SuppressWarnings("unchecked")
    public L get() {
      return (L) value;
    }

    public L getAsLeft() {
      return this.get();
    }

    public R getAsRight() {
      throw new UnsupportedOperationException();
    }

    public <T> T map(BiFunction<L, R, T> map) {
      return map.apply(get(), null);
    }

    public void consume(BiConsumer<L, R> con) {
      con.accept(get(), null);
    }
  }
  
  public static class Right<L,R> extends Either<L,R> {
    private Right(R value) {
      super(value);
    }
    
    public boolean isLeft() {
      return false;
    }

    public boolean isRight() {
      return true;
    }

    @SuppressWarnings("unchecked")
    public R get() {
      return (R) value;
    }

    public L getAsLeft() {
      throw new UnsupportedOperationException();
    }

    public R getAsRight() {
      return this.get();
    }

    public <T> T map(BiFunction<L, R, T> map) {
      return map.apply(null, get());
    }

    public void consume(BiConsumer<L, R> con) {
      con.accept(null, get());
    }
  }
}
