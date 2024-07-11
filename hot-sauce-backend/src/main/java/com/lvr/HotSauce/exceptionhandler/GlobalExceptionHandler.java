package com.lvr.HotSauce.exceptionhandler;

import com.lvr.HotSauce.customExceptions.InvalidQuantityException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleEntityNotFoundException(
      EntityNotFoundException entityNotFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity does not exist");
  }

  @ExceptionHandler(InvalidQuantityException.class)
  public ResponseEntity<String> handleInvalidQuantityException(
      InvalidQuantityException invalidQuantityException) {
    return ResponseEntity.badRequest().body("Quantity must be between 0 and 99");
  }
}
