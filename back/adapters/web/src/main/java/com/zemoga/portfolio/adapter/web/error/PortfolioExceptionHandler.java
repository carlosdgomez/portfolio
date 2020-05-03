package com.zemoga.portfolio.adapter.web.error;

import com.zemoga.portfolio.common.exception.ResourceNotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PortfolioExceptionHandler {

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ResourceNotFoundException.class)
  public ApiError handleResourceNotFound(ResourceNotFoundException ex,
      HttpServletRequest request) {
    return ApiError.builder()
        .withError("Not Found")
        .withMessage(ex.getMessage())
        .withPath(request.getRequestURI())
        .withStatus(404)
        .withTimestamp(new Date())
        .build();
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public ApiError handleResourceNotFound(ConstraintViolationException ex,
      HttpServletRequest request) {
    List<String> errors = Arrays.asList(ex.getMessage().split(","));
    return ApiError.builder()
        .withError("Bad Request")
        .withMessage(errors.get(0))
        .withPath(request.getRequestURI())
        .withStatus(400)
        .withTimestamp(new Date())
        .build();
  }
}
