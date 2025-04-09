package com.snowragecat.main.models.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record LeadFormRequest(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String company,
        @NotNull @Min(0) BigDecimal budget,
        @NotBlank @Size(max = 2000) String message
) {
}
