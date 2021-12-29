package com.cvrs.backend.service;

import com.cvrs.backend.dto.CitizenDto;
import com.cvrs.backend.dto.CustomDto.FormDto;
import com.cvrs.backend.entity.CitizenEntity;
import com.cvrs.backend.service.base.IBaseService;

public interface ICitizenService extends IBaseService<CitizenEntity, Long> {

    CitizenDto saveAllDetails(FormDto formDto);
}
