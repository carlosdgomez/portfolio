package com.zemoga.portfolio.adapter.web.error;

import java.util.Date;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
public class ApiError {

  private String error;
  private String message;
  private String path;
  private Integer status;
  private Date timestamp;
}
