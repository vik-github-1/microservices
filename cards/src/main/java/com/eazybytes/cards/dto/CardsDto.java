package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Schema(
      name ="Cards",
      description="Schema to hold Cards Information"
)
@Getter
@Setter
public class CardsDto {

    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    @Schema(description = "Mobile number of the customer", example = "4354437687")
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be null or empty")
    @Pattern(regexp="(^$|[0-9]{12})", message = "Card Number must be 12 digits")
    @Schema(description = "Card number of the customer", example = "100646930341")
    private Long cardNumber;

    @NotEmpty(message = "Card type cannot be null or empty")
    @Schema(description = "Card type of the customer", example = "Credit Card")
    private String cardType;

    @Positive(message = "Total card limit must be greater than zero")
    @Schema(description = "Total amount limit against a card", example = "1000000")
    private int totalLimit;

    @Positive(message = "Total amount Used should be greater or equal to zero")
    @Schema(description = "Total amount used by the customer",example = "1000")
    private int amountUsed;

    @Positive(message = "Total available amount should be greater or equal to zero")
    @Schema(description = "Total available amount of the customer", example = "90000")
    private int availableAmount;
}
