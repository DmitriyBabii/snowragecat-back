package com.snowragecat.openai.models;

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
