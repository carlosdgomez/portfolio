package com.zemoga.portfolio.core.service;

import com.zemoga.portfolio.common.annotation.UseCase;
import com.zemoga.portfolio.core.port.in.SlicePortfoliosUseCase;
import com.zemoga.portfolio.core.port.out.SlicePortfoliosPort;
import com.zemoga.portfolio.domain.Portfolio;
import java.util.List;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SlicePortfoliosService implements SlicePortfoliosUseCase {

  private final SlicePortfoliosPort slicePortfoliosPort;

  @Override
  public List<Portfolio> slicePortfolios(int page, int size) {
    return slicePortfoliosPort.slicePortfolios(page, size);
  }
}
