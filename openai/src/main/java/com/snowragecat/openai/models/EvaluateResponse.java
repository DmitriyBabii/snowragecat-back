package com.snowragecat.openai.models;

public record EvaluateResponse(
        Long leadId,
        Integer score,
        String comment,
        String message
) {
}
