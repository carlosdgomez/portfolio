package com.zemoga.portfolio.common;

import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.Portfolio.PortfolioBuilder;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PortfolioTestData {

  public static PortfolioBuilder defaultPortfolio() {
    return Portfolio.builder()
        .withId(PortfolioId.of(1L))
        .withDescription("Default description")
        .withImageUrl("http://foo.bar.baz")
        .withTwitterUserName("foo_bar_baz")
        .withTitle("Foo Bar");
  }
}
