package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.VaccineDistributionCenterToVaccineDto;
import com.cvrs.backend.entity.VaccineDistributionCenterToVaccineEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class VaccineDistributionCenterToVaccineMapper extends GenericBaseMapperImpl<VaccineDistributionCenterToVaccineEntity, VaccineDistributionCenterToVaccineDto> {

    private ModelMapper modelMapper;

    @Autowired
    public VaccineDistributionCenterToVaccineMapper(ModelMapper modelMapper, Class<VaccineDistributionCenterToVaccineEntity> entityClass, Class<VaccineDistributionCenterToVaccineDto> vaccineDistributionCenterToVaccineDtoClass, ModelMapper modelMapper1) {
        super(modelMapper, entityClass, vaccineDistributionCenterToVaccineDtoClass);
        this.modelMapper = modelMapper1;
    }
}
