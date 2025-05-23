package com.snowragecat.main.mappers;

import com.snowragecat.main.models.dtos.EvaluationBody;
import com.snowragecat.main.models.entities.Evaluation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EvaluationMapper {
    EvaluationBody toDto(Evaluation evaluation);

    Evaluation toEntity(EvaluationBody evaluationBody);
}
