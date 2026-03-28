package com.eazybytes.cards.controller;

import com.eazybytes.cards.constant.CardsConstants;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.dto.ErrorResponseDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.impl.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD Rest Apis for Cards in Eazy Bank",
        description = "CRUD Rest Api in Eazy Bank to Create , Update, Fetch and Delete card"
)
@RestController
@Validated
@RequestMapping("/api")
@AllArgsConstructor
public class CardsController {

    private ICardService cardService;

    @Operation(
            summary = "Create Card Rest Api",
            description = "Rest Api to create card in Eazy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status Created"
            ),
            @ApiResponse(
                     responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content= @Content(
                           schema = @Schema(implementation = ErrorResponseDto.class))

            )})
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(String mobileNumber){
        cardService.createCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card Details Rest Api",
            description = " Rest Api to fetch card details based on mobile number"
    )

    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Http Status OK"
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

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCard(String mobileNumber){
        CardsDto cardsDto=cardService.fetchCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Operation(
            summary = "Update Card Rest Api",
            description= " Rest Api to update Card based on Card Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = " Http Status Expectation failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@RequestBody CardsDto cardsDto){
        boolean isUpdated =cardService.updateCard(cardsDto);

        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Card Rest Api",
            description = " Rest Api to delete card based on mobile number"
    )

    @ApiResponses({
             @ApiResponse(
                     responseCode = "200",
                     description = " Http Status OK"
             ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Http Status Expectation Failed"
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
    public ResponseEntity<ResponseDto> deleteCard(String mobileNumber){
        boolean isDeleted= cardService.deleteCard(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_DELETE));
        }
    }

}
