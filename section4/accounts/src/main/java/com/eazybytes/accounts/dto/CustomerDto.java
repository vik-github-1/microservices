package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Schema to hold Customer and Accounts information"
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CustomerDto {

   // private Long customerId;

    @NotEmpty(message = "Customer Name cannot be null or empty")
    @Size(min = 5 , max = 30 ,message = "The length of customer name must be between 5 and 30")
    @Schema(
            description = "Name of the customer", example = "eazybytes"
    )
    private String name;

    @NotEmpty(message = "Email address cannot be null or empty")
    @Email(message = "Email Address should be a valid value")
    @Schema(
            description = "Email Address of the customer ", example = "tutor@eazybytes.com"
    )
    private String email;

    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    private String mobileNumber;

    @Schema(
            description = "Account Details of the customer "
    )
    private AccountsDto accountsDto;

}
