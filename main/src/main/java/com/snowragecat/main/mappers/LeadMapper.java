package com.snowragecat.main.mappers;

import com.snowragecat.main.jpa.entities.Lead;
import com.snowragecat.main.models.dtos.LeadFormRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LeadMapper {
    Lead toEntity(LeadFormRequest leadFormRequest);
}
