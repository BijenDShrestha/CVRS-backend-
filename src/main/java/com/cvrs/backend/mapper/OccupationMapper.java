package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.OccupationDto;
import com.cvrs.backend.entity.OccupationEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OccupationMapper extends GenericBaseMapperImpl<OccupationEntity, OccupationDto> {

    private ModelMapper modelMapper;

    @Autowired
    public OccupationMapper(ModelMapper modelMapper, Class<OccupationEntity> entityClass, Class<OccupationDto> occupationDtoClass, ModelMapper modelMapper1) {
        super(modelMapper, entityClass, occupationDtoClass);
        this.modelMapper = modelMapper1;
    }
}
