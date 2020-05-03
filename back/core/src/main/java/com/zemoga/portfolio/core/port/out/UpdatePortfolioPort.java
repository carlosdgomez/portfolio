package com.zemoga.portfolio.core.port.out;

import com.zemoga.portfolio.domain.Portfolio;

public interface UpdatePortfolioPort {

  /**
   * Updates a Portfolio by its ID in the data source.
   *
   * @param portfolio the {@link Portfolio} to be updated
   */
  void updatePortfolio(Portfolio portfolio);
}
