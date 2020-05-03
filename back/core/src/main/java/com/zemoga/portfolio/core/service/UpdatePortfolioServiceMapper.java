package com.zemoga.portfolio.core.service;

import com.zemoga.portfolio.core.port.in.UpdatePortfolioUseCase.UpdatePortfolioCommand;
import com.zemoga.portfolio.domain.Portfolio;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdatePortfolioServiceMapper {

  public static Portfolio mapToEntity(UpdatePortfolioCommand command) {
    return Portfolio.builder()
        .withId(command.getId())
        .withDescription(command.getDescription())
        .withImageUrl(command.getImageUrl())
        .withTwitterUserName(command.getTwitterUserName())
        .withTitle(command.getTitle())
        .build();
  }
}
