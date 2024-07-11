package com.lvr.HotSauce.customExceptions;

public class InvalidQuantityException extends RuntimeException {
  public InvalidQuantityException(String message) {
    super(message);
  }
}
