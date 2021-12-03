package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.MedicalConditionDto;
import com.cvrs.backend.entity.MedicalConditionEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class MedicalConditionMapper extends GenericBaseMapperImpl<MedicalConditionEntity, MedicalConditionDto> {

    private ModelMapper modelMapper;

    @Autowired
    public MedicalConditionMapper(ModelMapper modelMapper, Class<MedicalConditionEntity> entityClass, Class<MedicalConditionDto> medicalConditionDtoClass, ModelMapper modelMapper1) {
        super(modelMapper, entityClass, medicalConditionDtoClass);
        this.modelMapper = modelMapper1;
    }
}
