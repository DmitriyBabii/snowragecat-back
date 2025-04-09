package com.snowragecat.main.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class LeadResponse {
    private String name;
    private String email;
    private String company;
    private BigDecimal budget;
    private EvaluationResponse evaluation;
}
