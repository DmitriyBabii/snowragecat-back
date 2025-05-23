package com.snowragecat.main.mappers;

import com.snowragecat.main.models.dtos.LeadBody;
import com.snowragecat.main.models.dtos.LeadFormRequest;
import com.snowragecat.main.models.entities.Lead;
import com.snowragecat.shared.dtos.EvaluationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LeadMapper {
    Lead toEntity(LeadFormRequest leadFormRequest);

    LeadBody toDto(Lead lead);

    Lead toEntity(LeadBody leadBody);

    EvaluationRequest toEvaluation(Lead lead);
}
