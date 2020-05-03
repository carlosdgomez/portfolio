package com.zemoga.portfolio.adapter.web;

import static com.zemoga.portfolio.adapter.web.UpdatePortfolioControllerMapper.mapToCommand;

import com.zemoga.portfolio.common.annotation.WebAdapter;
import com.zemoga.portfolio.core.port.in.UpdatePortfolioUseCase;
import com.zemoga.portfolio.core.port.in.UpdatePortfolioUseCase.UpdatePortfolioCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class UpdatePortfolioController {

  private final UpdatePortfolioUseCase updatePortfolioUseCase;

  @PutMapping("/portfolios/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updatePortfolio(@PathVariable Long id, @RequestBody UpdatePortfolioRequest request) {
    UpdatePortfolioCommand command = mapToCommand(id, request);
    updatePortfolioUseCase.updatePortfolio(command);
  }
}
