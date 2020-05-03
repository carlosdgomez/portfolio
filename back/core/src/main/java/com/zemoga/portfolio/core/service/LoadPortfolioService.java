package com.zemoga.portfolio.core.service;

import com.zemoga.portfolio.common.annotation.UseCase;
import com.zemoga.portfolio.common.exception.ResourceNotFoundException;
import com.zemoga.portfolio.core.port.in.LoadPortfolioUseCase;
import com.zemoga.portfolio.core.port.out.LoadPortfolioPort;
import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class LoadPortfolioService implements LoadPortfolioUseCase {

  private final LoadPortfolioPort loadPortfolioPort;

  @Override
  public Portfolio loadPortfolio(PortfolioId id) {
    return loadPortfolioPort.loadPortfolio(id)
        .orElseThrow(() -> new ResourceNotFoundException(Portfolio.class, id.getValue()));
  }
}
