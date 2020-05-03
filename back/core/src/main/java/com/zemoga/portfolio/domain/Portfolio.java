package com.zemoga.portfolio.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
public class Portfolio {

  @NonNull
  private PortfolioId id;
  @NonNull
  private String description;
  @NonNull
  private String imageUrl;
  @NonNull
  private String twitterUserName;
  @NonNull
  private String title;

  @Value(staticConstructor = "of")
  public static class PortfolioId {
    private Long value;
  }
}
