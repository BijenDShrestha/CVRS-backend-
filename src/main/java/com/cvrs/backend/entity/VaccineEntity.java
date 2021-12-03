package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "vaccine")
public class VaccineEntity extends BaseEntity {

    @NotNull
    @Column(name = "batch_num")
    private String batchNum;

    @NotNull
    private Long units;

    @NotNull
    @ManyToOne
    private ManufacturingCompanyEntity manufacturingCompanyEntity;

}