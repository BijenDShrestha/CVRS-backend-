package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
public class VaccineDistributionCenter extends BaseEntity {

    @ManyToOne
    private Location location;

}
