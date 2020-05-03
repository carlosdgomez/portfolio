package com.zemoga.portfolio.adapter.web;

import com.zemoga.portfolio.core.port.in.UpdatePortfolioUseCase.UpdatePortfolioCommand;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdatePortfolioControllerMapper {

  public static UpdatePortfolioCommand mapToCommand(Long id, UpdatePortfolioRequest request) {
    return UpdatePortfolioCommand.builder()
        .withId(PortfolioId.of(id))
        .withDescription(request.getDescription())
        .withImageUrl(request.getImageUrl())
        .withTwitterUserName(request.getTwitterUserName())
        .withTitle(request.getTitle())
        .build();
  }
}
