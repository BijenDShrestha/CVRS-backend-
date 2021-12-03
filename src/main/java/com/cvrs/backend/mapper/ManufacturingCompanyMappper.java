package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.ManufacturingCompanyDto;
import com.cvrs.backend.entity.ManufacturingCompanyEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class ManufacturingCompanyMappper extends GenericBaseMapperImpl<ManufacturingCompanyEntity, ManufacturingCompanyDto> {
    private ModelMapper modelMapper;

    @Autowired
    public ManufacturingCompanyMappper(ModelMapper modelMapper, Class<ManufacturingCompanyEntity> entityClass, Class<ManufacturingCompanyDto> manufacturingCompanyDtoClass, ModelMapper modelMapper1) {
        super(modelMapper, entityClass, manufacturingCompanyDtoClass);
        this.modelMapper = modelMapper1;
    }
}
