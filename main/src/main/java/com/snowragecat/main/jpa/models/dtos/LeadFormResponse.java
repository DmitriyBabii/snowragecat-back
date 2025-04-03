package com.snowragecat.main.jpa.models.dtos;

public record LeadFormResponse(
        Integer score,
        String comment,
        String message
) {
}
