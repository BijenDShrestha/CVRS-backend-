package com.cvrs.backend.service;

import com.cvrs.backend.dto.CitizenDto;
import com.cvrs.backend.dto.CustomDto.FormDto;
import com.cvrs.backend.entity.CitizenEntity;
import com.cvrs.backend.service.base.IBaseService;

import java.util.Date;
import java.util.List;

public interface ICitizenService extends IBaseService<CitizenEntity, Long> {

    CitizenDto saveAllDetails(FormDto formDto);

    String dateInString(Date date);

    List<CitizenDto> findByStatusCode(String statusCode);

    List<CitizenDto> findByAgeCategory(Long id);

}
