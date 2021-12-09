package com.cvrs.backend.controller;

import com.cvrs.backend.controller.base.BaseController;
import com.cvrs.backend.dto.AdminDto;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.entity.AdminEntity;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.exception.NotSavedException;
import com.cvrs.backend.mapper.AdminMapper;
import com.cvrs.backend.service.IAdminService;
import com.cvrs.backend.util.APIConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstant.ADMIN)
public class AdminController extends BaseController {

    private IAdminService adminService;
    private AdminMapper adminMapper;

    @Autowired
    public AdminController(IAdminService adminService, AdminMapper adminMapper) {
        this.adminService = adminService;
        this.adminMapper = adminMapper;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody AdminDto adminDto){
        try {
            adminService.save(adminMapper.mapToEntity(adminDto));
        }catch (Exception exception){
            throw new NotSavedException("Error saving Admin", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", adminDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody AdminDto adminDto){
        try {
            adminService.save(adminMapper.mapToEntity(adminDto));
        }catch (Exception exception){
            throw new NotSavedException("Error updating Admin", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully saved", adminDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestBody AdminDto adminDto){
        try {
            adminService.delete(adminMapper.mapToEntity(adminDto));
        }catch (Exception exception){
            throw new NotFoundException("Not Found", exception);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @DeleteMapping(APIConstant.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id){
        try {
            adminService.deleteById(id);
        }catch (Exception exception){
            throw new NotFoundException("Not Found Admin Id: " + id);
        }
        return new ResponseEntity<>(new ResponseDto("Successfully deleted"), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_BY_ID)
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id){
        AdminEntity adminEntity = adminService.findById(id);
        if (adminEntity == null) throw new NotFoundException("Not Found Admin Id: " + id);
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", adminMapper.mapToDto(adminEntity)), HttpStatus.OK);
    }

    @GetMapping(APIConstant.FIND_ALL)
    public ResponseEntity<ResponseDto> findAll(){
        List<AdminEntity> adminEntityList = adminService.findAll();
        if (adminEntityList == null) throw new NotFoundException("Empty List");
        return new ResponseEntity<>(new ResponseDto("Successfully fetched", adminMapper.mapToDto(adminEntityList)), HttpStatus.OK);
    }
}
