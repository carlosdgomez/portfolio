package com.zemoga.portfolio.adapter.web;

import com.zemoga.portfolio.common.annotation.WebAdapter;
import com.zemoga.portfolio.core.port.in.SlicePortfoliosUseCase;
import com.zemoga.portfolio.domain.Portfolio;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class SlicePortfoliosController {

  private final SlicePortfoliosUseCase slicePortfoliosUseCase;

  @GetMapping("/portfolios")
  public List<SlicePortfolioResponse> slicePortfolio(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    List<Portfolio> portfolios = slicePortfoliosUseCase.slicePortfolios(page, size);
    return portfolios
        .stream()
        .map(SlicePortfoliosControllerMapper::mapToResponse)
        .collect(Collectors.toList());
  }
}
