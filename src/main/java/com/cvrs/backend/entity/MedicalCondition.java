package com.cvrs.backend.entity;


import com.cvrs.backend.entity.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MedicalCondition extends BaseEntity implements Serializable {

    private boolean serious;

}
