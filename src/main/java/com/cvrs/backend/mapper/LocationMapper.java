package com.cvrs.backend.mapper;

import com.cvrs.backend.dto.LocationDto;
import com.cvrs.backend.entity.LocationEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class LocationMapper extends GenericBaseMapperImpl<LocationEntity, LocationDto> {
    private ModelMapper modelMapper;
    @Autowired
    public LocationMapper(ModelMapper modelMapper, Class<LocationEntity> entityClass, Class<LocationDto> locationDtoClass, ModelMapper modelMapper1) {
        super(modelMapper, entityClass, locationDtoClass);
        this.modelMapper = modelMapper1;
    }
}
