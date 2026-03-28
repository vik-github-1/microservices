package com.eazybytes.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Loans",
        description = "Schema to hold Loans information")
@Data
public class LoansDto {

    @NotEmpty(message = "Mobile Number cannot be null or Empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Schema(
          description = "Mobile Number of the customer " , example = "4598765490"
    )
    private String mobileNumber;

    @NotEmpty(message = "Loan Number cannot be null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    @Schema(
            description = "Loan Number of the customer" , example = "546787654398"
    )
    private String loanNumber;

    @NotEmpty(message = "Loan Type cannot be null or empty")
    @Schema(
            description = "Type of Loan" , example = "Home Loan"
    )
    private String loanType;

    @Positive(message = " Total loan amount must be greater than zero")
    @Schema(
            description = "Total loan amount" , example = "100000"
    )
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid must be equal or greater than zero")
    @Schema(
            description = "Loan amount paid ", example = "10000"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount must be equal or greater than zero")
    @Schema(
            description = "Total outstanding amount", example = "90000"
    )
    private int outstandingAmount;
}
