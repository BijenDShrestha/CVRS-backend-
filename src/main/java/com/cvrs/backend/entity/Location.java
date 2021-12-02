package com.cvrs.backend.entity;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Location extends BaseEntity implements Serializable {
    @Column(name = "ward_no")
    private Integer wardNo;

    private String municipality;

    private String district;

    private String zone;

    private String state;

}
