package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.VaccineDistributionCenterDto;
import com.cvrs.backend.entity.VaccineDistributionCenterEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class VaccineDistributionCenterMapper extends GenericBaseMapperImpl<VaccineDistributionCenterEntity, VaccineDistributionCenterDto> {

    private ModelMapper modelMapper;

    @Autowired
    public VaccineDistributionCenterMapper(ModelMapper modelMapper, Class<VaccineDistributionCenterEntity> entityClass, Class<VaccineDistributionCenterDto> vaccineDistributionCenterDtoClass, ModelMapper modelMapper1) {
        super(modelMapper, entityClass, vaccineDistributionCenterDtoClass);
        this.modelMapper = modelMapper1;
    }
}
