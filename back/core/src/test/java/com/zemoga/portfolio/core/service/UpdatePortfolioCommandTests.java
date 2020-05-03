package com.zemoga.portfolio.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.zemoga.portfolio.core.port.in.UpdatePortfolioUseCase.UpdatePortfolioCommand;
import com.zemoga.portfolio.core.port.in.UpdatePortfolioUseCase.UpdatePortfolioCommand.UpdatePortfolioCommandBuilder;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

class UpdatePortfolioCommandTests {

  @Test
  void validationShouldPassWhenPortfolioIsOk() {
    UpdatePortfolioCommandBuilder builder = defaultUpdatePortfolioCommand();

    UpdatePortfolioCommand command = builder.build();

    assertThat(command).isNotNull();
  }

  @Test
  void validateShouldFailWhenIdIsNull() {
    UpdatePortfolioCommandBuilder builder = defaultUpdatePortfolioCommand()
        .withId(null);

    assertThrows(ConstraintViolationException.class, builder::build);
  }

  @Test
  void validateShouldFailWhenDescriptionIsNull() {
    UpdatePortfolioCommandBuilder builder = defaultUpdatePortfolioCommand()
        .withDescription(null);

    assertThrows(ConstraintViolationException.class, builder::build);
  }

  @Test
  void validateShouldFailWhenDescriptionIsEmpty() {
    UpdatePortfolioCommandBuilder builder = defaultUpdatePortfolioCommand()
        .withDescription("");

    assertThrows(ConstraintViolationException.class, builder::build);
  }

  @Test
  void validationShouldFailWhenImageUrlIsNull() {
    UpdatePortfolioCommandBuilder builder = defaultUpdatePortfolioCommand()
        .withImageUrl(null);

    assertThrows(ConstraintViolationException.class, builder::build);
  }

  @Test
  void validationShouldFailWhenImageUrlIsEmpty() {
    UpdatePortfolioCommandBuilder builder = defaultUpdatePortfolioCommand()
        .withImageUrl("");

    assertThrows(ConstraintViolationException.class, builder::build);
  }

  @Test
  void validationShouldFailWhenImageUrlIsInvalid() {
    UpdatePortfolioCommandBuilder builder = defaultUpdatePortfolioCommand()
        .withImageUrl("invalid-url");

    assertThrows(ConstraintViolationException.class, builder::build);
  }

  @Test
  void validationShouldFailWhenTwitterUserNameIsNull() {
    UpdatePortfolioCommandBuilder builder = defaultUpdatePortfolioCommand()
        .withTwitterUserName(null);

    assertThrows(ConstraintViolationException.class, builder::build);
  }

  @Test
  void validationShouldFailWhenTwitterUserNameIsEmpty() {
    UpdatePortfolioCommandBuilder builder = defaultUpdatePortfolioCommand()
        .withTwitterUserName("");

    assertThrows(ConstraintViolationException.class, builder::build);
  }

  @Test
  void validationShouldFailWhenTwitterUserNameTooLong() {
    UpdatePortfolioCommandBuilder builder = defaultUpdatePortfolioCommand()
        .withTwitterUserName("too_long_username");

    assertThrows(ConstraintViolationException.class, builder::build);
  }

  @Test
  void validationShouldFailWhenTwitterUserNameHasInvalidCharacters() {
    UpdatePortfolioCommandBuilder builder = defaultUpdatePortfolioCommand()
        .withTwitterUserName("inv@lid*");

    assertThrows(ConstraintViolationException.class, builder::build);
  }

  private UpdatePortfolioCommandBuilder defaultUpdatePortfolioCommand() {
    return UpdatePortfolioCommand.builder()
        .withId(PortfolioId.of(1L))
        .withDescription("Default description")
        .withImageUrl("http://foobar.baz")
        .withTwitterUserName("foo_bar_baz")
        .withTitle("Foo Bar");
  }
}
