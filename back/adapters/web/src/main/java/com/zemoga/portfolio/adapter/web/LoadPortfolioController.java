package com.zemoga.portfolio.adapter.web;

import static com.zemoga.portfolio.adapter.web.LoadPortfolioControllerMapper.mapToResponse;

import com.zemoga.portfolio.common.annotation.WebAdapter;
import com.zemoga.portfolio.core.port.in.LoadPortfolioUseCase;
import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class LoadPortfolioController {

  private final LoadPortfolioUseCase loadPortfolioUseCase;

  @GetMapping("/portfolios/{id}")
  public LoadPortfolioResponse loadPortfolio(@PathVariable Long id) {
    Portfolio portfolio = loadPortfolioUseCase.loadPortfolio(PortfolioId.of(id));
    return mapToResponse(portfolio);
  }
}
