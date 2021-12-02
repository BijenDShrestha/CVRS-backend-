package com.cvrs.backend.entity;


import com.cvrs.backend.entity.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class AgeCategory extends BaseEntity implements Serializable {

    private String range;

}
