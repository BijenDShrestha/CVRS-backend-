package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.VaccineDto;
import com.cvrs.backend.entity.VaccineEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class VaccineMapper extends GenericBaseMapperImpl<VaccineEntity, VaccineDto> {

    private ModelMapper modelMapper;

    @Autowired
    public VaccineMapper(ModelMapper modelMapper, Class<VaccineEntity> entityClass, Class<VaccineDto> vaccineDtoClass, ModelMapper modelMapper1) {
        super(modelMapper, entityClass, vaccineDtoClass);
        this.modelMapper = modelMapper1;
    }
}
