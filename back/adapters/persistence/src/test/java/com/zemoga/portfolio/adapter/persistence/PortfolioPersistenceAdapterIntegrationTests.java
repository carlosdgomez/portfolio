package com.zemoga.portfolio.adapter.persistence;

import static com.zemoga.portfolio.common.PortfolioTestData.defaultPortfolio;
import static org.assertj.core.api.Assertions.assertThat;

import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Import(PortfolioPersistenceAdapter.class)
class PortfolioPersistenceAdapterIntegrationTests {

  @Autowired
  PortfolioPersistenceAdapter portfolioPersistenceAdapter;

  @Test
  @Sql("PortfolioPersistenceAdapterTest.sql")
  void loadExistentPortfolio() {
    PortfolioId portfolioId = PortfolioId.of(1L);

    Optional<Portfolio> portfolio = portfolioPersistenceAdapter.loadPortfolio(portfolioId);

    assertThat(portfolio.isPresent()).isTrue();
  }

  @Test
  void loadNonExistentPortfolio() {
    PortfolioId portfolioId = PortfolioId.of(1L);

    Optional<Portfolio> portfolio = portfolioPersistenceAdapter.loadPortfolio(portfolioId);

    assertThat(portfolio.isPresent()).isFalse();
  }

  @Test
  @Sql("PortfolioPersistenceAdapterTest.sql")
  void updatePortfolio() {
    Portfolio portfolio = defaultPortfolio()
        .withDescription("New description")
        .build();

    portfolioPersistenceAdapter.updatePortfolio(portfolio);
    Optional<Portfolio> updatedPortfolio = portfolioPersistenceAdapter
        .loadPortfolio(portfolio.getId());

    assertThat(updatedPortfolio.isPresent()).isTrue();
    assertThat(updatedPortfolio.get().getDescription()).isEqualTo("New description");
  }

  @Test
  @Sql("PortfolioPersistenceAdapterTest.sql")
  void slicePortfolios() {
    List<Portfolio> portfolios = portfolioPersistenceAdapter.slicePortfolios(0, 2);

    assertThat(portfolios).isNotNull();
    assertThat(portfolios.size()).isEqualTo(2);
  }

  @Test
  void slicePortfoliosWhenTableIsEmpty() {
    List<Portfolio> portfolios = portfolioPersistenceAdapter.slicePortfolios(0, 2);

    assertThat(portfolios).isNotNull();
    assertThat(portfolios.size()).isEqualTo(0);
  }
}
