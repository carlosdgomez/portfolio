package com.zemoga.portfolio.adapter.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.portfolio.adapter.web.UpdatePortfolioRequest.UpdatePortfolioRequestBuilder;
import com.zemoga.portfolio.core.port.in.UpdatePortfolioUseCase;
import com.zemoga.portfolio.core.port.in.UpdatePortfolioUseCase.UpdatePortfolioCommand;
import com.zemoga.portfolio.core.port.in.UpdatePortfolioUseCase.UpdatePortfolioCommand.UpdatePortfolioCommandBuilder;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UpdatePortfolioController.class)
public class UpdatePortfolioControllerIntegrationTests {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  @MockBean
  UpdatePortfolioUseCase updatePortfolioUseCase;

  @Test
  void loadPortfolioSucceeds() throws Exception {
    UpdatePortfolioRequest request = givenUpdatePortfolioRequest().build();
    UpdatePortfolioCommand command = givenCommandFromRequest(request).build();

    mockMvc.perform(put("/portfolios/{id}", 1L)
        .header("Content-Type", "application/json")
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isNoContent());

    then(updatePortfolioUseCase).should().updatePortfolio(command);
  }

  @Test
  void loadPortfolioShouldFailWhenRequestDataIsInvalid() throws Exception {
    UpdatePortfolioRequest request = givenUpdatePortfolioRequest()
        .withDescription(null)
        .withImageUrl(null)
        .withTwitterUserName(null)
        .withTitle(null)
        .build();

    mockMvc.perform(put("/portfolios/{id}", 1L)
        .header("Content-Type", "application/json")
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());

    then(updatePortfolioUseCase).should(times(0)).updatePortfolio(any());
  }

  private UpdatePortfolioRequestBuilder givenUpdatePortfolioRequest() {
    return UpdatePortfolioRequest.builder()
        .withDescription("New description")
        .withImageUrl("http://new_image.url")
        .withTwitterUserName("new_foo_bar")
        .withTitle("New Foo Bar");
  }

  private UpdatePortfolioCommandBuilder givenCommandFromRequest(UpdatePortfolioRequest request) {
    return UpdatePortfolioCommand.builder()
        .withId(PortfolioId.of(1L))
        .withDescription(request.getDescription())
        .withImageUrl(request.getImageUrl())
        .withTwitterUserName(request.getTwitterUserName())
        .withTitle(request.getTitle());
  }
}
