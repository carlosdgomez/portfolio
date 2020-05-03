package com.zemoga.portfolio.adapter.web;

import static com.zemoga.portfolio.common.PortfolioTestData.defaultPortfolio;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.portfolio.common.exception.ResourceNotFoundException;
import com.zemoga.portfolio.core.port.in.LoadPortfolioUseCase;
import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(LoadPortfolioController.class)
public class LoadPortfolioControllerIntegrationTests {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  @MockBean
  LoadPortfolioUseCase loadPortfolioUseCase;

  @Test
  void loadPortfolioSucceeds() throws Exception {
    Portfolio portfolio = defaultPortfolio()
        .withId(PortfolioId.of(1L))
        .build();
    String expectedResponse = objectMapper.writeValueAsString(mapToResponse(portfolio));
    when(loadPortfolioUseCase.loadPortfolio(portfolio.getId())).thenReturn(portfolio);

    MvcResult mvcResult = mockMvc.perform(get("/portfolios/{id}", 1L)
        .header("Content-Type", "application/json"))
        .andExpect(status().isOk())
        .andReturn();

    assertThat(expectedResponse)
        .isEqualToIgnoringWhitespace(mvcResult.getResponse().getContentAsString());

    then(loadPortfolioUseCase).should().loadPortfolio(portfolio.getId());
  }

  @Test
  void loadNonExistentPortfolio() throws Exception {
    PortfolioId id = PortfolioId.of(1L);
    when(loadPortfolioUseCase.loadPortfolio(id)).thenThrow(ResourceNotFoundException.class);

    mockMvc.perform(get("/portfolios/{id}", id.getValue())
        .header("Content-Type", "application/json"))
        .andExpect(status().isNotFound());

    then(loadPortfolioUseCase).should().loadPortfolio(id);
  }

  private LoadPortfolioResponse mapToResponse(Portfolio portfolio) {
    return LoadPortfolioResponse.builder()
        .withId(portfolio.getId().getValue())
        .withDescription(portfolio.getDescription())
        .withImageUrl(portfolio.getImageUrl())
        .withTwitterUserName(portfolio.getTwitterUserName())
        .withTitle(portfolio.getTitle())
        .build();
  }
}
