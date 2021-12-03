package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.CitizenDto;
import com.cvrs.backend.entity.CitizenEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class CitizenMapper extends GenericBaseMapperImpl<CitizenEntity, CitizenDto> {
    private  ModelMapper modelMapper;
    @Autowired
    public CitizenMapper(ModelMapper modelMapper, Class<CitizenEntity> entityClass, Class<CitizenDto> citizenDtoClass, ModelMapper modelMapper1) {
        super(modelMapper, entityClass, citizenDtoClass);
        this.modelMapper = modelMapper1;
    }
}
