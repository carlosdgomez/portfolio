package com.zemoga.portfolio.adapter.web;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "with")
class LoadPortfolioResponse {

  private Long id;
  private String description;
  private String imageUrl;
  private String twitterUserName;
  private String title;
}
