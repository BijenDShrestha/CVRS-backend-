package com.cvrs.backend.dto;

import com.cvrs.backend.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CitizenDto extends BaseDto {
    private String firstName;

    private String middleName;

    private String lastName;

    private String gender;

    private Date dob;

    private String citizenship;

    private String phoneNum;

    private String vaccinatedStatus;

    private Long vaccineId;

    private Long medicalConditionId;

    private Long locationId;

    private Long occupationId;

    private Long ageCategoryId;
}
