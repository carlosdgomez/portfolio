package com.zemoga.portfolio.core.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.zemoga.portfolio.common.exception.ResourceNotFoundException;
import com.zemoga.portfolio.core.port.in.LoadPortfolioUseCase;
import com.zemoga.portfolio.core.port.in.UpdatePortfolioUseCase.UpdatePortfolioCommand;
import com.zemoga.portfolio.core.port.out.UpdatePortfolioPort;
import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import org.junit.jupiter.api.Test;

class UpdatePortfolioServiceTests {

  LoadPortfolioUseCase loadPortfolioUseCase = mock(LoadPortfolioUseCase.class);
  UpdatePortfolioPort updatePortfolioPort = mock(UpdatePortfolioPort.class);
  UpdatePortfolioService updatePortfolioService = new UpdatePortfolioService(
      loadPortfolioUseCase,
      updatePortfolioPort);

  @Test
  void updateExistentPortfolio() {
    UpdatePortfolioCommand command = defaultUpdatePortfolioCommand();
    Portfolio portfolio = mapToEntity(command);

    updatePortfolioService.updatePortfolio(command);

    then(loadPortfolioUseCase).should().loadPortfolio(command.getId());
    then(updatePortfolioPort).should().updatePortfolio(portfolio);
  }

  @Test
  void updateNonExistentPortfolio() {
    UpdatePortfolioCommand command = defaultUpdatePortfolioCommand();
    Portfolio portfolio = mapToEntity(command);
    when(loadPortfolioUseCase.loadPortfolio(command.getId()))
        .thenThrow(ResourceNotFoundException.class);

    assertThrows(ResourceNotFoundException.class,
        () -> updatePortfolioService.updatePortfolio(command));

    then(loadPortfolioUseCase).should().loadPortfolio(command.getId());
    then(updatePortfolioPort).should(times(0)).updatePortfolio(portfolio);
  }

  private UpdatePortfolioCommand defaultUpdatePortfolioCommand() {
    return UpdatePortfolioCommand.builder()
        .withId(PortfolioId.of(1L))
        .withDescription("Default description")
        .withImageUrl("http://foobar.baz")
        .withTwitterUserName("foo_bar_baz")
        .withTitle("Foo Bar")
        .build();
  }

  private Portfolio mapToEntity(UpdatePortfolioCommand command) {
    return Portfolio.builder()
        .withId(command.getId())
        .withDescription(command.getDescription())
        .withImageUrl(command.getImageUrl())
        .withTwitterUserName(command.getTwitterUserName())
        .withTitle(command.getTitle())
        .build();
  }
}
