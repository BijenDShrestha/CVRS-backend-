package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class ManufacturingCompany extends BaseEntity {

    @NotNull
    @Column(name = "phone_num", unique = true)
    private String phoneNum;

    @OneToOne
    private Location location;

}
