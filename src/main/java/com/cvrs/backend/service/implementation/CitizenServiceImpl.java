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
import com.cvrs.backend.mapper.CitizenMapper;
import com.cvrs.backend.mapper.LocationMapper;
import com.cvrs.backend.mapper.MedicalConditionMapper;
import com.cvrs.backend.mapper.OccupationMapper;
import com.cvrs.backend.repository.CitizenRepository;
import com.cvrs.backend.repository.LocationRepository;
import com.cvrs.backend.repository.MedicalConditionRepository;
import com.cvrs.backend.repository.OccupationRepository;
import com.cvrs.backend.service.ICitizenService;
import com.cvrs.backend.service.ILocationService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import com.cvrs.backend.util.CvrsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Transactional
@Service
public class CitizenServiceImpl extends BaseServiceImpl<CitizenEntity, Long> implements ICitizenService {
    private CitizenRepository citizenRepository;
    private ILocationService locationService;
    private LocationMapper locationMapper;
    private LocationRepository locationRepository;
    private OccupationRepository occupationRepository;
    private OccupationMapper occupationMapper;
    private OccupationServiceImpl occupationService;
    private MedicalConditionServiceImpl medicalConditionService;
    private MedicalConditionRepository medicalConditionRepository;
    private MedicalConditionMapper medicalConditionMapper;
    private CitizenMapper citizenMapper;

    @Autowired
    public CitizenServiceImpl(JpaRepository<CitizenEntity, Long> repository,
                              CitizenRepository citizenRepository, ILocationService locationService,
                              LocationMapper locationMapper, LocationRepository locationRepository,
                              OccupationRepository occupationRepository, OccupationMapper occupationMapper,
                              OccupationServiceImpl occupationService, MedicalConditionServiceImpl medicalConditionService,
                              MedicalConditionRepository medicalConditionRepository, MedicalConditionMapper medicalConditionMapper,
                              CitizenMapper citizenMapper) {
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
        this.citizenMapper = citizenMapper;
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
//            LocationDto locationDto = new LocationDto();
//            locationDto.setWardNo(formLocationDto.getWardNo());
//            locationDto.setMunicipality(formLocationDto.getMunicipality());
//            locationDto.setDistrict(formLocationDto.getDistrict());
//            locationDto.setZone(formLocationDto.getZone());
//            locationDto.setState(formLocationDto.getState());

//            LocationEntity saveLocationEntity = locationMapper.mapToEntity(formLocationDto);
//            locationService.save(saveLocationEntity);
//
//            //save the location and get the location_id;
//            LocationEntity locationEntityAfterSave = locationRepository.findByWardNoAndMunicipality(formLocationDto.getWardNo(), formLocationDto.getMunicipality());
//            citizenDto.setLocationEntityId(locationEntityAfterSave.getId());
            //or
            LocationEntity saveLocationEntity = locationRepository.save(locationMapper.mapToEntity(formLocationDto));
            citizenDto.setLocationEntityId(saveLocationEntity.getId());

        }else{
            citizenDto.setLocationEntityId(locationEntity.getId());
        }

        //check already existed occupation by occupation name
        OccupationDto formOccupationDto = formDto.getOccupationDto();
        OccupationEntity occupationEntity = occupationRepository.findByName(formOccupationDto.getName());
        if(occupationEntity == null){
//            OccupationDto occupationDto = new OccupationDto();
//            occupationDto.setName(formOccupationDto.getName());

            OccupationEntity saveOccupationEntity = occupationMapper.mapToEntity(formOccupationDto);
            occupationService.save(saveOccupationEntity);

//            OccupationEntity occupationEntityAfterSave = occupationRepository.findByName(formOccupationDto.getName());
//            citizenDto.setOccupationEntityId(occupationEntityAfterSave.getId());
            //or
            citizenDto.setOccupationEntityId(occupationRepository.findByName(formOccupationDto.getName()).getId());
        }else{
            citizenDto.setOccupationEntityId(occupationEntity.getId());
        }


        //check already existed medical condition by name
        MedicalConditionDto formMedicalConditionDto = formDto.getMedicalConditionDto();
        MedicalConditionEntity medicalConditionEntity = medicalConditionRepository.findByName(formMedicalConditionDto.getName());
        if(medicalConditionEntity == null){
//            MedicalConditionDto medicalConditionDto = new MedicalConditionDto();
//            medicalConditionDto.setName(formMedicalConditionDto.getName());
//            medicalConditionDto.setSerious(formMedicalConditionDto.getSerious());

            MedicalConditionEntity saveMedicalConditionEntity = medicalConditionMapper.mapToEntity(formMedicalConditionDto);
            medicalConditionService.save(saveMedicalConditionEntity);

            MedicalConditionEntity medicalConditionEntityAfterSave = medicalConditionRepository.findByName(formMedicalConditionDto.getName());
            citizenDto.setMedicalConditionEntityId(medicalConditionEntityAfterSave.getId());
        }else{
            citizenDto.setMedicalConditionEntityId(medicalConditionEntity.getId());
        }

        //set the priority of citizen
        if(medicalConditionEntity.getName().equalsIgnoreCase("NORMAL") || medicalConditionEntity.getName().equalsIgnoreCase("BLOOD PRESSURE")
        || medicalConditionEntity.getName().equalsIgnoreCase("DIABETES")){
            citizenDto.setPriority(false);
        } else {
            citizenDto.setPriority(true);
        }

        //set the age category
        String dob = dateInString(citizenDto.getDob());
        LocalDate localDob = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();
        Integer age = Period.between(localDob, currentDate).getYears();

        if(age <= 12){
            citizenDto.setAgeCategoryEntityId(Long.parseLong(CvrsUtils.CHILDREN));
        } else if (age > 12 && age <= 18){
            citizenDto.setAgeCategoryEntityId(Long.parseLong(CvrsUtils.TEEN));
        } else if (age > 18 && age <= 40){
        citizenDto.setAgeCategoryEntityId(Long.parseLong(CvrsUtils.YOUNG_ADULT));
        } else if (age > 40 && age <= 60){
            citizenDto.setAgeCategoryEntityId(Long.parseLong(CvrsUtils.MIDDLE_AGED_ADULT));
        } else {
            citizenDto.setAgeCategoryEntityId(Long.parseLong(CvrsUtils.OLD_ADULT));
        }

        //vaccinated status
        citizenDto.setVaccinatedStatus(CvrsUtils.PENDING);

        //Registration Number
        citizenDto.setRegNum(Long.valueOf(System.currentTimeMillis()).toString());

        return citizenDto;

    }


    public String dateInString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    @Override
    public List<CitizenDto> findByStatusCode(String statusCode) {
        List<CitizenEntity> citizenEntities = new ArrayList<>();
        citizenEntities = citizenRepository.findAllByVaccinatedStatus(statusCode);
        if(citizenEntities.size() == 0){
            return null;
        }
        return  citizenMapper.mapToDto(citizenEntities);
    }

    @Override
    public List<CitizenDto> findByAgeCategory(Long id){

        List<CitizenEntity> citizenEntities = new ArrayList<>();
        citizenEntities = citizenRepository.findAllByAgeCategoryEntityId(id);

        if(citizenEntities.size() ==0){
            return null;
        }

        return citizenMapper.mapToDto(citizenEntities);
    }

}


