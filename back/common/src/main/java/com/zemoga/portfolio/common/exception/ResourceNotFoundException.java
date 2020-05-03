package com.zemoga.portfolio.common.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException() {
    super();
  }

  public ResourceNotFoundException(Class<?> clazz, Object id) {
    super(String.format("%s was not found by ID %s", clazz.getSimpleName(), id));
  }
}
