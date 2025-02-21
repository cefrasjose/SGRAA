package com.sgraa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("message", ex.getMessage());
    response.put("status", HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<Map<String, Object>> handleBusinessRule(BusinessRuleException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("message", ex.getMessage());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<Map<String, Object>> handleUnauthorized(UnauthorizedException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("message", ex.getMessage());
    response.put("status", HttpStatus.UNAUTHORIZED.value());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }
}
