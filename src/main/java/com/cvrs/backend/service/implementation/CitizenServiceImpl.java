package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.CitizenEntity;
import com.cvrs.backend.repository.CitizenRepository;
import com.cvrs.backend.service.ICitizenService;
import com.cvrs.backend.service.implementation.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class CitizenServiceImpl extends BaseServiceImpl<CitizenEntity, Long> implements ICitizenService {
    private CitizenRepository citizenRepository;

    @Autowired
    public CitizenServiceImpl(JpaRepository<CitizenEntity, Long> repository, CitizenRepository citizenRepository) {
        super(repository);
        this.citizenRepository = citizenRepository;
    }
}
