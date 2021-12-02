package com.cvrs.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
public class Location extends BaseEntity implements Serializable {
    @Column(name = "ward_no")
    private Integer wardNo;

    private String municipality;

    private String district;

    private String zone;

    private String state;

}
