package com.cvrs.backend.service.implementation;

import com.cvrs.backend.entity.VaccineDistributionCenterToVaccineEntity;
import com.cvrs.backend.repository.VaccineDistributionCenterToVaccineRepository;
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

    @Autowired
    public VaccineDistributionCenterToVaccineServiceImpl(JpaRepository<VaccineDistributionCenterToVaccineEntity, Long> repository,
                                                         VaccineDistributionCenterToVaccineRepository vaccineDistributionCenterToVaccineRepository) {
        super(repository);
        this.vaccineDistributionCenterToVaccineRepository = vaccineDistributionCenterToVaccineRepository;
    }
}
