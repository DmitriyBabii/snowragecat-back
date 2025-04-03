package com.snowragecat.main.jpa.mappers;

import com.snowragecat.main.jpa.models.entities.Lead;
import com.snowragecat.main.jpa.models.dtos.LeadFormRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LeadMapper {
    Lead toEntity(LeadFormRequest leadFormRequest);
}
