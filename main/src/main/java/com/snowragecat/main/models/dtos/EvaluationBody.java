package com.snowragecat.main.models.dtos;

import com.snowragecat.main.models.enums.LeadStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationBody {
    private Long id;
    private Integer score;
    private String comment;
    private String message;
    private LeadStatus status;
}
