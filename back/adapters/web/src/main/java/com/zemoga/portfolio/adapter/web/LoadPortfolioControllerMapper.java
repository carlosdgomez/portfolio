package com.zemoga.portfolio.adapter.web;

import com.zemoga.portfolio.domain.Portfolio;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class LoadPortfolioControllerMapper {

  public static LoadPortfolioResponse mapToResponse(Portfolio portfolio) {
    return LoadPortfolioResponse.builder()
        .withId(portfolio.getId().getValue())
        .withDescription(portfolio.getDescription())
        .withImageUrl(portfolio.getImageUrl())
        .withTwitterUserName(portfolio.getTwitterUserName())
        .withTitle(portfolio.getTitle())
        .build();
  }
}
