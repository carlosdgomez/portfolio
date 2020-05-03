package com.zemoga.portfolio.core.port.in;

import com.zemoga.portfolio.common.exception.ResourceNotFoundException;
import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;

public interface LoadPortfolioUseCase {

  /**
   * Finds a {@link Portfolio} by its ID from the data source.
   *
   * @param id Portfolio ID
   * @return a {@link Portfolio} if it is found
   * @throws ResourceNotFoundException if a Portfolio could not be found
   */
  Portfolio loadPortfolio(PortfolioId id);
}
