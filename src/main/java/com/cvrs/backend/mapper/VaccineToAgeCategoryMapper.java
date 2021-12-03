package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.VaccineToAgeCategoryDto;
import com.cvrs.backend.entity.VaccineToAgeCategoryEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class VaccineToAgeCategoryMapper extends GenericBaseMapperImpl<VaccineToAgeCategoryEntity, VaccineToAgeCategoryDto> {

    private ModelMapper  modelMapper;


    @Autowired
    public VaccineToAgeCategoryMapper(ModelMapper modelMapper, Class<VaccineToAgeCategoryEntity> entityClass, Class<VaccineToAgeCategoryDto> vaccineToAgeCategoryDtoClass, ModelMapper modelMapper1) {
        super(modelMapper, entityClass, vaccineToAgeCategoryDtoClass);
        this.modelMapper = modelMapper1;
    }
}
