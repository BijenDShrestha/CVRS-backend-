package com.cvrs.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class VaccineToAgeCategory extends BaseEntity{

    @ManyToOne
    private AgeCategory ageCategory;

    @ManyToOne
    private Vaccine vaccine;
}
