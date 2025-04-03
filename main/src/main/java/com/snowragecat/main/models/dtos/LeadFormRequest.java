package com.snowragecat.main.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record LeadFormRequest(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String company,
        @NotNull BigDecimal budget,
        @NotBlank @Size(max = 2000) String message
) {
}
