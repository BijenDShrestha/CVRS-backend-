package com.cvrs.backend.mapper;


import com.cvrs.backend.dto.AdminDto;
import com.cvrs.backend.entity.AdminEntity;
import com.cvrs.backend.mapper.baseMapper.GenericBaseMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class AdminMapper extends GenericBaseMapperImpl<AdminEntity, AdminDto> {
    private ModelMapper modelMapper;

    @Autowired
    public AdminMapper(ModelMapper modelMapper) {
        super(modelMapper, AdminEntity.class, AdminDto.class);
        this.modelMapper = modelMapper;
    }
}
