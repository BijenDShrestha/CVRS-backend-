package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.dto.CustomDto.VaccineFormDto;
import com.cvrs.backend.dto.VaccineDistributionCenterToVaccineDto;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.VaccineDistributionCenterToVaccineMapper;
import com.cvrs.backend.service.implementation.VaccineDistributionCenterToVaccineServiceImpl;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstant.VACCINE_DISTRIBUTION_CENTER_TO_VACCINE)
public class VaccineDistributionCenterToVaccineController extends BaseController {
    VaccineDistributionCenterToVaccineServiceImpl vaccineDistributionCenterToVaccineService;
    VaccineDistributionCenterToVaccineMapper vaccineDistributionCenterToVaccineMapper;

    @Autowired
    public VaccineDistributionCenterToVaccineController(VaccineDistributionCenterToVaccineServiceImpl vaccineDistributionCenterToVaccineService,
                                                        VaccineDistributionCenterToVaccineMapper vaccineDistributionCenterToVaccineMapper) {
        this.vaccineDistributionCenterToVaccineService = vaccineDistributionCenterToVaccineService;
        this.vaccineDistributionCenterToVaccineMapper = vaccineDistributionCenterToVaccineMapper;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> saveVaccineDetails(@RequestBody VaccineFormDto vaccineFormDto){
        VaccineDistributionCenterToVaccineDto vaccineDistributionCenterToVaccineDto = vaccineDistributionCenterToVaccineService.saveVaccineDetails(vaccineFormDto);
        try {
            vaccineDistributionCenterToVaccineService.save(vaccineDistributionCenterToVaccineMapper.mapToEntity(vaccineDistributionCenterToVaccineDto));
        }catch (Exception exception){
            throw new NotSavedException("Not saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", vaccineDistributionCenterToVaccineDto), HttpStatus.OK);
    }
}
