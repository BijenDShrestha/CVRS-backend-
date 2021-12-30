package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.VaccineEntity;
import com.cvrs.backend.repository.VaccineRepository;
import com.cvrs.backend.service.IVaccineService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class VaccineServiceImpl extends BaseServiceImpl<VaccineEntity, Long> implements com.cvrs.backend.service.IVaccineService {
    private VaccineRepository vaccineRepository;

    @Autowired
    public VaccineServiceImpl(JpaRepository<VaccineEntity, Long> repository, VaccineRepository vaccineRepository) {
        super(repository);
        this.vaccineRepository = vaccineRepository;
    }
}
