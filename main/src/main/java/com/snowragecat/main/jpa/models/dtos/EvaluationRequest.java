package com.snowragecat.main.jpa.models.dtos;

import java.math.BigDecimal;

public record EvaluationRequest(
        Long leadId,
        String name,
        String email,
        String company,
        BigDecimal budget,
        String message
) {
}
