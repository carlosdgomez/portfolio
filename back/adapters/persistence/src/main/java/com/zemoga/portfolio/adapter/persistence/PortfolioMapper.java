package com.zemoga.portfolio.adapter.persistence;

import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PortfolioMapper {

  static Portfolio mapToDomainEntity(PortfolioJpaEntity portfolioEntity) {
    return Portfolio.builder()
        .withId(PortfolioId.of(portfolioEntity.getId()))
        .withDescription(portfolioEntity.getDescription())
        .withImageUrl(portfolioEntity.getImageUrl())
        .withTwitterUserName(portfolioEntity.getTwitterUserName())
        .withTitle(portfolioEntity.getTitle())
        .build();
  }

  static PortfolioJpaEntity mapToJpaEntity(Portfolio portfolio) {
    return PortfolioJpaEntity.builder()
        .withId(portfolio.getId().getValue())
        .withDescription(portfolio.getDescription())
        .withImageUrl(portfolio.getImageUrl())
        .withTwitterUserName(portfolio.getTwitterUserName())
        .withTitle(portfolio.getTitle())
        .build();
  }

  static List<Portfolio> slicePortfolios(List<PortfolioJpaEntity> portfolios) {
    return portfolios
        .stream()
        .map(PortfolioMapper::mapToDomainEntity)
        .collect(Collectors.toList());
  }
}
