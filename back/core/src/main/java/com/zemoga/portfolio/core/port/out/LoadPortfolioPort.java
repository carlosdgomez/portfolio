package com.zemoga.portfolio.core.port.out;

import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import java.util.Optional;

public interface LoadPortfolioPort {

  /**
   * Tries to find a Portfolio from the data source.
   *
   * @param id Portfolio ID
   * @return an {@link Optional}<{@link Portfolio}>
   */
  Optional<Portfolio> loadPortfolio(PortfolioId id);
}
