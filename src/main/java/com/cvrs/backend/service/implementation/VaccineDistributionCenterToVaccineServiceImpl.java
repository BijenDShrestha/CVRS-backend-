package com.cvrs.backend.service.implementation;

import com.cvrs.backend.dto.*;
import com.cvrs.backend.dto.CustomDto.VaccineFormDto;
import com.cvrs.backend.entity.*;
import com.cvrs.backend.mapper.LocationMapper;
import com.cvrs.backend.mapper.ManufacturingCompanyMapper;
import com.cvrs.backend.mapper.VaccineDistributionCenterMapper;
import com.cvrs.backend.mapper.VaccineMapper;
import com.cvrs.backend.repository.*;
import com.cvrs.backend.service.ILocationService;
import com.cvrs.backend.service.IVaccineDistributionCenterService;
import com.cvrs.backend.service.IVaccineDistributionCenterToVaccineService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class VaccineDistributionCenterToVaccineServiceImpl extends BaseServiceImpl<VaccineDistributionCenterToVaccineEntity, Long> implements IVaccineDistributionCenterToVaccineService {
    private VaccineDistributionCenterToVaccineRepository vaccineDistributionCenterToVaccineRepository;
    private VaccineRepository vaccineRepository;
    private VaccineServiceImpl vaccineService;
    private VaccineMapper vaccineMapper;
    private VaccineDistributionCenterRepository vaccineDistributionCenterRepository;
    private IVaccineDistributionCenterService vaccineDistributionCenterService;
    private VaccineDistributionCenterMapper vaccineDistributionCenterMapper;
    private ManufacturingCompanyRepository manufacturingCompanyRepository;
    private ManufacturingCompanyServiceImpl manufacturingCompanyService;
    private ManufacturingCompanyMapper manufacturingCompanyMapper;
    private LocationRepository locationRepository;
    private ILocationService locationService;
    private LocationMapper locationMapper;



    @Autowired
    public VaccineDistributionCenterToVaccineServiceImpl(JpaRepository<VaccineDistributionCenterToVaccineEntity, Long> repository,
                                                         VaccineDistributionCenterToVaccineRepository vaccineDistributionCenterToVaccineRepository,
                                                         VaccineRepository vaccineRepository, VaccineServiceImpl vaccineService, VaccineMapper vaccineMapper,
                                                         VaccineDistributionCenterRepository vaccineDistributionCenterRepository,
                                                         IVaccineDistributionCenterService vaccineDistributionCenterService,
                                                         VaccineDistributionCenterMapper vaccineDistributionCenterMapper,
                                                         ManufacturingCompanyRepository manufacturingCompanyRepository,
                                                         ManufacturingCompanyServiceImpl manufacturingCompanyService,
                                                         ManufacturingCompanyMapper manufacturingCompanyMapper,
                                                         LocationRepository locationRepository, ILocationService locationService,
                                                         LocationMapper locationMapper) {
        super(repository);
        this.vaccineDistributionCenterToVaccineRepository = vaccineDistributionCenterToVaccineRepository;
        this.vaccineDistributionCenterRepository = vaccineDistributionCenterRepository;
        this.vaccineDistributionCenterMapper = vaccineDistributionCenterMapper;
        this.vaccineDistributionCenterService = vaccineDistributionCenterService;
        this.vaccineRepository = vaccineRepository;
        this.vaccineService = vaccineService;
        this.vaccineMapper = vaccineMapper;
        this.manufacturingCompanyRepository = manufacturingCompanyRepository;
        this.manufacturingCompanyService = manufacturingCompanyService;
        this.manufacturingCompanyMapper = manufacturingCompanyMapper;
        this.locationRepository = locationRepository;
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }


    @Override
    public VaccineDistributionCenterToVaccineDto saveVaccineDetails(VaccineFormDto vaccineFormDto) {
        VaccineDistributionCenterToVaccineDto vaccineDistributionCenterToVaccineDto = new VaccineDistributionCenterToVaccineDto();

        //check the vaccine details in database
        VaccineDto formVaccineDto = vaccineFormDto.getVaccineDto();
        VaccineEntity vaccineEntity = vaccineRepository.findByBatchNum(formVaccineDto.getBatchNum());
        if(vaccineEntity == null){

            //set the manufacturing company
            ManufacturingCompanyDto formManufacturingCompanyDto = vaccineFormDto.getManufacturingCompanyDto();
            ManufacturingCompanyEntity manufacturingCompanyEntity = manufacturingCompanyRepository.findByName(formManufacturingCompanyDto.getName());
            if(manufacturingCompanyEntity == null){

                //set the company location
                LocationDto formCompanyLocation = vaccineFormDto.getCompanyLocationDto();
                LocationEntity companyLocation = locationRepository.findByWardNoAndMunicipality(formCompanyLocation.getWardNo(), formCompanyLocation.getMunicipality());
                if(companyLocation == null){
                    locationService.save(locationMapper.mapToEntity(formCompanyLocation));
                    LocationEntity companyLocationAfterSave = locationRepository.findByWardNoAndMunicipality(formCompanyLocation.getWardNo(), formCompanyLocation.getMunicipality());
                    formManufacturingCompanyDto.setLocationEntityId(companyLocationAfterSave.getId());
                } else{
                    formManufacturingCompanyDto.setLocationEntityId(companyLocation.getId());
                }

                manufacturingCompanyService.save(manufacturingCompanyMapper.mapToEntity(formManufacturingCompanyDto));
                ManufacturingCompanyEntity manufacturingCompanyAfterSave = manufacturingCompanyRepository.findByName(formManufacturingCompanyDto.getName());
                formVaccineDto.setManufacturingCompanyEntityId(manufacturingCompanyAfterSave.getId());

            } else{
                formVaccineDto.setManufacturingCompanyEntityId(manufacturingCompanyEntity.getId());
            }
            vaccineService.save(vaccineMapper.mapToEntity(formVaccineDto));
            VaccineEntity vaccineEntityAfterSave = vaccineRepository.findByBatchNum(formVaccineDto.getBatchNum());
            vaccineDistributionCenterToVaccineDto.setVaccineEntityId(vaccineEntityAfterSave.getId());

        } else{
            vaccineDistributionCenterToVaccineDto.setVaccineEntityId(vaccineEntity.getId());
        }

        //check for vaccine distribution center
        VaccineDistributionCenterDto formVaccineDistributionCenterDto = vaccineFormDto.getVaccineDistributionCenterDto();
        VaccineDistributionCenterEntity vaccineDistributionCenterEntity = vaccineDistributionCenterRepository.findByName(formVaccineDistributionCenterDto.getName());
        if (vaccineDistributionCenterEntity == null){

            //set the company location
            LocationDto formCenterLocation = vaccineFormDto.getCenterLocationDto();
            LocationEntity centerLocation = locationRepository.findByWardNoAndMunicipality(formCenterLocation.getWardNo(), formCenterLocation.getMunicipality());
            if(centerLocation == null){
                locationService.save(locationMapper.mapToEntity(formCenterLocation));
                LocationEntity centerLocationAfterSave = locationRepository.findByWardNoAndMunicipality(formCenterLocation.getWardNo(), formCenterLocation.getMunicipality());
                formVaccineDistributionCenterDto.setLocationEntityId(centerLocationAfterSave.getId());
            } else{
                formVaccineDistributionCenterDto.setLocationEntityId(centerLocation.getId());
            }
            vaccineDistributionCenterService.save(vaccineDistributionCenterMapper.mapToEntity(formVaccineDistributionCenterDto));
            VaccineDistributionCenterEntity vaccineDistributionCenterEntityAfterSave = vaccineDistributionCenterRepository.findByName(formVaccineDistributionCenterDto.getName());
            vaccineDistributionCenterToVaccineDto.setVaccineDistributionCenterEntityId(vaccineDistributionCenterEntityAfterSave.getId());
        } else {
            vaccineDistributionCenterToVaccineDto.setVaccineDistributionCenterEntityId(vaccineDistributionCenterEntity.getId());
        }


        return vaccineDistributionCenterToVaccineDto;
    }
}
