package com.snowragecat.shared.dtos;

import java.math.BigDecimal;

public record EvaluationRequest(
        Long id,
        String name,
        String email,
        String company,
        BigDecimal budget,
        String message
) {
}
