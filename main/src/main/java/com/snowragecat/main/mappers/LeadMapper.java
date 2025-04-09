package com.snowragecat.main.mappers;

import com.snowragecat.main.models.dtos.LeadFormRequest;
import com.snowragecat.main.models.dtos.LeadResponse;
import com.snowragecat.main.models.entities.Lead;
import com.snowragecat.shared.dtos.EvaluationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LeadMapper {
    Lead toEntity(LeadFormRequest leadFormRequest);

    LeadResponse toDto(Lead lead);

    EvaluationRequest toEvaluation(Lead lead);
}
