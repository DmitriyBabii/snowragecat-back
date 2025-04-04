package com.snowragecat.main.jpa.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EvaluateResponse(
        @JsonProperty("id") Long leadId,
        Integer score,
        String comment,
        String message
) {
}
