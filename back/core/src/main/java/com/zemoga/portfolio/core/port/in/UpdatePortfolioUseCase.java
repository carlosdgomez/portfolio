package com.zemoga.portfolio.core.port.in;

import com.zemoga.portfolio.common.SelfValidating;
import com.zemoga.portfolio.common.exception.ResourceNotFoundException;
import com.zemoga.portfolio.domain.Portfolio.PortfolioId;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

public interface UpdatePortfolioUseCase {

  /**
   * Updates a Portfolio by its ID in the data source.
   *
   * @param command an {@link UpdatePortfolioCommand} with the data to update
   * @throws ResourceNotFoundException if the Portfolio to update could not be found
   */
  void updatePortfolio(UpdatePortfolioCommand command);

  @Value
  @Builder(setterPrefix = "with")
  @EqualsAndHashCode(callSuper = false)
  class UpdatePortfolioCommand extends SelfValidating<UpdatePortfolioCommand> {

    @NotNull
    private PortfolioId id;
    @NotBlank
    private String description;
    @NotNull
    @URL(regexp = "[(http(s)?):\\/\\/(www\\.)?\\-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
    private String imageUrl;
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_]{1,15}")
    private String twitterUserName;
    @NotBlank
    private String title;

    public UpdatePortfolioCommand(
        PortfolioId id,
        String description,
        String imageUrl,
        String twitterUserName,
        String title) {
      this.id = id;
      this.description = description;
      this.imageUrl = imageUrl;
      this.twitterUserName = twitterUserName;
      this.title = title;
      this.validateSelf();
    }
  }
}
