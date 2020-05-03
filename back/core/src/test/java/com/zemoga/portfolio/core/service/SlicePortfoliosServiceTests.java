package com.zemoga.portfolio.core.service;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import com.zemoga.portfolio.core.port.out.SlicePortfoliosPort;
import org.junit.jupiter.api.Test;

class SlicePortfoliosServiceTests {

  SlicePortfoliosPort slicePortfoliosPort = mock(SlicePortfoliosPort.class);
  SlicePortfoliosService slicePortfoliosService = new SlicePortfoliosService(slicePortfoliosPort);

  @Test
  void slicePortfolio() {
    slicePortfoliosService.slicePortfolios(0, 2);

    then(slicePortfoliosPort).should().slicePortfolios(0, 2);
  }
}
