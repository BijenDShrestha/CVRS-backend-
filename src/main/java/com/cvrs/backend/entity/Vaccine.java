package com.cvrs.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
public class Vaccine extends BaseEntity{

    private String batch_NO;

    @OneToMany
    private Set<VaccineDistributionCenterToVacccine> distributionCenterToVacccineSet;
}
