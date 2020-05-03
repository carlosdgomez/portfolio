package com.zemoga.portfolio.adapter.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class UpdatePortfolioRequest {

  private String description;
  private String imageUrl;
  private String twitterUserName;
  private String title;
}
