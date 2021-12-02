package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class VaccineToAgeCategory extends BaseEntity implements Serializable {

    @ManyToOne
    private AgeCategory ageCategory;

    @ManyToOne
    private Vaccine vaccine;
}
