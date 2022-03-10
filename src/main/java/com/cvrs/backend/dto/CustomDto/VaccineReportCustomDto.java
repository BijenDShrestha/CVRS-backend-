package com.cvrs.backend.dto.CustomDto;

import com.cvrs.backend.dto.CitizenDto;
import com.cvrs.backend.dto.VaccineDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineReportCustomDto {

    private CitizenDto citizenDto;

    private VaccineDto vaccineDto;


}
