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
public final class LeadBody {
    private Long id;
    private String name;
    private String email;
    private String message;
    private String company;
    private BigDecimal budget;
    private EvaluationBody evaluation;
}
