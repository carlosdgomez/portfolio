package com.zemoga.portfolio.adapter.web;

import static com.zemoga.portfolio.common.PortfolioTestData.defaultPortfolio;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.portfolio.core.port.in.SlicePortfoliosUseCase;
import com.zemoga.portfolio.domain.Portfolio;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(SlicePortfoliosController.class)
public class SlicePortfoliosControllerIntegrationTests {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  @MockBean
  SlicePortfoliosUseCase slicePortfoliosUseCase;

  @Test
  void slicePortfolios() throws Exception {
    List<Portfolio> portfolios = givenPortfolioList();
    String expectedResponse = objectMapper.writeValueAsString(mapToResponse(portfolios.get(0)));
    when(slicePortfoliosUseCase.slicePortfolios(0, 2)).thenReturn(portfolios);

    MvcResult mvcResult = mockMvc.perform(get("/portfolios")
        .header("Content-Type", "application/json")
        .param("page", "0")
        .param("size", "2"))
        .andExpect(status().isOk())
        .andReturn();

    assertThat(expectedResponse)
        .isEqualToIgnoringWhitespace(mvcResult.getResponse().getContentAsString());

    then(slicePortfoliosUseCase).should().slicePortfolios(0, 2);
  }

  private List<Portfolio> givenPortfolioList() {
    List<Portfolio> portfolios = new ArrayList<>();
    portfolios.add(defaultPortfolio().build());
    return portfolios;
  }

  private List<SlicePortfolioResponse> mapToResponse(Portfolio portfolio) {
    List<SlicePortfolioResponse> response = new ArrayList<>();
    response.add(
        SlicePortfolioResponse.builder()
            .withId(portfolio.getId().getValue())
            .withDescription(portfolio.getDescription())
            .withImageUrl(portfolio.getImageUrl())
            .withTwitterUserName(portfolio.getTwitterUserName())
            .withTitle(portfolio.getTitle())
            .build()
    );
    return response;
  }
}
