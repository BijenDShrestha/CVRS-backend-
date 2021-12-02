package com.cvrs.backend.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
public class MedicalCondition extends BaseEntity implements Serializable {

    @Column(name = "serious")
    private boolean serious;


}
