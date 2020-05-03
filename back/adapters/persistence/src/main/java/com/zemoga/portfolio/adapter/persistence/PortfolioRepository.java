package com.zemoga.portfolio.adapter.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface PortfolioRepository extends CrudRepository<PortfolioJpaEntity, Long> {

  @Query("select p from PortfolioJpaEntity p")
  Slice<PortfolioJpaEntity> findAllSlice(Pageable pageable);
}
