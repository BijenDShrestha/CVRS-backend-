package com.cvrs.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class VaccineDistributionCenterToVacccine extends BaseEntity{

    @ManyToOne
    private VaccineDistributionCenter vaccineDistributionCenter;

    @ManyToOne
    private Vaccine vaccine;
}
