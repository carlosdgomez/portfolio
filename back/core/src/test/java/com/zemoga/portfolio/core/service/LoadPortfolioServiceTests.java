package com.zemoga.portfolio.core.service;

import static com.zemoga.portfolio.common.PortfolioTestData.defaultPortfolio;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

import com.zemoga.portfolio.common.exception.ResourceNotFoundException;
import com.zemoga.portfolio.core.port.out.LoadPortfolioPort;
import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LoadPortfolioServiceTests {

  LoadPortfolioPort loadPortfolioPort = Mockito.mock(LoadPortfolioPort.class);
  LoadPortfolioService loadPortfolioService = new LoadPortfolioService(loadPortfolioPort);

  @Test
  void loadPortfolio() {
    Portfolio portfolio = defaultPortfolio().build();
    PortfolioId id = portfolio.getId();
    when(loadPortfolioPort.loadPortfolio(id)).thenReturn(Optional.of(portfolio));

    loadPortfolioService.loadPortfolio(id);

    then(loadPortfolioPort).should().loadPortfolio(id);
  }

  @Test
  void whenLoadNonExistentPortfolioThrowsResourceNotFoundException() {
    PortfolioId id = PortfolioId.of(1L);

    assertThrows(ResourceNotFoundException.class, () -> loadPortfolioService.loadPortfolio(id));
  }
}
