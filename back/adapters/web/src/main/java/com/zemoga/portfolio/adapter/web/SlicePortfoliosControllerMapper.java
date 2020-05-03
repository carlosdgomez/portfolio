package com.zemoga.portfolio.adapter.web;

import com.zemoga.portfolio.domain.Portfolio;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class SlicePortfoliosControllerMapper {

  public static SlicePortfolioResponse mapToResponse(Portfolio portfolio) {
    return SlicePortfolioResponse.builder()
        .withId(portfolio.getId().getValue())
        .withDescription(portfolio.getDescription())
        .withImageUrl(portfolio.getImageUrl())
        .withTwitterUserName(portfolio.getTwitterUserName())
        .withTitle(portfolio.getTitle())
        .build();
  }
}
