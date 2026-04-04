package com.eazybytes.loans.controller;

import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.ErrorResponseDto;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.dto.ResponseDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@AllArgsConstructor
public class LoansController {


    private ILoansService loansService;

    @Operation(
            summary = "Create Loan Rest Api",
            description = "Rest Api to create loan in Eazy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status Created"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )

    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){
         loansService.createLoan(mobileNumber);
         return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));
    }


    @Operation(
            summary = "Fetch Loan Rest Api",
            description = "Easy bank rest api to fetch loan based on mobile number"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Http Status Ok"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description="Http Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )

                    )
            }
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoan(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){
        LoansDto loansDto=loansService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }


    @Operation(
            summary = "Update Loans Rest Api",
            description = "Easy Bank Rest Api to Update Loan"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Https Status Ok"
                    ),
                    @ApiResponse(
                            responseCode = "417",
                            description="Http Status Expectation Failed"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@Valid @RequestBody LoansDto loansDto){

        boolean isUpdated =loansService.updateLoan(loansDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));

        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Loans Rest Api",
            description = "Easy Bank Rest Api to Delete Loan"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Https Status Ok"
                    ),
                    @ApiResponse(
                            responseCode = "417",
                            description="Http Status Expectation Failed"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Http Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){

        boolean isDeleted =loansService.deleteLoan(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));

        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }
    }
}
