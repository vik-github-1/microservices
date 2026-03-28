package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to hold the error response"
)
@Setter
@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "Api path invoked by the client"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus statusCode;

    @Schema(
            description = "Error message representing the error happened"
    )
    private String statusMessage;

    @Schema(
            description = "Time representing when error happened"
    )
    private LocalDateTime errorTime;

}
