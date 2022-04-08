package org.ehrbase.functional;

public abstract class Try<S,E extends Exception> {
  public static <S1,E1 extends Exception> Success<S1,E1> success(S1 result) {
    return new Success<>(result);
  }
  
  public static <S1,E1 extends Exception> Failure<S1,E1> failure(E1 failure) {
    return new Failure<>(failure);
  }
  
  protected final Object result;
  
  protected Try(Object result) {
    this.result = result;
  }
  
  public abstract boolean isSuccess();
  public abstract boolean isFailure();
  public abstract Object get();
  public abstract S getOrThrow() throws E;
  public abstract Success<S,E> getAsSuccess();
  public abstract Failure<S,E> getAsFailure();
  
  public static class Success<S0,E0 extends Exception> extends Try<S0,E0> {
    private Success(S0 success) {
      super(success);
    }

    public boolean isSuccess() {
      return true;
    }

    public boolean isFailure() {
      return false;
    }
    
    @SuppressWarnings("unchecked")
    public S0 get() {
      return (S0) super.result;
    }

    public S0 getOrThrow() throws E0 {
      return get();
    }

    public Success<S0, E0> getAsSuccess() {
      return this;
    }

    public Failure<S0, E0> getAsFailure() {
      throw new UnsupportedOperationException();
    }
  }
  
  public static class Failure<S0,E0 extends Exception> extends Try<S0,E0> {
    private Failure(E0 failure) {
      super(failure);
    }

    public boolean isSuccess() {
      return false;
    }

    public boolean isFailure() {
      return true;
    }
    
    @SuppressWarnings("unchecked")
    public E0 get() {
      return (E0) super.result;
    }

    public S0 getOrThrow() throws E0 {
      throw get();
    }
    
    public Success<S0, E0> getAsSuccess() {
      throw new UnsupportedOperationException();
    }

    public Failure<S0, E0> getAsFailure() {
      return this;
    }
  }
}
