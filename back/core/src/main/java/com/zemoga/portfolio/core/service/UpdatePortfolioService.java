package com.zemoga.portfolio.core.service;

import static com.zemoga.portfolio.core.service.UpdatePortfolioServiceMapper.mapToEntity;

import com.zemoga.portfolio.common.annotation.UseCase;
import com.zemoga.portfolio.core.port.in.LoadPortfolioUseCase;
import com.zemoga.portfolio.core.port.in.UpdatePortfolioUseCase;
import com.zemoga.portfolio.core.port.out.UpdatePortfolioPort;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdatePortfolioService implements UpdatePortfolioUseCase {

  private final LoadPortfolioUseCase loadPortfolioUseCase;
  private final UpdatePortfolioPort updatePortfolioPort;

  @Override
  public void updatePortfolio(UpdatePortfolioCommand command) {
    loadPortfolioUseCase.loadPortfolio(command.getId());
    updatePortfolioPort.updatePortfolio(mapToEntity(command));
  }
}
