package com.cvrs.backend.dto;

import com.cvrs.backend.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccineDto extends BaseDto {
    private String batchNum;

    private Long units;

    private Long manufacturingCompanyEntityId;

}
