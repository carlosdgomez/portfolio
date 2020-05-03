package com.zemoga.portfolio.core.port.out;

import com.zemoga.portfolio.domain.Portfolio;
import java.util.List;

public interface SlicePortfoliosPort {

  /**
   * Finds a page of {@link Portfolio}s by a given size from the data source.
   *
   * @param page Page index of the slice
   * @param size Page size of the slice
   * @return a list of {@link Portfolio}s, if any (or an empty list otherwise)
   */
  List<Portfolio> slicePortfolios(int page, int size);
}
