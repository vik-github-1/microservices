package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account Information"
)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AccountsDto {

    @NotEmpty(message = "Account Number must not be empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    @Schema(
            description = "Account Number of a Eazy Bank Account",  example = "3454433243"
    )
    private Long accountNumber;

//    private Long customerId;
    @NotEmpty(message = "Account type cannot be null or empty")
    @Schema(
         description = "Account Type of Eazy Bank Account ", example = "SAVINGS"
            )
    private String accountType;

    @NotEmpty(message = "Branch Address cannot be null or empty")
    @Schema(
            description = "Eazy Bank Branch Address", example = "123 New York"
    )
    private String branchAddress;


}
