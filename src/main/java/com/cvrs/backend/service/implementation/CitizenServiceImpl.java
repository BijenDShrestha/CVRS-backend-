package com.cvrs.backend.service.implementation;

import com.cvrs.backend.dto.CitizenDto;
import com.cvrs.backend.dto.CustomDto.FormDto;
import com.cvrs.backend.dto.LocationDto;
import com.cvrs.backend.dto.MedicalConditionDto;
import com.cvrs.backend.dto.OccupationDto;
import com.cvrs.backend.entity.CitizenEntity;
import com.cvrs.backend.entity.LocationEntity;
import com.cvrs.backend.entity.MedicalConditionEntity;
import com.cvrs.backend.entity.OccupationEntity;
import com.cvrs.backend.exception.AlreadyExistedException;
import com.cvrs.backend.mapper.LocationMapper;
import com.cvrs.backend.mapper.MedicalConditionMapper;
import com.cvrs.backend.mapper.OccupationMapper;
import com.cvrs.backend.repository.CitizenRepository;
import com.cvrs.backend.repository.LocationRepository;
import com.cvrs.backend.repository.MedicalConditionRepository;
import com.cvrs.backend.repository.OccupationRepository;
import com.cvrs.backend.service.ICitizenService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CitizenServiceImpl extends BaseServiceImpl<CitizenEntity, Long> implements ICitizenService {
    private CitizenRepository citizenRepository;
    private LocationServiceImpl locationService;
    private LocationMapper locationMapper;
    private LocationRepository locationRepository;
    private OccupationRepository occupationRepository;
    private OccupationMapper occupationMapper;
    private OccupationServiceImpl occupationService;
    private MedicalConditionServiceImpl medicalConditionService;
    private MedicalConditionRepository medicalConditionRepository;
    private MedicalConditionMapper medicalConditionMapper;

    @Autowired
    public CitizenServiceImpl(JpaRepository<CitizenEntity, Long> repository,
                              CitizenRepository citizenRepository, LocationServiceImpl locationService,
                              LocationMapper locationMapper, LocationRepository locationRepository,
                              OccupationRepository occupationRepository, OccupationMapper occupationMapper,
                              OccupationServiceImpl occupationService, MedicalConditionServiceImpl medicalConditionService,
                              MedicalConditionRepository medicalConditionRepository, MedicalConditionMapper medicalConditionMapper) {
        super(repository);
        this.citizenRepository = citizenRepository;
        this.locationService = locationService;
        this.locationMapper = locationMapper;
        this.locationRepository = locationRepository;
        this.occupationMapper = occupationMapper;
        this.occupationRepository = occupationRepository;
        this.occupationService = occupationService;
        this.medicalConditionRepository = medicalConditionRepository;
        this.medicalConditionService = medicalConditionService;
        this.medicalConditionMapper = medicalConditionMapper;
    }

    public CitizenDto saveAllDetails(FormDto formDto){

        CitizenDto citizenDto = formDto.getCitizenDto();

        //check already registered citizen
        CitizenEntity citizenEntity = citizenRepository.findByCitizenshipEquals(citizenDto.getCitizenship());
        if(citizenEntity != null){
            throw new AlreadyExistedException("Citizen already registered");
        }



        LocationDto formLocationDto = formDto.getLocationDto();

        //check already existed location by ward and municipality
        LocationEntity locationEntity = locationRepository.findByWardNoAndMunicipality(formLocationDto.getWardNo(), formLocationDto.getMunicipality());
        if (locationEntity == null){
            LocationDto locationDto = new LocationDto();
            locationDto.setWardNo(formLocationDto.getWardNo());
            locationDto.setMunicipality(formLocationDto.getMunicipality());
            locationDto.setDistrict(formLocationDto.getDistrict());
            locationDto.setZone(formLocationDto.getZone());
            locationDto.setState(formLocationDto.getState());

            LocationEntity saveLocationEntity = locationMapper.mapToEntity(locationDto);
            locationService.save(saveLocationEntity);

            //save the location and get the location_id;
            LocationEntity locationEntityAfterSave = locationRepository.findByWardNoAndMunicipality(locationDto.getWardNo(), locationDto.getMunicipality());
            citizenDto.setLocationEntityId(locationEntityAfterSave.getId());

        }else{
            citizenDto.setLocationEntityId(locationEntity.getId());
        }

        //check already existed occupation by occupation name
        OccupationDto formOccupationDto = formDto.getOccupationDto();
        OccupationEntity occupationEntity = occupationRepository.findByName(formOccupationDto.getName());
        if(occupationEntity == null){
            OccupationDto occupationDto = new OccupationDto();
            occupationDto.setName(formOccupationDto.getName());

            OccupationEntity saveOccupationEntity = occupationMapper.mapToEntity(occupationDto);
            occupationService.save(saveOccupationEntity);

            OccupationEntity occupationEntityAfterSave = occupationRepository.findByName(occupationDto.getName());
            citizenDto.setOccupationEntityId(occupationEntityAfterSave.getId());
        }else{
            citizenDto.setOccupationEntityId(occupationEntity.getId());
        }


        //check already existed medical condition by name
        MedicalConditionDto formMedicalConditionDto = formDto.getMedicalConditionDto();
        MedicalConditionEntity medicalConditionEntity = medicalConditionRepository.findByName(formMedicalConditionDto.getName());
        if(medicalConditionEntity == null){
            MedicalConditionDto medicalConditionDto = new MedicalConditionDto();
            medicalConditionDto.setName(formMedicalConditionDto.getName());
            medicalConditionDto.setSerious(formMedicalConditionDto.getSerious());

            MedicalConditionEntity saveMedicalConditionEntity = medicalConditionMapper.mapToEntity(medicalConditionDto);
            medicalConditionService.save(saveMedicalConditionEntity);

            MedicalConditionEntity medicalConditionEntityAfterSave = medicalConditionRepository.findByName(medicalConditionDto.getName());
            citizenDto.setMedicalConditionEntityId(medicalConditionEntityAfterSave.getId());
        }else{
            citizenDto.setMedicalConditionEntityId(medicalConditionEntity.getId());
        }

        return citizenDto;

    }
}
