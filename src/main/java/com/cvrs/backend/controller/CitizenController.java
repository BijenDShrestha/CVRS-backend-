package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.CitizenDto;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.entity.CitizenEntity;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.CitizenMapper;
import com.cvrs.backend.service.ICitizenService;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstant.CITIZEN)
public class CitizenController extends BaseController {
    private CitizenMapper citizenMapper;
    private ICitizenService citizenService;

    @Autowired
    public CitizenController(CitizenMapper citizenMapper, ICitizenService citizenService) {
        this.citizenMapper = citizenMapper;
        this.citizenService = citizenService;
    }

    // Todo: saving all details of citizen in different entity while saving citizen
    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody CitizenDto citizenDto){
        try {
            citizenService.save(citizenMapper.mapToEntity(citizenDto));
        }catch (Exception exception){
            throw new NotSavedException("Not saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", citizenDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody CitizenDto citizenDto){
        try {
            citizenService.save(citizenMapper.mapToEntity(citizenDto));
        }catch (Exception exception){
            throw new NotSavedException("Not saved", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", citizenDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestBody CitizenDto citizenDto){
        try {
            citizenService.delete(citizenMapper.mapToEntity(citizenDto));
        }catch (Exception exception){
            throw new NotFoundException("Not Found", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @DeleteMapping(APIConstant.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id ){
        try {
            citizenService.deleteById(id);
        }catch (Exception exception){
            throw new NotFoundException("Not Found Citizen Id: " + id);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll(){
        List<CitizenEntity> citizenEntityList = citizenService.findAll();
        if(citizenEntityList == null) throw new NotFoundException("Empty List");
        return new ResponseEntity<>(new ResponseDto("Successfully Fetched", citizenMapper.mapToDto(citizenEntityList)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id){
        CitizenEntity citizenEntity = citizenService.findById(id);
        if(citizenEntity == null) throw new NotFoundException("Not Found Citizen Id: " + id);
        return new ResponseEntity<>(new ResponseDto("Successfully Fetched", citizenMapper.mapToDto(citizenEntity)), HttpStatus.OK);
    }

}
