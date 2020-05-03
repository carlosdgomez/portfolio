package com.zemoga.portfolio.adapter.persistence;

import com.zemoga.portfolio.common.annotation.PersistenceAdapter;
import com.zemoga.portfolio.core.port.out.LoadPortfolioPort;
import com.zemoga.portfolio.core.port.out.SlicePortfoliosPort;
import com.zemoga.portfolio.core.port.out.UpdatePortfolioPort;
import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@PersistenceAdapter
@RequiredArgsConstructor
class PortfolioPersistenceAdapter implements LoadPortfolioPort, UpdatePortfolioPort,
    SlicePortfoliosPort {

  private final PortfolioRepository portfolioRepository;

  @Override
  public Optional<Portfolio> loadPortfolio(PortfolioId portfolioId) {
    Optional<PortfolioJpaEntity> portfolioJpaEntity = portfolioRepository
        .findById(portfolioId.getValue());

    return portfolioJpaEntity.map(PortfolioMapper::mapToDomainEntity);
  }

  @Override
  public void updatePortfolio(Portfolio portfolio) {
    PortfolioJpaEntity jpaEntity = PortfolioMapper.mapToJpaEntity(portfolio);
    portfolioRepository.save(jpaEntity);
  }

  @Override
  public List<Portfolio> slicePortfolios(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Slice<PortfolioJpaEntity> portfolioPage = portfolioRepository.findAllSlice(pageable);
    return Optional.of(portfolioPage)
        .map(Slice::getContent)
        .map(PortfolioMapper::slicePortfolios)
        .orElseGet(Collections::emptyList);
  }
}
