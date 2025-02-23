package org.example.common.ui;


import lombok.extern.slf4j.Slf4j;
import org.example.common.domain.exception.ErrorCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(IllegalAccessException.class)
  public Response<Void> handleIllegalAccessException() {
    return Response.error(ErrorCode.INTERNAL_ERROR);
  }
  
  @ExceptionHandler(Exception.class)
  public Response<Void> handleException(IllegalAccessException exception) {
    log.error(exception.getMessage());
      return Response.error(ErrorCode.INTERNAL_ERROR);
  }
}
