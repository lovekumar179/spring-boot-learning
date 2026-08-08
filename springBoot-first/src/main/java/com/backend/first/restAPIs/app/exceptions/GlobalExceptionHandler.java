package com.backend.first.restAPIs.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
  // EXCEPTION HANDLING METHOD
//  @ExceptionHandler(IllegalArgumentException.class) // FOR SINGLE
  @ExceptionHandler({UserNotFoundException.class, IllegalArgumentException.class, NullPointerException.class}) // FOR MULTIPLE
  public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
//      IllegalArgumentException exception
      Exception exception
  ) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("timestamp", LocalDateTime.now());
    errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
    errorResponse.put("error", "Bad Request");
    errorResponse.put("message", exception.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  //TODO HANDLE INBUILD EXCEPTIONS
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<Map<String, Object>> handleMethodNotSupported(
      HttpRequestMethodNotSupportedException exception
  ) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("timestamp", LocalDateTime.now());
    errorResponse.put("status", HttpStatus.METHOD_NOT_ALLOWED.value());
    errorResponse.put("error", "Method not allowed on this end point");
    errorResponse.put("message", exception.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
  }

}

// DEFAULT SPRING BOOT ERROR MECHANISM
/*
* {
    "timestamp": "2026-08-08T08:40:06.992Z",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/user"
}
*/
